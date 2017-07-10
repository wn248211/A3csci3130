package com.acme.a3csci3130;

import android.support.test.rule.ActivityTestRule;
import android.widget.ListView;

import com.firebase.ui.database.FirebaseListAdapter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

public class Espresso {
    private String name_test, email_test, businessNum_test, primaryBus_test, province_test, address_test;
    private ListView contactListView;
    private FirebaseListAdapter<Contact> firebaseAdapter;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initString()
    {
        name_test = "Jack";
        email_test = "Jack.Jack@test.com";
        businessNum_test = "123456789";
        primaryBus_test = "Fisher";
        province_test = "NS";
        address_test = "1888 Duke st.";
    }

    @Test
    public void create()

    {

        //target a button that has the ID value of button
        onView(withId(R.id.submitButton)).perform(click());

        //make sure output textfield has no info
        onView(withId(R.id.name_create)).check(matches(withText("")));
        onView(withId(R.id.email_create)).check(matches(withText("")));
        onView(withId(R.id.businessNumber_create)).check(matches(withText("")));
        onView(withId(R.id.primaryBusiness_create)).check(matches(withText("")));
        onView(withId(R.id.province_create)).check(matches(withText("")));
        onView(withId(R.id.address_create)).check(matches(withText("")));

        //target a input field and type password into it
        onView(withId(R.id.name_create)).perform(typeText(name_test));
        onView(withId(R.id.email_create)).perform(typeText(email_test));
        onView(withId(R.id.businessNumber_create)).perform(typeText(businessNum_test));
        onView(withId(R.id.primaryBusiness_create)).perform(typeText(primaryBus_test));
        onView(withId(R.id.province_create)).perform(typeText(province_test));
        //onView(withId(R.id.address_create)).perform(typeText(address_test));
        //onView(withId(R.id.confirm_create)).perform(click());
    }

    @Test
    public void read()
    {
        onView(withId(R.id.editButton)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView_edit)).atPosition(0).perform(click());
    }

    @Test
    public void update()
    {
        //target a button that has the ID value of button
        onView(withId(R.id.editButton)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView_edit)).atPosition(0).perform(click());

        onView(withId(R.id.name_detail)).check(matches(withText(name_test)));

        onView(withId(R.id.name_detail)).perform(typeText("son"));

        //onView(withId(R.id.updateButton)).perform(click());
    }

    @Test
    public void delete()
    {
        //target a button that has the ID value of button
        onView(withId(R.id.deleteButton)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.listView_edit)).atPosition(0).perform(click());


    }
}
