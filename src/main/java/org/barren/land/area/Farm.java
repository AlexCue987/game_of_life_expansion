package org.barren.land.area;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.barren.land.ExpansionVisitor;
import org.barren.land.parser.InputParser;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Farm {
    private final Square[][] squares;
    private final int barrenBorderWidth = 1;
    private final int areaXSize;
    private final int areaYSize;

    public Farm(int areaXSize, int areaYSize){
        this.areaXSize = areaXSize;
        this.areaYSize = areaYSize;
        int arrayXSize = areaXSize + barrenBorderWidth * 2;
        int arrayYSize = areaYSize + barrenBorderWidth * 2;
        squares = new Square[arrayXSize][arrayYSize];
        populateSquares(arrayXSize, arrayYSize);
    }

    public static Farm fromStdinInput(String input){
        InputParser parser = new InputParser();
        List<List<Integer>> barrenLandCoordinates = parser.parseInput(input);
        List<Rectangle> barrenLand = barrenLandCoordinates.stream().
                map(Farm::getRectangle).collect(Collectors.toList());
        Farm farm = new Farm(400, 600);
        barrenLand.forEach(farm::setBarren);
        ExpansionVisitor expansionVisitor = new ExpansionVisitor();
        expansionVisitor.groupSquaresIntoAreas(farm);
        return farm;
    }

    public static Rectangle getRectangle(List<Integer> rectangleCoordinatesFromInput){
        Integer bottomLeftXAsIs = rectangleCoordinatesFromInput.get(0);
        Integer bottomLeftYAsIs = rectangleCoordinatesFromInput.get(1);
        int topRightXAdjusted = rectangleCoordinatesFromInput.get(2) + 1;
        int topRightYAdjusted = rectangleCoordinatesFromInput.get(3) + 1;
        return new Rectangle(bottomLeftXAsIs,
                bottomLeftYAsIs,
                topRightXAdjusted,
                topRightYAdjusted);
    }

    private void populateSquares(int arrayXSize, int arrayYSize) {
        for(int x=0; x<arrayXSize; x++){
            for(int y=0; y<arrayYSize; y++){
                Square square = new Square(x - 1, y - 1);
                if(x==0 || y==0 || x==arrayXSize-1 || y==arrayYSize-1){
                    square.setFertile(false);
                }
                squares[x][y] = square;
            }
        }
    }

    public Square getSquare(int x, int y){
        return squares[x+ barrenBorderWidth][y+ barrenBorderWidth];
    }

    public void setBarren(Rectangle barrenArea){
        for(int x=barrenArea.getBottomLeftX(); x<barrenArea.getTopRightX(); x++){
            for(int y=barrenArea.getBottomLeftY(); y<barrenArea.getTopRightY(); y++){
                Square square = getSquare(x, y);
                square.setFertile(false);
            }
        }
    }

    public List<Long> getAreas(){
        Map<Integer, Long> areasByNumber = Arrays.stream(squares).
                flatMap(Arrays::stream).
                filter(Square::isFertile).
                collect(Collectors.groupingBy(Square::getAreaNumber, Collectors.counting()));
        return areasByNumber.values().stream().sorted().collect(Collectors.toList());
    }

    public String getAreaSizesAsString(){
        return getAreas().stream().map(Object::toString).collect(Collectors.joining(" "));
    }
}
