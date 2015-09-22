package celebrity.name.game;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CelebrityNameGameStarter {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		CelebrityNameGame game = new CelebrityNameGame(args[0]);
		System.out.println("The longest name chain found is: ");
		System.out.println(game.startGame());
	}	
	
}
