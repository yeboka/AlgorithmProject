package com.company;

import java.util.*;

public class SocialNetwork {

    static String[] galery = {
            "|\\/\\/\\/|  \n" +
                    " |      |  \n" +
                    " |      |  \n" +
                    " | (o)(o)  \n" +
                    " C      _) \n" +
                    "  | ,___|  \n" +
                    "  |   /    \n" +
                    " /____\\    \n" +
                    "/      \\",
            "/\\ /\\  /\\      \n" +
                    "| V  \\/  \\---. \n" +
                    "\\_        /   \n" +
                    " (o)(o)  <__. \n" +
                    " _C         /  \n" +
                    "/____,   )  \\  \n" +
                    "\\     /----' \n" +
                    "   ooooo       \n" +
                    "  /     \\",
            "    &__ \n" +
                    "  /     \\     \n" +
                    " |       |    \n" +
                    " |  (o)(o)    \n" +
                    " C   .---_)   \n" +
                    "  |  \\__/     \n" +
                    "  /_____\\     \n" +
                    " /_____/ \\    \n" +
                    "/         \\",
            "  ,--./,-.\n" +
                    " / #      \\\n" +
                    "|          |\n" +
                    " \\        /\n" +
                    "  `._,._,'",
            "      )  (\n" +
                    "     (   ) )    \n" +
                    "      ) ( (     \n" +
                    "    _______)_   \n" +
                    " .-'---------|  \n" +
                    "( C|/\\/\\/\\/\\/|\n" +
                    " '-./\\/\\/\\/\\/|\n" +
                    "   '_________'\n" +
                    "    '-------'"
    };

    static Scanner in = new Scanner(System.in);
    static MyList<User> users;
    static MyList<Post> allPosts;
    static User user;
    static boolean isLogged;

    public static void main(String[] args) {
        users = new MyList<>();
        allPosts = new MyList<>();
        int index;
        users.add(new User("syr", "syrkhan", "madiyev", 18, "cheese@gmail.com", "male", "1"));
        users.add(new User("edu", "eduard", "nurlanov", 18, "male", "edu@gmail.com", "1"));
        users.add(new User("yerka", "yerkanat", "sultanov", 18, "yerka@gmail.com", "male", "1"));
        users.add(new User("qazaqtynqarabalasy", "yerbolat", "mukhan", 18, "yerbo@gmail.com", "male", "1"));
        users.add(new User("mukhanov", "zhanbolat", "mukhan", 18, "zhanbo@gmail.com", "male", "1"));

        while (true) {
            if (!isLogged) {
                System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||");
                System.out.println("1. Registration");
                System.out.println("2. Log in");
                System.out.println("3. Stop");
                int choose = in.nextInt();
                if (choose == 1) {
                    System.out.print("Type your username: ");
                    String username = in.next();
                    while (true) {
                        if (containsUserName(username)) {
                            System.out.print("This username is busy! Try again");
                            username = in.next();
                        } else {
                            break;
                        }
                    }
                    System.out.print("Type your gmail: ");
                    String gmail = in.next();
                    while (true) {
                        if (containsGmail(gmail) || !gmail.contains("@")) {
                            System.out.print("This gmail is busy or wrong! Try again");
                            gmail = in.next();
                        } else {
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
                    users.add(new User(username, name, lastname, age, gmail, gender, password));
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
                            3: Get K posts with a maximum number of likes\s
                            4: Search user  \s
                            5: Max common subscribers
                            6: logout           \s
                            -------------------------""");
                    System.out.print("Please select 1, 2, 3, 4 : ");

                    int input = in.nextInt();
                    if (input == 6) {
                        user = null;
                        isLogged = false;
                        break;
                    }
                    switch (input) {
                        case 1 -> {
                            myProfile();
                        }
                        case 2 -> {
                            addPhoto();
                        }
                        case 3 -> {
                            getKPosts();
                        }
                        case 4 -> {
                            search();
                        }
                        case 5 -> {
                            maxCommonSubscribers();
                        }
                    }
                }
            }
        }
    }

    public static void addPhoto() {
        System.out.println("Choose image to add: ");
        for (int i = 0; i < galery.length; i++) {
            System.out.println((i + 1) + ".\n" + galery[i] + "\n");
        }

        int choice = in.nextInt();
        while (choice < 1 || choice > galery.length) {
            System.out.println("invalid input :( Try again!");
            choice = in.nextInt();
        }
        in.nextLine();
        System.out.println("Enter description");
        String desc = in.nextLine();

        Post post = new Post(galery[choice - 1], desc, user.username);
        user.posts.add(post);
        allPosts.add(post);
    }

    public static void getKPosts() {
        System.out.println("\nEnter number of posts -->");
        int k = in.nextInt();
        while (k < 1 || k > allPosts.size()) {
            System.out.println("invalid input :( Try again!");
            k = in.nextInt();
        }
        MaxHeap maxHeap = new MaxHeap((int) Math.log(k) + 1);

        for (int i = 0; i < allPosts.size(); i++) {
            maxHeap.add(allPosts.get(i));
        }

        for (int i = 0; i < k; i++) {
            Post post = maxHeap.poll();
            System.out.println((i + 1) + ". " + post.getAuthor() + "\n" +
                    post);
        }
    }

    public static void maxCommonSubscribers() {
        MyList<User> maxUser = new MyList<>();
        int max = 0;
        for (int i = 0; i < user.followers.size(); i++) {
            int counter = 0;
            for (int j = 0; j < user.followers.size(); j++) {
                if (user.followers.get(i).followers.contains(user.followers.get(j)))
                    counter++;
            }
            if (counter > 0 && max == counter) {
                maxUser.add(user);
            } else if (max < counter) {
                maxUser = new MyList<>();
                maxUser.add(user.followers.get(i));
            }
        }
        for (int i = 0; i < maxUser.size(); i++) {
            System.out.println("--> " + maxUser.get(i).username);
        }
    }

    public static void myProfile() {
        user.getProfile();
        System.out.print("Please select operation -->  ");
        int choose = in.nextInt();

        switch (choose) {
            case 1 -> {
                System.out.println("-------------------------");
                for (int i = 0; i < user.followers.size(); i++) {
                    System.out.println("--> " + user.followers.get(i).username);
                }
                System.out.println("-------------------------");
            }
            case 2 -> {
                System.out.println("-------------------------");
                for (int i = 0; i < user.followings.size(); i++) {
                    System.out.println("--> " + user.followings.get(i).username);
                }
                System.out.println("-------------------------");
            }
            case 3 -> {
                System.out.println("-------------------------");
                for (int i = 0; i < user.blocked.size(); i++) {
                    System.out.println("--> " + user.blocked.get(i).username);
                }
                System.out.println("-------------------------");
            }
        }
    }


    public static void search() {
        System.out.println("All users -->");
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) != user) {
                System.out.println("--> " + users.get(i).username);
            }
        }
        System.out.println("Write one of usernames at the top ");
        System.out.println("Write 'back' if you don't want to unsubscribe");
        String username = in.next();

        if (username.equals("back")) return;

        while (!containsUserName(username)
                || username.equals(user.username)) {
            System.out.print("Please try again -->  ");
            username = in.next();
        }
        int index = findIndex(username);
        User tempUser = users.get(index);
        while (true) {
            tempUser.getProfile();

            String follow = tempUser.followers.contains(user) ? "Unfollow" : "Follow";
            String block = (user.blocked.contains(tempUser)) ? "Unblock" : "Block";
            System.out.printf("""
                            4: %s   \s
                            5: %s   \s
                            6: Like \s
                            7: Comment          \s
                            8: Back   \s
                            """,
                    follow, block
            );

            System.out.print("Please select operation -->  ");
            int choose = in.nextInt();

            if (choose == 8) return;

            switch (choose) {
                case 1 -> {
                    System.out.println("-------------------------");
                    for (int i = 0; i < tempUser.followers.size(); i++) {
                        System.out.println("--> " + tempUser.followers.get(i).username);
                    }
                    System.out.println("-------------------------");
                }
                case 2 -> {
                    System.out.println("-------------------------");
                    for (int i = 0; i < tempUser.followings.size(); i++) {
                        System.out.println("--> " + tempUser.followings.get(i).username);
                    }
                    System.out.println("-------------------------");
                }
                case 3 -> {
                    System.out.println("-------------------------");
                    for (int i = 0; i < tempUser.blocked.size(); i++) {
                        System.out.println("--> " + tempUser.blocked.get(i).username);
                    }
                    System.out.println("-------------------------");
                }
                case 4 -> {
                    if (follow.equals("Follow")) {
                        user.followings.add(tempUser);
                        tempUser.followers.add(user);
                    } else {
                        user.followings.remove(tempUser);
                        tempUser.followers.remove(user);
                    }
                }
                case 5 -> {
                    if (block.equals("Block")) {
                        if (user.containsFollowings(username)) {
                            user.followings.remove(tempUser);
                            tempUser.followers.remove(user);
                        }
                        user.blocked.add(tempUser);
                    } else {
                        user.blocked.remove(tempUser);
                    }
                }
                case 6 -> {
                    if (tempUser.posts.size() < 1) {
                        System.out.println("No posts yet :(\n");
                        break;
                    }
                    System.out.println("Choose post from profile --> ");
                    int choice = in.nextInt();
                    while (choice < 1 || choice > tempUser.posts.size()) {
                        System.out.println("invalid input :( Try again!\n");
                        choice = in.nextInt();
                    }
                    if (!tempUser.posts.get(choice - 1).getLikes().contains(user)) {
                        tempUser.posts.get(choice - 1).like(user);
                        Post.sort(tempUser.posts.getList(), 0, tempUser.posts.size() - 1);
                    } else {
                        System.out.println("ups! you already liked it :/\n");
                    }

                }
                case 7 -> {
                    if (tempUser.posts.size() < 1) {
                        System.out.println("No posts yet :(\n");
                        break;
                    }
                    System.out.println("Choose post from profile --> ");
                    int choice = in.nextInt();
                    while (choice < 1 || choice > tempUser.posts.size()) {
                        System.out.println("invalid input :( Try again!\n");
                        choice = in.nextInt();
                    }
                    System.out.println("Enter comment");
                    in.nextLine();

                    tempUser.posts.get(choice - 1).addComment(user.username + ": " + in.nextLine());
                }
            }
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
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)) return true;
        }
        return false;
    }

    public static boolean containsGmail(String gmail) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).gmail.equals(gmail)) return true;
        }
        return false;
    }
}

