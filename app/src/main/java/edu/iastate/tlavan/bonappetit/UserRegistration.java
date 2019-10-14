package edu.iastate.tlavan.bonappetit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UserRegistration extends AppCompatActivity {
    /**
     * The constant JSON_URL.
     * Used to access the UserDB database.
     */
    public static final String JSON_URL = "http://proj-309-gb-5.cs.iastate.edu/userRegistration.php";
    /**
     * The constant KEY_NAME.
     * Used to access the Name attribute in the UserDB database
     */
    public static final String KEY_NAME = "Name";
    /**
     * The constant KEY_EMAIL.
     * Used to access the Email attribute in the UserDB database
     */
    public static final String KEY_EMAIL = "Email";
    /**
     * The constant KEY_USERNAME.
     * Used to access the Username attribute in the UserDB database.
     */
    public static final String KEY_USERNAME = "Username";
    /**
     * The constant KEY_PASSWORD.
     * Used to access the Password attribute in the UserDB database.
     */
    public static final String KEY_PASSWORD = "Password";
    /**
     * Text box to add user to enter name
     */
    private EditText addName;
    /**
     * Text box to add user to enter email
     */
    private EditText addEmail;
    /**
     * Text box to add user to enter username
     */
    private EditText addUsername;
    /**
     * Text box to add user to enter password
     */
    private EditText addPassword;
    private EditText addPassword2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        addName = (EditText) findViewById(R.id.reg_name);
        addEmail = (EditText) findViewById(R.id.reg_email);
        addUsername = (EditText) findViewById(R.id.reg_username);
        addPassword = (EditText) findViewById(R.id.reg_password);
        addPassword.setTransformationMethod(new PasswordAsterisks());
        addPassword2 = (EditText) findViewById(R.id.reg_password2);
        addPassword2.setTransformationMethod(new PasswordAsterisks());

        //Add User to Database Action Button
        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                userRegistration();
            }
        });

        //Cancel Registration Action Button
        Button btnCancel = (Button) findViewById(R.id.btnRegCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cancelRegistration();
            }
        });
    }

    /**
     * This function allows the user to create a new account
     */
    private void userRegistration() {
        final String Name = addName.getText().toString().trim();
        final String Email = addEmail.getText().toString().trim();
        final String Username = addUsername.getText().toString().trim();
        final String Password = addPassword.getText().toString().trim();
        final String Password2 = addPassword2.getText().toString().trim();

        if(Password.equals(Password2)) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(UserRegistration.this, response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(UserRegistration.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_NAME, Name);
                    params.put(KEY_EMAIL, Email);
                    params.put(KEY_USERNAME, Username);
                    params.put(KEY_PASSWORD, Password);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            resetObjects();
        } else {
            Toast.makeText(getApplicationContext(), "Passwords do not match. Please try again.",
                    Toast.LENGTH_LONG).show();
            resetObjects();
        }
    }

    /**
     * Reset Texts & Cursor
     */
    private void resetObjects()
    {
        //Reset Boxes for Next Addition
        EditText name = (EditText) findViewById(R.id.reg_name);
        name.setText(null);

        EditText email = (EditText) findViewById(R.id.reg_email);
        email.setText(null);

        EditText username = (EditText) findViewById(R.id.reg_username);
        username.setText(null);

        EditText password = (EditText) findViewById(R.id.reg_password);
        password.setText(null);

        EditText password2 = (EditText) findViewById(R.id.reg_password2);
        password2.setText(null);

        //Reset Cursor Position
        name.requestFocus();
    }

    /**
     * This function allows the user to log out of the application
     */
    private void cancelRegistration() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
