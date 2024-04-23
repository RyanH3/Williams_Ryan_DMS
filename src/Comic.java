/*
 * Ryan Williams
 * CEN 3024C-26663 Software Development I
 * 23 April 2024
 * Comic.java
 * This class will hold information about a comic.
 */

/**
 * Holds information about a comic.
 */
public class Comic {
    private String title, author, imagePath;
    private int id, rating, currentChapter, totalChapters;
    private boolean completed, pinned;

    /**
     * Makes a Comic object.
     * @param title Title of comic.
     * @param author Author of comic.
     * @param imagePath URL of image to be used for comic.
     * @param id ID of comic.
     * @param rating Rating for comic out of 10.
     * @param currentChapter Current chapter of comic user is reading.
     * @param totalChapters Total chapters in comic.
     * @param completed Whether comic is completed or not.
     * @param pinned Whether comic is at top of list.
     */
    public Comic(String title, String author, String imagePath,
                 int id, int rating, int currentChapter, int totalChapters,
                 boolean completed, boolean pinned) {
        this.title = title;
        this.author = author;
        this.imagePath = imagePath;
        this.id = id;
        this.rating = rating;
        this.currentChapter = currentChapter;
        this.totalChapters = totalChapters;
        this.completed = completed;
        this.pinned = pinned;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCurrentChapter() {
        return this.currentChapter;
    }

    public void setCurrentChapter(int currentChapter) {
        this.currentChapter = currentChapter;
    }

    public int getTotalChapters() {
        return this.totalChapters;
    }

    public void setTotalChapters(int totalChapters) {
        this.totalChapters = totalChapters;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean getPinned() {
        return this.pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    /**
     * Prints information about the comic.
     */
    public void print() {
        System.out.println(this.imagePath +
                "\nComic: " + this.title +
                "\nAuthor: " + this.author +
                "\nID: " + this.id +
                "\nRating: " + this.rating + "/10" +
                "\nCurrent chapter: " + this.currentChapter + "/" + this.totalChapters +
                "\nStatus: " + (this.completed ? "Completed" : "Ongoing") +
                (this.pinned ? "\nPinned" : "") + "\n");
    }
}