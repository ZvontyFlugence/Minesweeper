//This entire class is essentially one large method that sets up the board and the map for the game
//The methods have been broken down to make modifications easier 

import java.util.ArrayList;
import java.util.Arrays;
//import static java.util.Arrays.deepToString;

public class Board {
    private static BoardItem[][] map;

    public Board(int size, int r, int c) {
        map = new BoardItem[size][size];
        randomize(r, c);
    }

    private void randomize(int r, int c) {
        int limit = (int) (Math.pow(map.length, 2) * 0.16);
        int mines = 0;

        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                map[i][j] = new Count(0, new Position(i, j));
            }
        }
        
        ArrayList<Position> safeSpaces = new ArrayList<Position>();
        safeSpaces.add(new Position(r, c));
        if(r == 0 && c == 0) {
            safeSpaces.add(map[r+1][c].getPos()); //Bottom
            safeSpaces.add(map[r+1][c+1].getPos()); //Bottom Right
            safeSpaces.add(map[r][c+1].getPos()); //Right
        } else if(r == 0 && c == map[r].length-1) {
            safeSpaces.add(map[r][c-1].getPos()); //Left
            safeSpaces.add(map[r+1][c-1].getPos()); //Bottom Left
            safeSpaces.add(map[r+1][c].getPos()); //Bottom
        } else if(r == map.length-1 && c == 0) {
            safeSpaces.add(map[r-1][c].getPos()); //Top
            safeSpaces.add(map[r-1][c+1].getPos()); //Top Right
            safeSpaces.add(map[r][c+1].getPos()); //Right
        } else if(r == map.length-1 && c == map[r].length-1) {
            safeSpaces.add(map[r-1][c].getPos()); //Top
            safeSpaces.add(map[r-1][c-1].getPos()); //Top Left
            safeSpaces.add(map[r][c-1].getPos()); //Left
        } else if(r == 0 && c > 0 && c < map[r].length-1) {
            safeSpaces.add(map[r][c-1].getPos()); //Left
            safeSpaces.add(map[r+1][c-1].getPos()); //Bottom Left
            safeSpaces.add(map[r+1][c].getPos()); //Bottom
            safeSpaces.add(map[r+1][c+1].getPos()); //Bottom Right
            safeSpaces.add(map[r][c+1].getPos()); //Right
        } else if(c == 0 && r > 0 && r < map.length-1) {
            safeSpaces.add(map[r+1][c].getPos()); //Bottom
            safeSpaces.add(map[r+1][c+1].getPos()); //Bottom Right
            safeSpaces.add(map[r][c+1].getPos()); //Right
            safeSpaces.add(map[r-1][c+1].getPos()); //Top Right
            safeSpaces.add(map[r-1][c].getPos()); //Top
        } else if(r == map.length-1 && c > 0 && c < map[r].length-1) {
            safeSpaces.add(map[r][c-1].getPos()); //Left
            safeSpaces.add(map[r-1][c-1].getPos()); //Top Left
            safeSpaces.add(map[r-1][c].getPos()); //Top
            safeSpaces.add(map[r-1][c+1].getPos()); //Top Right
            safeSpaces.add(map[r][c+1].getPos()); //Right
        } else if(c == map[r].length-1 && r > 0) {
            safeSpaces.add(map[r+1][c].getPos()); //Bottom
            safeSpaces.add(map[r+1][c-1].getPos()); //Bottom Left
            safeSpaces.add(map[r][c-1].getPos()); //Left
            safeSpaces.add(map[r-1][c-1].getPos()); //Top Left
            safeSpaces.add(map[r-1][c].getPos()); //Top
        } else if(r > 0 && r < map.length-1 && c > 0 && c < map[r].length-1){
            safeSpaces.add(map[r-1][c].getPos()); //Top
            safeSpaces.add(map[r-1][c-1].getPos()); //Top Left
            safeSpaces.add(map[r][c-1].getPos()); //Left
            safeSpaces.add(map[r+1][c-1].getPos()); //Bottom Left
            safeSpaces.add(map[r+1][c].getPos()); //Bottom
            safeSpaces.add(map[r+1][c+1].getPos()); //Bottom Right
            safeSpaces.add(map[r][c+1].getPos()); //Right
            safeSpaces.add(map[r-1][c+1].getPos()); //Top Right
        
        while(mines < limit) {
            int row = (int)(Math.random() * map.length);
            int col = (int)(Math.random() * map.length);
            if(!map[row][col].isMine() && !contains(row, col, safeSpaces)) {
                map[row][col] = new Mine(true, new Position(row, col));
                mines++;
            }
        }
        calculateValues();
    }
  }
    
    private boolean contains(int r, int c, ArrayList<Position> list) {
    	for (Position pos1: list)
    		if (equals(new Position(r, c), pos1))
    			return true;
    	return false;
    }
    
    private boolean equals(Position pos1, Position pos2) {
    	return pos1.getRow()==pos2.getColumn() && pos1.getColumn()==pos2.getColumn();
    }

    
    private void calculateValues() {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                int sum;
                if(!map[i][j].isMine()) {
                    sum = findMines(i, j);
                    map[i][j] = new Count(sum, new Position(i, j));
                }
            }
        }
    }

    private int findMines(int r, int c) {
        int sum = 0;

        if(r == 0 && c == 0) {
            if(map[r+1][c].isMine()) sum++; //Bottom
            if(map[r+1][c+1].isMine()) sum++; //Bottom Right
            if(map[r][c+1].isMine()) sum++; //Right
        } else if(r == 0 && c == map[r].length-1) {
            if(map[r][c-1].isMine()) sum++; //Left
            if(map[r+1][c-1].isMine()) sum++; //Bottom Left
            if(map[r+1][c].isMine()) sum++; //Bottom
        } else if(r == map.length-1 && c == 0) {
            if(map[r-1][c].isMine()) sum++; //Top
            if(map[r-1][c+1].isMine()) sum++; //Top Right
            if(map[r][c+1].isMine()) sum++; //Right
        } else if(r == map.length-1 && c == map[r].length-1) {
            if(map[r-1][c].isMine()) sum++; //Top
            if(map[r-1][c-1].isMine()) sum++; //Top Left
            if(map[r][c-1].isMine()) sum++; //Left
        } else if(r == 0 && c > 0 && c < map[r].length-1) {
            if(map[r][c-1].isMine()) sum++; //Left
            if(map[r+1][c-1].isMine()) sum++; //Bottom Left
            if(map[r+1][c].isMine()) sum++; //Bottom
            if(map[r+1][c+1].isMine()) sum++; //Bottom Right
            if(map[r][c+1].isMine()) sum++; //Right
        } else if(c == 0 && r > 0 && r < map.length-1) {
            if(map[r+1][c].isMine()) sum++; //Bottom
            if(map[r+1][c+1].isMine()) sum++; //Bottom Right
            if(map[r][c+1].isMine()) sum++; //Right
            if(map[r-1][c+1].isMine()) sum++; //Top Right
            if(map[r-1][c].isMine()) sum++; //Top
        } else if(r == map.length-1 && c > 0 && c < map[r].length-1) {
            if(map[r][c-1].isMine()) sum++; //Left
            if(map[r-1][c-1].isMine()) sum++; //Top Left
            if(map[r-1][c].isMine()) sum++; //Top
            if(map[r-1][c+1].isMine()) sum++; //Top Right
            if(map[r][c+1].isMine()) sum++; //Right
        } else if(c == map[r].length-1 && r > 0) {
            if(map[r+1][c].isMine()) sum++; //Bottom
            if(map[r+1][c-1].isMine()) sum++; //Bottom Left
            if(map[r][c-1].isMine()) sum++; //Left
            if(map[r-1][c-1].isMine()) sum++; //Top Left
            if(map[r-1][c].isMine()) sum++; //Top
        } else if(r > 0 && r < map.length-1 && c > 0 && c < map[r].length-1){
            if(map[r-1][c].isMine()) sum++; //Top
            if(map[r-1][c-1].isMine()) sum++; //Top Left
            if(map[r][c-1].isMine()) sum++; //Left
            if(map[r+1][c-1].isMine()) sum++; //Bottom Left
            if(map[r+1][c].isMine()) sum++; //Bottom
            if(map[r+1][c+1].isMine()) sum++; //Bottom Right
            if(map[r][c+1].isMine()) sum++; //Right
            if(map[r-1][c+1].isMine()) sum++; //Top Right
        }
        return sum;
    }

    public int size() {
        return map.length;
    }

    public BoardItem[][] getBoard() {
        return map;
    }

    public BoardItem get(Position pos) {
        return map[pos.getRow()][pos.getColumn()];
    }

    public String toString() {
        String res = "";
        for(Object[] rows : map) {
            res += Arrays.toString(rows) + "\n";
        }
        return res;
    }
}