package com.company;

import java.util.*;

public class SocialNetwork {
    static ArrayList<User> users;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        users = new ArrayList<>();
        int index;
        users.add(new User("syr","syrkhan", "madiyev", 18, "cheese@gmail.com", "male", "1", false));
        users.add(new User("edu","eduard", "nurlanov", 18, "male", "edu@gmail.com", "1", false));
        users.add(new User("yerka","yerkanat", "sultanov", 18, "yerka@gmail.com", "male", "1", false));
        users.add(new User("qazaqtynqarabalasy","yerbolat", "mukhan", 18, "yerbo@gmail.com", "male", "1", false));
        users.add(new User("mukhanov","zhanbolat", "mukhan", 18, "zhanbo@gmail.com", "male", "1", false));

        while (true) {
            System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("1. Registration");
            System.out.println("2. Log in");
            System.out.println("3. Stop");
            int choose = in.nextInt();
            if (choose == 1) {
                System.out.print("Type your username: ");
                String username = in.next();
                while (!containsUserName(username)) {
                    System.out.print("Please type your gmail again: ");
                    username = in.next();
                }
                String gmail = in.next();
                while (!containsGmail(gmail)) {
                    System.out.print("Please type your gmail again: ");
                    gmail = in.next();
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
                users.add(new User(username,name, lastname, age, gmail, gender, password, true));
            } else if (choose == 2) {
                System.out.print("Please input username: ");
                String username = in.next();
                index = findIndex(username);
                if (index == -1) {
                    System.out.println("The user not fount");
                } else {
                    System.out.print("Please input password: ");
                    String password = in.next();
                    if (users.get(index).password.equals(password)) {
                        login(index);
                    } else {
                        System.out.println("Password is not true, try again ");
                    }
                }
            } else {
                break;
            }
        }
    }

    public static void login(int ind) {
        int input;
        int num;
        while (true) {
            System.out.println("///////////////////////////////////"
                    + "\n1: Show my subscribes"
                    + "\n2: Follow "
                    + "\n3: Unfollow         "
                    + "\n4: Block          "
                    + "\n5: Show my profile    "
                    + "\n6: Add photo     "
                    + "\n7: Show friends profile    "
                    + "\n8: logout            "
                    + "\n//////////////////////////////////");
            System.out.print("Please select 1, 2, ..., 8 : ");
            input = in.nextInt();
            switch (input) {
                case 1 -> {
                    System.out.println("---->");
                    for (int i = 0; i < users.get(ind).followings.size(); i++) {
                        System.out.println(i + 1 + ": " + users.get(ind).followings.get(i).username);
                    }
                }
                case 2 -> {
                    follow(ind);
                }
                case 3 -> {
                   unFollow(ind);
                }
                case 4 -> {
                   block(ind);
                }
            }
        }
    }

    public static void follow(int ind) {
        int l = 0;
        for (int i = 0; i < users.size(); i++) {
            if(i != ind) {
                if (!users.get(ind).followings.contains(users.get(i))) {
                    System.out.println((l + 1) + " " + users.get(i).username);
                    l++;
                }
            }
        }
        System.out.println("Write one of usernames at the top: ");
        System.out.println("Write 'back' if you don't want to subscribe");
        String username = in.next();
        while(!containsUserName(username) || users.get(ind).containsFollowings(username) || username.equals(users.get(ind).username)) {
            if (!username.equals("back") ) {
                System.out.print("Please try again -->  ");
                username = in.next();
            }
            else {
                break;
            }
        }
        int index = findIndex(username);
        if(containsUserName(username)) {
            users.get(ind).followings.add(users.get(index));
            users.get(index).followers.add(users.get(ind));
        }
    }

    public static void unFollow(int ind) {
        System.out.println("Your subscribes --> ");
        for (int i = 0; i < users.get(ind).followings.size(); i++) {
            System.out.println(i+1 + ": " + users.get(ind).followings.get(i).username);
        }
        System.out.println("Write one of usernames at the top ");
        System.out.println("Write 'back' if you don't want to unsubscribe");
        String username = in.next();
        while(!users.get(ind).containsFollowings(username) || username.equals(users.get(ind).username)) {
            if (!username.equals("back")) {
                System.out.print("Please try again -->  ");
                username = in.next();
            }
            else {
                break;
            }
        }
        int index = findIndex(username);
        if(users.get(ind).containsFollowings(username)) {
            users.get(ind).followings.remove(users.get(index));
            users.get(index).followers.remove(users.get(ind));
        }
    }

    public static void block(int ind) {
        System.out.println("All users -->");
        int l = 0;
        for(int i = 0; i < users.size(); i++) {
            if(i != ind) {
                System.out.println(l +1 + ": " + users.get(i).username);
                l++;
            }
        }

        System.out.println("Write one of usernames at the top");
        System.out.println("Write 'back' if you don't want to unsubscribe");
        String username = in.next();

        while (!containsUserName(username) || username.equals(users.get(ind).username)) {
            if (!username.equals("back")) {
                System.out.print("Please try again -->  ");
                username = in.next();
            }
            else {
                break;
            }
        }
        int index = findIndex(username);
        if(containsUserName(username)) {
            if (users.get(ind).containsFollowings(username)) {
                users.get(ind).followings.remove(users.get(index));
                users.get(index).followers.remove(users.get(ind));
            }
            users.get(ind).blocked.add(users.get(index));
        }
    }

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

