package edu.css.amanda.cis3334_p10sqlite_nicholsamanda;

/**
 * Created by Amanda Nichols on 3/27/2018.
 */

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;


public class MainActivity extends ListActivity {
    private CommentsDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new CommentsDataSource(this);

        // open database
        datasource.open();

        List<Comment> values = datasource.getAllComments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();

        // declare comment variable, set to null
        Comment comment = null;

        // create switch statement for buttons
        switch (view.getId()) {

            // add comment to end of list when add button is clicked
            case R.id.add:
                String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                // pick comment from string array at random
                int nextInt = new Random().nextInt(3);
                // add the new comment to the database
                comment = datasource.createComment(comments[nextInt]);
                // add the new comment to the adapter (view)
                adapter.add(comment);
                break;

            case R.id.delete:   // delete first comment when delete button is clicked
                if (getListAdapter().getCount() > 0) {
                    // find first comment
                    comment = (Comment) getListAdapter().getItem(0);
                    // delete first comment from database
                    datasource.deleteComment(comment);
                    // delete first comment from adapter (the view?)
                    adapter.remove(comment);
                }
                break;
        } // end switch
        adapter.notifyDataSetChanged();
    }

    // open database
    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    // close database
    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}
