import java.util.ArrayList;

public class RoomManager {

    private ArrayList<Room> rooms;

    public boolean createRoom(String roomId, int capacity){

        if(isRoom(roomId)){
            return false;
        }
        else{
            Room newRoom = new Room(roomId, capacity, new ArrayList<>());
            rooms.add(newRoom);
            return true;
        }

    }

    public void removeRoom(String roomId){

        if(isRoom(roomId)){
            Room room = getRoom(roomId);
            rooms.remove(room);
        }

    }

    public boolean isRoom(String roomId){

        return getRoom(roomId) != null;

    }

    public Room getRoom(String roomId){

        for(Room room: rooms){
            if(room.getRoomId().equals(roomId)){
                return room;
            }
        }
        return null;

    }

    public boolean isRoomOccupiedAt(String roomId, String time){
    // true only if room with 'roomId' exists and that room is occupied at 'time'
        if(isRoom(roomId)){
            Room room =  getRoom(roomId);
            ArrayList<String> occupiedTimes = room.getOccupiedTimes();
            return occupiedTimes.contains(time);
        }
        return false;

    }

    public boolean occupyRoomAt(String roomId, String time){

        if(isRoom(roomId) && !isRoomOccupiedAt(roomId, time)){
            Room room = getRoom(roomId);
            ArrayList<String> occupiedTimes = room.getOccupiedTimes();
            occupiedTimes.add(time);
            room.setOccupiedTimes(occupiedTimes);
            return true;
        }
        return false;

    }

    public void freeRoomAt(String roomId, String time){

        if(isRoom(roomId) && isRoomOccupiedAt(roomId, time)){
            Room room = getRoom(roomId);
            ArrayList<String> occupiedTimes = room.getOccupiedTimes();
            occupiedTimes.remove(time);
            room.setOccupiedTimes(occupiedTimes);
        }

    }

    public int getCapacityOfRoom(String roomId){
    // returns -1 if no room with roomId exists. Otherwise, it returns room capacity.
        if(isRoom(roomId)){
            Room room = getRoom(roomId);
            return room.getCapacity();
        }
        return -1;

    }

}