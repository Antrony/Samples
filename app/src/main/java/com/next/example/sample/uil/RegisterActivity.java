package com.next.example.sample.uil;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.next.example.sample.R;
import com.next.example.sample.bal.Employee;
import com.next.example.sample.bal.Validations;
import com.next.example.sample.dal.DataAccessLayer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by next on 29/9/16.
 */
public class RegisterActivity extends AppCompatActivity {
    EditText etxtV_first, etxtV_last, etxtV_username, etxtV_password, etxtV_confirm_password;
    Button btn_back_to_login, btn_register;
    String first_name, last_name, team, username, password, confirm_password;
    Spinner spin_team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getViewCasting();
        ArrayAdapter<String> spinneritems = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.teams));
        spin_team.setAdapter(spinneritems);
        spin_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                team = adapter.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),
                        "Selected Team : " + team, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Validations validations = new Validations();
                    first_name = etxtV_first.getText().toString().trim();
                    last_name = etxtV_last.getText().toString().trim();
                    username = etxtV_username.getText().toString().trim();
                    password = etxtV_password.getText().toString().trim();
                    confirm_password = etxtV_confirm_password.getText().toString().trim();
                    if (validations.checkEditTextIsEmptyOrNot(first_name) == true) {
                        etxtV_first.setError("First Name Should Not be Empty");
                    } else if (validations.namecheck(first_name) == false) {
                        etxtV_first.setError("First Name Should contain Only Text and between 3-12 Characters");
                    } else if (validations.checkEditTextIsEmptyOrNot(last_name) == true) {
                        etxtV_last.setError("Last Name Should Not be Empty");
                    } else if (validations.checkEditTextIsEmptyOrNot(username) == true) {
                        etxtV_username.setError("UserName Should Not be Empty");
                    } else if (validations.unamecheck(username) == false) {
                        etxtV_username.setError("UserName Should be more than 4 characters and it may be the alphanumeric combination");
                    } else if (validations.checkEditTextIsEmptyOrNot(password) == true) {
                        etxtV_password.setError("Password is Required Field");
                    } else if (validations.pwordcheck(password) == false) {
                        etxtV_password.setError("Password should contain a lowercase and a uppercase letter, a number, a Special Character and should be more than Six Characters");
                    } else if (validations.checkEditTextIsEmptyOrNot(confirm_password) == true) {
                        etxtV_confirm_password.setError("Please Confirm Your Password");
                    } else if (validations.confirmpword(confirm_password, password) == false) {
                        etxtV_confirm_password.setError("Oops!! Password Mismatches!!");
                    } else {
                        insertDb();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btn_back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void insertDb() {
        DataAccessLayer dal = new DataAccessLayer(this);
        dal.open();
        Cursor idCursor = dal.fetch(dal.TRN_TABLE_EMPLOYEE);
        int reg_id_count = idCursor.getCount() + 1;
        System.out.println("count value:" + reg_id_count);
        long insert_employee = dal.insertEmployee(reg_id_count, first_name, last_name, team, username, password,"0");
        if (insert_employee != 0) {
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(RegisterActivity.this, EmployeeListActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Registeration Failure", Toast.LENGTH_SHORT).show();
        }
        dal.close();
    }




    public void getViewCasting(){
        etxtV_first=(EditText)findViewById(R.id.etxtV_first);
        etxtV_last=(EditText)findViewById(R.id.etxtV_last);
        etxtV_username=(EditText)findViewById(R.id.etxtV_username);
        etxtV_password=(EditText)findViewById(R.id.etxtV_password);
        etxtV_confirm_password=(EditText)findViewById(R.id.etxtV_confirm_password);
        btn_back_to_login=(Button)findViewById(R.id.btn_back_to_login);
        btn_register=(Button)findViewById(R.id.btn_register);
        spin_team= (Spinner)findViewById(R.id.spin_team);
    }
}
