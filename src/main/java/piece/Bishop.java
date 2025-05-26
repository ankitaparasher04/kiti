package piece;

import javpro.GamePanel;

public class Bishop extends Piece1 {
    public Bishop (int color , int col , int row){
        super(color,col,row);
        if(color == GamePanel.WHITE){
            image = getImage("/images/bw");
        }
        else {
            image = getImage("/images/bb");
        }
    }
    
}
