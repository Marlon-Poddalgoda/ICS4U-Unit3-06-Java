import java.util.*;
/**
 * This program is the Tic Tac Toe board game.
 *
 * @author  Marlon Poddalgoda
 * @version 1.0
 * @since two0two1-0five-1six
 */

public final class TicTacToe {
    private TicTacToe() {

        // Throw an exception if this ever *is* called
        throw new AssertionError("Instantiating utility class.");
    }
    /**
    * This takes in user input and calls other functions.
    * @param args
    */
    public static void main(final String[] args) {
        // main stub, get user input here
        boolean boardFull = false;
        boolean checkWinnerX = false;
        boolean checkWinnerO = false;

        // variables
        final int nine = 9;

        Scanner input = new Scanner(System.in);
        String[] board = {"1", "2", "3", "4", "5", "6", "7", "8", "9" };
        System.out.println("Welcome to tic tac toe!\n");

        do {
            printBoard(board);
            System.out.println("\nWhich space would you like to the X? ");
            if (input.hasNextInt()) {
                int space = input.nextInt();

                if (space > nine || space < 0) {
                    System.out.println("That spot is invalid!");
                } else if (board[space - 1].equalsIgnoreCase("X")
                            || board[space - 1].equalsIgnoreCase("O")) {
                    System.out.println("That spot's taken!");
                } else if (isNumeric(board[space - 1])) {
                    board[space - 1] = "X";
                    checkWinnerX = winOrLost(board, "X");
                    if (checkWinnerX) {
                        System.out.println("\nX has won.");
                        break;
                    }
                    // place a function call here to get the best move for O
                    if (!isFull(board)) {
                        // call function
                        miniMax(board, 0);
                        checkWinnerO = winOrLost(board, "O");
                        if (checkWinnerO) {
                            System.out.println("\nO has won.");
                            break;
                        }
                    }
                } else {
                    System.out.println("Error, try again.");
                    break;
                }
            } else {
                System.out.println("Error, try again.");
                break;
            }
            boardFull = isFull(board);
        } while (!boardFull);

        input.close();
        printBoard(board);

        checkWinnerX = winOrLost(board, "X");
        checkWinnerO = winOrLost(board, "O");
        if (checkWinnerX) {
            System.out.println("\nX has won.");
        } else if (checkWinnerO) {
            System.out.println("\nO has won.");
        } else {
            System.out.println();
            System.out.println("It's a tie.");
        }

        System.out.println("\nGame Over.");
    }

    /**
    * Checks to determine a winner/loser.
    * @param board This is the gameboard.
    * @param playerCheck This is the player.
    * @return This value returns.
    */
    public static boolean winOrLost(final String[] board,
                                    final String playerCheck) {
        // constans
        final int two = 2;
        final int three = 3;
        final int four = 4;
        final int five = 5;
        final int six = 6;
        final int seven = 7;
        final int eight = 8;

        // returns true or false for the player to see if they won
        if ((board[0] == playerCheck && board[1] == playerCheck && board[two]
                                                            == playerCheck)
                || (board[three] == playerCheck && board[four]
                == playerCheck && board[five] == playerCheck)
                || (board[six] == playerCheck && board[seven]
                == playerCheck && board[eight] == playerCheck)
                || (board[0] == playerCheck && board[three]
                == playerCheck && board[six] == playerCheck)
                || (board[1] == playerCheck && board[four]
                == playerCheck && board[seven] == playerCheck)
                || (board[two] == playerCheck && board[five]
                == playerCheck && board[eight] == playerCheck)
                || (board[0] == playerCheck && board[four]
                == playerCheck && board[eight] == playerCheck)
                || (board[two] == playerCheck && board[four]
                == playerCheck && board[six] == playerCheck)) {
            return true;
        } else {
            return false;
        }
    }

    /**
    * Uses Min-Max recursive function to determine optimal move.
    * @param gameBoard This is the gameboard.
    * @param counter This is the counter used recursively.
    * @return gameBoard This returns the gameboard to Main.
    */
    public static String[] miniMax(final String[] gameBoard,
                                    int counter) {
    // constants
    final int two = 2;
    final int three = 3;
    final int four = 4;
    final int five = 5;
    final int six = 6;
    final int seven = 7;
    final int eight = 8;
    final int nine = 9;
    final int twenty = 20;
    final int fifty = 50;

    // try statement
    try {
        // check if counter is below nine
        if (counter < nine) {
            if (isNumeric(gameBoard[counter])) {

                // temp value to store current value
                String tempVal = gameBoard[counter];

                // temporarily set current value to "O"
                gameBoard[counter] = "O";

                //check current value to determine comp win
                if (winOrLost(gameBoard, "O")) {
                    // if victorious, set current value to "O"
                    gameBoard[counter] = "O";
                    counter += fifty;
                // reset temp value
                } else {
                    gameBoard[counter] = tempVal;
                }

                // temporarily set value to "X"
                gameBoard[counter] = "X";

                // check current value to determine human win
                if (winOrLost(gameBoard, "X")) {
                    // if victorious, set current value to "O", to block move
                    gameBoard[counter] = "O";
                    counter += fifty;
                } else {
                    // reset temp value
                    gameBoard[counter] = tempVal;
                }
            }

            // recursive call.
            miniMax(gameBoard, counter + 1);

        // check if counter is less than eight, greater than two0
        } else if (counter > eight && counter < twenty) {
            // generate random num
            int random = (int) (Math.random() * (three - 0 + 1) + 0);

            specificCases(gameBoard, random);
        }
        // return statement
        return gameBoard;

        } catch (ArrayIndexOutOfBoundsException exception) {
        // return statement, if counter exceeds if statements
        return gameBoard;
        }
    }

    /**
    * Checks for specific cases.
    * @param gameBoard This is the gameboard.
    * @param random This is a random num.
    * @return gameBoard This is gamebord.
    */
    public static String[] specificCases(final String[] gameBoard,
                                          final int random) {
            // constants
            final int two = 2;
            final int three = 3;
            final int four = 4;
            final int five = 5;
            final int six = 6;
            final int seven = 7;
            final int eight = 8;

            // check if middle is unoccupied
            if (isNumeric(gameBoard[four])) {
                gameBoard[four] = "O";

            // checking for specific cases
            // if middle is O, top left is X and bottom right is X
            } else if ((gameBoard[four]).equals("O")
                    && (gameBoard[0]).equals("X")
                    && (gameBoard[eight]).equals("X")
                    && isNumeric(gameBoard[1])
                    && isNumeric(gameBoard[three])
                    && isNumeric(gameBoard[five])
                    && isNumeric(gameBoard[seven])) {

                // tells program random place to play moves
                // only used in certain situations
                if (random == 0 && isNumeric(gameBoard[1])) {
                    // set value to "O"
                    gameBoard[1] = "O";
                } else if (random == 1 && isNumeric(gameBoard[three])) {
                    // set value to "O"
                    gameBoard[three] = "O";
                } else if (random == two && isNumeric(gameBoard[five])) {
                    // set value to "O"
                    gameBoard[five] = "O";
                } else if (random == three && isNumeric(gameBoard[seven])) {
                    // set value to "O"
                    gameBoard[seven] = "O";
                }

            // if middle is O, top right is X and bottom left is X
            } else if ((gameBoard[four]).equals("O")
                    && (gameBoard[two]).equals("X")
                    && (gameBoard[six]).equals("X")
                    && isNumeric(gameBoard[1])
                    && isNumeric(gameBoard[three])
                    && isNumeric(gameBoard[five])
                    && isNumeric(gameBoard[seven])) {

                // tells program random place to play moves
                // only used in certain situations
                if (random == 0 && isNumeric(gameBoard[1])) {
                    gameBoard[1] = "O";
                } else if (random == 1 && isNumeric(gameBoard[three])) {
                    gameBoard[three] = "O";
                } else if (random == two && isNumeric(gameBoard[five])) {
                    gameBoard[five] = "O";
                } else if (random == three && isNumeric(gameBoard[seven])) {
                    gameBoard[seven] = "O";
                }

            // if middle is O, middle top is X and top right is X
            } else if ((gameBoard[four]).equals("O")
                    && (gameBoard[1]).equals("X")
                    && (gameBoard[three]).equals("X")
                    && isNumeric(gameBoard[0])
                    && isNumeric(gameBoard[two])
                    && isNumeric(gameBoard[five])
                    && isNumeric(gameBoard[six])
                    && isNumeric(gameBoard[seven])
                    && isNumeric(gameBoard[eight])) {
                // set top left to O
                gameBoard[0] = "O";

            // if middle is O, middle top is X and middle right is X
            } else if ((gameBoard[four]).equals("O")
                    && (gameBoard[1]).equals("X")
                    && (gameBoard[five]).equals("X")
                    && isNumeric(gameBoard[0])
                    && isNumeric(gameBoard[two])
                    && isNumeric(gameBoard[three])
                    && isNumeric(gameBoard[six])
                    && isNumeric(gameBoard[seven])
                    && isNumeric(gameBoard[eight])) {
                // set top right to O
                gameBoard[two] = "O";

            // if middle is O, middle left is X and middle bottom is X
            } else if ((gameBoard[four]).equals("O")
                    && (gameBoard[three]).equals("X")
                    && (gameBoard[seven]).equals("X")
                    && isNumeric(gameBoard[0])
                    && isNumeric(gameBoard[1])
                    && isNumeric(gameBoard[two])
                    && isNumeric(gameBoard[five])
                    && isNumeric(gameBoard[six])
                    && isNumeric(gameBoard[eight])) {
                // set bottom left to O
                gameBoard[six] = "O";

            // if middle is O, middle right is X and middle bottom is X
            } else if ((gameBoard[four]).equals("O")
                    && (gameBoard[five]).equals("X")
                    && (gameBoard[seven]).equals("X")
                    && isNumeric(gameBoard[0])
                    && isNumeric(gameBoard[1])
                    && isNumeric(gameBoard[two])
                    && isNumeric(gameBoard[three])
                    && isNumeric(gameBoard[six])
                    && isNumeric(gameBoard[eight])) {
                // set bottom right to O
                gameBoard[eight] = "O";

            // tells program random place to play moves
            // only used in certain situations
            } else if (random == 1 && isNumeric(gameBoard[two])) {
                gameBoard[two] = "O";
            } else if (random == two && isNumeric(gameBoard[six])) {
                gameBoard[six] = "O";
            } else if (random == three && isNumeric(gameBoard[eight])) {
                gameBoard[eight] = "O";
            } else if (random == 0 && isNumeric(gameBoard[1])) {
                gameBoard[1] = "O";
            } else if (random == 1 && isNumeric(gameBoard[three])) {
                gameBoard[three] = "O";
            } else if (random == two && isNumeric(gameBoard[five])) {
                gameBoard[five] = "O";
            } else if (random == three && isNumeric(gameBoard[seven])) {
                gameBoard[seven] = "O";
            }
        return gameBoard;
    }

    /**
    * Checks if board if full.
    * @param presentBoard This is the gameboard.
    * @return full This value is a boolean expression.
    */
    public static boolean isFull(final String[] presentBoard) {
        // returns whether board is full or not
        boolean full = true;
        for (int counter = 0; counter < presentBoard.length; counter++) {
            if (isNumeric(presentBoard[counter])) {
                full = false;
                break;
            }
        }
        return full;
    }

    /**
    * Prints the board to the console.
    * @param theBoard This is the gameboard.
    */
    public static void printBoard(final String[] theBoard) {
        // constants
        final int two = 2;
        final int three = 3;
        final int four = 4;
        final int five = 5;
        final int six = 6;
        final int seven = 7;
        final int eight = 8;
        final int nine = 9;

        // prints current game state
        System.out.println("----+----+----");
        for (int count = 0; count < nine; count++) {
            if (count == two || count == five || count == eight) {
                System.out.print("| " + theBoard[count] + " |\n");
                System.out.println("----+----+----");
            } else {
                System.out.print("| " + theBoard[count] + " ");
            }
        }
    }

    /**
    * Checks if value is numeric.
    * @param strNum This is a string.
    * @return This value returns.
    */
    public static boolean isNumeric(final String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
