package com.example.restaurantprojectai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class SignupActivity extends AppCompatActivity {
    private ImageView alreadyHaveAccount;
    private EditText editTextUsername, editTextEmail, editTextPassword,editTextPhoneN;
    private Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        alreadyHaveAccount= (ImageView) findViewById(R.id.imageViewAlreadyHave);
        editTextUsername= (EditText) findViewById(R.id.editTextUserName);
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        editTextPhoneN= (EditText) findViewById(R.id.editTextPhoneNumber);

        buttonSignup= (Button) findViewById(R.id.buttonSignUp);

        buttonSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username= String.valueOf(editTextUsername.getText());
                String email= String.valueOf(editTextEmail.getText());
                String password= String.valueOf(editTextPassword.getText());
                String phoneNumber= String.valueOf(editTextPhoneN.getText());

                if(username.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please make sure all fields are not empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[4];
                        field[0] = "username";
                        field[1] = "email";
                        field[2] = "password";
                        field[3] = "phonenumber";
                        //Creating array for data
                        String[] data = new String[4];
                        data[0] = username;
                        data[1] = email;
                        data[2] = password;
                        data[3] = phoneNumber;
                        PutData putData = new PutData(MainActivity.IP+"signup.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                if(result.equals("Sign Up Success")){
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(SignupActivity.this , MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                        //End Write and Read data with URL
                    }
                });

            }
        });

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignupActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });






    }
}