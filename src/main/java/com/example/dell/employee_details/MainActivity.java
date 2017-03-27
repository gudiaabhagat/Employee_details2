package com.example.dell.employee_details;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText name_et, password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name_et = (EditText) findViewById(R.id.user_id);
        password_et = (EditText) findViewById(R.id.paas_id);

    }

    public void login(final View v) {
        final String UserName = name_et.getText().toString();
        String Password = password_et.getText().toString();
        if (UserName.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter your Emplyee ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Password.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
            return;
        }

        {

        }
        JSONObject js = new JSONObject();
        try {
            js.put("name", UserName);
            js.put("pas", Password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jobjreq = new JsonObjectRequest("http://192.168.43.234/test.php", js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);
                try {
                    if (response.getString("pass_key").equals("password matched")) {

                        SharedPreferences.Editor sp = getSharedPreferences("Emp_info",MODE_PRIVATE).edit();
                        sp.putString("eid",UserName);
                        sp.commit();
                        Intent i = new Intent(MainActivity.this, Drawer_activity.class);
                        startActivity(i);
                        finish();

                    }
                    else if(response.get("pass_key").equals("Enter Correct password"))
                    {
                        Toast.makeText(MainActivity.this,"Please Enter Correct Password",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Employee doesn't exist",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);

            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000, 3, 2));

        AppController ap = new AppController(MainActivity.this);
        ap.addToRequestQueue(jobjreq);

    }
}
