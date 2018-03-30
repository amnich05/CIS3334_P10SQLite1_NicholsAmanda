package edu.css.amanda.cis3334_p10sqlite_nicholsamanda;

/**
 * Created by Amanda Nichols on 3/27/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// helper class to open and create a database
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "comments";         // the name of the table
    public static final String COLUMN_ID = "_id";                   // the column name of the primary key
    public static final String COLUMN_COMMENT = "comment";          // the column name of the comment field

    private static final String DATABASE_NAME = "commments.db";     // the name of the database
    private static final int DATABASE_VERSION = 1;                  // version name

    // Database creation sql statement
    /* SQL table create ==============
    *  CREATE TABLE comments(
    *  COMMENT_ID string,
    *  COMMENT string,
    *  );
     */
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}
