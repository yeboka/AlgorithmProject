package com.company;

public class Post {
    private String img;
    private String description;
    private MyList<User> likes;
    private MyList<String> comments;
    private String author;

    public Post (String img, String description, String author){
        this.img = img;
        this.description = description;
        this.likes = new MyList<>();
        this.comments = new MyList<>();
        this.author = author;
    }

    public Post (String img, String description, int numOfLikes){
        this.img = img;
        this.description = description;
        this.likes = new MyList<>();
        for (int i = 0; i < numOfLikes; i++) {
            likes.add(new User("xx", "yerbo", "mukan", 18, "@@", "male", "0"));
        }
        this.comments = new MyList<>();
    }

    public MyList<User> getLikes() {
        return likes;
    }

    public String getAuthor () { return author; }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void like(User user) {
        likes.add(user);
    }

    public int getNumOfLikes() {
        return likes.size();
    }

    @Override
    public String toString() {

        StringBuilder commentsList = new StringBuilder();
        for (int i = 0; i < comments.size(); i++) {
            commentsList.append(comments.get(i));
        }

        return img + "\n" + description + '\n' +
                "likes: " + likes.size() +
                "\t comments: " + comments.size() + "\n" +
                commentsList;
    }

    public static void sort (Object[] posts, int low, int high) {

        if (low < high) {
            // pi is partitioning iSndex, arr[p]
            // is now at right place
            int pi = partition(posts, low, high);

            // Separately sort elements before
            // partition and after partition
            sort(posts, low, pi - 1);
            sort(posts, pi + 1, high);
        }
    }

    private static int partition(Object[] arr, int low, int high) {
        // pivot
        Post pivot = (Post) arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            Post post = (Post) arr[j];
            if (post.likes.size() > pivot.likes.size()) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    private static void swap(Object[] arr, int i, int j) {
        Post temp = (Post) arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
