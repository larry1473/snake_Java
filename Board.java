import java.util.Random;

public class Board {

    private Tile[][] board;
    private Snake snake;
    private static Board instance;
    //private Apple apple;
    private int width = 10;
    private int length = 10;

    private  Board(){
        //this.snake = Snake.getSnake();
        this.board  = new Tile[width][length];
        createBoard();
        // placeSnake(5,5,this.snake); //Todo change the place of this code
        placeApple();

    }
    

    private void  createBoard(){

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                this.board[i][j] = new Tile(new Coordinate(i, j),null);
            }
        }

    }

    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(Tile[][] b){
        this.board = b;
    }

    /**
     * place the snake's head on the board at the coordinate x y 
     * @param x
     * @param y
     */

    public void placeSnake( Coordinate pos, SnakeCell snake){
        this.board[pos.getX()][pos.getY()].setObject(snake);

    }

    public void placeApple(){
        Random r = new Random();
        int x  = r.nextInt(width);
        int y = r.nextInt(length);
        if(this.board[x][y].getCell() == null){ // checking if the tile doesn't contain something on it before placing and apple on it eg a snake cell.
            this.board[x][y].setObject(new Apple(x,y));

        }
        else{ // if the tile is not empty we recall the method to get new x and w values.
            placeApple();
        }
        
    
    }

    public String toString(){
        String res = "";
        for(int  i =  0 ; i < this.board.length; i++ ){
            for(int j = 0; j < this.board[i].length; j++){
                res += this.board[i][j].toString() + " ";
                if(j == this.board[i].length - 1){
                    res += "\n";
                }

            }
        }
        return res;
    }

    public static Board getBoardInstance(){
        if(instance == null){
            instance = new Board();
            System.out.println(instance);
            return instance;
        }
        return instance;
    }

    public Tile getTile(Coordinate cor){
        return this.board[cor.getX()][cor.getY()];

    }

    public void resetBoard(){

       createBoard();
    }

 
}
