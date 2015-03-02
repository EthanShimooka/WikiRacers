package wikiracers.wikiracers;

/**
 * Created by Tyler on 2/20/2015.
 * intended to display user information and stats.
 * currently displays a log out button
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseUser;

public class UserPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        ParseUser currentUser = ParseUser.getCurrentUser();
        String username = currentUser.getString("username");
        String name = currentUser.getString("name");
        String email = currentUser.getString("email");
        String phone = currentUser.getString("phone");
        Integer totalWins = currentUser.getInt("numFinishedGames");
        Integer totalSteps = currentUser.getInt("numTotalSteps");

        TextView usernameTextView=(TextView)findViewById(R.id.username);
        usernameTextView.setText(username);
        TextView emailTextView=(TextView)findViewById(R.id.email);
        emailTextView.setText(email);
        if (!name.equals("")) {
            TextView nameTextView = (TextView) findViewById(R.id.name);
            nameTextView.setText(name);
        }
        if (!phone.equals("")) {
            TextView phoneTextView = (TextView) findViewById(R.id.phone);
            phoneTextView.setText(phone);
        }
        TextView totalStep = (TextView) findViewById(R.id.total_steps);
        totalStep.setText(totalSteps.toString());
        TextView totalWinsView = (TextView) findViewById(R.id.num_wins);
        totalWinsView.setText(totalWins.toString());
        TextView avgStepsView = (TextView) findViewById(R.id.avg_steps);
        if(totalSteps == 0){avgStepsView.setText("0");}
        else{
            double avgSteps = totalSteps.doubleValue()/totalWins.doubleValue();
            avgStepsView.setText(String.valueOf(avgSteps));
        }
        //logout button
        findViewById(R.id.logout_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new AlertDialog.Builder(UserPage.this)
                        .setMessage("Are you sure you want to log out?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                final ProgressDialog dlg = new ProgressDialog(UserPage.this);
                                dlg.setTitle("Logging out...");
                                dlg.setMessage("Please wait...");
                                dlg.show();
                                ParseUser.logOut();
                                dlg.dismiss();
                                startActivity(new Intent(UserPage.this, SettingsPage.class));
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }

        });

        //menu button
        findViewById(R.id.main_menu_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(UserPage.this, MenuActivity.class));
            }

        });

        //friend button
        findViewById(R.id.add_friend_button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startActivity(new Intent(UserPage.this, FriendPage.class));
            }});
    }
}