package piece;

import javpro.GamePanel;
import javpro.Type;

public class Bishop extends Piece1 {
    public Bishop (int color , int col , int row){
        super(color,col,row);
        type = Type.BISHOP;
        if(color == GamePanel.WHITE){
            image = getImage("/images/bw");
        }
        else {
            image = getImage("/images/bb");
        }
    }
    public boolean canMove(int targetCol , int targetRow){
        if(inWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false ){

            if(Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)){
                if(isValidSquare(targetCol, targetRow) && pieceIsOnDiagonalLine(targetCol, targetRow) == false){
                    return true ;
                }
            }
        }
        return false ;
    }
    
}
