package piece;

import javpro.GamePanel;
import javpro.Type;
public class King extends Piece1 {
    public King (int color , int col , int row){
        super(color,col,row);
        type = Type.KING;
        if(color == GamePanel.WHITE){
            image = getImage("/images/kw");
        }
        else {
            image = getImage("/images/kb");
        }
    }

    public boolean canMove(int targetCol , int targetRow){
        if(inWithinBoard(targetCol, targetRow)){

            //Movement
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1 || 
             Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1 ){
                if(isValidSquare(targetCol, targetRow)){
                    return true;
                }
                // return true;
            }

            //Castling
            if(moved == false) {
                //Right castling
                if(targetCol == preCol+2 && targetRow == preRow && pieceIsOnStraightLine(targetCol, targetRow) == false ){
                    for(Piece1 piece1 : GamePanel.simPieces ){
                        if(piece1.col == preCol+3 && piece1.row == preRow && piece1.moved == false ){
                            GamePanel.castlingP = piece1 ;
                            return true ;
                        }
                    }
                }
                //left castling 
                if(targetCol == preCol-2 && targetRow == preRow  && pieceIsOnStraightLine(targetCol, targetRow) == false){
                    Piece1 p[] = new Piece1[2];
                    for(Piece1 piece1 :  GamePanel.simPieces){
                        if(piece1.col == preCol-3 && piece1.row == targetRow ){
                            p[0] = piece1;
                        }
                        if(piece1.col == preCol-4 && piece1.row == targetRow ){
                            p[1] = piece1;
                        }
                        if(p[0] == null && p[1] != null && p[1].moved == false){
                            GamePanel.castlingP = p[1];
                            return true ;
                        }
                    }
                }
            }
        }
        return false;
    }
    
}
