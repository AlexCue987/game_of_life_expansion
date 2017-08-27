package org.barren.land.area;

import lombok.*;

@Data
@EqualsAndHashCode
@ToString
public class Square {
    public static final int NO_AREA_ASSIGNED = 0;
    @Setter
    private boolean fertile;
    @Setter
    private int areaNumber;
    private final int x;
    private final int y;

    Square(boolean fertile, int areaNumber, int x, int y){
        this.fertile = fertile;
        this.areaNumber = areaNumber;
        this.x = x;
        this.y = y;
    }

    public Square(int x, int y){
        this(true, NO_AREA_ASSIGNED, x, y);
    }

    public boolean isFertileAndUnassigned(){
        return fertile && areaNumber==NO_AREA_ASSIGNED;
    }
}
