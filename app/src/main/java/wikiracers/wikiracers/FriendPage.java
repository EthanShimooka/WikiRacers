package wikiracers.wikiracers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kylel_000 on 3/1/2015.
 */
public class FriendPage extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friend_page);
        final Button addFriendBttn = (Button)findViewById(R.id.addFriendButton);
        final TextView findFriendText = (TextView)findViewById(R.id.friendTextSearch);
        OnClickListener listen = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (view == addFriendBttn){
                    Toast.makeText(getApplicationContext(), (findFriendText.getText() + " looking"),Toast.LENGTH_LONG).show();

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
                    String name = findFriendText.getText().toString();
                    query.whereEqualTo("username", name);
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, ParseException e) {
                            if (parseObject==null){
                                Toast.makeText(getApplicationContext(), (findFriendText.getText() + " not found"),Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), (parseObject.getString("username") + " found"),Toast.LENGTH_LONG).show();
                                ParseObject self = ParseUser.getCurrentUser();
                                ParseObject friend = parseObject;
                                if (self.getString("username").equals(friend.getString("username"))){
                                    Toast.makeText(getApplicationContext(), ("You can't add yourself!"),Toast.LENGTH_LONG).show();
                                }else {
                                    ParseObject object = new ParseObject("Friends");
                                    object.put("Friend", self);
                                    object.put("Friend2", friend);
                                    object.put("FriendName", self.getString("username"));
                                    object.put("FriendName2", friend.getString("username"));
                                    object.saveInBackground();
                                    Toast.makeText(getApplicationContext(), (friend.getString("username") + " added"),Toast.LENGTH_LONG).show();
                                    //TODO: add to table
                                    /* //this overwrites the entire listview
                                    ListView listView = (ListView)findViewById(R.id.friendList);
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1);
                                    listView.setAdapter(adapter);
                                    adapter.add(friend.getString("username"));
                                    */
                                }
                            }
                        }
                    });

                }
            }
        };
        addFriendBttn.setOnClickListener(listen);
        ParseQuery<ParseObject> findFriends = ParseQuery.getQuery("Friends");
        findFriends.whereEqualTo("Friend", ParseUser.getCurrentUser());

        //back button
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){

                Util.playWavSound(getApplicationContext(), "select");

                //Call to new browser Activity, when button is pressed.
                //Todo: fix error regarding pressing back arrow button (<|) when playing game
                startActivity(new Intent(getApplicationContext(), SettingsPage.class));
            }
        });

        //menu button
        Button menuButton = (Button) findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){

                Util.playWavSound(getApplicationContext(), "select");

                //Call to new browser Activity, when button is pressed.
                //Todo: fix error regarding pressing back arrow button (<|) when playing game
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });

        findFriends.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (parseObjects == null) {
                    //no friends q.q
                } else {
                    //yes friends :)
                    ListView listView = (ListView)findViewById(R.id.friendList);
                    ArrayList<String> listItems = new ArrayList<String>();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, listItems);
                    listView.setAdapter(adapter);
                    for (ParseObject po : parseObjects){
                        listItems.add(po.getString("FriendName2"));
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }


}
