package org.barren.land;

import org.barren.land.area.Farm;
import org.barren.land.area.Offset;
import org.barren.land.area.Square;

import java.util.ArrayList;
import java.util.List;

public class ExpansionVisitor {
    public void groupSquaresIntoAreas(Farm farm){
        int areaNumber = 1;
        for(int x=0; x<farm.getAreaXSize(); x++){
            for(int y=0; y<farm.getAreaYSize(); y++){
//                System.out.println(x + ", " + y);
                Square square = farm.getSquare(x, y);
                if(square.isFertileAndUnassigned()){
                    expandSquareIntoArea(farm, square, areaNumber++);
                }
            }
        }
    }

    void expandSquareIntoArea(Farm farm, Square seed, int areaNumber){
        List<Square> nextBorder = new ArrayList<>();
        nextBorder.add(seed);
        while(nextBorder.size() > 0){
            List<Square> border = nextBorder;
            nextBorder = new ArrayList<>();
//            System.out.println(border);
            for(Square square: border){
                for(Offset offset: Offset.getAdjacentOffsets()){
                    int adjacentX = square.getX() + offset.getX();
                    int adjacentY = square.getY() + offset.getY();
                    Square adjacentSquare = farm.getSquare(adjacentX, adjacentY);
                    if(adjacentSquare.isFertileAndUnassigned()){
                        adjacentSquare.setAreaNumber(areaNumber);
                        nextBorder.add(adjacentSquare);
                    }
                }
            }
        }
    }
}
