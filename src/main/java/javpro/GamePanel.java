package javpro; 
import java.awt.AlphaComposite;
// import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import piece.*;

public class GamePanel extends JPanel implements Runnable{
    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();


    //pieces
    public static ArrayList<Piece1> pieces = new ArrayList<>();
    public static ArrayList<Piece1> simPieces = new ArrayList<>();
    ArrayList<Piece1> promoPieces = new ArrayList<>();
    Piece1 activeP ;
    public static Piece1 castlingP;

    //color
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE ;

    //booleans
    boolean canMove ;
    boolean validSquare;
    boolean promotion ;

    public GamePanel(){
        setPreferredSize(new DimensionUIResource(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces();
        // testPromotion();
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
        pieces.add(new King(WHITE, 4,7 ));

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

    // public void testPromotion (){
    //     pieces.add(new Pawn(WHITE, 0, 3));
    //     pieces.add(new Pawn(BLACK, 5, 4));
    // }

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

        if(promotion){
            promoting();
        }
        else {
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
                        //move confiremed
    
                        //update the piece list in case a piece has been captured and removed during the simulation
                        copyPieces(simPieces, pieces); 
                        activeP.updatePosition();
                        if(castlingP != null ){
                            castlingP.updatePosition();
                        }
                        if(canPromote()){
                            promotion = true;
                        }
                        else{
                            changePlayer();
                        }
                    } else {
                        // the move is not valid so reset everything 
                        copyPieces(pieces, simPieces);
                        activeP.resetPosition();
                        activeP = null;
                    }
                }
    
                // if(activeP != null ){
                //     if(validSquare){
                //         // This means the simulation determined it's a valid move
                //         // Apply the move to the actual pieces list
                //         activeP.updatePosition();
                //         // No need to copy simPieces to pieces here if your `simulate` logic is robust
                //         // as simPieces would be altered correctly during simulation.
                //         // However, if you *only* remove the hitting piece in simulate(),
                //         // you would need to manage that removal in the 'pieces' list as well.
                //         // It's generally cleaner to only apply changes to 'simPieces' during simulation
                //         // and then copy 'simPieces' to 'pieces' once the move is confirmed.
                //         copyPieces(simPieces, pieces); // Keep this if simulate only "checks" the move
                //         changePlayer();
                //     } else {
                //         // Invalid move, revert simPieces to original pieces state
                //         copyPieces(pieces, simPieces);
                //         activeP.resetPosition();
                //         activeP = null;
                //     }
                // }
            }
            
        }
                
    }
    private void simulate(){

        copyPieces(pieces, simPieces);

        canMove = false ;
        validSquare = false ;

        //reset the castling piece position
        if(castlingP != null){
            castlingP.col = castlingP.preCol;
            castlingP.x = castlingP.getX(castlingP.col);
            castlingP = null ;
        }


        // The active piece's position updates based on mouse
        activeP.x = mouse.x - Board.HALF_SQUARE_SIZE ;
        activeP.y = mouse.y - Board.HALF_SQUARE_SIZE;
        activeP.col = activeP.getCol(activeP.x);
        activeP.row = activeP.getRow(activeP.y);

        //check if the piece is hovering over a reachable square
        if(activeP.canMove(activeP.col, activeP.row)) {
            canMove = true ;
            if(activeP.hittingP != null ){
                simPieces.remove(activeP.hittingP.getIndex());
            }
            checkCastling();
            validSquare = true ;
        }

        // // Check if piece can move to the new simulated position
        // if(activeP.canMove(activeP.col, activeP.row)){
        //     canMove = true;

        //     // Store the hitting piece if there is one
        //     Piece1 potentialHittingP = activeP.getHittingP(activeP.col, activeP.row);

        //     // Temporarily remove the hitting piece from simPieces for validation
        //     if (potentialHittingP != null) {
        //         // Check if the potentialHittingP is of the opposite color
        //         if (potentialHittingP.color != activeP.color) {
        //             simPieces.remove(potentialHittingP);
        //             validSquare = true; // It's a valid capture
        //         } else {
        //             // Cannot capture own piece
        //             validSquare = false;
        //         }
        //     } else {
        //         validSquare = true; // Valid move to empty square
        //     }
        // }
    }
    private void checkCastling(){
        if(castlingP != null ){
            if(castlingP.col == 0){
                castlingP.col +=3 ;
            }
            else if(castlingP.col == 7 ){
                castlingP.col -=2 ;
            }
            castlingP.x = castlingP.getX(castlingP.col);
        }
    }
    private void changePlayer(){
        if(currentColor == WHITE){
            currentColor = BLACK;
            //reset black's two atepped status
            for(Piece1 piece1 : pieces){
                if(piece1.color == BLACK){
                    piece1.twoStepped = false ;
                }
            }
        }
        else {
            currentColor = WHITE;
            //reset white's two stepped status
            for(Piece1 piece1 : pieces){
                if(piece1.color == WHITE){
                    piece1.twoStepped = false;
                }
            }
        }
        activeP = null;
    }
    private boolean canPromote(){
        if(activeP.type == Type.PAWN){
            if(currentColor == WHITE && activeP.row == 0 || currentColor == BLACK && activeP.row == 7){
                promoPieces.clear();
                promoPieces.add(new Rook(currentColor, 9, 2));
                promoPieces.add(new Knight(currentColor, 9, 3));
                promoPieces.add(new Bishop(currentColor, 9, 4));
                promoPieces.add(new Queen(currentColor, 9, 5));
                return true ;
            }
        }
        return false;
    }
    private void promoting(){
        if(mouse.pressed){
            for(Piece1 piece1 : promoPieces){
                if(piece1.col == mouse.x/Board.SQUARE_SIZE && piece1.row == mouse.y/Board.SQUARE_SIZE){
                    switch(piece1.type){
                        case ROOK : simPieces.add(new Rook(currentColor, activeP.col, activeP.row)) ; break ;
                        case KNIGHT : simPieces.add(new Knight(currentColor, activeP.col, activeP.row)) ; break ;
                        case BISHOP : simPieces.add(new Bishop(currentColor, activeP.col, activeP.row)) ; break ;
                        case QUEEN : simPieces.add(new Queen(currentColor, activeP.col, activeP.row)) ; break ;
                        default : break ;
                    }
                    simPieces.remove(activeP.getIndex());
                    copyPieces(simPieces, pieces);
                    activeP = null ;
                    promotion = false ;
                    changePlayer();

                }
            }
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

        //STATUS MESSAGES
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(new Font("Book Antiqua",Font.PLAIN,40));
        g2.setColor(Color.white);  
        
        if(promotion){
            g2.drawString("Promot To : ",840, 150);
            for(Piece1 piece : promoPieces){
                g2.drawImage(piece.image, piece.getX(piece.col), piece.getY(piece.row), Board.SQUARE_SIZE , Board.SQUARE_SIZE , null) ;

            }
        }
        else{
            if(currentColor == WHITE) {
                g2.drawString("White's turn",840,550);
            }
            else {
                g2.drawString("Black's turn", 840, 250);
            }
        }
        
    }
}
