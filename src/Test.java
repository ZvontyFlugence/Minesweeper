import java.util.ArrayList;

public class Test {
    private Object[][] map;

    public Test(int size) {
      this.map = new Object[size][size];
    }

    public void firstGen(Position pos) {
      int r = pos.getRow();
      int c = pos.getColumn();
      ArrayList mapList = new ArrayList<Integer>();
      map[r-1][c-1] = new Integer(0); //Top Left
      mapList.add(map[r-1][c-1]);
      map[r-1][c] = new Integer(0); //Top
      mapList.add(map[r-1][c]);
      map[r-1][c+1] = new Integer(0); //Top Right
      mapList.add(map[r-1][c+1]);
      map[r][c+1] = new Integer(0); //Right
      mapList.add(map[r][c+1]);
      map[r+1][c+1] = new Integer(0); //Bottom Right
      mapList.add(map[r+1][c+1]);
      map[r+1][c] = new Integer(0); //Bottom
      mapList.add(map[r+1][c]);
      map[r+1][c-1] = new Integer(0); //Bottom Left
      mapList.add(map[r+1][c-1]);
      map[r][c-1] = new Integer(0); //Left
      mapList.add(map[r][c-1]);

      int row = (int)(Math.random()*map.length);
      int col = (int)(Math.random()*map.length);
      int mines = 0;
      int limit = (int)(Math.pow(map.length, 2) * 0.16);

      while(row == pos.getRow() && col == pos.getColumn()) {
        row = (int)(Math.random()*map.length);
        col = (int)(Math.random()*map.length);
      }
      while(mines < limit) {
        if(!mapList.contains(map[row][col])) {
          map[row][col] = new Mine(true, new Position(row, col));
        }
      }
    }

}
