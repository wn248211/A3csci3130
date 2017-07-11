package com.acme.a3csci3130;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

/* This class is about showing all existing contacts, and create button for creating a new business contact
* edit button for show detail about a contact, and erase button for deleting a contact
*   @author Wenlong Wu
*   @since 1.0
* */

public class MainActivity extends Activity {

     /*
  * attributes
  * ListView: list to hold all business contacts
  * FirebaseListAdapter<Contact>: This class is a generic way of backing an Android ListView with a Firebase location.
  * @parameter Contact: The class type to use as a model for the data contained in the children of the given Firebase location
  * */

    private ListView contactListView;
    private FirebaseListAdapter<Contact> firebaseAdapter;

    /*
* onCreate method: set up firebase
* @parameter savedInstanceState
* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReferenceFromUrl("https://a4-csci3130.firebaseio.com/");

        //Get the reference to the UI contents
        contactListView = (ListView) findViewById(R.id.listView);

        //Set up the List View
       firebaseAdapter = new FirebaseListAdapter<Contact>(this, Contact.class,
                android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Contact model, int position) {
                TextView contactName = (TextView)v.findViewById(android.R.id.text1);
                contactName.setText(model.name);
            }
        };
        contactListView.setAdapter(firebaseAdapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact person = (Contact) firebaseAdapter.getItem(position);
                showDetailView(person);
            }
        });
    }

    //method createContactButton: a method will help you create a business contact
    public void createContactButton(View v)
    {
        Intent intent=new Intent(this, CreateContactAcitivity.class);
        startActivity(intent);
    }

    //method showDetailView: a method will help you check business contacts in list view
    private void showDetailView(Contact person)
    {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("Contact", person);
        startActivity(intent);
    }

    //method showDetailView: a method will help you view business contacts in list view
    public void showEditView(View v)
    {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    //method showDetailView: a method will help you delete a business contact in list view
    public void showDeleteView(View v)
    {
        Intent intent = new Intent(this, DeleteAcitivity.class);
        startActivity(intent);
    }



}
