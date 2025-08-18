/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dto;
import java.util.List;
/**
 *
 * @author zzzdi
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
