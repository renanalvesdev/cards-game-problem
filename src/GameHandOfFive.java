import java.util.List;

public class GameHandOfFive extends Game{

	public GameHandOfFive(List<String> players) {
		super(players);
	}

	@Override
	protected MiddleStack createMiddleStack(int code) {
		return new MiddleStackHandOfFive(code);
	}

	@Override
	protected Player createPlayer(String name, int code) {
		return new PlayerHandOfFive(this, name, code);
	}

}
