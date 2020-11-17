import java.util.ArrayList;

public class Room {

    private String roomId;
    private int capacity;
    private ArrayList<String> occupiedTimes;

    public Room(String roomId, int capacity, ArrayList<String> occupiedTimes){

        this.roomId = roomId;
        this.capacity = capacity;
        this.occupiedTimes = occupiedTimes;

    }

    public int getCapacity() {
        return capacity;
    }

    public String getRoomId() {
        return roomId;
    }

    public ArrayList<String> getOccupiedTimes() {
        return occupiedTimes;
    }

    public void setOccupiedTimes(ArrayList<String> occupiedTimes) {
        this.occupiedTimes = occupiedTimes;
    }

}
