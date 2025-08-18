/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;
import java.io.Serializable;
import java.util.Date;
import utils.Utils;

/**
 *
 * @author zzzdi
 */
public class Guest implements Serializable,Workable {
    private String reservationID;
    private String nationalID;
    private String fullName;
    private Date birthdate;
    private String gender;
    private String phoneNumber;
    private String roomID;
    private int rentalDays;
    private Date startDate;
    private String nameOfCoTenant;

    @Override
    public boolean equals(Object obj) {
        Guest guest= (Guest)obj;
        return this.reservationID.equals(guest.getReservationID());
    }

    public Guest() {
    }

    public Guest(String reservationID) {
        this.reservationID = reservationID;
    }
    
    public Guest(String reservationID, String roomID, Date startDate) {
        this.reservationID = reservationID;
        this.roomID = roomID;
        this.startDate = startDate;
    }

    public Guest(String reservationID, String nationalID, String fullName, Date birthdate, String gender, String phoneNumber, String roomID, int rentalDays, Date startDate, String nameOfCoTenant) {
        this.reservationID = reservationID;
        this.nationalID = nationalID;
        this.fullName = fullName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.roomID = roomID;
        this.rentalDays = rentalDays;
        this.startDate = startDate;
        this.nameOfCoTenant = nameOfCoTenant;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }    

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getNameOfCoTenant() {
        return nameOfCoTenant;
    }

    public void setNameOfCoTenant(String nameOfCoTenant) {
        this.nameOfCoTenant = nameOfCoTenant;
    }

    @Override
    public String toString() {
        return "Guest{" + "nationalID=" + nationalID + ", fullName=" + fullName + ", birthdate=" + birthdate + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", roomID=" + roomID + ", rentalDays=" + rentalDays + ", startDate=" + startDate + ", nameOfCoTenant=" + nameOfCoTenant + '}';
    }

    @Override
    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public boolean create() {
        boolean check = false;
        try {
            nationalID = Utils.getString("Input nationalID: ", Utils.NATIONAL_ID_VALID);
            fullName = Utils.getString("Input full Name: ", Utils.NAME_VALID);
            birthdate = Utils.getDate("Input birthdate: ");
            gender = Utils.getString("Input gender: ");
            phoneNumber = Utils.getString("Input phone number: ", Utils.PHONE_VALID);
            rentalDays = Utils.getInt("Input number of rent days: ", Utils.MIN, Utils.MAX);
            nameOfCoTenant = Utils.getString("Input ten partner: ");
            check = true;
        } catch (Exception e) {
        }
        return check;
    }

    @Override
    public boolean update() {
        boolean check = false;
        try {
            fullName = Utils.updateString("Update full Name: ",fullName, Utils.NAME_VALID);
            birthdate = Utils.updateDate("Update birthdate: ", birthdate);
            gender = Utils.updateString("Update gender: ", gender);
            phoneNumber = Utils.updateString("Update phone number: ", phoneNumber, Utils.PHONE_VALID);
            rentalDays = Utils.updateInt("Update number of rent days: ", Utils.MIN, Utils.MAX, rentalDays);
            nameOfCoTenant = Utils.updateString("Update ten partner: ", nameOfCoTenant);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }

}
