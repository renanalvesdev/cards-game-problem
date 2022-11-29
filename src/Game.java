import java.util.ArrayList;
import java.util.List;

public abstract class Game {

	private int numberOfPlayers;
	private int cardsPerPlayer;
	private int cardsPerStacks;
	private List<Player> players;
	private List<MiddleStack> stacks;
	private Player winner;

	public Game(List<String> playersName) {
		super();
		players = new ArrayList<Player>();
		this.stacks = new ArrayList<MiddleStack>();
		this.numberOfPlayers = 4;
		this.cardsPerPlayer = 4;
		this.cardsPerStacks = 2;
		new GameStartup(this, playersName);
	}

	
	private boolean allCardsHasSameType(List<Card> cards) {
		
		boolean allSameType = true;
		
		for (Card card : cards) {
		    if(!card.sameType((cards.get(0))))
		    	allSameType = false;
		}
		
		return allSameType;
	}
	
	public boolean isWinner(Player player) {
		return allCardsHasSameType(player.getHand().get());
	}
	
	public void checkWinner(Player player) {
		if(isWinner(player)) {
			System.out.println(player.getHand() + " WON THE GAME. CONGRATS !");
			this.winner = player;
		}
	}
	
	public boolean hasWinner() {
		return this.winner != null;
	}

	
	protected abstract Player createPlayer(String name, int code);
	protected abstract MiddleStack createMiddleStack(int code);
	

	
	
	
	
	//getters and setters
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public int getCardsPerPlayer() {
		return cardsPerPlayer;
	}

	public void setCardsPerPlayer(int cardsPerPlayer) {
		this.cardsPerPlayer = cardsPerPlayer;
	}

	public int getCardsPerStacks() {
		return cardsPerStacks;
	}

	public void setCardsPerStacks(int cardsPerStacks) {
		this.cardsPerStacks = cardsPerStacks;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<MiddleStack> getStacks() {
		return stacks;
	}

	public void setStacks(List<MiddleStack> stacks) {
		this.stacks = stacks;
	}

	
}
