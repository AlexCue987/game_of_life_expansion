package org.barren.land.area;

import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
public class Offset {
    private final int x;
    private final int y;

    public static final Offset UP = new Offset(0, 1);
    public static final Offset DOWN = new Offset(0, -1);
    public static final Offset LEFT = new Offset(-1, 0);
    public static final Offset RIGHT = new Offset(1, 0);

    private static final List<Offset> adjacentOffsets = Arrays.asList(UP, DOWN, LEFT, RIGHT);

    public static final List<Offset> getAdjacentOffsets(){return adjacentOffsets;}
}
