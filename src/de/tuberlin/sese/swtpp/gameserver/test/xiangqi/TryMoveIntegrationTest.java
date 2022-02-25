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
	    assertGameState("4g4/4a4/9/9/9/9/9/9/9/3G5",false,false,false);
	    
	    startGame("4g4/4a4/9/9/9/9/9/9/9/4G4",false);
	    assertMove("e9-f9",false,true);
	    assertGameState("5g3/4a4/9/9/9/9/9/9/9/4G4",true,false,false);
	    
	}
	
	@Test
	public void setBoardTest() {
	    startGame("rheagaehr/9/1c5c1/s1s1s1s1s/9/9/S1S1S1S1S/1C5C1/9/RHEAGAEHR",true);
	    assertMove("e3-e4",true,true);
	    assertGameState("rheagaehr/9/1c5c1/s1s1s1s1s/9/4S4/S1S3S1S/1C5C1/9/RHEAGAEHR",false,false,false);
	}
	
	@Test
	public void deathstare() {
		startGame("4g4/4a4/9/9/9/9/9/9/9/4G4",true);
		assertMove("e0-d0",true,true);
		assertGameState("4g4/4a4/9/9/9/9/9/9/9/3G5",false,false,false);
		
		startGame("4g4/4a4/9/9/9/9/9/9/4A4/4G4",true);
		assertMove("e1-d0",true,true);
		assertGameState("4g4/4a4/9/9/9/9/9/9/9/3AG4",false,false,false);
		
		startGame("4g4/9/9/9/9/9/9/9/4A4/4G4",true);
		assertMove("e1-d0",true,false);
		assertGameState("4g4/9/9/9/9/9/9/9/4A4/4G4",true,false,false);
	}
	
	@Test
	public void figuren() {
		// Rook
		startGame("4g4/4a4/e8/9/4r4/4R4/9/9/4A4/4G4",true);
		assertMove("e4-a4",true,true);
		assertMove("e5-i5",false,true);
		assertMove("a4-a1",true,true);
		assertMove("i5-i8",false,true);
		assertMove("a1-a9",true,false);
		assertMove("a1-i1",true,false);
		assertGameState("4g4/4a3r/e8/9/9/9/9/9/R3A4/4G4",true,false,false);
		
		// Cannon
		startGame("4g4/4a4/7C1/9/7S1/7s1/1C1H1h1c1/9/4A4/4G4",true);
		assertMove("b3-h3",true,false);
		assertMove("b3-f3",true,true);
		assertMove("h3-f3",false,false);
		
		assertMove("h3-h7",false,false);
		assertMove("h3-h5",false,true);
		assertMove("h7-h5",true,false);
		assertGameState("4g4/4a4/7C1/9/7c1/7s1/3H1C3/9/4A4/4G4",true,false,false);
		
		//All Others
		startGame("rheagaehr/9/1c5c1/s1s1s1s1s/9/9/S1S1S1S1S/1C5C1/9/RHEAGAEHR",true);
		//King
	    assertMove("e0-d1",true,false);
	    assertMove("e0-e1",true,true);
	    
	    //Advisor
	    assertMove("d9-d8",false,false);
	    assertMove("d9-e8",false,true);
	    
	    //Horse
	    assertMove("h0-f1",true,false);
	    assertMove("h0-g2",true,true);
	    
	    //Pawn
	    assertMove("e6-d6",false,false);
	    assertMove("e6-e5",false,true);
	    
	    //Elephant
	    assertMove("g0-e2",true,true);
	    assertMove("e8-d9",false,true);
	    
	    assertMove("e1-f1",true,true);
	    assertMove("e5-e4",false,true);
	    
	    assertMove("e2-g0",true,false);
	    assertMove("c3-c4",true,true);
	    
	    assertMove("e4-d4",false,true);
	    assertMove("c4-c5",true,true);
	    
	    assertMove("d4-e4",false,true);
	    assertMove("c5-d5",true,true);
	    
	    
	    assertGameState("rheagaehr/9/1c5c1/s1s3s1s/3S5/4s4/S3S1S1S/1C2E1HC1/5G3/RHEA1A2R",false,false,false);

		
	}
	
	@Test
	public void missings() {
		startGame("4g4/4a4/4e4/9/9/9/9/4H4/9/4G4",true);
	    assertMove("e2-f4",true,true);
	    assertMove("e7-c9",false,true);
	    assertMove("f4-g2",true,true);
	    assertMove("c9-a7",false,true);
	    assertMove("g2-e3",true,true);
	    assertMove("a7-c5",false,true);
	    assertMove("e3-g4",true,true);
	    assertMove("c5-e3",false,false);
	    assertMove("c5-e7",false,true);
	    
	    assertMove("g4-f2",true,true);
	    assertMove("e9-d9",false,true);
	    
	    assertMove("f2-e4",true,true);
	    assertMove("d9-e9",false,true);
	    
	    assertMove("e4-g3",true,true);
	    assertMove("e9-d9",false,true);
	    
	    assertMove("g3-e2",true,true);
	    assertMove("d9-e9",false,true);
	    
	    assertGameState("4g4/4a4/4e4/9/9/9/9/4H4/9/4G4",true,false,false);
	    
	    
	    
	    startGame("4g4/9/9/9/4s4/4S4/9/9/4C4/rH2G4",false);
	    assertMove("e5-e4",false,false);
	    assertMove("e9-d9",false,true);
	    assertMove("b0-c2",true,false);
	    
	    assertGameState("3g5/9/9/9/4s4/4S4/9/9/4C4/rH2G4",true,false,false);
	    
	    
	}
}
