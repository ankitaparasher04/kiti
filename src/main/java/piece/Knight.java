package piece;

import javpro.GamePanel;
public class Knight extends Piece1 {
    public Knight (int color , int col , int row){
        super(color,col,row);
        if(color == GamePanel.WHITE){
            image = getImage("/images/nw");
        }
        else {
            image = getImage("/images/nb");
        }
    }

    public boolean canMove(int targetCol , int targetRow){
        if(isValidSquare(targetCol, targetRow)){
            //knight can move if its movement ratio of col and row is 1:2 or 2:1 
            if(Math.abs(targetCol - preCol)* Math.abs(targetRow - preRow) == 2 ){
                if(isValidSquare(targetCol, targetRow)){
                    return true;
                }
            }
        }
        return false;
    }
    
}
