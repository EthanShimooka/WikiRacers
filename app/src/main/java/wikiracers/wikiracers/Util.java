package wikiracers.wikiracers;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

/**
 * =========================================
 * Authored by Ethan , 2/28/2015
 * Project : WikiRacers
 *
 * Class Util
 *
 * All support functions not native to activities
 * or those too cumbersome to implement in Activity
 * classes are to be represented here.
 *
 * =========================================
 */
public class Util {


    // Function to format wikipedia strings with underscores to white spaces.
    // Takes (STRING) and returns (STRING)

    //TODO: Test module
    public static String textFormat(String input_string){

        String formatted_string = "";
        for (int i=0; i<input_string.length(); i++) {
            char c = input_string.charAt(i);
            if ( c == '_')   c = ' ';
            formatted_string += c;
        }
    return formatted_string;
    }


    //Todo:Check that there are no conflicts
    // Function to fetch specified URL

    public static String get_page_title(String url){
        //Gets everything after the final / in the Url aka the page_title
        int get_last_slash = url.lastIndexOf('/');
        String page_title = url.substring(get_last_slash+1);
        return page_title;
    }



       //Function for playing various a sound clips

       public static void playWavSound(Context context, String sound_file) {
           MediaPlayer mp;

           if (sound_file == "right"){
              mp = MediaPlayer.create(context,R.raw.sfx_right );
           }else if (sound_file == "wrong"){
              mp = MediaPlayer.create(context,R.raw.sfx_wrong );
             }else{
               mp = MediaPlayer.create(context,R.raw.sfx_select );
           }
           mp.start();
    }


}
