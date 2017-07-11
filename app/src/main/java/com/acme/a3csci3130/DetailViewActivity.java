package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/* This class is about showing all info about a business contact which have name, email, business number, primary business
* province and address
*   @author Wenlong Wu
*   @since 1.0
* */

public class DetailViewActivity extends Activity {

    /*
    * attributes
    * several EditText field to hold info from business contact
    * Contact type attribute, which inherit info from MainActivity @see method "showDetailView"
    * */

    private EditText nameField, emailField, businessNumberField, primaryBusField, provinceField, addressField;
    private MyApplicationData appState;
    Contact receivedPersonInfo;

    /*
    * onCreate method: define these EditText by Id
    * @parameter savedInstanceState
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name_detail);
        emailField = (EditText) findViewById(R.id.email_detail);
        businessNumberField = (EditText) findViewById(R.id.Business_Num_detail);
        primaryBusField = (EditText) findViewById(R.id.Primary_Bus_detail);
        provinceField = (EditText) findViewById(R.id.province_detail);
        addressField = (EditText) findViewById(R.id.address_detail);

        //check whether inherited object has info or not
        // if it has, set these info to EditText fields
        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            businessNumberField.setText(receivedPersonInfo.businessNum);
            primaryBusField.setText(receivedPersonInfo.primaryBusiness);
            provinceField.setText(receivedPersonInfo.province);
            addressField.setText(receivedPersonInfo.address);
        }
    }

    /*
   * updateContact method: get text from EditText field, and update these values by instead the old info on text fields.
   * Meanwhile, use check methods (@see Check) to check whether these instance variables following
   * formatting rules
   * */
    public void updateContact(View v){

        String newNameField = nameField.getText().toString();
        String newEmailField = emailField.getText().toString();
        String newBusinessNumberField = businessNumberField.getText().toString();
        String newPrimaryBusField = primaryBusField.getText().toString();
        String newProvinceField = provinceField.getText().toString();
        String newAddressField = addressField.getText().toString();

        //instantiation and check them info.
        //if the result is false, a toast message will show up
        //different case has different toast message
        Check check = new Check();
        if (!check.checkProvince(newProvinceField)) {
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(DetailViewActivity.this, "Your province is incorrect!",
                    Toast.LENGTH_LONG).show();
        } else if (!check.checkPrimaryB(newPrimaryBusField)) {
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(DetailViewActivity.this, "Your primary business is incorrect!",
                    Toast.LENGTH_LONG).show();
        }
        else if (!check.checkBussinessNum(newBusinessNumberField)) {
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(DetailViewActivity.this, "Your business number must be 9-digits!",
                    Toast.LENGTH_LONG).show();
        }
        else if (!check.checkName(newNameField)) {
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(DetailViewActivity.this, "Your name must be 2-48 characters!!",
                    Toast.LENGTH_LONG).show();
        }
        else if (!check.checkAddress(newAddressField)) {
            LayoutInflater myInflater = LayoutInflater.from(this);
            View view = myInflater.inflate(R.layout.activity_create_contact_acitivity, null);
            Toast mytoast = new Toast(this);
            mytoast.setView(view);
            Toast.makeText(DetailViewActivity.this, "Your address must be less than 50 characters!",
                    Toast.LENGTH_LONG).show();
        }
        //if all check passed, a business contact will be updated
        //after that, return to main menu
        else {
            Contact newPerson = new Contact(receivedPersonInfo.uid, newNameField, newEmailField, newBusinessNumberField,
                    newPrimaryBusField, newProvinceField, newAddressField);
            appState.firebaseReference.child(receivedPersonInfo.uid).setValue(newPerson);
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    /*
  * eraseContact method: remove business contact from list view
  * after that, return to main menu
  * */
    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
