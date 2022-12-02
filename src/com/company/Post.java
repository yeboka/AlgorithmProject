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

    @Override
    public String toString() {
        return img + "\n" + description + '\n' +
                "likes: " + likes.size() +
                "\t comments: " + comments.size();
    }
}
