
public class PlayerTask implements Runnable{
	
	private Player player;
	
	public PlayerTask(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		try {
			player.play();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
