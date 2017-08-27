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

    @Test
    public void example1_works(){
        String input = "{\"0 292 399 307\"}";
        Farm farm = Farm.fromStdinInput(input);
        String actual = farm.getAreaSizesAsString();
        Assert.assertEquals("116800 116800", actual);
    }

    @Test
    public void example2_works(){
        String input = "{\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}";
        Farm farm = Farm.fromStdinInput(input);
        String actual = farm.getAreaSizesAsString();
        Assert.assertEquals("22816 192608", actual);
    }
}
