package piece;

import javpro.GamePanel;
public class King extends Piece1 {
    public King (int color , int col , int row){
        super(color,col,row);
        if(color == GamePanel.WHITE){
            image = getImage("/images/kw");
        }
        else {
            image = getImage("/images/kb");
        }
    }

    public boolean canMove(int targetCol , int targetRow){
        if(inWithinBoard(targetCol, targetRow)){
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1 || 
             Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1 ){
                if(isValidSquare(targetCol, targetRow)){
                    return true;
                }
                // return true;
            }
        }
        return false;
    }
    
}
