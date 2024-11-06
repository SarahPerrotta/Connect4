import java.util.Scanner;
import java.io.*;

class Disc {
    private String symbol;

    public Disc(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

class Player {
    private String name;
    private String symbol;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public int takeTurn(Scanner scanner) {
        System.out.print(name + " (" + symbol + "), enter column (0-6): ");
        return scanner.nextInt();
    }

    public String getSymbol() {
        return symbol;
    }
}

class Grid implements Serializable {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private Disc[][] board;

    public Grid() {
        board = new Disc[COLS][ROWS];
    }

    public int dropDisc(Player player, int column) {
        int row = getAvailableRow(column);
        if (row == -1) {
            System.out.println("Column is full. Please choose another.");
            return -1;
        }
        board[column][row] = new Disc(player.getSymbol());
        return row;
    }

    public int getAvailableRow(int column) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[column][i] == null) {
                return i;
            }
        }
        return -1;
    }

    public boolean checkWin(int column, int row) {
        return checkDirection(column, row, 1, 0) || // Horizontal
               checkDirection(column, row, 0, 1) || // Vertical
               checkDirection(column, row, 1, 1) || // Diagonal down-right
               checkDirection(column, row, 1, -1);  // Diagonal down-left
    }

    private boolean checkDirection(int column, int row, int deltaX, int deltaY) {
        int count = 1;
        String playerSymbol = board[column][row].getSymbol();
        int x, y;

        for (x = column + deltaX, y = row + deltaY; x >= 0 && x < COLS && y >= 0 && y < ROWS && board[x][y] != null && board[x][y].getSymbol().equals(playerSymbol); x += deltaX, y += deltaY) {
            count++;
        }

        for (x = column - deltaX, y = row - deltaY; x >= 0 && x < COLS && y >= 0 && y < ROWS && board[x][y] != null && board[x][y].getSymbol().equals(playerSymbol); x -= deltaX, y -= deltaY) {
            count++;
        }

        return count >= 4;
    }

    public boolean isFull() {
        for (int i = 0; i < COLS; i++) {
            if (board[i][0] == null) {
                return false;
            }
        }
        return true;
    }

    public void saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ConnectFourSave.dat"))) {
            out.writeObject(this);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game.");
        }
    }

    public static Grid loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("ConnectFourSave.dat"))) {
            System.out.println("Game loaded successfully.");
            return (Grid) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game.");
            return new Grid();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------\n");
        for (int i = 0; i < ROWS; i++) {  // Start from the bottom row
            for (int j = 0; j < COLS; j++) {
                sb.append("| ");
                if (board[j][i] == null) {
                    sb.append(" ");
                } else {
                    sb.append(board[j][i].getSymbol());
                }
                sb.append(" ");
            }
            sb.append("|\n");
        }
        sb.append("-----------------------------\n");
        return sb.toString();
    }
    
}

public class ConnectFour {
    public static void main(String[] args) {
        Grid grid;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Load saved game? (y/n): ");
        String loadChoice = scanner.nextLine();
        if (loadChoice.equalsIgnoreCase("y")) {
            grid = Grid.loadGame();
        } else {
            grid = new Grid();
        }

        // Initialise players
        System.out.print("Enter name for Player 1: ");
        String name1 = scanner.nextLine();
        Player player1 = new Player(name1, "X");

        System.out.print("Enter name for Player 2: ");
        String name2 = scanner.nextLine();
        Player player2 = new Player(name2, "O");

        Player currentPlayer = player1;
        boolean gameContinues = true;

        while (gameContinues) {
            System.out.println(grid);
            System.out.print("Enter 's' to save, or any other key to play: ");
            String choice = scanner.nextLine();

            if (choice.equals("s")) {
                grid.saveGame();
            } else {
                int column;
                while (true) {
                    column = currentPlayer.takeTurn(scanner);
                    if (column >= 0 && column < 7) {
                        break;
                    } else {
                        System.out.println("Invalid column. Please enter a number between 0 and 6.");
                    }
                }
                
                int row = grid.dropDisc(currentPlayer, column);
                if (row != -1) {
                    if (grid.checkWin(column, row)) {
                        System.out.println(grid);
                        System.out.println(currentPlayer.getSymbol() + " wins!");
                        gameContinues = false;
                    } else if (grid.isFull()) {
                        System.out.println(grid);
                        System.out.println("It's a tie!");
                        gameContinues = false;
                    } else {
                        currentPlayer = (currentPlayer == player1) ? player2 : player1;
                    }
                }
            }
        }
        scanner.close();
    }
}
