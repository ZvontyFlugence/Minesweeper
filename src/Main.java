import java.util.Arrays;
import java.util.Scanner;

public class Main {

    //Returns false if position has a mine, true otherwise
    public static boolean select(Board board, Position pos) {
        Object[][] map = board.getBoard();
        if(("" + map[pos.getRow()][pos.getColumn()].getClass()).equals("class Mine")) {
            return false;
        } else {
            return true;
        }
    }

    //Returns true if mark is correct, false otherwise;
    public static boolean mark(Board  board, Object[][] map, Position pos) {
        map[pos.getRow()][pos.getColumn()] = "X";
        Object[][] grid = board.getBoard();
        if(("" + grid[pos.getRow()][pos.getColumn()].getClass()).equals("class Mine")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Board board = new Board(8);
        //System.out.println(board);

        boolean isAlive = true;
        Scanner scan = new Scanner(System.in);

        Object[][] map = new Object[board.size()][board.size()];
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                map[i][j] = "?";
            }
        }

        for(Object[] rows : map) {
            System.out.println(Arrays.toString(rows));
        }

        while(isAlive) {
            System.out.println("Enter an index position (row col):");
            int row = scan.nextInt();
            int col = scan.nextInt();
            scan.nextLine();
            System.out.println("Enter 'select' or 'mark':");
            String choice = scan.nextLine();
            int correct = 0;
            if(choice.equals("select")) {
                boolean guess = select(board, new Position(row, col));
                if(guess) {
                    map[row][col] = board.get(new Position(row, col));
                    for(Object[] rows : map) {
                        System.out.println(Arrays.toString(rows));
                    }
                } else {
                    System.out.println("Sorry, that was a mine!");
                    isAlive = guess;
                }
            } else if(choice.equals("mark")) {
                boolean isCorrect = mark(board, map, new Position(row, col));

                if(isCorrect) correct++;
                if(correct == (int)(Math.pow(board.size(), 2) * 0.16)) {
                    System.out.println("Congrats you won!");
                    isAlive = false;
                }

                for(Object[] rows : map) {
                    System.out.println(Arrays.toString(rows));
                }
            } else {
                System.out.println("That is not a valid choice");
            }
        }
    }
}