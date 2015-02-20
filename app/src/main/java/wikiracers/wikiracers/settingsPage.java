package wikiracers.wikiracers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;

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
        ParseObject testObject = new ParseObject("User");
        testObject.put("username", name);
        testObject.put("password", pass);
        testObject.saveInBackground();

    }
}