package wikiracers.wikiracers;

import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.WebView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.parse.ParseUser;


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
 *   Util Methods:
 *
 *   public static String textFormat(String input_string)
 *
 *   public static String get_page_title(String url)
 *
 *   public static String get_page_title(String url)
 *
 *   public static boolean check_url(String url)
 *
 *   public static void remove_html_elements(WebView mWebView)
 *
 *   public static String get_dictionary_word
 *
 *   public static String currentUserString(ParseUser currentUser)
 *
 *   public static void playWavSound(Context context, String sound_file)
 *
 * =========================================
 */
public class Util {


    // textFormat()
    // Function to format wikipedia strings with underscores to white spaces.
    // Takes (STRING) and returns (STRING)

    public static String textFormat(String input_string){

        String formatted_string = "";
        for (int i=0; i<input_string.length(); i++) {
            char c = input_string.charAt(i);
            if ( c == '_')   c = ' ';
            formatted_string += c;
        }
    return formatted_string;
    }


    // get_page_title()
    // Function to fetch specified URL

    public static String get_page_title(String url){
        //Gets everything after the final / in the Url aka the page_title
        int get_last_slash = url.lastIndexOf('/');
        String page_title = url.substring(get_last_slash+1);
        return page_title;
    }

    public static boolean check_url(String url){
        int get_last_slash = url.lastIndexOf('/');
        String base_url = url.substring(0, get_last_slash);
        return base_url == "http://en.m.wikipedia.org/wiki/";
    }

    public static void remove_html_elements(WebView mWebView){
        //Removes Search bar
        mWebView.loadUrl("javascript:(function() { " + "document.getElementsByClassName('header')[0].style.display = 'none'; " + "})()");
        //Removes last edit info
        mWebView.loadUrl("javascript:(function() { " + "document.getElementsByClassName('last-modified-bar truncated-text')[0].style.display = 'none'; " + "})()");
        //Removes Watch page and edit page icons at the top of the page
        mWebView.loadUrl("javascript:(function() { " + "document.getElementById('page-actions').style.display = 'none'; " + "})()");
        //Removes edit page icons throughout the page
        mWebView.loadUrl("javascript:(function() { " + "document.getElementsByClassName('icon icon-edit-enabled edit-page icon-32px')[0].style.display = 'none'; " + "})()");
        //Removes "Read in another language" button
        mWebView.loadUrl("javascript:(function() { " + "document.getElementById('page-secondary-actions').style.display = 'none'; " + "})()");
        //Removes Footer
        mWebView.loadUrl("javascript:(function() { " + "document.getElementById('footer').style.display = 'none'; " + "})()");

        mWebView.loadUrl("javascript:(function() { " + "var block_name = document.getElementById('References');" +
        "var block = block_name.parentElement;" + "var control = block.getAttribute('aria-controls');" +
        "control.style.display = 'none';" + "})()"
        );
    }


    // get_dictionary_word()
    // Searches for simplified dictionary word

    public static String get_dictionary_word(Context myContext){
        String word = null;
        Random rand = new Random();
        try {
            InputStream dict = myContext.getAssets().open("dictionary.txt");
            int n = 0;
            for(Scanner sc = new Scanner(dict); sc.hasNext(); )
            {
                ++n;
                String line = sc.nextLine();
                if(rand.nextInt(n) == 0)
                    word = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        word = word.substring(0,1).toUpperCase() + word.substring(1);
        return word;
    }


    // currentUserString ()
    // Accepts a ParseUser profile and extracts and returns a string value associated with
    // said profile

    public static String currentUserString(ParseUser currentUser) {
        String currentUserString;

        currentUserString = currentUser.getUsername();
        return currentUserString;
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
        if(mp!=null)  {
          if(!(mp.isPlaying())){
            mp.stop();
            mp.release();
            }
        }
    }




}
