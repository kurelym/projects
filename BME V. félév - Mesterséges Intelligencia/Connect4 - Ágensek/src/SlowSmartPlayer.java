import java.util.ArrayList;
import java.util.Random;


public class SlowSmartPlayer extends Player{
    public SlowSmartPlayer(int playerIndex, int[] boardSize, int nToConnect) {
        super(playerIndex, boardSize, nToConnect);
    }


    public int step(Board board) {
		int kinem = board.getLastPlayerIndex();
	    int ki = (kinem == 1) ? 2 : 1; // Ellenkező játékos indexe

		int maxertek = -100;
		int mitlepjunk = 3;
	    for (int lepes : board.getValidSteps()) {
	        Board uj = new Board(board); // Másolat készítése az eredeti tábláról
	        uj.step(ki, lepes); // Lépés a másolaton
			int most=MinMaxDontes(uj,5);
			if(most>maxertek) {
				maxertek=most;
				mitlepjunk = lepes;
			}
	    }
	    while(!board.getValidSteps().contains(mitlepjunk)) {
	    	Random random = new Random();
	    	mitlepjunk = random.nextInt(6 + 1);
	    }
		return mitlepjunk;

    }
    
    
    private int MinMaxDontes(Board board, int melyseg) {
		return MinErtek(board, melyseg);
		
	}


	int MaxErtek(Board allapot, int melyseg) {
    	if(VegeTeszt(allapot) || melyseg == 0) {
    		return Hasznossag(allapot);
    	}
    	int k = -100;
    	for(Board s: KovetoAllapotok(allapot)) {
    		k = Math.max(k, MinErtek(s,melyseg-1));
    	}
    	return k;
    }
    
    int MinErtek(Board allapot, int melyseg) {
    	if(VegeTeszt(allapot) || melyseg == 0) {
    		return Hasznossag(allapot);
    	}
    	
    	int v = 100;
    	for(Board s: KovetoAllapotok(allapot)) {
    		v = Math.min(v, MaxErtek(s,melyseg-1));
    	}
    	return v;
    	
    	
    }
    
	ArrayList<Board> KovetoAllapotok(Board board) {
	    ArrayList<Board> elerheto = new ArrayList<Board>();

	    int kinem = board.getLastPlayerIndex();
	    int ki = (kinem == 1) ? 2 : 1; // Ellenkező játékos indexe

	    for (int lepes : board.getValidSteps()) {
	        Board uj = new Board(board); // Másolat készítése az eredeti tábláról
	        uj.step(ki, lepes); // Lépés a másolaton
	        elerheto.add(uj); // Másolat hozzáadása az elérhető táblákhoz
	    }

	    return elerheto;
	}
	
	boolean VegeTeszt(Board board) {
		return board.gameEnded();
	}
    
	int Hasznossag(Board board) {
		int winner = board.getWinner();
		if (winner == 1) {
			return -100; // ember győz
		} else if (winner == 2) {
			return 100; //AI győz
		}
		///jó dolgok:
		if(board.getLastPlayerColumn() == 2) {
			if(top120( board.getState() )) return -1;
			if(top12(board.getState())) return -1;
		}
		if(contains0XXX0(board.getState(),2)) return 9;
		if(contains0XXX0(board.getState(),1)) return -9;
		
		if(containsXXX0(board.getState(),2)) return 8;
		if(containsXXX0(board.getState(),1)) return -8;
		
		if(contains0XX0(board.getState(),2)) return 6;
		if(contains0XX0(board.getState(),1)) return -6;
		

		
		
		return 1;
	}
	public static boolean contains0XXX0(int[][] matrix, int number) {
	    int rows = matrix.length;
	    int cols = matrix[0].length;

	    // Függőleges ellenőrzés
	    for (int i = 0; i < rows - 3; i++) {
	        for (int j = 0; j < cols; j++) {
	            if (matrix[i][j] == 0 && matrix[i + 1][j] == number &&
	                matrix[i + 2][j] == number && matrix[i + 3][j] == number) {
	                return true;
	            }
	        }
	    }

	    // Vízszintes ellenőrzés
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols - 4; j++) {
	            if (matrix[i][j] == 0 && matrix[i][j + 1] == number &&
	                matrix[i][j + 2] == number && matrix[i][j + 3] == number &&
	                matrix[i][j + 4] == 0) {
	                return true;
	            }
	        }
	    }

	    // Átlós ellenőrzés (bal felső saroktól jobb alsó sarok)
	    for (int i = 0; i < rows - 4; i++) {
	        for (int j = 0; j < cols - 4; j++) {
	            if (matrix[i][j] == 0 && matrix[i + 1][j + 1] == number &&
	                matrix[i + 2][j + 2] == number && matrix[i + 3][j + 3] == number &&
	                matrix[i + 4][j + 4] == 0) {
	                return true;
	            }
	        }
	    }

	    // Átlós ellenőrzés (jobb felső saroktól bal alsó sarok)
	    for (int i = 0; i < rows - 4; i++) {
	        for (int j = cols - 1; j >= 4; j--) {
	            if (matrix[i][j] == 0 && matrix[i + 1][j - 1] == number &&
	                matrix[i + 2][j - 2] == number && matrix[i + 3][j - 3] == number &&
	                matrix[i + 4][j - 4] == 0) {
	                return true;
	            }
	        }
	    }

	    return false;
	}
	
	
	public static boolean top120 (int[][] matrix) {
	    int cols = matrix[0].length;
	    
	    for(int j = 0; j < cols; j++) {
	    	if( matrix[0][j] == 0 && matrix [1][j] == 2 && matrix[2][j] == 1) return true;
	    }
	    
	    return false;
	}
	public static boolean top12 (int[][] matrix) {
	    int cols = matrix[0].length;
	    
	    for(int j = 0; j < cols; j++) {
	    	if( matrix[0][j] == 2 && matrix [1][j] == 1) return true;
	    }
	    
	    return false;
	}
	
	
	public static boolean containsXXX0(int[][] matrix, int number) {
	    int rows = matrix.length;
	    int cols = matrix[0].length;

	    // Átlós ellenőrzés (bal felső saroktól jobb alsó sarok)
	    for (int i = 0; i < rows - 3; i++) {
	        for (int j = 0; j < cols - 3; j++) {
	            if (matrix[i][j] == 0 && matrix[i + 1][j + 1] == number &&
	                matrix[i + 2][j + 2] == number && matrix[i + 3][j + 3] == number) {
	                return true;
	            }
	        }
	    }

	    // Átlós ellenőrzés (jobb felső saroktól bal alsó sarok)
	    for (int i = 0; i < rows - 3; i++) {
	        for (int j = cols - 1; j >= 3; j--) {
	            if (matrix[i][j] == 0 && matrix[i + 1][j - 1] == number &&
	                matrix[i + 2][j - 2] == number && matrix[i + 3][j - 3] == number) {
	                return true;
	            }
	        }
	    }

	    // Vízszintes ellenőrzés
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols - 3; j++) {
	            if (matrix[i][j] == number && matrix[i][j + 1] == number &&
	                matrix[i][j + 2] == number && matrix[i][j + 3] == 0) {
	                return true;
	            }
	            if (matrix[i][j] == 0 && matrix[i][j + 1] == number &&
		                matrix[i][j + 2] == number && matrix[i][j + 3] == number) {
		                return true;
		            }
	        }
	    }

	    // Függőleges ellenőrzés
	    for (int i = 0; i < rows - 3; i++) {
	        for (int j = 0; j < cols; j++) {
	            if (matrix[i][j] == 0 && matrix[i + 1][j] == number &&
	                matrix[i + 2][j] == number && matrix[i + 3][j] == number) {
	                return true;
	            }
	        }
	    }

	    return false;
	}
	public static boolean contains0XX0(int[][] matrix, int number) {
	    int rows = matrix.length;
	    int cols = matrix[0].length;

	    // Átlós ellenőrzés (bal felső saroktól jobb alsó sarok)
	    for (int i = 0; i < rows - 3; i++) {
	        for (int j = 0; j < cols - 3; j++) {
	            if (matrix[i][j] == 0 && matrix[i + 1][j + 1] == number &&
	                matrix[i + 2][j + 2] == number && matrix[i + 3][j + 3] == 0) {
	                return true;
	            }
	        }
	    }

	    // Átlós ellenőrzés (jobb felső saroktól bal alsó sarok)
	    for (int i = 0; i < rows - 3; i++) {
	        for (int j = cols - 1; j >= 3; j--) {
	            if (matrix[i][j] == 0 && matrix[i + 1][j - 1] == number &&
	                matrix[i + 2][j - 2] == number && matrix[i + 3][j - 3] == 0) {
	                return true;
	            }
	        }
	    }

	    // Vízszintes ellenőrzés
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols - 3; j++) {
	            if (matrix[i][j] == 0 && matrix[i][j + 1] == number &&
	                matrix[i][j + 2] == number && matrix[i][j + 3] == 0) {
	                return true;
	            }
	        }
	    }

	    // Függőleges ellenőrzés
	    for (int i = 0; i < rows - 2; i++) {
	        for (int j = 0; j < cols; j++) {
	            if (matrix[i][j] == 0 && matrix[i + 1][j] == number &&
	                matrix[i + 2][j] == number) {
	                return true;
	            }
	        }
	    }

	    return false;
	}

}
