import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    private BoardItem[][] map;

    public Test(int size) {
      this.map = new BoardItem[size][size];
    }

    public void firstGen(Position pos) {
        int r = pos.getRow();
        int c = pos.getColumn();
        //List of all invalid positions for mines
        ArrayList mapList = new ArrayList<BoardItem>();
        map[r-1][c-1] = new Count(0, new Position(r-1, c-1)); //Top Left
        mapList.add(map[r-1][c-1]);
        map[r-1][c] = new Count(0, new Position(r-1, c)); //Top
        mapList.add(map[r-1][c]);
        map[r-1][c+1] = new Count(0, new Position(r-1, c+1)); //Top Right
        mapList.add(map[r-1][c+1]);
        map[r][c+1] = new Count(0, new Position(r, c+1)); //Right
        mapList.add(map[r][c+1]);
        map[r+1][c+1] = new Count(0, new Position(r+1, c+1)); //Bottom Right
        mapList.add(map[r+1][c+1]);
        map[r+1][c] = new Count(0, new Position(r+1, c)); //Bottom
        mapList.add(map[r+1][c]);
        map[r+1][c-1] = new Count(0, new Position(r+1, c-1)); //Bottom Left
        mapList.add(map[r+1][c-1]);
        map[r][c-1] = new Count(0, new Position(r, c-1)); //Left
        mapList.add(map[r][c-1]);

        int row = (int)(Math.random()*map.length);
        int col = (int)(Math.random()*map.length);
        int mines = 0;
        int limit = (int)(Math.pow(map.length, 2) * 0.16);
        //Continuously generate positions until it is not equal to the first clicked point
        while(row == pos.getRow() && col == pos.getColumn()) {
            row = (int)(Math.random()*map.length);
            col = (int)(Math.random()*map.length);
        }
        //Set mines at random valid positions
        while(mines < limit) {
            //Making sure none of the 8 invalid positions are used when generating mines
            if(!mapList.contains(map[row][col])) {
              map[row][col] = new Mine(true, new Position(row, col));
            }
        }

        for(BoardItem[] itemRow : map) {
            for(BoardItem item : itemRow) {
                if(!item.isMine()) {
                    item = new Count(findMines(item.getPos().getRow(),
                            item.getPos().getColumn()), item.getPos());
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
        for (Object[] rows : map) {
            res += Arrays.toString(rows) + "\n";
        }
        return res;
    }
}