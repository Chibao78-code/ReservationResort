package controlles;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import dto.Guest;
import dto.I_List;
import dto.Room;
import utils.Utils;

public class ReservationList extends ArrayList<Guest> implements I_List {

    private ArrayList<Room> roomList = new ArrayList();

    public ReservationList() {
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    } 
        @Override
    public boolean importData(String fileName) {
        boolean check = false;
        int count = 0;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                roomList = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    count++;
                    try {
                        String[] tmp = line.split(";");
                        if (tmp.length == 6) {
                            String id = tmp[0].trim();
                            String name = tmp[1].trim();
                            String type = tmp[2].trim();
                            double rate = Double.parseDouble(tmp[3].trim());
                            int capacity = Integer.parseInt(tmp[4].trim());
                            String furniture = tmp[5].trim();
                            if (getRoomByID(id) == null && rate > 0 && capacity > 0) {
                                roomList.add(new Room(id, name, type, rate, capacity, furniture));
                            }
                        }
                    } catch (Exception e) {
                    }
                }
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(roomList.size() + " rooms successfully loaded.");
        System.out.println(count - roomList.size() + " entries failed.");
        return check;
    }

       @Override
    public Room getRoomByID(String id) {
        Room r = null;
        for (Room room : roomList) {
            if (room.getId().equalsIgnoreCase(id)) { 
                r = room;
            }
        }
        return r;
    }

    @Override
    public void displayAvaiableRoom() {
        if (roomList.isEmpty()) {
            System.out.println("Room list is currently empty, not loaded yet.");
        } else {
            System.out.println("Active Room List.");
            System.out.println("RoomID | RoomName           | Type     | Rate  | Capacity | Furniture");
            System.out.println("-------+--------------------+----------+-------+----------+--------------------");
            for (Room r : roomList) {
                if (r.getCapacity() > 0) {
                    System.out.printf("%-7s| %-19s| %-9s|%6.1f |%9d | %-19s\n", r.getId(), r.getName(), r.getType(), r.getRate(), r.getCapacity(), r.getFurniture());
                }
            }
        }
    }    @Override
    public boolean enterGuest() {
        boolean check = false;
        try {
            String reservationID = Utils.generateString();
            System.out.println("Your reservationID: " + reservationID);
            boolean checkExist = true;
            String roomID = "";
            do {
                roomID = Utils.getString("Input room ID: ");
                int roomIndex = this.getRoomList().indexOf(new Room(roomID));
                if (roomIndex != -1) {
                    checkExist = false;
                } else {
                    System.out.println("Room ID not found!");
                }
            } while (checkExist);
            checkExist = true;
            Date startDate, now = null;
            //R101, 22:00
            //R101,  22:00
            do {
                startDate = Utils.getDate("Input start date: ");
                now = new Date();
                if (startDate.compareTo(now) < 0) {
                    System.out.println("Start Date must be a future date.");
                }
                if (checkReservation(roomID, startDate)) {
                    System.out.println("Duplicated reservation.Start date is not available.");
                } else {
                    checkExist = false;
                }
            } while (startDate.compareTo(now) < 0 || checkExist);
            
            Guest guest = new Guest(reservationID, roomID, startDate);
            guest.create();

            this.add(guest);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }
     public boolean checkReservation(String roomID, Date startDate) {
        boolean check = false;
        for (Guest g : this) {
            if (g.getRoomID().equalsIgnoreCase(roomID) && g.getStartDate().compareTo(startDate) == 0) {
                check = true;
                break;
            }
        }
        return check;
    }
      @Override
    public boolean updateGuest(String guestID) {
        boolean check = false;
        try {
            for (Guest guest : this) {
                if (guest.getNationalID().equalsIgnoreCase(guestID)) {
                    guest.update();
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }
        @Override
    public List<Guest> searchGuest(String guestID) {
        List<Guest> listResult = new ArrayList();
        for (Guest g : this) {
            if (g.getNationalID().equalsIgnoreCase(guestID)) {
                listResult.add(g);
            }
        }
        return listResult;
    }

    @Override
    public Guest deleteGuest(String reservationID) {
        Guest guest = null;
        int index = this.indexOf(new Guest(reservationID));
        if (index != -1) {
            Date now = new Date();
            if (this.get(index).getStartDate().compareTo(now) < 0) {
                System.out.println("The room booking for this guest cannot be cancelled.");
            } else {
                boolean confirm = Utils.confirmYesNo("Are you sure delete(y/n): ");
                if (confirm) {
                    guest = this.remove(index);
                } else {
                    System.out.println("You cancel.Fail");
                }
            }
        }
        return guest;
    }
    
    @Override
    public void listVacantRooms() {
        ArrayList<Room> listVacantRooms = new ArrayList();
        boolean checkExistRoom = false;
        for (Room r : roomList) {
            for (Guest g : this) {
                checkExistRoom = false;
                if (r.getId().equalsIgnoreCase(g.getRoomID())) {
                    checkExistRoom = true;
                }
            }
            if (checkExistRoom == false) {
                listVacantRooms.add(r);
            }
        }
        if (listVacantRooms.isEmpty()) {
            System.out.println("All rooms have currently been rented out- no rooms are available");
        } else {
            System.out.println("Available Room List.");
            System.out.println("RoomID | RoomName           | Type     | Rate  | Capacity | Furniture");
            System.out.println("-------+--------------------+----------+-------+----------+--------------------");
            for (Room r : listVacantRooms) {
                System.out.printf("%-7s| %-19s| %-9s|%6.1f |%9d | %-19s\n", r.getId(), r.getName(), r.getType(), r.getRate(), r.getCapacity(), r.getFurniture());
            }
        }
    }


}
