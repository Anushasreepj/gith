class Cell {
    private LiveState liveState;

    public Cell() {
        this.liveState = LiveState.DEAD;
    }

    public LiveState getLiveState() {
        return this.liveState;
    }

    public void kill() {
        this.liveState = LiveState.DEAD;
    }

    public void resurrect() {
        this.liveState = LiveState.ALIVE;
    }

    public LiveState getNextStageByNumberOfLiveNeighbors(int liveNeighbours) {
        if (liveCellDiesOfOverpopulation(liveNeighbours) || liveCellDiesOfLoneliness(liveNeighbours)) {
            return LiveState.DEAD;
        }
        else if (liveCellLivesOn(liveNeighbours) || deadCellGetsReborn(liveNeighbours)) {
            return LiveState.ALIVE;
        }
        else {
            return LiveState.DEAD;
        }
    }

    private boolean liveCellDiesOfLoneliness(int liveNeighbours) {
        return this.liveState == LiveState.ALIVE && liveNeighbours < 2;
    }

    private boolean liveCellDiesOfOverpopulation(int liveNeighbours) {
        return this.liveState == LiveState.ALIVE && liveNeighbours > 3;
    }

    private boolean liveCellLivesOn(int liveNeighbours) {
        return this.liveState == LiveState.ALIVE && (liveNeighbours == 2 || liveNeighbours == 3);
    }

    private boolean deadCellGetsReborn(int liveNeighbours) {
        return this.liveState == LiveState.DEAD && liveNeighbours == 3;
    }
}
