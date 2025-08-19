/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 */
public class Room implements Serializable,Workable {

    private String id;
    private String name;
    private String type;
    private double rate;
    private int capacity;
    private String furniture;

    @Override
    public boolean equals(Object obj) {
        Room room= (Room)obj;
        return this.id.equals(room.getId());
    }
    
    public Room() {
    }

    public Room(String id) {
        this.id = id;
    }    
    
    public Room(String id, String name, String type, double rate, int capacity, String furniture) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rate = rate;
        this.capacity = capacity;
        this.furniture = furniture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", name=" + name + ", type=" + type + ", rate=" + rate + ", capacity=" + capacity + ", furniture=" + furniture + '}';
    }
    

    @Override
    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public boolean create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
