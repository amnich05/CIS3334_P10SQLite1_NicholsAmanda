package edu.css.amanda.cis3334_p10sqlite_nicholsamanda;

/**
 * Created by Amanda Nichols on 3/27/2018.
 */

// class to set comments, get comments, and give comments an id
public class Comment {
    private long id;            // id for the comment
    private String comment;     // declare comment variable

    // method to get the id for a selected comment
    public long getId() {
        return id;
    }

    // method to assign an id to a comment
    public void setId(long id) {
        this.id = id;
    }

    // method to get the comment
    public String getComment() {
        return comment;
    }

    // method to set a comment
    public void setComment(String comment) {
        this.comment = comment;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment;
    }
}
