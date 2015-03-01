package wikiracers.wikiracers;

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

    public static boolean update_db(int steps){
        ParseUser currentUser = ParseUser.getCurrentUser();

        //check if the user is logged in
        if (currentUser != null){
            currentUser.increment("numFinishedGames");
            currentUser.increment("numTotalSteps", steps);
            currentUser.saveInBackground();
            return true;
        }
        else{return false;}
    }

}
