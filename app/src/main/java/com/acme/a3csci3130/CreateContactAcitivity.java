package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, emailField, businessNumField, addressField;
    private EditText primaryBusinessField, provinceField;
    private MyApplicationData appState;

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

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String businessNum = businessNumField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String province = provinceField.getText().toString();
        String address = addressField.getText().toString();

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
        }else {
            Contact person = new Contact(personID, name, email, businessNum, primaryBusiness, province, address);

            appState.firebaseReference.child(personID).setValue(person);

            finish();
        }
    }
}
