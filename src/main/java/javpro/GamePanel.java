package javpro;
import java.awt.AlphaComposite;
// import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import piece.*;

public class GamePanel extends JPanel implements Runnable{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();


    //pieces
    public static ArrayList<Piece1> pieces = new ArrayList<>();
    public static ArrayList<Piece1> simPieces = new ArrayList<>();
    Piece1 activeP;

    //color
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE ;

    //booleans
    boolean canMove ;
    boolean validSquare;

    public GamePanel(){
        setPreferredSize(new DimensionUIResource(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        copyPieces(pieces ,simPieces);

    }
    public void launchGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setPieces(){
        //white team 
        pieces.add(new Pawn(WHITE,0 , 6));
        pieces.add(new Pawn(WHITE,1 ,6 ));
        pieces.add(new Pawn(WHITE, 2, 6));
        pieces.add(new Pawn(WHITE, 3, 6));
        pieces.add(new Pawn(WHITE, 4,6 ));
        pieces.add(new Pawn(WHITE, 5,6 ));
        pieces.add(new Pawn(WHITE, 6,6 ));
        pieces.add(new Pawn(WHITE, 7, 6));
        pieces.add(new Rook(WHITE, 0,7 ));
        pieces.add(new Rook(WHITE, 7,7 ));
        pieces.add(new Knight(WHITE, 1,7 ));
        pieces.add(new Knight(WHITE, 6, 7));
        pieces.add(new Bishop(WHITE, 2,7 ));
        pieces.add(new Bishop(WHITE, 5,7 ));
        pieces.add(new Queen(WHITE, 3,7 ));
        pieces.add(new King(WHITE, 4,4 ));

        //Black team
        pieces.add(new Pawn(BLACK,0 , 1));
        pieces.add(new Pawn(BLACK,1 ,1 ));
        pieces.add(new Pawn(BLACK, 2, 1));
        pieces.add(new Pawn(BLACK, 3, 1));
        pieces.add(new Pawn(BLACK, 4,1 ));
        pieces.add(new Pawn(BLACK, 5,1 ));
        pieces.add(new Pawn(BLACK, 6,1 ));
        pieces.add(new Pawn(BLACK, 7, 1));
        pieces.add(new Rook(BLACK, 0,0 ));
        pieces.add(new Rook(BLACK, 7,0 ));
        pieces.add(new Knight(BLACK, 1,0 ));
        pieces.add(new Knight(BLACK, 6, 0));
        pieces.add(new Bishop(BLACK, 2,0 ));
        pieces.add(new Bishop(BLACK, 5,0 ));
        pieces.add(new Queen(BLACK, 3,0 ));
        pieces.add(new King(BLACK, 4,0 ));
    }

    private void copyPieces(ArrayList<Piece1> source , ArrayList<Piece1> target ){
        target.clear();
        for(int i = 0 ; i < source.size() ; i++ ){
            target.add(source.get(i));
        }
    }

    @Override
    public void run(){
        double drawIntervel = 1000000000/FPS;
        double delta = 0 ;
        long lastTime = System.nanoTime();
        long currentTime ;
        while(gameThread != null ){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawIntervel;
            lastTime = currentTime ;
            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }
    private void update(){
        //mouse butten pressed
        if(mouse.pressed){
            if(activeP == null){
                //if the activeP is null , check if you can pick up a piece
                for(Piece1 piece1 : simPieces) {
                    //if the mouse is on an ally piece , pick it up as the activeP
                    if(piece1.color == currentColor && 
                        piece1.col == mouse.x/Board.SQUARE_SIZE && 
                        piece1.row == mouse.y/Board.SQUARE_SIZE ){
                        activeP = piece1;
                    }
                }
            }
            else{
                //if the player is holding a piece , simulate the move
                simulate();
            }
        }
        // Mouse Button Released
        if(mouse.pressed == false ){
            if(activeP != null ){
                if(validSquare){
                    //move confirmed

                    //update the piece list in case a piece has been captured and removed during the simulation 
                    copyPieces(simPieces, pieces);
                    activeP.updatePosition();
                }
                else{
                    copyPieces(pieces, simPieces);
                    activeP.resetPosition();
                    activeP = null;
                }
            }
        }
    }
    private void simulate(){

        canMove = false ;
        validSquare = false ;

        // reset the piece list in every loop
        // this is basically for restoring the removed piece during the simulatin 
        copyPieces(simPieces, pieces);

        //if a piece is being held , update its position
        activeP.x = mouse.x-Board.HALF_SQUARE_SIZE ;
        activeP.y = mouse.y-Board.HALF_SQUARE_SIZE;
        activeP.col = activeP.getCol(activeP.x);
        activeP.row = activeP.getRow(activeP.y);

        //check if piece is hovering over a reachable square
        if(activeP.canMove(activeP.col, activeP.row)){
            canMove = true ;

            // if hitting a piece is hovering over a reachable square
            if(activeP.hittingP != null){
                simPieces.remove(activeP.hittingP.getIndex());
            }

            validSquare = true ;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        
        //board
        board.draw(g2);

        //piecs
        for(Piece1 p :simPieces ){
            p.draw(g2);
        }

        if(activeP != null){
            if(canMove){
                g2.setColor(Color.white);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2.fillRect(activeP.col*Board.SQUARE_SIZE, activeP.row*Board.SQUARE_SIZE, 
                        Board.SQUARE_SIZE,Board.SQUARE_SIZE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
            }
            //draw the active piece in the end so it would not e hidden by the board or the colored square
            activeP.draw(g2);
        }

    }
}
