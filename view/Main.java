package view;

import java.util.Calendar;
import java.util.List;
import dto.I_List;
import dto.I_Menu;
import controllers.Menu;
import controllers.ReservationList;
import dto.Guest;
import dto.Room;
import utils.Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 */
public class Main {

    public static void main(String args[]) {
        I_Menu menu = new Menu();
        menu.addItem("1. Import Room Data from Text File.");
        menu.addItem("2. Display Available Room List.");
        menu.addItem("3. Enter Guest Information.");
        menu.addItem("4. Update Guest Stay Information.");
        menu.addItem("5. Search Guest by National ID.");
        menu.addItem("6. Delete Guest Reservation Before Arrival.");
        menu.addItem("7. List Vacant Rooms.");
        menu.addItem("8. Monthly Revenue Report.");
        menu.addItem("9. Revenue Report by Room Type.");
        menu.addItem("10. Save Guest Information.");
        menu.addItem("11. Quit.");
        int choice;
        boolean cont = true;
        String filePath = "src/data/Active_Room_List.txt";
        I_List list = new ReservationList();
        list.loadGuestInformation();
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    boolean check;
                    check = list.importData(filePath);
                    if (check) {
                        System.out.println("Import data success!");
                    } else {
                        System.out.println("Import data fail!");
                    }
                    break;
                case 2:
                    list.displayAvaiableRoom();
                    break;
                case 3:
                    if (list.enterGuest()) {
                        System.out.println("Add guest success");
                    } else {
                        System.out.println("Add guest fail!");
                    }
                    break;
                case 4:
                    String guestID = Utils.getString("Input update guestID: ", Utils.NATIONAL_ID_VALID);
                    if (list.updateGuest(guestID)) {
                        System.out.println("Update success!");
                    } else {
                        System.out.println("Update fail!");
                    }
                    break;
                case 5:
                    String guestIDSearch = Utils.getString("Input guestID: ", Utils.NATIONAL_ID_VALID);
                    List<Guest> listResult = list.searchGuest(guestIDSearch);
                    if (listResult.isEmpty()) {
                        System.out.println("Not guest found with the request ID : " + guestIDSearch);
                    } else {
                        for (Guest g : listResult) {
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("Guest information [Reservation ID: " + g.getReservationID() + "]");
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("Guest information [National ID: " + g.getNationalID() + "]");
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println(" Full name     : " + g.getFullName());
                            System.out.println(" Phone number  : " + g.getPhoneNumber());
                            System.out.println(" Birth date    : " + Utils.sdf.format(g.getBirthdate()));
                            System.out.println(" Gender        : " + g.getGender());
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println(" Rental room   : " + g.getRoomID());
                            System.out.println(" Check in      : " + Utils.sdf.format(g.getStartDate()));
                            System.out.println(" Rental days   : " + g.getRentalDays());
                            String dateCheckOut = null;
                            try {
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(g.getStartDate());
                                cal.add(Calendar.DAY_OF_MONTH, g.getRentalDays());
                                dateCheckOut = Utils.sdf.format(cal.getTime());
                            } catch (Exception e) {
                                System.out.println("Invalid date check out.");
                            }
                            System.out.println(" Check out     : " + dateCheckOut);
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("Room information:");
                            Room r = list.getRoomByID(g.getRoomID());
                            System.out.println(" + ID       : " + r.getId());
                            System.out.println(" + Room     : " + r.getName());
                            System.out.println(" + Type     : " + r.getType());
                            System.out.println(" + Daly rate: " + r.getRate()+ "$");
                            System.out.println(" + Capacity : " + r.getCapacity());
                            System.out.println(" + Funiture : " + r.getFurniture());
                        }
                    }
                    break;
                case 6:
                    String reservationID = Utils.getString("Input Reservation ID: ");
                    Guest g = list.deleteGuest(reservationID);
                    if(g != null){
                        System.out.println("Delete success!");
                    }else{
                        System.out.println("Delete fail!");
                    }
                    break;
                case 7:
                    list.listVacantRooms();
                    break;
                case 8:
                    list.monthlyRevenueReport();
                    break;
                case 9:
                    list.revenueReportByRoomType();
                    break;
                case 10:
                    if(list.saveGuestInformation()){
                        System.out.println("Save file success");
                    }else{
                        System.out.println("Save file fail!");
                    }
                    break;
                case 11:
                    cont = menu.confirmYesNo("Are you continues(Y/N)");
                    break;
            }
        } while (choice >= 0 && choice <= 11 && cont);
    }
}
