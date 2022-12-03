package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    List<Post> posts;

    public User(String username, String name, String lastname, int age, String gmail, String gender, String password) {
        this.username = username;
        this.gmail = gmail;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.password = password;
        posts = new ArrayList<>();
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

    public void getProfile() {
        System.out.println(
                "@" + username + "\n" +
                name + " " + lastname + "\n" +
                gmail + "\n"
        );

        if (posts.size() == 0) System.out.println("There are no publications yet");
        for (Post post :
                posts) {
            System.out.println(post.toString());
        }

        System.out.println(
                "1: followers (" + followers.size() + ")\n" +
                "2: followings (" + followings.size() + ")\n" +
                "3: black list (" + blocked.size() + ")"
           );
    }
}


