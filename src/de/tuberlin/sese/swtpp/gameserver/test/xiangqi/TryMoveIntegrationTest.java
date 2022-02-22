package de.tuberlin.sese.swtpp.gameserver.test.xiangqi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.tuberlin.sese.swtpp.gameserver.control.GameController;
import de.tuberlin.sese.swtpp.gameserver.model.Game;
import de.tuberlin.sese.swtpp.gameserver.model.Player;
import de.tuberlin.sese.swtpp.gameserver.model.User;

public class TryMoveIntegrationTest {


	User user1 = new User("Alice", "alice");
	User user2 = new User("Bob", "bob");
	
	Player redPlayer = null;
	Player blackPlayer = null;
	Game game = null;
	GameController controller;
	
	@Before
	public void setUp() throws Exception {
		controller = GameController.getInstance();
		controller.clear();
		
		int gameID = controller.startGame(user1, "", "xiangqi");
		
		game =  controller.getGame(gameID);
		redPlayer = game.getPlayer(user1);

	}
	
	public void startGame() {
		controller.joinGame(user2, "xiangqi");		
		blackPlayer = game.getPlayer(user2);
	}
	
	public void startGame(String initialBoard, boolean redNext) {
		startGame();
		
		game.setBoard(initialBoard);
		game.setNextPlayer(redNext? redPlayer:blackPlayer);
	}
	
	public void assertMove(String move, boolean red, boolean expectedResult) {
		if (red)
			assertEquals(expectedResult, game.tryMove(move, redPlayer));
		else 
			assertEquals(expectedResult,game.tryMove(move, blackPlayer));
	}
	
	public void assertGameState(String expectedBoard, boolean redNext, boolean finished, boolean redWon) {
		assertEquals(expectedBoard,game.getBoard());
		assertEquals(finished, game.isFinished());

		if (!game.isFinished()) {
			assertEquals(redNext, game.getNextPlayer() == redPlayer);
		} else {
			assertEquals(redWon, redPlayer.isWinner());
			assertEquals(!redWon, blackPlayer.isWinner());
		}
	}
	

	/*******************************************
	 * !!!!!!!!! To be implemented !!!!!!!!!!!!
	 *******************************************/
	
	@Test
	public void exampleTest() {
	    startGame("rheagaehr/9/1c5c1/s1s1s1s1s/9/9/S1S1S1S1S/1C5C1/9/RHEAGAEHR",true);
	    assertMove("e3-e4",true,true);
	    assertGameState("rheagaehr/9/1c5c1/s1s1s1s1s/9/4S4/S1S3S1S/1C5C1/9/RHEAGAEHR",false,false,false);
	}
	
	
	//MyTests
	@Test
	public void tryMoveXiangqiGame() {
		//Player
		startGame("4g4/4a4/9/9/9/9/9/9/9/4G4",false);
	    assertMove("e0-d0",true,false);
	    assertGameState("4g4/4a4/9/9/9/9/9/9/9/4G4",false,false,false);
	    
		startGame("4g4/4a4/9/9/9/9/9/9/9/4G4",false);
	    assertMove("e0-d0",false,false);
	    assertGameState("4g4/4a4/9/9/9/9/9/9/9/4G4",false,false,false);
		
		
		//Formatting
	    startGame("4g4/4a4/9/9/9/9/9/9/9/4G4",true);
	    assertMove("e0d0",true,false);
	    assertGameState("4g4/4a4/9/9/9/9/9/9/9/4G4",true,false,false);
	    
	    startGame("4g4/4a4/9/9/9/9/9/9/9/4G4",true);
	    assertMove("0e-d0",true,false);
	    assertGameState("4g4/4a4/9/9/9/9/9/9/9/4G4",true,false,false);
	    
	    //SetnextPlayer
	    startGame("4g4/4a4/9/9/9/9/9/9/9/4G4",true);
	    assertMove("e0-d0",true,true);
	    assertGameState("4g4/4a4/9/9/9/9/9/9/9/4G4",false,false,false);
	    
	    startGame("4g4/4a4/9/9/9/9/9/9/9/4G4",false);
	    assertMove("e9-d9",false,true);
	    assertGameState("4g4/4a4/9/9/9/9/9/9/9/4G4",true,false,false);
	    
	}
	
	@Test
	public void setBoardTest() {
	    startGame("rheagaehr/9/1c5c1/s1s1s1s1s/9/9/S1S1S1S1S/1C5C1/9/RHEAGAEHR",true);
	    assertMove("e3-e4",true,true);
	    assertGameState("rheagaehr/9/1c5c1/s1s1s1s1s/9/4S4/S1S3S1S/1C5C1/9/RHEAGAEHR",false,false,false);
	}
	
	
	
	@Test
	public void rook() {
		startGame("4g4/4a4/9/9/9/9/9/9/9/4G4",true);
	    assertMove("e0-d0",true,true);
	    assertGameState("4g4/4a4/9/9/9/9/9/9/9/4G4",false,false,false);
	}
	
	@Test
	public void knight() {
		startGame("rheagaehr/9/1c5c1/s1s1s1s1s/9/9/S1S1S1S1S/1C5C1/9/RHEAGAEHR",true);
	    assertMove("e3-e4",true,true);
	    assertGameState("rheagaehr/9/1c5c1/s1s1s1s1s/9/4S4/S1S3S1S/1C5C1/9/RHEAGAEHR",false,false,false);
	}
	
	@Test
	public void elephant() {
		startGame("rheagaehr/9/1c5c1/s1s1s1s1s/9/9/S1S1S1S1S/1C5C1/9/RHEAGAEHR",true);
	    assertMove("e3-e4",true,true);
	    assertGameState("rheagaehr/9/1c5c1/s1s1s1s1s/9/4S4/S1S3S1S/1C5C1/9/RHEAGAEHR",false,false,false);
	}
	
	@Test
	public void advisor() {
		startGame("rheagaehr/9/1c5c1/s1s1s1s1s/9/9/S1S1S1S1S/1C5C1/9/RHEAGAEHR",true);
	    assertMove("e3-e4",true,true);
	    assertGameState("rheagaehr/9/1c5c1/s1s1s1s1s/9/4S4/S1S3S1S/1C5C1/9/RHEAGAEHR",false,false,false);
	}
	
	@Test
	public void general() {
		startGame("rheagaehr/9/1c5c1/s1s1s1s1s/9/9/S1S1S1S1S/1C5C1/9/RHEAGAEHR",true);
	    assertMove("e3-e4",true,true);
	    assertGameState("rheagaehr/9/1c5c1/s1s1s1s1s/9/4S4/S1S3S1S/1C5C1/9/RHEAGAEHR",false,false,false);
	}
	
	@Test
	public void cannon() {
		startGame("rheagaehr/9/1c5c1/s1s1s1s1s/9/9/S1S1S1S1S/1C5C1/9/RHEAGAEHR",true);
	    assertMove("e3-e4",true,true);
	    assertGameState("rheagaehr/9/1c5c1/s1s1s1s1s/9/4S4/S1S3S1S/1C5C1/9/RHEAGAEHR",false,false,false);
	}
	
	@Test
	public void soldier() {
		startGame("rheagaehr/9/1c5c1/s1s1s1s1s/9/9/S1S1S1S1S/1C5C1/9/RHEAGAEHR",true);
	    assertMove("e3-e4",true,true);
	    assertGameState("rheagaehr/9/1c5c1/s1s1s1s1s/9/4S4/S1S3S1S/1C5C1/9/RHEAGAEHR",false,false,false);
	}
	 

	    
	    

}
