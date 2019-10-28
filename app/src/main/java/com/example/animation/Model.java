package com.example.animation;

public class Model {
    int image;
    String des;
    boolean test;
    int numeve;

    public Model(int image, String des, boolean test, int numeve) {
        this.image = image;
        this.des = des;
        this.test = test;
        this.numeve = numeve;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public int getNumeve() {
        return numeve;
    }

    public void setNumeve(int numeve) {
        this.numeve = numeve;
    }
}
