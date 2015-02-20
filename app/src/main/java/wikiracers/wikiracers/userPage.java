package wikiracers.wikiracers;

/**
 * Created by Tyler on 2/20/2015.
 * intended to display user information and stats.
 * currently displays a log out button
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseUser;

public class userPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        findViewById(R.id.logout_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final ProgressDialog dlg = new ProgressDialog(userPage.this);
                dlg.setTitle("Logging out...");
                dlg.setMessage("Please wait...");
                dlg.show();

                ParseUser.logOut();
                startActivity(new Intent(userPage.this, settingsPage.class));
            }

        });
    }
}