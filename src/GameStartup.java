import java.util.List;

public class GameStartup {

	private Game game;
	private Deck deck;

	public GameStartup(Game game, List<String> playersNames) {
		this.game = game;
		init(playersNames);
	}

	public void init(List<String> playersNames) {
		deck = new Deck();
		createPlayers(playersNames);
		createStacks();
		positionPlayers();
		distributeCards();
		startGame();
	}

	private void createPlayers(List<String> playersNames) {

		int code = 0;

		for (String name : playersNames) {
			players().add(game.createPlayer(name, code++));
		}
	}

	private void createStacks() {
		int stackCode = 0;
		for (Player player : players()) {
			stacks().add(game.createMiddleStack(stackCode++));
		}

		System.out.println("stacks() created: " + stacks());
	}

	private void positionPlayers() {
		for (int i = 0; i < players().size(); i++) {
			int leftStackIndex = i + 1;
			int rightStackIndex = i;
			leftStackIndex = leftStackIndex == stacks().size() ? 0 : leftStackIndex;
			players().get(i).setLeftStack(stacks().get(leftStackIndex));
			players().get(i).setRightStack(stacks().get(rightStackIndex));
		}

		System.out.println("positioned players() -> " + players());
	}

	private void distributeCards() {
		distributingPlayerCards();
		distributingStackCards();
		printGame();
	}

	private void distributingPlayerCards() {
		System.out.println("\nDistributing card for players()");
		for (Player player : players()) {
			System.out.println(deck);
			player.getHand().addAll(deck.removeCards(cardsPerPlayer()));
			System.out.println(player);
		}
	}

	private void distributingStackCards() {
		System.out.println("\nDistributing card for middle stacks()");
		for (MiddleStack stack : stacks()) {
			stack.addAll(deck.removeCards(cardsPerStacks()));
			System.out.println(stack);
		}
	}
	
	private void startGame() {
		for(Player player : players()) {
			PlayerTask playerTask = new PlayerTask(player); 
			Thread playerThread = new Thread(playerTask, player.getName());
			playerThread.start();
		}
	}

	private void printGame() {
		System.out.println("Deck -> " + deck);
	}

	private List<Player> players() {
		return game.getPlayers();
	}

	private List<MiddleStack> stacks() {
		return game.getStacks();
	}

	private int cardsPerPlayer() {
		return game.getCardsPerPlayer();
	}

	private int cardsPerStacks() {
		return game.getCardsPerStacks();
	}

}
