import java.util.Random;

public class Board {

    int[][] gameBoard;
    int aliveFlowers;
    private int side;

    Board(int size) {

        side = size;
        this.gameBoard = new int[side][side];

        Random r = new Random();

        //not aliveFlowers+1 because no flowers is a acceptable result
        aliveFlowers = r.nextInt(side * side);

        //no checking for if index is already a flower to provide more randomness
        for (int i = 0; i < aliveFlowers; i++) {
            gameBoard[r.nextInt(side)][r.nextInt((side))] = 1;
        }
    }


    public void aliveFlowers() {
        int alive = 0;

        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                if (gameBoard[x][y] == 1) {
                    alive++;
                }
            }

        }
        aliveFlowers = alive;
    }

    //process turn actions
    public int[][] turn() {
        int[][] changedBoard = new int[side][side];

        for (int y = 0; y < side; y++) {
            for (int x = 0; x < side; x++) {

                int neighbors = checkLivingNeighbors(x, y);

                //if the flower is already alive
                if (gameBoard[x][y] == 1) {
                    //if less then two neighbors the flower dies
                    if (neighbors < 2) {
                        changedBoard[x][y] = 0;
                    }
                    //if the flower has 3 or more neighbors the flower dies
                    else if (neighbors > 3) {
                        changedBoard[x][y] = 0;
                    }
                    //if the flower has two neighbors the flower stays alive
                    else {
                        changedBoard[x][y] = 1;
                    }

                }
                //if flower is dead and has three neighbors it comes back to life
                else if (neighbors == 3) {
                    changedBoard[x][y] = 1;
                }
            }
        }

        return changedBoard;
    }

    //check board for the number of living flowers to a given space
    public int checkLivingNeighbors(int x, int y) {
        int numNeighbors = 0;

        if (x + 1 < side) {
            if (gameBoard[x + 1][y] == 1) {
                numNeighbors++;
            }
        }
        if (x - 1 > 0) {
            if (gameBoard[x - 1][y] == 1) {
                numNeighbors++;
            }
        }
        if (y - 1 > 0) {
            if (gameBoard[x][y - 1] == 1) {
                numNeighbors++;
            }
        }
        if (y + 1 < side) {
            if (gameBoard[x][y + 1] == 1) {
                numNeighbors++;
            }
        }
        if (y + 1 < side && x - 1 > 0) {
            if (gameBoard[x - 1][y + 1] == 1) {
                numNeighbors++;
            }
        }
        if (y + 1 < side && x + 1 < side) {
            if (gameBoard[x + 1][y + 1] == 1) {
                numNeighbors++;
            }
        }
        if (y - 1 > 0 && x - 1 > 0) {
            if (gameBoard[x - 1][y - 1] == 1) {
                numNeighbors++;
            }
        }
        if (y - 1 > 0 && x + 1 < side) {
            if (gameBoard[x + 1][y - 1] == 1) {
                numNeighbors++;
            }
        }
        return numNeighbors;
    }

    public void printBoard() {
        int aliveFlowers = 0;
        int deadFlowers = 0;
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                if (gameBoard[x][y] == 0) {
                    System.out.print(" 0 ");
                    deadFlowers++;
                }
                if (gameBoard[x][y] == 1) {
                    System.out.print(" 1 ");
                    aliveFlowers++;
                }
            }
            System.out.print("\n");

        }
        System.out.println("alive flowers = " + aliveFlowers + "\t\tdead flowers =" + deadFlowers);
    }

}
