package wikiracers.wikiracers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.Parse;


/**
 * =========================================
 * Authored by Ethan , 2/15/2015
 * Project : WikiRacers
 * =========================================
 *
 * TODO: PLEASE READ OVER THE COMMENTS FOR EACH CLASS
 *
 * Messages:
 *
 * 2/15/2015
 *
 * Hey guys, Ethan Here, I have made the base structure for our menu system. As you can see, they
 * are not all fragments like originally planned. There is a few reasons. For one, fragments
 * should be used to display small menu's and objects within an activity, rather than running
 * all activity-like fragments parallel with each other. I was having this conversation with my
 * mentor and he was advising to go for a more simple structure with separate activities for each
 * full screen window. This means our stats screen may possibly end up being another activity.
 *
 *
 *
 *
 */
public class MenuActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_layout);


        if (haveNetworkConnection() == false) {
            Context context = getApplicationContext();
            CharSequence text = "Network Unavailable!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
// Enable Local Datastore.
        //Parse.enableLocalDatastore(this);
        Parse.initialize(this, "pUt8rvMtRcdgXu3PMpyLyjbxkTIA4xEeVEJ4C1hp", "P7dA3hQGSO8NpuCODjgzJFH1F7ADn487syS55FBq");







        //ParseObject testObject = new ParseObject("TestObject");
        //testObject.put("foo", "bar");
        //testObject.saveInBackground();

        Button menuButton = (Button) findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){

                //Call to new browser Activity, when button is pressed.
                //Todo: fix error regarding pressing back arrow button (<|) when playing game
                startActivity(new Intent(getApplicationContext(), WebBrowser.class));
            }
        });

        Button settingButton = (Button) findViewById(R.id.settings_button);
        settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){

                //Call to new browser Activity, when button is pressed.
                //Todo: fix error regarding pressing back arrow button (<|) when playing game
                startActivity(new Intent(getApplicationContext(), SettingsPage.class));
            }
        });



    }




    /* (non-Javadoc)
  * @see android.app.Activity#onDestroy()
  *
  *  The final call you receive before your activity is destroyed. This can happen either because
  *  the activity is finishing (someone called finish() on it, or because the system is temporarily
  *  destroying this instance of the activity to save space. You can distinguish between these two
  *  scenarios with the isFinishing() method.
  *
  */
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    /* (non-Javadoc)
    * @see android.app.Activity#onPause()
    *
    * Called as part of the activity lifecycle when an activity is going into the background, but
    * has not (yet) been killed. The counterpart to onResume(). When activity B is launched in
    * front of activity A, this callback will be invoked on A. B will not be created until A's
    * onPause() returns, so be sure to not do anything lengthy here.
    *
    */
    @Override
    protected void onPause() {
        super.onPause();

    }

    /* (non-Javadoc)
    * @see android.app.Activity#onRestart()
    *
    *Called after your activity has been stopped, prior to it being started again.
    *Always followed by onStart()
    *
    */
    @Override
    protected void onRestart() {
        super.onRestart();

    }

    /* (non-Javadoc)
    * @see android.app.Activity#onResume()
    *
    * Called when the activity will start interacting with the user. At this point your activity
    * is at the top of the activity stack, with user input going to it.
    * Always followed by onPause().
    */
    @Override
    protected void onResume() {
        super.onResume();

    }

    /* (non-Javadoc)
    * @see android.app.Activity#onStart()
    *
    * Called when the activity is becoming visible to the user. Followed by onResume() if the
    * activity comes to the foreground, or onStop() if it becomes hidden.
    *
    */
    @Override
    protected void onStart() {
        super.onStart();

    }
    /* (non-Javadoc)
    * @see android.app.Activity#onStop()
    *
    * Called when you are no longer visible to the user. You will next receive either onRestart(),
    * onDestroy(), or nothing, depending on later user activity.
    * ---Note that this method may never be called, in low memory situations where the system does
    * not have enough memory to keep your activity's process running after
    * its onPause() method is called.
    */
    @Override
    protected void onStop() {
        super.onStop();
    }



    //haveNetworkConnection() Check that there is an active internet connection with either wiFi or
    // cellular connection.

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


}