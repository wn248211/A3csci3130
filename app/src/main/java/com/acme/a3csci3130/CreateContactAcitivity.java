package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/* This class is about creating new business contact, which have name, email, business number, primary business
* province and address
*   @author Wenlong Wu
*   @since 1.0
* */

public class CreateContactAcitivity extends Activity {

    /*
    * attributes
    * submitButton: click this button to submit creation
    * several EditText field to hold info from business contact
    * */
    private Button submitButton;
    private EditText nameField, emailField, businessNumField, addressField;
    private EditText primaryBusinessField, provinceField;
    private MyApplicationData appState;

    /*
    * onCreate method: define these EditText by Id
    * @parameter savedInstanceState
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());
        submitButton = (Button) findViewById(R.id.submitButton);

        nameField = (EditText) findViewById(R.id.name_create);
        emailField = (EditText) findViewById(R.id.email_create);
        businessNumField = (EditText) findViewById(R.id.businessNumber_create);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness_create);
        provinceField = (EditText) findViewById(R.id.province_create);
        addressField = (EditText) findViewById(R.id.address_create);

    }
    /*
    * submitInfoButton method: get text from EditText field, and assign these values to instance variables.
    * Meanwhile, use check methods (@see Check) to check whether these instance variables following
    * formatting rules
    * */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String businessNum = businessNumField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String province = provinceField.getText().toString();
        String address = addressField.getText().toString();

        //instantiation and check them info.
        //if the result is false, a toast message will show up
        //different case has different toast message
        Check check = new Check();
        if (!check.checkProvince(province)) {
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(CreateContactAcitivity.this, "Your province is incorrect!",
                    Toast.LENGTH_LONG).show();
        } else if (!check.checkPrimaryB(primaryBusiness)) {
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(CreateContactAcitivity.this, "Your primary business is incorrect!",
                    Toast.LENGTH_LONG).show();
        }
        else if (!check.checkBussinessNum(businessNum)) {
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(CreateContactAcitivity.this, "Your business number must be 9-digits!",
                    Toast.LENGTH_LONG).show();
        }
        else if (!check.checkName(name)) {
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(CreateContactAcitivity.this, "Your name must be 2-48 characters!!",
                    Toast.LENGTH_LONG).show();
        }
        else if (!check.checkAddress(address)) {
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(CreateContactAcitivity.this, "Your address must be less than 50 characters!",
                    Toast.LENGTH_LONG).show();
        }
        //if all check passed, a business contact will be created
        else {
            Contact person = new Contact(personID, name, email, businessNum, primaryBusiness, province, address);

            appState.firebaseReference.child(personID).setValue(person);

            finish();
        }
    }
}
