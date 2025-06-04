package piece;

import javpro.GamePanel;
import javpro.Type;
public class Pawn extends Piece1{
    public Pawn(int color, int col , int row){
        super(color , col , row);

        type = Type.PAWN;

        if(color == GamePanel.WHITE){
            image = getImage("/images/pw");
        }
        else{
            image = getImage("/images/pb");
        }
    }
    public boolean canMove(int targetCol , int targetRow){
        if(inWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false){
            //define the move value based on its color 
            int moveValue ;
            if(color == GamePanel.WHITE){
                moveValue = -1  ;
            }
            else {
                moveValue = 1 ;
            }
            // cjeck the hitting piece 
            hittingP = getHittingP(targetCol, targetRow);

            // 1 square movement
            if(targetCol == preCol && targetRow == preRow + moveValue && hittingP == null ){
                return true ;
            }
            // 2 square movement 
            if(targetCol == preCol && targetRow == preRow + moveValue*2 && hittingP == null && moved == false &&
                                        pieceIsOnStraightLine(targetCol, targetRow) == false){
                        return true ;
            }
            //Diagonal movement & capture (if a piece is on a square diagonally in front o it )
            if(Math.abs(targetCol-preCol) == 1 && targetRow == preRow + moveValue && hittingP != null && hittingP.color != color){
                return true ;
            }

            // En Passant 
            if(Math.abs(targetCol-preCol) == 1 && targetRow == preRow + moveValue){
                for(Piece1 piece1 : GamePanel.simPieces){
                    if(piece1.col == targetCol && piece1.row == preRow && piece1.twoStepped == true){
                        hittingP = piece1;
                        return true ;
                    }
                }
            }

        }
        return false;
    }
}
