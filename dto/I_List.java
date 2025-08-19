package dto;

import java.util.List;

/* Interface for a group of objects
 */
/**
 *
 */
public interface I_List {

    boolean importData(String fileName);

    boolean enterGuest();

    boolean updateGuest(String guestID);

    Room getRoomByID(String id);
    
    List<Guest> searchGuest(String guestID);

    Guest deleteGuest(String reservationID);
    
    void displayAvaiableRoom();
    
    void listVacantRooms();

    void monthlyRevenueReport();

    void revenueReportByRoomType();

    boolean saveGuestInformation();
    
    void loadGuestInformation();
}
