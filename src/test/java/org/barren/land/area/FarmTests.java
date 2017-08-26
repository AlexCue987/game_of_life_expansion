package org.barren.land.area;

import org.junit.Assert;
import org.junit.Test;

public class FarmTests {
    @Test
    public void constructor_works(){
        Farm farm = new Farm(1, 2);
        for(int x=-1; x<=1; x++){
            for(int y=-1; y<=2; y++){
                Square square = farm.getSquare(x, y);
                if(x==0 && (y==0 || y==1)){
                    Assert.assertTrue(square.isFertile());
                }else{
                    Assert.assertFalse(square.isFertile());
                }
            }
        }
    }

    @Test
    public void constructor_worksForAnotherArea(){
        Farm farm = new Farm(2, 1);
        for(int x=-1; x<=2; x++){
            for(int y=-1; y<=1; y++){
                Square square = farm.getSquare(x, y);
                if((x==0 || x==1) && y==0){
                    Assert.assertTrue(square.isFertile());
                }else{
                    Assert.assertFalse(square.isFertile());
                }
            }
        }
    }

    @Test
    public void setBarren_works(){
        Farm farm = new Farm(9, 10);
        Rectangle rectangle = new Rectangle(2, 3, 4, 5);
        farm.setBarren(rectangle);
        for(int x=0; x<9; x++){
            for(int y=0; y<10; y++){
                Square square = farm.getSquare(x, y);
                if(x>=2 && x<4 && y>=3 && y<5){
                    Assert.assertFalse(square.isFertile());
                }else{
                    Assert.assertTrue(square.isFertile());
                }
            }
        }
    }
}
