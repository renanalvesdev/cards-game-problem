
public class PlayerHandOfFive extends Player {

	public PlayerHandOfFive(GameHandOfFive game, String name, int code) {
		super(game, name, code);
	}

	@Override
	protected void move() throws InterruptedException {
		pick();
		discard();
	}

}
