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
        boolean isAlive = true;
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter your desired board size: ");
        int size = scan.nextInt();
        Board board = new Board(size);


        Object[][] map = new Object[board.size()][board.size()];
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                map[i][j] = "?";
            }
        }
        int counter = 0;
        for (int i=0; i<size; i++)
            System.out.print("  " + i);
        System.out.println("");
        for(Object[] rows : map) {
            System.out.println("" + counter + Arrays.toString(rows));
            counter++;
        }

        int correct = 0;
        while(isAlive) {
            System.out.println("Enter an index position (row col):");
            int row = scan.nextInt();
            int col = scan.nextInt();
            scan.nextLine();
            System.out.println("Enter 'select' or 'mark':");
            String choice = scan.nextLine();
            if(choice.equals("select")) {
                boolean guess = select(board, new Position(row, col));
                if(guess) {
                    map[row][col] = board.get(new Position(row, col));

                    if(map[row][col].equals(0)) {
                        if(row == 0 && col == 0) {
                            if(map[row+1][col].equals(0)) //Bottom
                                map[row+1][col] = board.get(new Position(row+1, col));
                            if(map[row+1][col+1].equals(0)) //Bottom Right
                                map[row+1][col+1] = board.get(new Position(row+1, col+1));
                            if(map[row][col+1].equals(0)) //Right
                                map[row][col+1] = board.get(new Position(row, col+1));
                        } else if(row == 0 && col == map[row].length-1) {
                            if(map[row][col-1].equals(0)) //Left
                                map[row][col-1] = board.get(new Position(row, col-1));
                            if(map[row+1][col-1].equals(0)) //Bottom Left
                                map[row+1][col-1] = board.get(new Position(row+1, col-1));
                            if(map[row+1][col].equals(0)) //Bottom
                                map[row+1][col] = board.get(new Position(row+1, col));
                        } else if(row == map.length-1 && col == 0) {
                            if(map[row-1][col].equals(0)) //Top
                                map[row-1][col] = board.get(new Position(row-1, col));
                            if(map[row-1][col+1].equals(0)) //Top Right
                                map[row-1][col+1] = board.get(new Position(row-1, col+1));
                            if(map[row][col+1].equals(0)) //Right
                                map[row][col+1] = board.get(new Position(row, col+1));
                        } else if(row == map.length-1 && col == map[row].length-1) {
                            if(map[row-1][col].equals(0)) //Top
                                map[row-1][col] = board.get(new Position(row-1, col));
                            if(map[row-1][col-1].equals(0)) //Top Left
                                map[row-1][col-1] = board.get(new Position(row-1, col-1));
                            if(map[row][col-1].equals(0)) //Left
                                map[row][col-1] = board.get(new Position(row, col-1));
                        } else if(row == 0 && col > 0 && col < map[row].length-1) {
                            if(map[row][col-1].equals(0)) //Left
                                map[row][col-1] = board.get(new Position(row, col-1));
                            if(map[row+1][col-1].equals(0)) //Bottom Left
                                map[row+1][col-1] = board.get(new Position(row+1, col-1));
                            if(map[row+1][col].equals(0)) //Bottom\
                                map[row+1][col] = board.get(new Position(row+1, col));
                            if(map[row+1][col+1].equals(0)) //Bottom Right
                                map[row+1][col+1] = board.get(new Position(row+1, col+1));
                            if(map[row][col+1].equals(0)) //Right
                                map[row][col+1] = board.get(new Position(row, col+1));
                        } else if(col == 0 && row > 0 && row < map.length-1) {
                            if(map[row+1][col].equals(0)) //Bottom
                                map[row+1][col] = board.get(new Position(row+1, col));
                            if(map[row+1][col+1].equals(0)) //Bottom Right
                                map[row+1][col+1] = board.get(new Position(row+1, col+1));
                            if(map[row][col+1].equals(0)) //Right
                                map[row][col+1] = board.get(new Position(row, col+1));
                            if(map[row-1][col+1].equals(0)) //Top Right
                                map[row-1][col+1] = board.get(new Position(row-1, col+1));
                            if(map[row-1][col].equals(0)) //Top
                                map[row-1][col] = board.get(new Position(row-1, col));
                        } else if(row == map.length-1 && col > 0 && col < map[row].length-1) {
                            if(map[row][col-1].equals(0)) //Left
                                map[row][col-1] = board.get(new Position(row, col-1));
                            if(map[row-1][col-1].equals(0)) //Top Left
                                map[row-1][col-1] = board.get(new Position(row-1, col-1));
                            if(map[row-1][col].equals(0)) //Top
                                map[row-1][col] = board.get(new Position(row-1, col));
                            if(map[row-1][col+1].equals(0)) //Top Right
                                map[row-1][col+1] = board.get(new Position(row-1, col+1));
                            if(map[row][col+1].equals(0)) //Right
                                map[row][col+1] = board.get(new Position(row, col+1));
                        } else if(col == map[row].length-1 && row > 0) {
                            if(map[row+1][col].equals(0)) //Bottom
                                map[row+1][col] = board.get(new Position(row+1, col));
                            if(map[row+1][col-1].equals(0)) //Bottom Left
                                map[row+1][col-1] = board.get(new Position(row+1, col-1));
                            if(map[row][col-1].equals(0)) //Left
                                map[row][col-1] = board.get(new Position(row, col-1));
                            if(map[row-1][col-1].equals(0)) //Top Left
                                map[row-1][col-1] = board.get(new Position(row-1, col));
                            if(map[row-1][col].equals(0)) //Top
                                map[row-1][col] = board.get(new Position(row-1, col));
                        } else {
                            if(map[row-1][col].equals(0)) //Top
                                map[row-1][col] = board.get(new Position(row-1, col));
                            if(map[row-1][col-1].equals(0)) //Top Left
                                map[row-1][col-1] = board.get(new Position(row-1, col));
                            if(map[row][col-1].equals(0)) //Left
                                map[row][col-1] = board.get(new Position(row, col-1));
                            if(map[row+1][col-1].equals(0)) //Bottom Left
                                map[row+1][col-1] = board.get(new Position(row+1, col-1));
                            if(map[row+1][col].equals(0)) //Bottom
                                map[row+1][col] = board.get(new Position(row+1, col));
                            if(map[row+1][col+1].equals(0)) //Bottom Right
                                map[row+1][col+1] = board.get(new Position(row+1, col+1));
                            if(map[row][col+1].equals(0)) //Right
                                map[row][col+1] = board.get(new Position(row, col+1));
                            if(map[row-1][col+1].equals(0)) //Top Right
                                map[row-1][col+1] = board.get(new Position(row-1, col+1));
                        }
                    }

                    if(!guess) correct++;
                    if(correct == (int)(Math.pow(board.size(), 2) * 0.16)) {
                        System.out.println("Congrats you won!");
                        isAlive = false;
                        for (int i=0; i<size; i++)
                            System.out.print("  " + i);
                        System.out.println("");
                        counter = 0;
                        for(Object[] rows : map) {
                            System.out.println("" + counter + Arrays.toString(rows));
                            counter++;
                        }
                        break;
                    }
                    for (int i=0; i<size; i++)
                        System.out.print("  " + i);
                    System.out.println("");
                    counter = 0;
                    for(Object[] rows : map) {
                        System.out.println("" + counter + Arrays.toString(rows));
                        counter++;
                    }

                } else {
                    counter = 0;
                    for (int i=0; i<size; i++)
                        System.out.print("  " + i);
                    System.out.println("");
                    for(Object[] rows : board.getBoard()) {
                        System.out.println("" + counter + Arrays.toString(rows));
                        counter++;
                    }
                    System.out.println("Sorry, that was a mine!");
                    isAlive = guess;
                }
            }
            else if(choice.equals("mark")) {
                boolean isCorrect = mark(board, map, new Position(row, col));

                if(isCorrect) correct++;
                if(correct == (int)(Math.pow(board.size(), 2) * 0.16)) {
                    System.out.println("Congrats you won!");
                    isAlive = false;
                }
                counter=0;
                for (int i=0; i<size; i++)
                    System.out.print("  " + i);
                System.out.println("");
                for(Object[] rows : map) {
                    System.out.println("" + counter + Arrays.toString(rows));
                    counter++;
                }
            }
            else {
                System.out.println("That is not a valid choice");
            }
        }
        scan.close();
    }
}