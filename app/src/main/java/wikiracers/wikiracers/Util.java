package wikiracers.wikiracers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.WebView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

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

    public static void remove_html_elements(WebView mWebView){
        //Removes Search bar
        mWebView.loadUrl("javascript:(function() { " + "document.getElementsByClassName('header')[0].style.display = 'none'; " + "})()");
        //Removes last edit info
        mWebView.loadUrl("javascript:(function() { " + "document.getElementsByClassName('last-modified-bar truncated-text')[0].style.display = 'none'; " + "})()");
        //Removes Watch page and edit page icons at the top of the page
        mWebView.loadUrl("javascript:(function() { " + "document.getElementById('page-actions').style.display = 'none'; " + "})()");
        //Removes edit page icons throughout the page
        mWebView.loadUrl("javascript:(function() { " + "document.getElementsByClassName('icon icon-edit-enabled edit-page icon-32px').style.display = 'none'; " + "})()");
        //Removes "Read in another language" button
        mWebView.loadUrl("javascript:(function() { " + "document.getElementByClassName('languageSelector mw-ui-button button').style.display = 'none'; " + "})()");
        //Removes Footer
        mWebView.loadUrl("javascript:(function() { " + "document.getElementById('footer').style.display = 'none'; " + "})()");
    }

    public String get_dictionary_word(Context myContext){
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
        return word;
    }








}
