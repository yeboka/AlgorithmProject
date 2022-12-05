package com.company;

public class Post {
    private String img;
    private String description;
    private MyList<User> likes;
    private MyList<String> comments;


    public Post (String img, String description){
        this.img = img;
        this.description = description;
        this.likes = new MyList<>();
        this.comments = new MyList<>();
    }

    public boolean equals (Post post) {
        return this.img.equals(post.img) && this.description.equals(post.description);
    }

    public MyList<User> getLikes() {
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

            // pi is partitioning index, arr[p]
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
