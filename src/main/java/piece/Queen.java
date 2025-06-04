
package piece;

import javpro.GamePanel;
import javpro.Type;public class Queen extends Piece1 {
    public Queen (int color , int col , int row){
        super(color,col,row);
        type = Type.QUEEN;
        if(color == GamePanel.WHITE){
            image = getImage("/images/qw");
        }
        else {
            image = getImage("/images/qb");
        }
    }

    public boolean canMove(int targetCol , int targetRow ){

        if(inWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false){

            //vertical & horizontal 
            if(targetCol == preCol || targetRow == preRow){
                if(isValidSquare(targetCol, targetRow) && pieceIsOnStraightLine(targetCol, targetRow) == false){
                    return true;
                }
            }
            
            //Diagonal
            if(Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)){
                if(isValidSquare(targetCol, targetRow) && pieceIsOnDiagonalLine(targetCol, targetRow) == false){
                    return true;
                }
            }
        }

        return false ;
    }
    
}
