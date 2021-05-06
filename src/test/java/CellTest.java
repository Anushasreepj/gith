
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("SimplifiableAssertion")
public class CellTest {
    @Test
    public void InitialStateIsDead(){
        Cell cell = new Cell();
        assertEquals(false, cell.getState());
    }

    @Test
    public void DeadCellCanGainLife(){
        Cell cell = new Cell();
        cell.changeState();
        assertEquals(true, cell.getState());
    }

    @Test
    public void LiveCellCanDie(){
        Cell cell = new Cell();
        cell.changeState();
        cell.changeState();
        assertEquals(false, cell.getState());
    }

    @Test
    public void LiveCellDiesOfLoneliness(){
        Cell cell0 = new Cell();
        cell0.changeState();
        Cell cell1 = new Cell();
        cell0.changeState();

        assertEquals(false, cell0.getNextStageByNumberOfLiveNeighbors(0));
        assertEquals(false, cell1.getNextStageByNumberOfLiveNeighbors(1));
    }

    @Test
    public void LiveCellLivesOn() { //ToDieAnotherCycle
        Cell cell2 = new Cell();
        cell2.changeState();
        Cell cell3 = new Cell();
        cell3.changeState();

        assertEquals(true, cell2.getNextStageByNumberOfLiveNeighbors(2));
        assertEquals(true, cell3.getNextStageByNumberOfLiveNeighbors(3));
    }

    @Test
    public void LiveCellDiesOfBeingSquishedByOverpopulation(){
        Cell cell4 = new Cell();
        cell4.changeState();
        assertEquals(false, cell4.getNextStageByNumberOfLiveNeighbors(4));
    }

    @Test
    public void DeadCellGetsRebornThroughNeighboringReproduction(){
        Cell cell = new Cell();
        assertEquals(true, cell.getNextStageByNumberOfLiveNeighbors(3));
    }

    @Test
    public void DeadCellDoesntGetRebornBecauseOfAWrongNumberOfNeigbours(){
        Cell cell2 = new Cell();
        assertEquals(false, cell2.getNextStageByNumberOfLiveNeighbors(2));
        Cell cell4 = new Cell();
        assertEquals(false, cell4.getNextStageByNumberOfLiveNeighbors(4));
    }
}
