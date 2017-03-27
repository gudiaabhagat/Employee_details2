package com.example.dell.employee_details;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MultiProfile extends AppCompatActivity {
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        fm= getSupportFragmentManager();

    }

    public void personal(View v)
    {

        FragmentTransaction ft = fm.beginTransaction();
        Fragment cm = new PersonalD();

        ft.replace(R.id.frame_id ,cm);

        ft.commit();

    }
    public void professional(View v)
    {

        FragmentTransaction ft = fm.beginTransaction();
        Fragment cm = new ProfessionalD();

        ft.replace(R.id.frame_id ,cm);

        ft.commit();

    }
    public void health (View v)
    {

        FragmentTransaction ft = fm.beginTransaction();
        Fragment cm = new HealthD();

        ft.replace(R.id.frame_id ,cm);

        ft.commit();

    }
}
