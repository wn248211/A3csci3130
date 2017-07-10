package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField, businessNumberField, primaryBusField, provinceField, addressField;
    private MyApplicationData appState;
    Contact receivedPersonInfo;

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

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            businessNumberField.setText(receivedPersonInfo.businessNum);
            primaryBusField.setText(receivedPersonInfo.primaryBusiness);
            provinceField.setText(receivedPersonInfo.province);
            addressField.setText(receivedPersonInfo.address);
        }
    }

    public void updateContact(View v){

        String newNameField = nameField.getText().toString();
        String newEmailField = emailField.getText().toString();
        String newBusinessNumberField = businessNumberField.getText().toString();
        String newPrimaryBusField = primaryBusField.getText().toString();
        String newProvinceField = provinceField.getText().toString();
        String newAddressField = addressField.getText().toString();
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
        }else {
            Contact newPerson = new Contact(receivedPersonInfo.uid, newNameField, newEmailField, newBusinessNumberField,
                    newPrimaryBusField, newProvinceField, newAddressField);
            appState.firebaseReference.child(receivedPersonInfo.uid).setValue(newPerson);
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
