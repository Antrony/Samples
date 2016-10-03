package com.next.example.sample.uil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.next.example.sample.R;
import com.next.example.sample.dal.DataAccessLayer;

public class MainActivity extends AppCompatActivity {
    EditText eTxt_username,eTxt_password;
    Button btn_login;
    TextView txtV_register;
    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getViewCasting();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkdatabase();
            }
        });
        txtV_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void checkdatabase(){

        username=eTxt_username.getText().toString();
        password=eTxt_password.getText().toString();
        if(username.equals("admin")&&password.equals("admin")){
            Toast.makeText(this,"Login Successfull",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(MainActivity.this,RecyclerActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this,"Invalid Username or Password",Toast.LENGTH_SHORT).show();
        }
    }
    public void getViewCasting(){
        eTxt_username=(EditText)findViewById(R.id.eTxt_username);
        eTxt_password=(EditText)findViewById(R.id.eTxt_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        txtV_register=(TextView)findViewById(R.id.txtV_register);
    }
}
