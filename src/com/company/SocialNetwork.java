package com.company;

import java.util.*;

public class SocialNetwork {
    static ArrayList<User> users;
    static Scanner in = new Scanner(System.in);
    static User user;
    static boolean isLogged;

    public static void main(String[] args) {
        users = new ArrayList<>();
        int index;
        users.add(new User("syr","syrkhan", "madiyev", 18, "cheese@gmail.com", "male", "1"));
        users.add(new User("edu","eduard", "nurlanov", 18, "male", "edu@gmail.com", "1"));
        users.add(new User("yerka","yerkanat", "sultanov", 18, "yerka@gmail.com", "male", "1"));
        users.add(new User("qazaqtynqarabalasy","yerbolat", "mukhan", 18, "yerbo@gmail.com", "male", "1"));
        users.add(new User("mukhanov","zhanbolat", "mukhan", 18, "zhanbo@gmail.com", "male", "1"));

        while (true) {
            if (!isLogged) {
                System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||");
                System.out.println("1. Registration");
                System.out.println("2. Log in");
                System.out.println("3. Stop");
                int choose = in.nextInt();
                if (choose == 1) {
                    System.out.print("Type your username: ");
                    String username = in.next();
                    while (true) {
                        if (containsUserName(username)){
                            System.out.print("This username is busy! Try again");
                            username = in.next();
                        }else {
                            break;
                        }
                    }
                    String gmail = in.next();
                    while (true) {
                        if (containsGmail(gmail)){
                            System.out.print("This gmail is busy! Try again");
                            gmail = in.next();
                        }else {
                            break;
                        }
                    }
                    System.out.print("Type your name: ");
                    String name = in.next();
                    System.out.print("Type your surname: ");
                    String lastname = in.next();
                    System.out.print("Type your age: ");
                    int age = in.nextInt();
                    System.out.print("Type your gender: ");
                    String gender = in.next();
                    System.out.print("Type your password: ");
                    String password = in.next();
                    users.add(new User(username,name, lastname, age, gmail, gender, password));
                } else if (choose == 2) {
                    System.out.print("Please input username: ");
                    String username = in.next();
                    index = findIndex(username);
                    if (index == -1) {
                        System.out.println("The user is not found");
                    } else {
                        System.out.print("Please input password: ");
                        String password = in.next();
                        if (users.get(index).password.equals(password)) {
                            user = users.get(index);
                            isLogged = true;
                        } else {
                            System.out.println("Password is not correct! Try again");
                        }
                    }
                } else {
                    break;
                }
            } else {
                while (true) {
                    System.out.println("""
                            -------------------------
                            1: Show my profile   \s
                            2: Add photo    \s
                            3: Search user  \s
                            4: logout           \s
                            -------------------------""");
                    System.out.print("Please select 1, 2, 3, 4 : ");
                    int input = in.nextInt();
                    if (input == 4) {
                        user = null;
                        isLogged = false;
                        break;
                    }
                    switch (input) {
                        case 1 -> {
                            myProfile();
                        }
                        case 2 -> {

                        }
                        case 3 -> {
                            search();
                        }

                    }
                }
            }
        }
    }

    public static void myProfile() {
        user.getProfile();
        System.out.print("Please select operation -->  ");
        int choose = in.nextInt();

        switch (choose) {
            case 1 -> {
                System.out.println("-------------------------");
                for(User follower: user.followers) {
                    System.out.println("--> " + follower.username);
                }
                System.out.println("-------------------------");
            }
            case 2 -> {
                System.out.println("-------------------------");
                for(User following: user.followings) {
                    System.out.println("--> " + following.username);
                }
                System.out.println("-------------------------");
            }
            case 3 -> {
                System.out.println("-------------------------");
                for(User blocked: user.blocked) {
                    System.out.println("--> " + blocked.username);
                }
                System.out.println("-------------------------");
            }
        }
    }



    public static void search() {
        System.out.println("All users -->");
        for(User temp: users) {
            if(temp!=user) {
                System.out.println("--> " + temp.username);
            }
        }
        System.out.println("Write one of usernames at the top ");
        System.out.println("Write 'back' if you don't want to unsubscribe");
        String username = in.next();

        if (username.equals("back")) return;

        while(!containsUserName(username)
                || username.equals(user.username))
        {
                System.out.print("Please try again -->  ");
                username = in.next();
        }
        int index = findIndex(username);

        while (true) {
            users.get(index).getProfile();

            String follow = users.get(index).followers.contains(user) ? "Unfollow" : "Follow";
            String block = (user.blocked.contains(users.get(index))) ? "Unblock" : "Block";
            System.out.println(user.blocked.contains(user));
            System.out.printf("""
                            4: %s   \s
                            5: %s   \s
                            6: Like \s
                            7: Comment          \s
                            8: Back   \s
                            """,
                    follow,block
            );

            System.out.print("Please select operation -->  ");
            int choose = in.nextInt();

            if (choose == 8) return;

            switch (choose) {
                case 1 -> {
                    System.out.println("-------------------------");
                    for (User followers : users.get(index).followers) {
                        System.out.println("--> " + followers.username);
                    }
                    System.out.println("-------------------------");
                }
                case 2 -> {
                    System.out.println("-------------------------");
                    for (User following : users.get(index).followings) {
                        System.out.println("--> " + following.username);
                    }
                    System.out.println("-------------------------");
                }
                case 3 -> {
                    System.out.println("-------------------------");
                    for (User blocked : users.get(index).blocked) {
                        System.out.println("--> " + blocked.username);
                    }
                    System.out.println("-------------------------");
                }
                case 4 -> {
                    if(follow.equals("Follow")) {
                        user.followings.add(users.get(index));
                        users.get(index).followers.add(user);
                    }else {
                        user.followings.remove(users.get(index));
                        users.get(index).followers.remove(user);
                    }
                }
                case 5 -> {

                    if(block.equals("Block")) {
                        if (user.containsFollowings(username)) {
                            user.followings.remove(users.get(index));
                            users.get(index).followers.remove(user);
                        }
                        user.blocked.add(users.get(index));
                    }else {
                        user.blocked.remove(users.get(index));
                    }
                }
                case 6 -> {

                }
                case 7 -> {

                }
            }
        }
    }







//    public static void follow() {
//        for (User temp : users) {
//            if (temp != user
//                    && !user.containsFollowings(temp.username)
//                    && !user.containsBlocked(temp.username)) {
//                System.out.println(temp.username);
//            }
//        }
//
//        System.out.println("Write one of usernames at the top: ");
//        System.out.println("Write 'back' if you don't want to subscribe");
//        String username = in.next();
//
//        if (username.equals("back")) return;
//
//
//        while(!containsUserName(username)
//                || user.containsFollowings(username)
//                || username.equals(user.username)
//                || user.containsBlocked(username))
//        {
//                System.out.print("Please try again -->  ");
//                username = in.next();
//        }
//        int index = findIndex(username);
//        user.followings.add(users.get(index));
//        users.get(index).followers.add(user);
//    }

//    public static void unFollow() {
//        System.out.println("Your subscribes --> ");
//        for (int i = 0; i < user.followings.size(); i++) {
//            System.out.println(i+1 + ": " + user.followings.get(i).username);
//        }
//        System.out.println("Write one of usernames at the top ");
//        System.out.println("Write 'back' if you don't want to unsubscribe");
//        String username = in.next();
//        while(!user.containsFollowings(username) || username.equals(user.username)) {
//            if (!username.equals("back")) {
//                System.out.print("Please try again -->  ");
//                username = in.next();
//            }
//            else {
//                break;
//            }
//        }
//        int index = findIndex(username);
//        if(user.containsFollowings(username)) {
//            user.followings.remove(users.get(index));
//            users.get(index).followers.remove(user);
//        }
//    }
//
//    public static void block() {
//        System.out.println("All users -->");
//        int l = 0;
//        for(int i = 0; i < user.followings.size(); i++) {
//
//        }
//
//        System.out.println("Write one of usernames at the top");
//        System.out.println("Write 'back' if you don't want to unsubscribe");
//        String username = in.next();
//
//        while (!containsUserName(username)
//                || username.equals(user.username)
//                || user.containsBlocked(username)) {
//            if (!username.equals("back")) {
//                System.out.print("Please try again -->  ");
//                username = in.next();
//            }
//            else {
//                break;
//            }
//        }
//        int index = findIndex(username);
//        if(containsUserName(username)) {
//            if (user.containsFollowings(username)) {
//                user.followings.remove(users.get(index));
//                users.get(index).followers.remove(user);
//            }
//            user.blocked.add(users.get(index));
//        }
//    }

    public static int findIndex(String username) {
        int j = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)) {
                j = i;
                break;
            }
        }
        return j;
    }

    public static boolean containsUserName(String username) {
        for (User user : users) {
            if (user.username.equals(username)) return true;
        }
        return false;
    }

    public static boolean containsGmail(String gmail) {
        for (User user : users) {
            if (user.gmail.equals(gmail)) return true;
        }
        return false;
    }
}

