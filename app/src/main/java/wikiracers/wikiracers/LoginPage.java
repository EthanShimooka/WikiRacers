package wikiracers.wikiracers;

/**
 * Created by Tyler on 2/19/2015.
 * sign in page.  has register button too.  returns to the same page upon success.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.ParseUser;

public class LoginPage extends Activity {

    //initialize variables
    private EditText username = null;
    private EditText password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //grab values
        username = (EditText) findViewById(R.id.edit_username);
        password = (EditText) findViewById(R.id.edit_password);

        //register button
        findViewById(R.id.register_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterPage.class));
                Util.playWavSound(getApplicationContext(), "select");  //sound


            }
        });

        //log in button
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Util.playWavSound(getApplicationContext(), "select");   //sound


                final ProgressDialog dlg = new ProgressDialog(LoginPage.this);
                dlg.setTitle("Logging in...");
                dlg.setMessage("Please wait...");
                dlg.show();

                //retrieve values
                String name = username.getText().toString();
                String pass = password.getText().toString();

                //error checking
                boolean input_error = false;
                StringBuilder validationErrorMessage = new StringBuilder("Missing the following entries:");
                if (name.trim().length() == 0) {
                    input_error = true;
                    validationErrorMessage.append(" username");
                }
                if (pass.trim().length() == 0) {
                    if(input_error) validationErrorMessage.append(",");
                    input_error = true;
                    validationErrorMessage.append(" password");
                }
                if (input_error) {
                    Toast.makeText(LoginPage.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
                    dlg.dismiss();
                    Util.playWavSound(getApplicationContext(), "wrong");

                    return;
                }

                //login
                ParseUser.logInInBackground(name, pass, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, com.parse.ParseException e) {
                        dlg.dismiss();
                        if (e != null) {
                            //login error
                            Toast.makeText(LoginPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            //login success
                            Intent intent = new Intent(LoginPage.this, SettingsPage.class);
                            startActivity(intent);
                            Util.playWavSound(getApplicationContext(), "right");

                        }
                    }
                });
            }
        });

        //menu button
        findViewById(R.id.main_menu_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(LoginPage.this, MenuActivity.class));
                Util.playWavSound(getApplicationContext(), "wrong");

            }

        });
    }
}