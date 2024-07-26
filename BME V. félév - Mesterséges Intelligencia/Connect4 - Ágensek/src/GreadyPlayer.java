import java.util.Random;

public class GreadyPlayer extends Player{
    public GreadyPlayer(int playerIndex, int[] boardSize, int nToConnect) {
        super(playerIndex, boardSize, nToConnect);
    }


    public int step(Board board) {
    	for(int lepes : board.getValidSteps()) {
    		Board masolat = new Board(board);
    		masolat.step(playerIndex, lepes);
    		if(masolat.gameEnded()) return lepes;
    	}
    	for(int lepes : board.getValidSteps()) {	
    		Board masolat = new Board(board);
    		masolat.step(board.getLastPlayerIndex(), lepes);
    		if(masolat.gameEnded()) return lepes;
    	}
    	Random random = new Random();
    	int lepes = random.nextInt(6 + 1);
    	while(!board.stepIsValid(lepes)) {
    		lepes = random.nextInt(6 + 1);
    	}
    	
    	return lepes;
    }

}
