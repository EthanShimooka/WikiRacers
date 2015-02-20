package wikiracers.wikiracers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseObject;

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
public class menuActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_layout);

// Enable Local Datastore.
        Parse.enableLocalDatastore(getApplicationContext());

        Parse.initialize(this, "pUt8rvMtRcdgXu3PMpyLyjbxkTIA4xEeVEJ4C1hp", "P7dA3hQGSO8NpuCODjgzJFH1F7ADn487syS55FBq");

// TODO: delete this after done testing with db
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        final Button menuButton = (Button) findViewById(R.id.menu_button);
        final Button subscribeButton = (Button) findViewById(R.id.register_button);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == menuButton){
                    startActivity(new Intent(getApplicationContext(), webBrowser.class));
                }
                else if(view == subscribeButton){
                    startActivity(new Intent(getApplicationContext(), Register.class));
                }
            }
        };
        menuButton.setOnClickListener(listener);
        subscribeButton.setOnClickListener(listener);
        /*
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){

                //Call to new browser Activity, when button is pressed.
                //Todo: fix error regarding pressing back arrow button (<|) when playing game
            startActivity(new Intent(getApplicationContext(), webBrowser.class));
            }
        });
        */
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


}