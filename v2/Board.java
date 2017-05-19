import java.util.Arrays;

public class Board {
    private static Item[][] map;

    public Board(int size) {
        map = new Item[size][size];
    }

    public static void firstGen(Position pos) {
        int row = pos.getRow();
        int col = pos.getCol();
        int mines = 0;
        int limit = (int)(Math.pow(map.length, 2)*0.16);

        if(row == 0 && col != 0 && col != map.length-1) {
            Item[] list = { map[row][col-1], map[row+1][col-1], map[row+1][col], map[row+1][col+1], map[row][col+1] };
        } else if(row == map.length-1 && col != 0 && col != map.length-1) {
            Item[] list = { map[row][col-1], map[row-1][col-1], map[row-1][col], map[row-1][col+1], map[row][col+1] };
        } else if(row == 0 && col == 0) {
            Item[] list = { map[row+1][col], map[row+1][col+1], map[row][col+1] };
        } else if(row == map.length-1 && col == map.length-1) {
            Item[] list = { map[row][col-1], map[row-1][col-1], map[row-1][col] };
        } else if(col == 0 && row != 0 && row != map.length-1) {
            Item[] list = { map[row-1][col], map[row-1][col+1], map[row][col+1], map[row+1][col+1], map[row+1][col] };
        } else if(col == map.length-1 && row != 0 && row != map.length-1) {
            Item[] list = { map[][], map[][], map[][], map[][], map[][] };
        } else if(col == 0 && row == map.length-1) {
            Item[] list = { map[][], map[][], map[][] };
        } else if(row == 0 && col == map.length-1) {
            Item[] list = { map[row][col-1], map[row+1][col-1], map[row+1][col] };
        } else {
            Item[] list = { map[row-1][col-1], map[row-1][col], map[row-1][col+1],
                    map[row][col-1], map[row][col], map[row][col+1],
                    map[row+1][col-1], map[row+1][col], map[row+1][col+1] };
        }

        int r = (int)(Math.random()*map.length);
        int c = (int)(Math.random()*map.length);

        for(int i=0; i<map.length; i++) {
            for(int k=0; k<map.length; k++) {

            }
        }
    }
}