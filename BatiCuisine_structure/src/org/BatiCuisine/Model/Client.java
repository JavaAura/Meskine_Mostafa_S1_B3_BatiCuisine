package org.BatiCuisine.Model;

import java.util.UUID;

public class Client {
    private UUID id;
    private String name;
    private String address;
    private String phone;
    private boolean isProfessional;

    // Constructor
    public Client(UUID id, String name, String address, String phone, boolean isProfessional) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isProfessional = isProfessional;
    }

    // Default constructor
    public Client() {
        this.id = UUID.randomUUID();
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    // toString method (optional)
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
