public class Tile {
    private int x;
    private int y;
    private Coordinate coordinate;
    private Object cell;

    public Tile(Coordinate coordinate, Object cell){
        this.coordinate = coordinate;
        this.cell = cell;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Object getCell() {
        return cell;
    }

    public void setObject(Object cell) {
        this.cell = cell;
    }

    public String toString(){

        String res = "";

        if(this.cell == null){
            res = "_";
            return  res;
        }
        else{
            res = this.cell.toString();
            return res;
        }
        
    }


    
}
