
package piece;

import javpro.GamePanel;public class Queen extends Piece1 {
    public Queen (int color , int col , int row){
        super(color,col,row);
        if(color == GamePanel.WHITE){
            image = getImage("/images/qw");
        }
        else {
            image = getImage("/images/qb");
        }
    }
    
}
