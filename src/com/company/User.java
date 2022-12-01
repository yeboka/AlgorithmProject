package com.company;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String username;
    String name;
    String lastname;
    String gmail;
    ArrayList<User> followers;
    ArrayList<User> followings;
    ArrayList<User> blocked;
    int age;
    String gender;
    String password;
    List<String> photos;

    public User(String username, String name, String lastname, int age, String gmail, String gender, String password, boolean addPhoto) {
        this.username = username;
        this.gmail = gmail;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.password = password;
        photos = new ArrayList<>();
        followers = new ArrayList<>();
        followings = new ArrayList<>();
        blocked = new ArrayList<>();
    }

    public boolean containsFollowings(String username) {
        for(User user: followings ) {
            if(user.username.equals(username)) return true;
        }
        return false;
    }

    public boolean containsFollowers(String username) {
        for(User user: followings ) {
            if(user.username.equals(username)) return true;
        }
        return false;
    }

    public boolean containsBlocked(String username) {
        for(User user: followings ) {
            if(user.username.equals(username)) return true;
        }
        return false;
    }
}


