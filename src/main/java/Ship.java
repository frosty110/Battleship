public interface Ship {

    public int length = 3;
    public int hits = 0;
    public boolean isAlive();

    public int getLength();

    public int[] getLocation();

    public void setLocation(int[] location);

    @Override
    public String toString();
}
