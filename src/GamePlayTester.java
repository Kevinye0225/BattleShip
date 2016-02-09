
public class GamePlayTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComputerPlayer computer = new ComputerPlayer();
		HumanPlayer human = new HumanPlayer();
		GamePlay play = new GamePlay();
		play.initialize(human, computer);
		System.out.println("Finish placing ships, Game starts: ");
		play.playGame();


	}

}
