package com.company;

public class User {
    public String username;
    String name;
    String lastname;
    String gmail;
    MyList<User> followers;
    MyList<User> followings;
    MyList<User> blocked;
    int age;
    String gender;
    String password;
    MyList<Post> posts;

    public User(String username, String name, String lastname, int age, String gmail, String gender, String password) {
        this.username = username;
        this.gmail = gmail;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.password = password;
        posts = new MyList<>();
        followers = new MyList<>();
        followings = new MyList<>();
        blocked = new MyList<>();
    }

    public boolean equals (User user) {
        return this.username.equals(user.username);
    }

    public boolean containsFollowings(String username) {
        for(int i = 0; i < followings.size(); i++) {
            if(followings.get(i).username.equals(username)) return true;
        }
        return false;
    }

    public boolean containsFollowers(String username) {
        for(int i = 0; i < followers.size(); i++) {
            if(followers.get(i).username.equals(username)) return true;
        }
        return false;
    }

    public boolean containsBlocked(String username) {
        for(int i = 0; i < blocked.size(); i++) {
            if(blocked.get(i).username.equals(username)) return true;
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
        for (int i = 0; i < posts.size(); i++) {
            System.out.println(posts.get(i).toString());
        }

        System.out.println(
                "\n1: followers (" + followers.size() + ")\n" +
                "2: followings (" + followings.size() + ")\n" +
                "3: black list (" + blocked.size() + ")\n"
           );
    }
}


