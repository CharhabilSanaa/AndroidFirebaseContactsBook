package com.charhabil.contactsbook;

/**author: charhabil**/
/**class user :getters and setters + constructeurs*/

public class User {
    private String name;
    private String number;
    private String emei;
    private String position;

    public User(String name, String number, String emei, String position) {
        this.name = name;
        this.number = number;
        this.emei = emei;
        this.position = position;
    }

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmei() {
        return emei;
    }

    public void setEmei(String emei) {
        this.emei = emei;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
