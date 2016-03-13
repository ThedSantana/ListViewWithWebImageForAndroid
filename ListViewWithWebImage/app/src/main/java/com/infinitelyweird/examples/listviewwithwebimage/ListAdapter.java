package com.infinitelyweird.examples.listviewwithwebimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;    // Added for using picasso to download images for this list.
import java.util.ArrayList;             // Array list to store our list items.

import javax.xml.parsers.SAXParserFactory;

/**
 * Created by dustinflegel on 3/12/16.
 */
public class ListAdapter extends BaseAdapter {
    // We have an array list that stores multiple
    // instances of the ListItem class that
    // is defined near the bottom of this class.
    ArrayList<ListItem> listItems;

    // Create a reference to the calling
    // activity.
    Activity activity;

    public ListAdapter(Activity activity, ArrayList<ListItem> listItems)
    {
        super();                    // Call Super to finish out the initialization.
        this.activity = activity;   // Assign the passed activity to the local activity object.
        this.listItems = listItems; // Assign the passed list object to the local list object.
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*
        Note: this part of the class is VERY important to get correct, because this is the part that
        will actually inflate the text and image view we're using as a list item,
        and it will also get the text and image path that should go into this list item.
    */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // It's time to inflate our view.  Let's do that with the following steps:

        // 1. Specify an instance of the ViewHolder class (from below...)
        ViewHolder holder;

        // 2. Specify our layout inflater by getting it from the activity
        //    we passed in earlier.
        LayoutInflater inflater = activity.getLayoutInflater();

        // 3. Check if convertView was specified in this method call.  Initially
        //    this value is going to be null, so we'll have to inflate our objects
        if (convertView == null)
        {
            // 4. inflate an instance of our single_column_list, which is specified
            //    in the list_item_with_image.xml file found in the res > layout group
            //    in our project.
            convertView = inflater.inflate(R.layout.list_item_with_image, null);

            // 5. Create a new instance of the ViewHolder class specified below.
            //    because we're going to need to reference our TextView for the
            //    row being created.
            holder = new ViewHolder();

            // 6. Now, we're going to assign the listItemText object found in
            //    list_item_with_image.xml to the local class variable for reference
            //    later.
            holder.listViewText = (TextView)convertView.findViewById(R.id.textView);

            // 7. Let's go ahead and find the image view in our list_item_with_image.xml
            //    and assign the listViewImage object for reference later.
            holder.listViewImage = (ImageView)convertView.findViewById(R.id.imageView);

            // 8. We will now go ahead and set the "tag" of our "holder" class object
            //    in case we need to recall it later.
            convertView.setTag(holder);

        } else {
            // 9. Since convertView wasn't null this time around
            //    we'll assign the object we stored in the "tag" of
            //    convertView to the object 'holder', since we don't
            //    have to re-inflate it or reinstantiate it.
            holder = (ViewHolder)convertView.getTag();
        }

        // 10.   This is where we assign the ListItem object item
        ListItem listItem = listItems.get(position);

        // 10a.
        //      let's get the string value stored in our ListItem class
        //      and set the text property of the textField
        //      in the object "holder"
        holder.listViewText.setText(listItem.itemText);

        // 11.
        // In order to simplify this, we use the Picasso api in our application.
        // This is added into the build.grade (Under Gradle Scripts, look for build.grade (Module: app)
        // and add this to the dependencies section:
        // eg.
        //  dependencies {
        //        compile fileTree(dir: 'libs', include: ['*.jar'])
        //        testCompile 'junit:junit:4.12'
        //        compile 'com.android.support:appcompat-v7:23.2.0'
        //        compile 'com.android.support:design:23.2.0'
        //        compile 'com.squareup.picasso:picasso:2.5.0'
        //  }
        //
        // and then a message should appear in the upper right-hand corner
        // of the screen, giving you the option to sync the application. Clicking
        // that will download all of the required dependencies automatically for you
        // and then you're done. :)
        Picasso.with(convertView.getContext())
                .load(listItem.imageUrl)        // Load the image from the imageUrl found in the listItem
                .into(holder.listViewImage);    // Specify the listViewImage as the object to load the
                                                // image into.

        // 12. Finally, we'll return the either newly instantiated convertView object
        //     or our reused convertView
        return convertView;
    }

    public class ViewHolder {
        TextView listViewText;      // Specify the list item text view
        ImageView listViewImage;    // Specify the list item image view

    }

}
