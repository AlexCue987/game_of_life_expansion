package org.barren.land.area;

import org.barren.land.ExpansionVisitor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExpansionVisitorTests {
    @Test
    public void groupSquaresIntoAreas_noBarrenLand(){
        Farm farm = new Farm(2, 2);
        ExpansionVisitor visitor = new ExpansionVisitor();
        visitor.groupSquaresIntoAreas(farm);
        List<Long> actual = farm.getAreas();
        List<Long> expected = Collections.singletonList(4L);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void groupSquaresIntoAreas_allLandBarren(){
        Farm farm = new Farm(2, 2);
        farm.setBarren(new Rectangle(0, 0, 2, 2));
        ExpansionVisitor visitor = new ExpansionVisitor();
        visitor.groupSquaresIntoAreas(farm);
        List<Long> actual = farm.getAreas();
        Assert.assertEquals(0, actual.size());
    }

    @Test
    public void groupSquaresIntoAreas_twoVerticalStripes(){
        Farm farm = new Farm(10, 10);
        farm.setBarren(new Rectangle(0, 4, 10, 7));
        ExpansionVisitor visitor = new ExpansionVisitor();
        visitor.groupSquaresIntoAreas(farm);
        List<Long> actual = farm.getAreas();
        List<Long> expected = Arrays.asList(30L, 40L);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void groupSquaresIntoAreas_twoHorizontalStripes(){
        Farm farm = new Farm(10, 10);
        farm.setBarren(new Rectangle(4, 0, 7, 10));
        ExpansionVisitor visitor = new ExpansionVisitor();
        visitor.groupSquaresIntoAreas(farm);
        List<Long> actual = farm.getAreas();
        List<Long> expected = Arrays.asList(30L, 40L);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void groupSquaresIntoAreas_twoCornersSeparateAreas(){
        Farm farm = new Farm(10, 10);
        farm.setBarren(new Rectangle(0, 0, 3, 5));
        farm.setBarren(new Rectangle(3, 5, 10, 10));
        ExpansionVisitor visitor = new ExpansionVisitor();
        visitor.groupSquaresIntoAreas(farm);
        List<Long> actual = farm.getAreas();
        List<Long> expected = Arrays.asList(15L, 35L);
        Assert.assertEquals(expected, actual);
    }
}
