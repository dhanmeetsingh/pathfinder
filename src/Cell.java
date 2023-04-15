public class Cell {
    private int row;
    private int col;
    private boolean isBlocked;
    private double gScore;
    private double fScore;
    private Cell parent;
    
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.isBlocked = false;
        this.gScore = Double.POSITIVE_INFINITY;
        this.fScore = Double.POSITIVE_INFINITY;
        this.parent = null;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public boolean isBlocked() {
        return isBlocked;
    }
    
    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
    
    public double getGScore() {
        return gScore;
    }
    
    public void setGScore(double gScore) {
        this.gScore = gScore;
    }
    
    public double getFScore() {
        return fScore;
    }
    
    public void setFScore(double fScore) {
        this.fScore = fScore;
    }
    
    public Cell getParent() {
        return parent;
    }
    
    public void setParent(Cell parent) {
        this.parent = parent;
    }
    
    public double distanceTo(Cell other) {
        int dx = Math.abs(this.col - other.col);
        int dy = Math.abs(this.row - other.row);
        return Math.sqrt(dx * dx + dy * dy);
    }
}
