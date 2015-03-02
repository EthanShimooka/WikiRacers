package wikiracers.wikiracers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;
import com.parse.ParseUser;

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
                    Toast.makeText(getApplicationContext(), (findFriendText.getText() + " lookinh"),Toast.LENGTH_LONG).show();

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
                    String name = findFriendText.getText().toString();
                    query.whereEqualTo("name", name);
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject parseObject, ParseException e) {
                            if (parseObject==null){
                                Toast.makeText(getApplicationContext(), (findFriendText.getText() + " not found"),Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(), (parseObject.getString("name") + " found"),Toast.LENGTH_LONG).show();
                                ParseObject self = ParseUser.getCurrentUser();
                                ParseObject friend = parseObject;
                                ParseObject object = new ParseObject("Friends");
                                object.put("Friend", self);
                                object.put("Friend2",friend);
                                object.saveInBackground();
                            }
                        }
                    });

                }
            }
        };
        addFriendBttn.setOnClickListener(listen);
    }


}
