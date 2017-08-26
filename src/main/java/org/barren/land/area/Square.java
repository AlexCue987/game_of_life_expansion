package org.barren.land.area;

import lombok.*;

@Data
@EqualsAndHashCode
@ToString
public class Square {
    public static final int NO_AREA_ASSIGNED = 0;
    @Setter
    private boolean fertile;
    private final int areaNumber;

    Square(boolean fertile, int areaNumber){
        this.fertile = fertile;
        this.areaNumber = areaNumber;
    }

    public Square(){
        this(true, NO_AREA_ASSIGNED);
    }

    public boolean isFertileAndUnassigned(){
        return fertile && areaNumber==NO_AREA_ASSIGNED;
    }
}
