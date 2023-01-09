public class Cell {

    private final int row, col;
    private String cellType;

    public Cell(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public String getCellType()
    {
        return cellType;
    }

    public void setCellType(String cType)
    {
        this.cellType = cType;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }
}