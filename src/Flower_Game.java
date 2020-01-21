import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/********************************************************************
 * This project implements the flower game from Destiny 2. The project
 * creates a field of flowers with a side size given by the user. The
 * project the follows four rules to determine if any of the flowers
 * live, die, or come back to life. the project then loops until all
 * flowers die.
 */
public class Flower_Game {

    public static void main(String[] args) throws IOException {

        //get input to set size of game
        System.out.print("how large a side should the flower field have?\n");
        //Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Reading data using readLine
        int size = Integer.parseInt(reader.readLine());

        //create new board of given size size
        Board newBoard = new Board(size);

        //print board and divider
        newBoard.printBoard();
        System.out.println("--------------------------");
        System.out.println("--------------------------");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("pause failed");
        }

        //counter for stalemated turns
        int equilibriumTurns = 0;

        //start game and go until there have been 2 turns of no changes
        while (newBoard.aliveFlowers > 0) {

            //get number of currently alive flowers
            int flowers = newBoard.aliveFlowers;

            //take turn
            newBoard.gameBoard = newBoard.turn();
            newBoard.printBoard();

            ///check to see if the number of flowers has changed if no add to equilibrium turns. if there have been 2
            //equilibrium turns end the game
            newBoard.aliveFlowers();
            if (flowers == newBoard.aliveFlowers) {
                equilibriumTurns++;
                if (equilibriumTurns == 2) {
                    break;
                }
            } else {
                equilibriumTurns = 0;
            }
            try {
                Thread.sleep(0010);
            } catch (InterruptedException e) {
                System.out.println("pause failed");
            }
            System.out.println("--------------------------");
            System.out.println("--------------------------");

        }
    }
}
