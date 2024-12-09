package model;

public class UtilityCalendar {

    private String[] mesi;
    private boolean isFirstDayFound;
    private int currDay;
    private int lastDay;
    private int[][] matrix;
    private int[] firstRow;

    public void setFirstRow(int[] firstRow) {
        this.firstRow = firstRow;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setLastDay(int lastDay) {
        this.lastDay = lastDay;
    }

    public void setCurrDay(int currDay) {
        this.currDay = currDay;
    }

    public void setFirstDayFound(boolean firstDayFound) {
        this.isFirstDayFound = firstDayFound;
    }

    public void setMesi(String[] mesi) {
        this.mesi = mesi;
    }

    // costrutt
    public UtilityCalendar(int currday, boolean firstDayFound, String[] mesi, int[][] matrix) {
        setLastDay(0);
        setCurrDay(currday);
        setFirstDayFound(firstDayFound);
        setMesi(mesi);
        setMatrix(matrix);
    }

    public int[] getFirstRow() {
        return firstRow;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getLastDay() {
        return lastDay;
    }

    public int getCurrDay() {
        return currDay;
    }

    public boolean IsFirstDayFound() {
        return isFirstDayFound;
    }

    public void AddOneDay() {
        setCurrDay(getCurrDay() + 1);
    }

    public String[] getMesiArr() {
        return mesi;
    }

    public void ResetAll() {
        setFirstDayFound(false);
        setCurrDay(1);
        setLastDay(0);
        setFirstRow(new int[7]);
        setMatrix(new int[6][7]);
    }
}
