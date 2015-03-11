package wikiracers.wikiracers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/*
*This is the page after you've won the game, it will redirect you to this page.
*Tried to do an alert dialog instead of redirecting to another page but have some issues atm
*/
//Todo: get name, get counts test

public class WinnerPage extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_page);
        statisticbtn();
        menuButton();

    }

    //Redirects to stats page
    private void statisticbtn(){
        Button statsbtn = (Button) findViewById(R.id.statisticsbutton);
        View.OnClickListener statsYell = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingsPage.class));
                Util.playWavSound(getApplicationContext(), "select");

            }
        };
        statsbtn.setOnClickListener(statsYell);
    }
    //Redirects to menuActivity
    private void menuButton(){
        Button menuBtn = (Button) findViewById(R.id.menu_button);
        View.OnClickListener statsYell = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                Util.playWavSound(getApplicationContext(), "select");

            }
        };
        menuBtn.setOnClickListener(statsYell);
    }
    private void hibtn(){
        Button menuBtn = (Button) findViewById(R.id.hiscorebutton);
        View.OnClickListener statsYell = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), HiscorePage.class));
            }
        };
        menuBtn.setOnClickListener(statsYell);
    }
}