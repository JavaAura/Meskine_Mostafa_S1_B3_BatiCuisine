package org.BatiCuisine.Model;

import java.util.UUID;

public class Client {
    private UUID clientID;
    private String name;
    private String address;
    private String phone;
    private boolean isProfessional;

    // Constructor
    public Client(UUID clientID, String name, String address, String phone, boolean isProfessional) {
        this.clientID = clientID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isProfessional = isProfessional;
    }

    // Default constructor
    public Client() {
        this.clientID = UUID.randomUUID();
    }

    // Getters and Setters
    public UUID getClientID() {
        return clientID;
    }

    public void setClientID(UUID clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isProfessional() {
        return isProfessional;
    }

    public void setProfessional(boolean isProfessional) {
        this.isProfessional = isProfessional;
    }

    // toString method
    @Override
    public String toString() {
        return "Client{" +
                "name : '" + name + '\'' +
                ", address : '" + address + '\'' +
                ", phone : '" + phone + '\'' +
                ", Professional : " + isProfessional +
                '}';
    }
}
