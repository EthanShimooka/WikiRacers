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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseUser;

public class userPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        ParseUser currentUser = ParseUser.getCurrentUser();
        String username = currentUser.getString("username");
        String name = currentUser.getString("name");
        String email = currentUser.getString("email");
        String phone = currentUser.getString("phone");

        LinearLayout lView = (LinearLayout)findViewById(R.id.user_data);
        TextView usernameText = new TextView(userPage.this);
        usernameText.setText(username);
        lView.addView(usernameText);
        TextView nameText = new TextView(userPage.this);
        nameText.setText(name);
        lView.addView(nameText);
        TextView emailText = new TextView(userPage.this);
        emailText.setText(email);
        lView.addView(emailText);
        TextView phoneText = new TextView(userPage.this);
        phoneText.setText(phone);
        lView.addView(phoneText);

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