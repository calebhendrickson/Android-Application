package edu.iastate.tlavan.bonappetit;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * The type Login Screen (Main activity).
 * This is the login screen to BonAPPétit
 *
 * @author Tracy La Van
 */
public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    /**
     * Username field.
     */
    EditText username;
    /**
     * Password field.
     */
    EditText password;

    /**
     * Attempts Remaining Text
     */
    TextView tx1;
    /**
     * The counter for number of login attempts remaining.
     */
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
        tx1 = (TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);

        //Log in button action
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin") &&
                        password.getText().toString().equals("adminpw")) {

                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                    //Redirect to opening page
                    goToNextScreen();
                }else{
                    if (counter !=0) {
                        Toast.makeText(getApplicationContext(), "Incorrect Credentials",
                                Toast.LENGTH_SHORT).show();

                        tx1.setVisibility(View.VISIBLE);
                        tx1.setBackgroundColor(Color.RED);
                        counter--;
                        tx1.setText(Integer.toString(counter));

                        resetObjects();

                    }

                    if (counter == 0) {
                        Toast.makeText(getApplicationContext(), "Incorrect Credentials",
                                Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Closing Application",
                                Toast.LENGTH_SHORT).show();

                        resetObjects();

                        //Close application after displaying messages
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable(){
                            @Override
                            public void run(){
                                closeApp();
                            }
                        }, 7500);

                    }
                }
            }
        });

        //Register new user button action
        Button btnRegisterUser = (Button)findViewById(R.id.btnRegister);
        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });

        //Cancel button action (closes app)
        Button btnCancel = (Button)findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Goodbye!",
                        Toast.LENGTH_SHORT).show();

                //Close application after displaying message
                Handler handler = new Handler();
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        closeApp();
                    }
                }, 2500);
            }
        });
    }

    /**
     * Reset Texts & Cursor
     */
    private void resetObjects()
    {
        //Reset Boxes for Password Retry
        EditText username = (EditText) findViewById(R.id.editText);
        username.setText(null);

        EditText password = (EditText) findViewById(R.id.editText2);
        password.setText(null);

        //Reset Cursor Position
        username.requestFocus();
    }

    /**
     * This function allows the user to register.
     */
    private void goToRegister()
    {
        Intent intent = new Intent(this, UserRegistration.class);
        startActivity(intent);
        finish();
    }

    /**
     * This function allows the user to enter the app if login
     * credentials are correct.
     */
    private void goToNextScreen()
    {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        finish();
    }

    /**
     * This function closes out of the application.
     */
    private void closeApp()
    {
        finish();
    }
}
