import java.util.Arrays;

import static java.util.Arrays.deepToString;

public class Board {
    private static Object[][] map;

    public Board(int size) {
        map = new Object[size][size];
        randomize();
    }

    private void randomize() {
        int limit = (int) (Math.pow(map.length, 2) * 0.16);
        int mines = 0;

        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                map[i][j] = new Integer(0);
            }
        }

        while(mines < limit) {
            int row = (int)(Math.random() * map.length);
            int col = (int)(Math.random() * map.length);
            if(!isMine(map[row][col])) {
                map[row][col] = new Mine(true, new Position(row, col));
                mines++;
            }
        }
        calculateValues();
    }

    private void calculateValues() {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                int sum;
                if(!isMine(map[i][j])) {
                    sum = findMines(i, j);
                    map[i][j] = new Integer(sum);
                }
            }
        }
    }

    private int findMines(int r, int c) {
        int sum = 0;
        if(r == 0 && c == 0) {
            if(isMine(map[r+1][c])) sum++; //Bottom
            if(isMine(map[r+1][c+1])) sum++; //Bottom Right
            if(isMine(map[r][c+1])) sum++; //Right
        } else if(r == 0 && c == map[r].length-1) {
            if(isMine(map[r][c-1])) sum++; //Left
            if(isMine(map[r+1][c-1])) sum++; //Bottom Left
            if(isMine(map[r+1][c])) sum++; //Bottom
        } else if(r == map.length-1 && c == 0) {
            if(isMine(map[r-1][c])) sum++; //Top
            if(isMine(map[r-1][c+1])) sum++; //Top Right
            if(isMine(map[r][c+1])) sum++; //Right
        } else if(r == map.length-1 && c == map[r].length-1) {
            if(isMine(map[r-1][c])) sum++; //Top
            if(isMine(map[r-1][c-1])) sum++; //Top Left
            if(isMine(map[r][c-1])) sum++; //Left
        } else if(r == 0 && c > 0 && c < map[r].length-1) {
            if(isMine(map[r][c-1])) sum++; //Left
            if(isMine(map[r+1][c-1])) sum++; //Bottom Left
            if(isMine(map[r+1][c])) sum++; //Bottom
            if(isMine(map[r+1][c+1])) sum++; //Bottom Right
            if(isMine(map[r][c+1])) sum++; //Right
        } else if(c == 0 && r > 0 && r < map.length-1) {
            if(isMine(map[r+1][c])) sum++; //Bottom
            if(isMine(map[r+1][c+1])) sum++; //Bottom Right
            if(isMine(map[r][c+1])) sum++; //Right
            if(isMine(map[r-1][c+1])) sum++; //Top Right
            if(isMine(map[r-1][c])) sum++; //Top
        } else if(r == map.length-1 && c > 0 && c < map[r].length-1) {
            if(isMine(map[r][c-1])) sum++; //Left
            if(isMine(map[r-1][c-1])) sum++; //Top Left
            if(isMine(map[r-1][c])) sum++; //Top
            if(isMine(map[r-1][c+1])) sum++; //Top Right
            if(isMine(map[r][c+1])) sum++; //Right
        } else if(c == map[r].length-1 && r > 0) {
            if(isMine(map[r+1][c])) sum++; //Bottom
            if(isMine(map[r+1][c-1])) sum++; //Bottom Left
            if(isMine(map[r][c-1])) sum++; //Left
            if(isMine(map[r-1][c-1])) sum++; //Top Left
            if(isMine(map[r-1][c])) sum++; //Top
        } else if(r > 0 && r < map.length-1 && c > 0 && c < map[r].length-1){
            if(isMine(map[r-1][c])) sum++; //Top
            if(isMine(map[r-1][c-1])) sum++; //Top Left
            if(isMine(map[r][c-1])) sum++; //Left
            if(isMine(map[r+1][c-1])) sum++; //Bottom Left
            if(isMine(map[r+1][c])) sum++; //Bottom
            if(isMine(map[r+1][c+1])) sum++; //Bottom Right
            if(isMine(map[r][c+1])) sum++; //Right
            if(isMine(map[r-1][c+1])) sum++; //Top Right
        }
        return sum;
    }

    public boolean isMine(Object object) {
        return ("" + object.getClass()).equals("class Mine");
    }

    public int size() {
        return map.length;
    }

    public Object[][] getBoard() {
        return map;
    }

    public Object get(Position pos) {
        return map[pos.getRow()][pos.getColumn()];
    }

    public String toString() {
        String res = "";
        for(Object[] rows : map) {
            res += Arrays.toString(rows) + "\n";
        }
        return res;
        //return deepToString(map);
    }
}