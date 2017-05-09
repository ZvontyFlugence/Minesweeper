import java.util.Arrays.deepToString;

public class Board {
    private Object[][] map;

    public Board(int size) {
        map = new Object[size][size];
        randomize();
    }

    private void randomize() {
        int limit = (int) Math.floor(Math.pow(map.length, 2) * 0.16);
        int mines = 0;

        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                map[i][j] = new Integer(0);
            }
        }

        while(mines < limit) {
            int row = (int)(Math.random() * map.length);
            int col = (int)(Math.random() * map.length);

            if(!("" + map[row][col].getClass()).equals("class Mine")) {
                map[row][col] = new Mine();
                mines++;
            }
        }
        calculateValues();
    }

    private void calculateValues() {
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                int sum;
                if(!("" + map[row][col].getClass()).equals("class Mine")) {
                    sum = findMines();
                }
            }
        }
    }

    private int findMines() {
        int sum = 0;
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                if(i == 0 && j == 0) {
                    if(isMine(map[i+1][j]) == true) sum++;
                    if(isMine(map[i+1][j+1]) == true) sum++;
                    if(isMine(map[i][j+1]) == true) sum++;
                } else if(i == map.length-1 && j == map[i].length-1) {
                    if(isMine(map[i-1][j]) == true) sum++;
                    if(isMine(map[i-1][j-1]) == true) sum++;
                    if(isMine(map[i][j-1]) == true) sum++;
                }
            }
        }
    }

    public boolean isMine(Object object) {
        return ("" + object.getClass()).equals("class Mine");
    }
}