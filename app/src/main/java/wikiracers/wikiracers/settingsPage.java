package wikiracers.wikiracers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;

/**
 * Created by Tyler on 2/19/2015.
 */
public class settingsPage extends Activity {

    private EditText username = null;
    private EditText password = null;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        username = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.editText2);
        login = (Button) findViewById(R.id.button1);
    }

    public void login(View view) {
        String name = username.getText().toString();
        String pass = password.getText().toString();

        ParseUser user = new ParseUser();
        user.setUsername(name);
        user.setPassword(pass);

// other fields can be set just like with ParseObject
        //user.put("phone", "650-253-0000");

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Context context = getApplicationContext();
                    CharSequence text = "Success";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Context context = getApplicationContext();
                    CharSequence text = "Fail";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                }
            }

            @Override
            public void done(com.parse.ParseException e) {

            }
        });

        //ParseObject testObject = new ParseObject("User");
        //testObject.put("username", name);
        //testObject.put("password", pass);
        //testObject.saveInBackground();

    }
}