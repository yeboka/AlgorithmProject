package com.company;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private String img;
    private String description;
    private List<User> likes;
    private List<String> comments;


    public Post (String img, String description){
        this.img = img;
        this.description = description;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public List<User> getLikes() {
        return likes;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void like(User user) {
        likes.add(user);
    }

    @Override
    public String toString() {

        StringBuilder commentsList = new StringBuilder();
        for (String comment :
                comments) {
            commentsList.append(comment);
        }

        return img + "\n" + description + '\n' +
                "likes: " + likes.size() +
                "\t comments: " + comments.size() + "\n" +
                commentsList;
    }
}
