package wikiracers.wikiracers;

/**
 * Created by Tyler on 2/19/2015.
 * sign in page.  has register button too.  returns to the same page upon success.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.parse.ParseUser;

public class SettingsPage extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();

        //check if the user is logged in
        if (currentUser != null) {
            //the user is logged in, navigate to the user page
            startActivity(new Intent(this, UserPage.class));
        } else {
            //the user is not logged in, navigate to the log in page
            startActivity(new Intent(this, LoginPage.class));
        }
    }
}