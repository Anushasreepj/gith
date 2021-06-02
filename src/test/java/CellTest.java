
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CellTest {
    @Test
    public void InitialStateIsDead(){
        Cell cell = new Cell();
        assertEquals(LiveState.DEAD, cell.getLiveState());
    }

    @Test
    public void DeadCellCanGainLife(){
        Cell cell = new Cell();
        cell.resurrect();
        assertEquals(LiveState.ALIVE, cell.getLiveState());
    }

    @Test
    public void LiveCellCanDie(){
        Cell cell = new Cell();
        cell.resurrect();
        cell.kill();
        assertEquals(LiveState.DEAD, cell.getLiveState());
    }

    @Test
    public void LiveCellDiesOfLoneliness(){
        Cell cell0 = new Cell();
        cell0.resurrect();
        Cell cell1 = new Cell();
        cell0.resurrect();

        assertEquals(LiveState.DEAD, cell0.getNextStageByNumberOfLiveNeighbors(0));
        assertEquals(LiveState.DEAD, cell1.getNextStageByNumberOfLiveNeighbors(1));
    }

    @Test
    public void LiveCellLivesOn() { //ToDieAnotherCycle
        Cell cell2 = new Cell();
        cell2.resurrect();
        Cell cell3 = new Cell();
        cell3.resurrect();

        assertEquals(LiveState.ALIVE, cell2.getNextStageByNumberOfLiveNeighbors(2));
        assertEquals(LiveState.ALIVE, cell3.getNextStageByNumberOfLiveNeighbors(3));
    }

    @Test
    public void LiveCellDiesOfBeingSquishedByOverpopulation(){
        Cell cell4 = new Cell();
        cell4.resurrect();
        assertEquals(LiveState.DEAD, cell4.getNextStageByNumberOfLiveNeighbors(4));
    }

    @Test
    public void DeadCellGetsRebornThroughNeighboringReproduction(){
        Cell cell = new Cell();
        assertEquals(LiveState.ALIVE, cell.getNextStageByNumberOfLiveNeighbors(3));
    }

    @Test
    public void DeadCellDoesntGetRebornBecauseOfAWrongNumberOfNeigbours(){
        Cell cell2 = new Cell();
        assertEquals(LiveState.DEAD, cell2.getNextStageByNumberOfLiveNeighbors(2));
        Cell cell4 = new Cell();
        assertEquals(LiveState.DEAD, cell4.getNextStageByNumberOfLiveNeighbors(4));
    }
}
