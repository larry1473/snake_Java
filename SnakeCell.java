/**
 * @author Larry Fotso 
 * This will the cells that make the snake's body
 */
public class SnakeCell {
    
    private int cellId; // all the snake cells will have a cell id;
    private Coordinate coordinate;

    public SnakeCell(int cellId){

        this.cellId = cellId;
    }

    public int getCellId() {
        return cellId;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String toString(){
        return "*";
    }

    
}


