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

}
