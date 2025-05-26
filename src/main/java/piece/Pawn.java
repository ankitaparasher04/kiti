package piece;

import javpro.GamePanel;
public class Pawn extends Piece1{
    public Pawn(int color, int col , int row){
        super(color , col , row);

        if(color == GamePanel.WHITE){
            image = getImage("/images/pw");
        }
        else{
            image = getImage("/images/pb");
        }
    }
}
