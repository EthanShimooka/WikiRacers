/**
 * Created by Tyler Marshik on 2/19/2015.
 * This is the register page
 * on success the user is redirected to the login page
 */
package wikiracers.wikiracers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterPage extends Activity {

    private EditText username = null;
    private EditText name = null;
    private EditText email = null;
    private EditText phone = null;
    private EditText password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        //variable grab
        username = (EditText) findViewById(R.id.edit_username);
        name = (EditText) findViewById(R.id.edit_name);
        email = (EditText) findViewById(R.id.edit_email);
        phone = (EditText) findViewById(R.id.edit_phone);
        password = (EditText) findViewById(R.id.edit_password);

        //create button
        findViewById(R.id.create_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final ProgressDialog dlg = new ProgressDialog(RegisterPage.this);
                dlg.setTitle("Signing Up...");
                dlg.setMessage("Please Wait...");
                dlg.show();

                //get vars
                String username_string = username.getText().toString();
                String name_string = name.getText().toString();
                String email_string = email.getText().toString();
                String phone_string = phone.getText().toString();
                String password_string = password.getText().toString();

                //error checking
                boolean input_error = false;
                StringBuilder validationErrorMessage = new StringBuilder("Missing the following entries:");
                if (username_string.trim().length() == 0) {
                    input_error = true;
                    validationErrorMessage.append(" username");
                }
                if (email_string.trim().length() == 0) {
                    if(input_error) validationErrorMessage.append(",");
                    input_error = true;
                    validationErrorMessage.append(" email");
                }
                if (password_string.trim().length() == 0) {
                    if(input_error) validationErrorMessage.append(",");
                    input_error = true;
                    validationErrorMessage.append(" password");
                }
                if (input_error) {
                    Toast.makeText(RegisterPage.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
                    dlg.dismiss();
                    return;
                }

                //place data in Parse object
                ParseUser user = new ParseUser();
                user.setUsername(username_string);
                user.setPassword(password_string);
                user.setEmail(email_string);
                user.put("phone", phone_string);
                user.put("name", name_string);

                //Push to Parse database
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(com.parse.ParseException e) {
                        dlg.dismiss();
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Intent intent = new Intent(RegisterPage.this, SettingsPage.class);
                            startActivity(intent);
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            Toast.makeText(RegisterPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        //back button
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){

                Util.playWavSound(getApplicationContext(), "select");

                //Call to new browser Activity, when button is pressed.
                //Todo: fix error regarding pressing back arrow button (<|) when playing game
                startActivity(new Intent(getApplicationContext(), SettingsPage.class));
            }
        });

        //menu button
        findViewById(R.id.main_menu_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(RegisterPage.this, MenuActivity.class));
                Util.playWavSound(getApplicationContext(), "wrong");

            }

        });
    }
}