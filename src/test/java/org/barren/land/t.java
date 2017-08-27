package org.barren.land;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class t {
    @Test
    public void works(){
        Integer[][] numbers = new Integer[2][3];
        for(int i=0; i<2; i++)
            for(int j=0; j<3; j++)
                numbers[i][j] = i*10 + j;
        Collection<Integer> ints = Arrays.stream(numbers).
                flatMap(Arrays::stream).collect(Collectors.toList());
    }
}
