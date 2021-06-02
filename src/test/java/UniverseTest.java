
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UniverseTest {
    @Test
    public void defaultUniverseIsSixteenTimesSixteen(){
        Universe defaultUniverse = new Universe();
        assertEquals(16, defaultUniverse.grid.length);
        for (int i = 0; i < defaultUniverse.grid.length; i++) {
            assertEquals(16, defaultUniverse.grid[i].length);
        }
    }
    @Test
    public void customUniverseIsCustomSize(){
        int customX = 20;
        int customY = 20;
        Universe customUniverse = new Universe(customX, customY);

        Cell[][] grid = customUniverse.getGrid();

        assertEquals(customX, grid.length);

        for (int i = 0; i < grid.length; i++){
            assertEquals(customY, grid.length);
        }
    }
    @Test
    public void getsCorrectAmountOfNeighboursForCenter(){

        Universe testUniverse = createTestUniverse();


        assertEquals(6, testUniverse.getAliveNeighbours(1,1));
    }

    @Test
    public void getsCorrectAmountOfNeighboursForCorners(){

        Universe testUniverse = createTestUniverse();

        //Top Left
        assertEquals(2, testUniverse.getAliveNeighbours(0,2));

        //Top Right
        assertEquals(2, testUniverse.getAliveNeighbours(2,2));

        //Bottom Right
        assertEquals(2, testUniverse.getAliveNeighbours(2,0));

        //Bottom Left
        assertEquals(2, testUniverse.getAliveNeighbours(0,0));
    }

    @Test
    public void getsCorrectAmountOfNeighboursForCellsOnWallsButNotCorners(){
        Universe testUniverse = createTestUniverse();

        //Top
        assertEquals(3, testUniverse.getAliveNeighbours(0,1));

        //Bottom
        assertEquals(3, testUniverse.getAliveNeighbours(2,1));

        //Right
        assertEquals(3, testUniverse.getAliveNeighbours(1,2));

        //Left
        assertEquals(3, testUniverse.getAliveNeighbours(1,0));
    }


    public Universe createTestUniverse(){
        Universe testUniverse = new Universe(3,3);
        testUniverse.grid[0][0].resurrect();
        testUniverse.grid[0][1].resurrect();
        testUniverse.grid[0][2].resurrect();

        testUniverse.grid[1][0].kill();
        testUniverse.grid[1][1].resurrect();
        testUniverse.grid[1][2].kill();

        testUniverse.grid[2][0].resurrect();
        testUniverse.grid[2][1].resurrect();
        testUniverse.grid[2][2].resurrect();

        return testUniverse;
    }
}
