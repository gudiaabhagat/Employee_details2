package com.example.dell.employee_details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Drawer_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
    }

    public void personalDetail(View v)
    {
        Intent i= new Intent(Drawer_activity.this,MultiProfile.class);
        i.putExtra("from_key","personal");
        startActivity(i);


    }

    public void professionalDetail(View v)
    {
        Intent i= new Intent(Drawer_activity.this,MultiProfile.class);
        i.putExtra("from_key","professional");
        startActivity(i);


    }
    public void HealthDetails(View v)
    {
        Intent i= new Intent(Drawer_activity.this,MultiProfile.class);
        i.putExtra("from_key","health");
        startActivity(i);


    }
    public void Leaves(View v)
    {
        Intent i= new Intent(Drawer_activity.this,LeaveDetail.class);
        startActivity(i);


    }
}
