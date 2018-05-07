package BattleShipGame;

public class Submarine implements Ship {
    int length = 3;
    int hits = 0;
    int[] location;

    public boolean isAlive(){
        return (hits < length);
    }

    public Submarine(){
    }

    public int[] getLocation(){
        return location;
    }

    public int getLength(){
        return length;
    }

    public String toString(){
        return "Submarine";
    }

    public void setLocation(int[] location){
        this.location = location;
    }
}
