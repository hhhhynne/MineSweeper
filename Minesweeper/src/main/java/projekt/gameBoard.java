package projekt;
import java.util.Random;

public class gameBoard implements board{
    private Spot[][] board;
    private int maxBomb;

    public gameBoard() {
        board = new Spot[11][11]; // lager et 11x11 brett, men spillet vil bare være 10x10. Dette er for å slippe masse if setninger i utfyllingen av brettet.
        maxBomb = 10;
    }

    public void fillBoard() { // fyller brettet med spotter
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++){
                board[i][j] = new Spot(i,j);
            }
        }

    }

    public Spot[][] getBoard() {
        return board;
    }

    public int getMaxBomb() {
        return maxBomb;
    }

    public void showgameBoard(){ //printer brettet i terminalen. For å sjekke at ting går som det skal. 
        for(int i = 1; i < 10; i++){
            for(int j = 1; j < 10; j++){
                System.out.print(board[i][j].getStringSpot() + "|");
            }
            System.out.println();
        }
    }

    public void makeBombs() { //plasserer bombene på tilfedlige koordinater på brettet. +1 er fordi brettet er fra 1-10, og ikke 0-11.
        int bombs = 0;
        Random random = new Random();
        while (bombs < maxBomb) {
            int random1 = random.nextInt(9);
            int random2 = random.nextInt(9);
            board[random1+1][random2+1] = new bomb(random1+1, random2+1);
            bombs ++;
        }
    }

    public void getBombsNearby() { //sjekker om naboene er av typen bombe. Teller antall bomber i naborutene.
        for(int row = 1; row < 10; row++){ 
            for(int col = 1; col < 10; col++){
                int x = row;
                int y = col;
                if (!(board[row][col] instanceof bomb)){
                    for (int i = x - 1; i < (x + 2); i++) {
                        for (int j = y -1; j < (y + 2); j++) {
                            if (board[i][j] instanceof bomb) {
                                board[row][col].addBomb();
                            }
                        }

                }
                if (board[row][col].getBombsNearby() != 0 && !(board[row][col] instanceof bomb)) {
                    board[row][col] = new neighbour(row, col, String.valueOf(board[row][col].getBombsNearby()) );
                    //spots med x antall bomber som naboer
                }
                else if (board[row][col].getBombsNearby() == 0 && !(board[row][col] instanceof bomb)) {
                    board[row][col] = new safeSpot(row, col);
                    //spots uten bomber som nabo
                }    
                }
        
    
            }
        
        }
    }

    public void safeNeighbours() { //gir de trygge rutene(safeSpot - de uten bomber som nabo) sine naboer. Dette er for å legge til en funksjon i spillet.
        for(int row = 1; row < 10; row++){ 
            for(int col = 1; col < 10; col++){
                int x = row;
                int y = col;
                if (board[row][col] instanceof safeSpot) {
                    for (int i = x - 1; i < (x + 2); i++) {
                        for (int j = y -1; j < (y + 2); j++) {
                            if (!(board[i][j] instanceof bomb) && board[i][j] != board[row][col]) {
                                safeSpot safe = (safeSpot) board[row][col];
                                safe.addNeighbours(board[i][j]);
                            }
                        }

                    }
                }
            }
        }
    }


    public gameBoard generateNewBoard() { //lager et nytt brett
        gameBoard ny = new gameBoard();
        ny.fillBoard();
        ny.makeBombs();
        ny.getBombsNearby();
        ny.safeNeighbours();
        ny.showgameBoard();
        return ny;
    }

    public boolean checklose(int x, int y){//sjekker om spilleren treffer et brett. +1 er for at koordinatene fra spillet skal matche med brettet
        if (board[x+1][y+1] instanceof bomb) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        gameBoard test = new gameBoard();
        test.fillBoard();
        test.makeBombs();
        test.getBombsNearby();
        test.safeNeighbours();
        test.showgameBoard();
    }


}
