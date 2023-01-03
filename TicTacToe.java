import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

                printGameBoard(gameBoard);

                // loop the game until the user wins, loses or it's a tie

                while (true) {
                    // players turn
                    Scanner scan = new Scanner(System.in);
                    // get the users input for where to play their piece
                    System.out.println("Enter position (1-9): ");
                    
                    // loop until a vailid number is entered
                    boolean flag = false;
                    do {
                        try {
                            int playerPos = scan.nextInt();
                            // loop asking the user for their input until a valid spot is entered
                            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos) || playerPos < 1 || playerPos > 9) {
                                System.out.println("Invalid position! Choose again.");
                                playerPos = scan.nextInt();
                            }
                            // place the users piece on the board
                            placePiece(gameBoard, playerPos, "player");
                            flag = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Only numbers are allowed. Enter number (1-9)");
                            scan.next();
                            flag = true;
                        }
                    } while (flag);
                    
                    
                    // print the board
                    printGameBoard(gameBoard);
                    // check if user wins or it's the end of the game
                    String result = checkWinner();
                    if (result.length() > 0) {
                        System.out.println(result);
                        break;
                    }
                    // cpu's turn
                    Random rand = new Random();
                    int cpuPos = rand.nextInt(9) + 1;
                    while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                        cpuPos = rand.nextInt(9) + 1;
                    }
                    placePiece(gameBoard, cpuPos, "cpu");

                    printGameBoard(gameBoard);

                    result = checkWinner();
                    if (result.length() > 0) {
                        System.out.println(result);
                        break;
                    }
                    
                }
                

    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {

        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else {
            symbol = 'O';
            cpuPositions.add(pos);
        }
        
        switch(pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }

    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List middleCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List rightCross = Arrays.asList(1, 5, 9);
        List leftCross = Arrays.asList(3, 5, 7);


        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(middleCol);
        winning.add(rightCol);
        winning.add(rightCross);
        winning.add(leftCross);

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "Contratulations! You win!";
            } else if (cpuPositions.containsAll(l)) {
                return "You lose! Better luck next time.";
            }
        }
        if (playerPositions.size() + cpuPositions.size() == 9) {
            return "It's a tie!";
        }
        return "";
    }
    
}