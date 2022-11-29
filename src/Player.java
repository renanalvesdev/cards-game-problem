public abstract class Player {

	protected Game game;
	protected MiddleStack rightStack;
	protected MiddleStack leftStack;
	protected PlayerHand hand;
	private String name;
	private int code;
	
	public Player(Game game, String name, int code) {
		this.game = game;
		this.name = name;
		this.code = code;
		this.hand = new PlayerHand();
	}
	
	protected abstract void move() throws InterruptedException;

	protected void play() throws InterruptedException {
		log(" [JOGANDO] ");
		while(!game.isWinner(this) && !game.hasWinner()) {
			log(" [TENTA REALIZAR JOGADA] ");
			move();
		}
		
		if(game.isWinner(this)) {
			log("[REIVINDICA VITORIA] ");
			game.checkWinner(this);
		}
		
		synchronized (leftStack) {			
			leftStack.notifyAll();
		}
		
		synchronized (rightStack) {
			rightStack.notifyAll();
		}
		
		if(game.hasWinner()) {
			log("[PAROU DE JOGAR POIS O JOGO POSSUI VENCEDOR] ");
		}
		
	}
	
	protected void pick() throws InterruptedException {
		synchronized (rightStack) {
			log(" [TENTA PEGAR CARTA DA DIREITA] ");
			while(!rightStack.hasCards()) {
				log(" [NAO HA CARTAS NA PILHA DIREITA. ESPERANDO ...] ");
				rightStack.wait();
				log("[VOLTA A TENTAR PEGAR]");
			}
			Card pickedCard = rightStack.remove(); 
			hand.add(pickedCard);
			log(" [PEGOU CARTA DA DIREITA "+pickedCard+" ]");
			rightStack.notifyAll();
		}
	}
	
	private void choosedCard() {
		/*Map<CardType, List<Card>> cardsPerType = new HashMap<CardType, List<Card>>();
		
		for(Card card: hand.get()) {
			cardsPerType.computeIfAbsent(card.getCardType() ,type ->new ArrayList<Card>()).add(card);
		}*/
	}
	
	protected void discard() throws InterruptedException {
		synchronized (leftStack) {
			log(" [TENTA DESCARTAR CARTA DA ESQUERDA] ");
			while(!leftStack.hasSpace()) {
				log(" [NAO HA ESPACO NA PILHA ESQUERDA. ESPERANDO ..] ");
				leftStack.wait();
				log("[VOLTA A TENTAR DESCARTAR]");
			}
			
			Card discardedCard = hand.removeLessFrequent();
			leftStack.add(discardedCard);
			log(" [DESCARTOU CARTA NA PILHA ESQUEDA "+ discardedCard + " ]");
			leftStack.notifyAll();
		}
	}
	
	private void log(String msg) {
		System.out.println(this.name + " - " + msg + " - " + this.hand);
	}

	//GETTERS AND SETTERS
	
	public MiddleStack getRightStack() {
		return rightStack;
	}
	public void setRightStack(MiddleStack rightStack) {
		this.rightStack = rightStack;
	}
	public MiddleStack getLeftStack() {
		return leftStack;
	}
	public void setLeftStack(MiddleStack leftStack) {
		this.leftStack = leftStack;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public PlayerHand getHand() {
		return hand;
	}

	@Override
	public String toString() {
		return "Player [code=" + code + ", name=" + name + ", hand=" + hand + ", rightStack=" + rightStack
				+ ", leftStack=" + leftStack + "]";
	}

	
	
	
}
