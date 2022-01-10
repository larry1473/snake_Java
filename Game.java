import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.GridBagConstraints;

import java.awt.*;
import java.awt.event.*;
public class Game extends JFrame implements KeyListener {

    private Board board;
    private Snake snake;
    private Tile[][] boardGame;
    private BoardPanel boardPanel;
    private boolean running = false;
    private int snakeXCoor = 5 , snakeYCoor = 5;
    private Coordinate snakePositionCoordinate;


    private Dimension windowDimension = new Dimension(900,900);

    public Game(){
        this.board = Board.getBoardInstance();
        this.snake = Snake.getSnake();
        this.boardGame = board.getBoard();
        this.snakePositionCoordinate = new Coordinate(snakeXCoor, snakeYCoor);
        this.board.placeSnake(snakePositionCoordinate, this.snake.getHead()); // we want to place the snake on the grid 

        // graphci board
        this.boardPanel = new BoardPanel();
        


        this.setLayout(new BorderLayout());
        this.setTitle("Snake");
        this.add(boardPanel,BorderLayout.CENTER);
        this.setSize(windowDimension);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addKeyListener(this);
        this.pack();
        this.setVisible(true);
    }

    public void start(){
        this.snake.getHead().setCoordinate(this.snakePositionCoordinate);
        this.running = true;
        //render(this.board.getBoard());
        //this.createGraphicBoard();
        //this.PlacePanels();
        run();
    }

    private void run(){
        long lastTime = System.nanoTime();
        final double amtOfTicks = 60.0;
        double ns = 1000000000 / amtOfTicks;
        double delta = 0;
        while (running){
            long now  = System.nanoTime();
            delta += (now -lastTime) / ns;
            lastTime = now;
            //this.IsDead = this.snake.getIsDead();
            if(delta > 1){
                tick();
                //render(this.b.getBoard());
                delta--;

            }
            
            




        }
        stop();


    }

    private void stop() {
    }

    private void tick(){
       System.out.println(this.board.toString());
       System.out.println(this.snake.getPoints());


    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        this.snake.move(e);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                boardPanel.reDrawBoardPanel(boardGame);
            }
            
        });
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    private class BoardPanel extends JPanel{

        private TilePanel[][] tilePanels;
        private  final Dimension BOARD_DIMENSION = new Dimension(800,800) ; 

        private BoardPanel(){
            super( new GridLayout(10,10));
            this.tilePanels = new TilePanel[10][10];
            this.CreateGraphicBoard();
            
        }

        private void  CreateGraphicBoard(){
            for(int i = 0; i < boardGame.length; i++){
                for(int y = 0; y < boardGame[i].length;y++){
                    TilePanel tile = new TilePanel(new Coordinate(i,y));
                    this.tilePanels[i][y] = tile;
                    this.add(tile);

                }
            }
            setPreferredSize(BOARD_DIMENSION);
            validate();
        }

        private void reDrawBoardPanel(Tile[][] board){
            removeAll();
            for(int i = 0; i < this.tilePanels.length;i++){
                for(int y = 0; y < this.tilePanels[i].length;y++){
                    this.tilePanels[i][y].reDrawTilePanel(board);
                    add(this.tilePanels[i][y]);
                }
            }
            validate();
            repaint();

        }


        
    }

    private class TilePanel extends JPanel{
        private  final Dimension TILE_PANEL_DIMENSION = new Dimension(5,5);
        private Dimension cellDimension = new Dimension(10,10);
        private Coordinate coordinate;

        private TilePanel(Coordinate coordinate){
            super( new BorderLayout());
            this.setPreferredSize(TILE_PANEL_DIMENSION);
            this.setForeground(Color.white);
            this.setBackground(Color.BLACK);
            this.coordinate = coordinate;
            placeCells(boardGame);
            
            validate();
            

        }

        public void reDrawTilePanel(Tile[][] board) {
            removeAll();
            this.setForeground(Color.white);
            this.setBackground(Color.BLACK);
            placeCells(board);
            validate();
            repaint();
        }

        private void placeCells(Tile[][] board){
            this.removeAll();
            if(boardGame[this.coordinate.getX()][this.coordinate.getY()].getCell() != null && boardGame[this.coordinate.getX()][this.coordinate.getY()].getCell().getClass().getSimpleName().equals("SnakeCell") ){
                
                if(boardGame[this.coordinate.getX()][this.coordinate.getY()].getCell() == snake.getSnakeCells().get(0)){
                    SnakeCellPanel snakeCellPanel = new SnakeCellPanel(new Color(  166, 78, 220 ));
                    this.add(snakeCellPanel);
                }
                else{

                    SnakeCellPanel snakeCellPanel = new SnakeCellPanel(new Color( 39, 84, 48 ));
                    this.add(snakeCellPanel);

                }
                

                

            }
            else if(boardGame[this.coordinate.getX()][this.coordinate.getY()].getCell() != null && boardGame[this.coordinate.getX()][this.coordinate.getY()].getCell().getClass().getSimpleName().equals("Apple")){
                ApplePanel applePanel = new ApplePanel();
                this.add(applePanel);

            }


        }

    }

    private class SnakeCellPanel extends JPanel{
        private Dimension cellDimension = new Dimension(20,20);

        private SnakeCellPanel(Color c){
            this.setForeground(Color.white);
            this.setBackground(c);
            this.setPreferredSize(cellDimension);
            validate();
        }

    }

    private class ApplePanel extends JPanel{

        private  Dimension AppleDimension = new Dimension(20,20);

        private ApplePanel(){
            this.setForeground(Color.white);
            this.setBackground(Color.black);
            this.setSize(18,18);
            validate();
        }

        private int getRadius(){
            return Math.min(20, 15);
        }

        public Insets getInsets(){

            int radius = getRadius();
            int xOffset = (getWidth() - radius) / 2;
            int yOffset = (getHeight() - radius) / 2;
            // These are magic numbers, you might like to calculate
            // your own values based on your needs
            Insets insets = new Insets(
                    radius / 6,
                    radius / 6,
                    radius / 6,
                    radius / 6);
            return insets;

        }

        public  void paintComponent(Graphics g) {

            super.paintComponent(g);

            int radius = getRadius();
            int xOffset = (getWidth() - radius) / 2;
            int yOffset = (getHeight() - radius) / 2;

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.RED);
            g2d.fillOval(xOffset, yOffset, radius, radius);
            g2d.setColor(Color.RED);
            g2d.drawOval(xOffset, yOffset, radius, radius);
            g2d.dispose();

        }
    }
    
}
