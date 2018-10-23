package com.example.admin.myapplication;

public class Donor {
    int id;
    String name, gender, bloodGroup, mobileNumber;
    double age, quantity;

    public Donor(int id, String name, String gender, String bloodGroup, String mobileNumber, double aDouble, double age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.quantity = quantity;
    }

    public Donor() {
    }

    public Donor(String bloodGroup, double quantity) {
        this.bloodGroup = bloodGroup;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public double getAge() {
        return age;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", age=" + age +
                ", quantity=" + quantity +
                '}';
    }
}
