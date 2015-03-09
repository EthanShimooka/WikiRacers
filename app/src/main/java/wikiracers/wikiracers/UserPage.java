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
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.List;

public class UserPage extends Activity {

    Integer worldAttempts = 0;
    Integer worldWins = 0;
    Integer worldMoves = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        ParseUser currentUser = ParseUser.getCurrentUser();
        String username = currentUser.getString("username");
        String name = currentUser.getString("name");
        String email = currentUser.getString("email");
        String phone = currentUser.getString("phone");
        Integer attempts = currentUser.getInt("numAttemptedGames");
        Integer totalWins = currentUser.getInt("numFinishedGames");
        Integer totalSteps = currentUser.getInt("numTotalSteps");

        //global stats

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

        //moves
        TextView totalStep = (TextView) findViewById(R.id.total_steps);
        totalStep.setText(totalSteps.toString());
        TextView worldMovesView = (TextView) findViewById(R.id.world_moves);
        worldMovesView.setText(worldMoves.toString());

        //wins
        TextView totalWinsView = (TextView) findViewById(R.id.num_wins);
        totalWinsView.setText(totalWins.toString());
        TextView worldWinsView = (TextView) findViewById(R.id.world_wins);
        worldWinsView.setText(worldWins.toString());

        //avg moves
        TextView avgStepsView = (TextView) findViewById(R.id.avg_steps);
        if(totalSteps == 0){avgStepsView.setText("0");}
        else{
            double avgSteps = totalSteps.doubleValue()/totalWins.doubleValue();
            avgSteps = (double)Math.round(avgSteps * 100) / 100;
            avgStepsView.setText(String.valueOf(avgSteps));
        }

        //attempts
        TextView attemptsView = (TextView) findViewById(R.id.attempts);
        attemptsView.setText(attempts.toString());
        TextView worldAttemptsView = (TextView) findViewById(R.id.world_attempts);
        worldAttemptsView.setText(worldAttempts.toString());

        //WLR
        double WLR = 0.0;
        if (attempts.doubleValue() >= .8){
            WLR = totalWins.doubleValue()/attempts.doubleValue();
        }
        WLR = (double)Math.round(WLR * 100) / 100;
        TextView WLRView = (TextView) findViewById(R.id.WLR);
        WLRView.setText(String.valueOf(WLR));
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