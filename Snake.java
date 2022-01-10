import java.util.ArrayList;
import java.awt.event.*;

public class Snake {

    private ArrayList<SnakeCell> snakeCells;
    private int snakeLength;

    private SnakeCell head;
    private SnakeCell tail;
    private Board board;
    private int points;
    private boolean isDead;

    private static Snake instance;

    private Snake() {

        this.snakeCells = new ArrayList<>();
        this.isDead = false;
        this.points = 0;
        snakeCells.add(new SnakeCell(snakeCells.size()));
        this.head = snakeCells.get(0); // getting the head from list of cells contained in this class
        this.tail = snakeCells.get(this.snakeCells.size() - 1);
        this.snakeLength = this.snakeCells.size();
        this.board = Board.getBoardInstance();

    }

    public SnakeCell getHead() {
        return head;
    }

    public void addCell() {
        this.snakeCells.add(new SnakeCell(snakeCells.size()));
    }

    /**
     * singleton design pattern to stop the user from creating many instance of
     * snakes
     * 
     * @return an instance of snake
     */

    public static Snake getSnake() {
        if (instance == null) {
            instance = new Snake();
            return instance;
        }
        return instance;
    }

    public ArrayList<SnakeCell> getSnakeCells() {
        return snakeCells;
    }

    public void move(KeyEvent e) {

        // moving left to right
        Coordinate currentPos = this.head.getCoordinate();
        if (e.getKeyCode() == 37) { // left
            Coordinate des = new Coordinate(currentPos.getX(), currentPos.getY() - 1); // Calculating the snakes

            if(isDead(des)){

                board.resetBoard();
                this.snakeCells.clear();// remove all the snake cells 
                this.board.placeApple();
                this.points = 0;
                this.head = new SnakeCell(snakeLength);
                this.head.setCoordinate(new Coordinate(5, 5));
                this.snakeCells.add(head);
                this.board.placeSnake(head.getCoordinate(), this.head);
            }
                                                                                      
            else if (canMove(des, currentPos)) { // if the move is legal we can change the snakes coordinates
                if (this.board.getBoard()[des.getX()][des.getY()].getCell() != null && this.board.getBoard()[des.getX()][des.getY()].getCell().getClass().getSimpleName().equals("Apple")) {
                    this.board.placeApple();// placing the new apple when the old one has been eating
                    SnakeCell cell = new SnakeCell(snakeLength); // duplicating the head
                    cell.setCoordinate(des); 
                    this.board.placeSnake(des, cell);
                    this.snakeCells.add(0, cell);
                    this.head = this.snakeCells.get(0);
                    this.head.setCoordinate(this.snakeCells.get(0).getCoordinate());
                    this.points += 5;
    
                } else {
                    

                     // Correct code

                    this.board.getBoard()[this.tail.getCoordinate().getX()][this.tail.getCoordinate().getY()].setObject(null);
                    SnakeCell cell = new SnakeCell(snakeLength);
                    cell.setCoordinate(des);
                    this.snakeCells.remove(this.tail);
                    // des = new Coordinate(this.head.getCoordinate().getX(),
                    // this.head.getCoordinate().getY()-1);
                    this.board.placeSnake(des, cell);
                    this.snakeCells.add(0, cell);
                    this.head = this.snakeCells.get(0);
                    this.head.setCoordinate(this.snakeCells.get(0).getCoordinate());
                    this.tail = this.snakeCells.get(this.snakeCells.size() - 1);
                    tail.setCoordinate(this.snakeCells.get(this.snakeCells.size() - 1).getCoordinate());

                }
            }
        } else if (e.getKeyCode() == 39) { // right
            Coordinate des = new Coordinate(currentPos.getX(), currentPos.getY() + 1); // Calculating the snakes
            if(isDead(des)){

                board.resetBoard();
                this.snakeCells.clear();// remove all the snake cells 
                this.board.placeApple();
                this.points = 0;
                this.head = new SnakeCell(snakeLength);
                this.head.setCoordinate(new Coordinate(5, 5));
                this.snakeCells.add(head);
                this.board.placeSnake(head.getCoordinate(), this.head);
            }

            else if(canMove(des, currentPos)){
                if (this.board.getBoard()[des.getX()][des.getY()].getCell() != null && this.board.getBoard()[des.getX()][des.getY()].getCell().getClass().getSimpleName()
                            .equals("Apple")) {
                    this.board.placeApple();// placing the new apple when the old one has been eating
                    SnakeCell cell = new SnakeCell(snakeLength); // duplicating the head
                    cell.setCoordinate(des); 
                    this.board.placeSnake(des, cell);
                    this.snakeCells.add(0, cell);
                    this.head = this.snakeCells.get(0);
                    this.head.setCoordinate(this.snakeCells.get(0).getCoordinate());
                    this.points += 5;
                    System.out.println(this.points);
            } else {
                this.board.getBoard()[this.tail.getCoordinate().getX()][this.tail.getCoordinate().getY()].setObject(null);
                SnakeCell cell = new SnakeCell(snakeLength);
                cell.setCoordinate(des);
                this.snakeCells.remove(this.tail);
                // des = new Coordinate(this.head.getCoordinate().getX(),
                // this.head.getCoordinate().getY()-1);
                this.board.placeSnake(des, cell);
                this.snakeCells.add(0, cell);
                this.head = this.snakeCells.get(0);
                this.head.setCoordinate(this.snakeCells.get(0).getCoordinate());
                this.tail = this.snakeCells.get(this.snakeCells.size() - 1);
                tail.setCoordinate(this.snakeCells.get(this.snakeCells.size() - 1).getCoordinate());
                
            }
                
            }
            

        } else if (e.getKeyCode() == 38) {// up
            Coordinate des = new Coordinate(currentPos.getX() - 1, currentPos.getY()); // Calculating the snakes
            if(isDead(des)){

                board.resetBoard();
                this.snakeCells.clear();// remove all the snake cells 
                this.board.placeApple();
                this.points = 0;
                this.head = new SnakeCell(snakeLength);
                this.head.setCoordinate(new Coordinate(5, 5));
                this.snakeCells.add(head);
                this.board.placeSnake(head.getCoordinate(), this.head);
            }
    
            else if(canMove(des, currentPos)){
                if (this.board.getBoard()[des.getX()][des.getY()].getCell() != null && this.board.getBoard()[des.getX()][des.getY()].getCell().getClass().getSimpleName()
                            .equals("Apple")) {
                    this.board.placeApple();// placing the new apple when the old one has been eating
                    SnakeCell cell = new SnakeCell(snakeLength); // duplicating the head
                    cell.setCoordinate(des); 
                    this.board.placeSnake(des, cell);
                    this.snakeCells.add(0, cell);
                    this.head = this.snakeCells.get(0);
                    this.head.setCoordinate(this.snakeCells.get(0).getCoordinate());
                    this.points += 5;
                    System.out.println(this.points);
            } else {
                this.board.getBoard()[this.tail.getCoordinate().getX()][this.tail.getCoordinate().getY()].setObject(null);
                SnakeCell cell = new SnakeCell(snakeLength);
                cell.setCoordinate(des);
                this.snakeCells.remove(this.tail);
                // des = new Coordinate(this.head.getCoordinate().getX(),
                // this.head.getCoordinate().getY()-1);
                this.board.placeSnake(des, cell);
                this.snakeCells.add(0, cell);
                this.head = this.snakeCells.get(0);
                this.head.setCoordinate(this.snakeCells.get(0).getCoordinate());
                this.tail = this.snakeCells.get(this.snakeCells.size() - 1);
                tail.setCoordinate(this.snakeCells.get(this.snakeCells.size() - 1).getCoordinate());
                
            }
                
            }
            

        } else if (e.getKeyCode() == 40) { // down
            Coordinate des = new Coordinate(currentPos.getX() + 1, currentPos.getY()); // Calculating the snakes
            if(isDead(des)){

                board.resetBoard();
                this.snakeCells.clear();// remove all the snake cells 
                this.board.placeApple();
                this.points = 0;
                this.head = new SnakeCell(snakeLength);
                this.head.setCoordinate(new Coordinate(5, 5));
                this.snakeCells.add(head);
                this.board.placeSnake(head.getCoordinate(), this.head);
            }
 
            else if(canMove(des, currentPos)){
                if (this.board.getBoard()[des.getX()][des.getY()].getCell() != null && this.board.getBoard()[des.getX()][des.getY()].getCell().getClass().getSimpleName()
                            .equals("Apple")) {
                    this.board.placeApple();// placing the new apple when the old one has been eating
                    SnakeCell cell = new SnakeCell(snakeLength); // duplicating the head
                    cell.setCoordinate(des); 
                    this.board.placeSnake(des, cell);
                    this.snakeCells.add(0, cell);
                    this.head = this.snakeCells.get(0);
                    this.head.setCoordinate(this.snakeCells.get(0).getCoordinate());
                    this.points += 5;
                    System.out.println(this.points);
            } else {
                this.board.getBoard()[this.tail.getCoordinate().getX()][this.tail.getCoordinate().getY()].setObject(null);
                SnakeCell cell = new SnakeCell(snakeLength);
                cell.setCoordinate(des);
                this.snakeCells.remove(this.tail);
                // des = new Coordinate(this.head.getCoordinate().getX(),
                // this.head.getCoordinate().getY()-1);
                this.board.placeSnake(des, cell);
                this.snakeCells.add(0, cell);
                this.head = this.snakeCells.get(0);
                this.head.setCoordinate(this.snakeCells.get(0).getCoordinate());
                this.tail = this.snakeCells.get(this.snakeCells.size() - 1);
                tail.setCoordinate(this.snakeCells.get(this.snakeCells.size() - 1).getCoordinate());
                
            }
                
            }// destination coordinates.
            

        }

    }

    public boolean isDead(Coordinate des) {
        // we want to test if the snake is dead.
        /**
         * Can be done by testing if we are tring to move to a certain position on the
         * grid tha t already contains a snake cell.
         */

        if (this.board.getBoard()[des.getX()][des.getY()].getCell() != null
                && this.board.getBoard()[des.getX()][des.getY()].getCell().getClass().getSimpleName()
                        .equals("SnakeCell")) {
            this.isDead = true;
            return true;
        }

        return false;
    }

    public boolean canMove(Coordinate des, Coordinate pos) {
        // we want test if the snake is not at the edge of the grid.
        if (isOutOfBound(des)) {
            return true;
        }
        return false;
    }

    public boolean isOutOfBound(Coordinate des) {
        if (des.getX() < 0 || des.getX() > 9) {
            return false;
        } else if (des.getY() < 0 || des.getY() > 9) {
            return false;

        }
        return true;
    }

    /**
     * public boolean isAllowedMove(Coordinate des, Coordinate pos){
     * if(pos.getX() - des.getX() != 1 || pos.getX() - des.getX() != -1){
     * return false;
     * }
     * return true;
     * 
     * }
     */

    public String toString() {
        return "x";
    }

    public int getPoints() {
        return points;
    }

    public boolean getIsDead() {
        return this.isDead;
    }

}