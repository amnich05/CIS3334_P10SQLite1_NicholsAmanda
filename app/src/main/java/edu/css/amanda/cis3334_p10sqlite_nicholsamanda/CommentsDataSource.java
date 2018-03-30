package edu.css.amanda.cis3334_p10sqlite_nicholsamanda;

/**
 * Created by Amanda on 3/27/2018.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT };

    // creates a new helper
    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    // opens database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // closes database
    public void close() {
        dbHelper.close();
    }

    // creates a comment in the database ; creates an id for the comment
    /* sample SQL =============
    *  INSERT INTO TABLE_COMMENTS(comment)
    *  VALUES('hello');
     */
    public Comment createComment(String comment) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    // deletes a comment in the database based on comment id
    /* sample SQL =============
    *  DELETE FROM TABLE_COMMENTS
    *  WHERE id=1;
     */
    public void deleteComment(Comment comment) {
        // finds comment with desired id
        long id = comment.getId();
        // verify
        System.out.println("Comment deleted with id: " + id);
        // deletes comments with desired id
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    // gets all comments in the database ; no particular group, selection, or order
    /* sample SQL =============
    *  SELECT * FROM TABLE_COMMENTS;
     */
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        // selects all comments from the table
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    // where to place the new comment (column)
    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)));
        comment.setComment(cursor.getString(1));
        return comment;
    }
}