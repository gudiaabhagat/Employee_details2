package com.example.dell.employee_details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SanctionLogin extends AppCompatActivity {
    EditText san_name, san_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanction_login);
        san_name =(EditText)findViewById(R.id.san_id);
        san_pass=(EditText)findViewById(R.id.spass_id);
    }
    public void login(final View v) {
        String sanction_id = san_name.getText().toString();
        String san_password = san_pass.getText().toString();
        if (sanction_id.equals("")) {
            Toast.makeText(SanctionLogin.this, "Please enter your Emplyee ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (san_password.equals("")) {
            Toast.makeText(SanctionLogin.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject js = new JSONObject();
        try {
            js.put("sanid", sanction_id);
            js.put("sanpass", san_password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jobjreq = new JsonObjectRequest("http://192.168.43.234/sanction_login.php", js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);
                try {
                    if (response.getString("pass_key").equals("password matched")) {

                        Intent i = new Intent(SanctionLogin.this, Drawer_activity.class);
                        startActivity(i);
                        finish();

                    }
                    else if(response.get("pass_key").equals("Enter Correct password"))
                    {
                        Toast.makeText(SanctionLogin.this,"Please Enter Correct Password",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(SanctionLogin.this,"Employee doesn't exist",Toast.LENGTH_SHORT).show();
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

        AppController ap = new AppController(SanctionLogin .this);
        ap.addToRequestQueue(jobjreq);

    }
}

