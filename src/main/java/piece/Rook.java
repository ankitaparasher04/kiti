package piece;

import javpro.GamePanel;
import javpro.Type;
public class Rook extends Piece1 {
    public Rook (int color , int col , int row){
        super(color,col,row);
        type = Type.ROOK;
        if(color == GamePanel.WHITE){
            image = getImage("/images/rw");
        }
        else {
            image = getImage("/images/rb");
        }
    }
    public boolean canMove(int targetCol , int targetRow){
        if(inWithinBoard(targetCol , targetRow) && isSameSquare(targetCol, targetRow) == false){
            //rook can move as long as either its col or row is the same
            if(targetCol == preCol || targetRow == preRow){
                if(isValidSquare(targetCol, targetRow)  && pieceIsOnStraightLine(targetCol, targetRow) == false ){
                    return true;
                }
            }
        }
        return false;
    }
    
}
