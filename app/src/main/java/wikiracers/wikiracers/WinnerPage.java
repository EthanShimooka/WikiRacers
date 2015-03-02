package wikiracers.wikiracers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import java.net.URL;
import android.util.Log;
// Alert Dialog Pops up after winning pop
public class WinnerPage extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_page);
        menuButton();

        LayoutInflater inflater= LayoutInflater.from(this);
        View view=inflater.inflate(R.layout.winner_page, null);
        TextView textview=(TextView)view.findViewById(R.id.scroll);




        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("You're a Winner!");
        alertDialog.setMessage("This is your path count: " + WebBrowser.pageCount + "\n" + "\n" + "This is your Path: "+ "\n"  + WebBrowser.list_URL);
        alertDialog.setIcon(R.drawable.ic_launcher);

        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Share It", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Confirm", Toast.LENGTH_SHORT).show();

            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Button", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Affirmative", Toast.LENGTH_SHORT).show();

            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Main Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                Toast.makeText(getApplicationContext(), "Ay", Toast.LENGTH_SHORT).show();

            }
        });

        alertDialog.show();
    }
    //Redirects to menuActivity
    private void menuButton() {
        Button menuBtn = (Button) findViewById(R.id.menu_button);
        View.OnClickListener statsYell = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        };
        menuBtn.setOnClickListener(statsYell);
    }


}
