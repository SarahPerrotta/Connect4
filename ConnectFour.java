import java.util.Scanner;

public class connectFour {

    private static final int ROWS = 6;
    private static final int COLS = 7;

    private int[][] gameBoard = new int[COLS][ROWS];
    private static final int EMPTY = 0;
    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;

    public static void main(String[] args) {
        connectFour game = new connectFour();
        game.play();
    }

    private void play() {
        int currentPlayer = PLAYER_1;
        boolean gameContinues = true;

        while (gameContinues) {
            printBoard();

            int column = getPlayerMove(currentPlayer);
            int row = getAvailableRow(column);

            if (row == -1) {
                System.out.println("Column is full. Please choose another.");
            } else {
                gameBoard[column][row] = currentPlayer;

                if (checkWin(column, row)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameContinues = false;
                } else if (isBoardFull()) {
                    System.out.println("It's a tie!");
                    gameContinues = false;
                } else {
                    currentPlayer = currentPlayer == PLAYER_1 ? PLAYER_2 : PLAYER_1;
                }
            }
        }
    }

    private int getPlayerMove(int player) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player " + player + ", please enter a column (0-6): ");
        return scanner.nextInt();
    }

    private int getAvailableRow(int column) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (gameBoard[column][i] == EMPTY) {
                return i;
            }
        }
        return -1; // Column is full
    }

    private boolean checkWin(int column, int row) {
        // Check horizontal (code to check for wins in different directions)
        int count = 1;
        for (int i = column - 1; i >= 0 && gameBoard[i][row] == gameBoard[column][row]; i--) {
            count++;
        }
        for (int i = column + 1; i < COLS && gameBoard[i][row] == gameBoard[column][row]; i++) {
            count++;
        }
        if (count >= 4) {
            return true;
        }

        // Check vertical
        count = 1;
        for (int i = row - 1; i >= 0 && gameBoard[column][i] == gameBoard[column][row]; i--) {
            count++;
        }
        for (int i = row + 1; i < ROWS && gameBoard[column][i] == gameBoard[column][row]; i++) {
            count++;
        }
        if (count >= 4) {
            return true;
        }

        // Check diagonal (up-left to down-right)
        count = 1;
        for (int i = column - 1, j = row - 1; i >= 0 && j >= 0 && gameBoard[i][j] == gameBoard[column][row]; i--, j--) {
            count++;
        }
        for (int i = column + 1, j = row + 1; i < COLS && j < ROWS && gameBoard[i][j] == gameBoard[column][row]; i++, j++) {
            count++;
        }
        if (count >= 4) {
            return true;
        }

        // Check diagonal (up-right to down-left)
        count = 1;
        for (int i = column + 1, j = row - 1; i < COLS && j >= 0 && gameBoard[i][j] == gameBoard[column][row]; i++, j--) {
            count++;
        }
        for (int i = column - 1, j = row + 1; i >= 0 && j < ROWS && gameBoard[i][j] == gameBoard[column][row]; i--, j++) {
            count++;
        }
        return count >= 4;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < COLS; i++) {
            if (gameBoard[i][0] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private void printBoard() {
        System.out.println("-----------------");
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLS; j++) {
                System.out.print("|");
                if (gameBoard[j][i] == EMPTY) {
                    System.out.print(" ");
                } else if (gameBoard[j][i] == PLAYER_1) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println("|");
        }
        System.out.println("-----------------");
    }
}