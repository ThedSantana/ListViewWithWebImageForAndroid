package com.infinitelyweird.examples.listviewwithwebimage;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*

        BEFORE YOU ADD THIS CODE, MAKE SURE YOU HAVE A ListAdapter created
        AND A LAYOUT FOR YOUR ROW.

        (see ListAdapter class and list_item_with_image.xml)
     */


    // The first step to populating a list item
    // is to create an object to store data that
    // will be use for each row in the list.
    private ArrayList<ListItem> listViewItems;

    // Next, we'll want to create a reference to
    // our List Adapter.  We'll pass our current
    // activity as 'this' and the object we just
    // created to store our items called 'listViewItems'
    private ListAdapter listAdapter = new ListAdapter(this, listViewItems);

    // We want this available throughout this class
    // so we'll declare our listView object
    // here.
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create an instance of ArrayList<ListItem> and assign it to the object
        // we defined above. 'listViewItems'
        listViewItems = new ArrayList<ListItem>();

        // For the purpose of this example, I found 5 random images on Google search that were 64x64 and just copied
        // the URLs into here.  Replace these URLs with any images / text you have hosted somewhere.
        listViewItems.add(new ListItem("http://files.softicons.com/download/application-icons/psferox-icons-by-psferox/png/64x64/15.png", "Image 1"));
        listViewItems.add(new ListItem("http://megaicons.net/static/img/icons_sizes/10/62/64/magic-ball-icon.png", "Image 2"));
        listViewItems.add(new ListItem("http://megaicons.net/static/img/icons_sizes/7/59/64/pixelmator-icon.png", "Image 3"));
        listViewItems.add(new ListItem("http://icons.iconarchive.com/icons/chrisl21/minecraft/64/Diamond-Sword-icon.png", "Image 4"));
        listViewItems.add(new ListItem("http://www.rw-designer.com/icon-image/5540-64x64x8.png", "Image 5"));

        // Next, we'll assign our array list ('listViewItems')
        // our listAdapter listItems property found in
        // ListAdapter.java.
        listAdapter.listItems = listViewItems;

        // Let's find our listView item and ...
        listView = (ListView)findViewById(R.id.listView);

        // ...  set the variable we declared above
        // so we can show the data we added to listViewItems
        listView.setAdapter(listAdapter);

        // Now, how do we handle 'touch' events, you say?  Well, surprisingly
        // it's not a touch event, but it's an OnItemClick event.
        // You don't have to memorize this code, but you should note a quick
        // shortcut where you can type new OnClickLi and then pressing Tab on your keyboard
        // should generate the @Override event.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // I decided to just use Snackbar to display a message at the bottom of the screen
                // to show the item you clicked.
                Snackbar.make(view,
                        listViewItems.get(position).itemText, // Get the item text from the listViewItems array list.
                        Snackbar.LENGTH_LONG)                 // Tell 'snackbar' how long we want to display the item.
                        .show();                              // Don't forget to call .show() at the end, or it won't
                                                              // display for you.
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
