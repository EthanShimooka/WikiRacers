/* WikiRacers - Web Browser Activity
 *
 *
 *
 * */
package wikiracers.wikiracers;

import android.app.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/////////////////////////////////////////////
//test with git


//WebBrowser class

public class WebBrowser extends Activity {

    private WebView mWebView;  //New Webview Element
    static int pageCount = -1;
    static String currentURL = "";
    static String startingURL = "";
    static String target_URL = "";
    static String target_URL_full = "";
    static List<String> list_URL = new ArrayList<String>();
    static Boolean gameStart = false;
    static Boolean gameRun = false; // might be the same as gameStart
    static Boolean peekMode = false; // toggles when the user is playing or looking at target
    static Boolean backSwitch = true; //acts as a switch for the back button (prevents spamming)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Determine screen size
        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            setContentView(R.layout.activity_web_browser_large);
        }
        else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            setContentView(R.layout.activity_web_browser);
        }
        else {
            setContentView(R.layout.activity_web_browser);
        }
        final TextView countText = (TextView) findViewById(R.id.countView2);
        final TextView targetPageText = (TextView)findViewById(R.id.browser_webView_Text);


        //Links Activity Element to refrencable object
        mWebView = (WebView) findViewById(R.id.browser_webView_Window);
        //Sets internal JavaScript to ON
        mWebView.getSettings().setJavaScriptEnabled(true);
        //Sets Starting URL
        if (currentURL.equals("")) {
            mWebView.loadUrl("http://en.wikipedia.org/wiki/Special:Random");
        }
        else{
            //booted up already started game
            mWebView.loadUrl(currentURL);
            targetPageText.setText(Util.get_page_title(target_URL));
            countText.setText(String.valueOf(pageCount));
            peekMode = false;
        }

        mWebView.setWebViewClient(new mWebViewClient(){

            //counts when a page is loaded completely
            //used instead of onPageStarted for counting reasons (redirections, etc.)
            //TODO: make sure the count doesn't go up when page load fails

            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view, url);



                if(peekMode){

                }
                else{backSwitch = true;
                    if (!url.equals(currentURL)) {
                        currentURL = url;
                        Util.remove_html_elements(mWebView);
                        if(gameRun) {
                            pageCount++;
                            if (pageCount == 1){
                                Util.db_increase_attempts();
                            }
                            if (list_URL.contains(currentURL) == false)
                               Util.playWavSound(getApplicationContext(), "right");

                        }
                        Log.d("game", url + " ~ " + String.valueOf(pageCount) + "target:" + target_URL + " start:" + startingURL);
                        countText.setText(String.valueOf(pageCount));
                        backSwitch = true;
                        //win condition
                        if (Util.get_page_title(url).equals(target_URL)){
                            Util.update_db(pageCount);
                            startActivity(new Intent(getApplicationContext(), WinnerPage.class));
                            list_URL.add(url);
                            TextView url_target = (TextView) findViewById(R.id.browser_webView_Text);
                            url_target.setText("Winner");
                            int i = 0;
                            for (;i<list_URL.size();++i){
                                Log.d("victory", list_URL.get(i));
                            }
                            Log.d("path", "url: " + url);
                            gameRun = gameStart = false; //allows player to browse around post game without messing with stats
                        }
                        //game is initialized here
                        if(startingURL.equals("") && !url.equals("http://en.m.wikipedia.org/wiki/Special:Random")){
                            mWebView.loadUrl("http://en.m.wikipedia.org/wiki/Special:Random");
                            startingURL = url;
                        }
                        else if(target_URL.equals("") && !url.equals("http://en.m.wikipedia.org/wiki/Special:Random")){
                            TextView url_target;
                            url_target = (TextView)findViewById(R.id.browser_webView_Text);

                            //TODO: Check if the string parse works                                                                      ///*
                            url_target.setText(Util.textFormat( Util.get_page_title(url)));
                            target_URL = Util.get_page_title(url);
                            target_URL_full = url;
                            mWebView.loadUrl(startingURL);
                            pageCount = -1;
                            gameStart = gameRun = true;
                        }else if (gameStart){
                            //Todo: test this (play the game all the way through)
                            list_URL.add(url);



                        }
                    }
                }
            }

            //Run for a successfully loaded window in webview
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                backSwitch = false; //to prevent cancelling a load
                super.onPageStarted(view,url,favicon);

                //Conditionals to check for link pressing sound
                //if (backSwitch == true)
                //   Util.playWavSound(getApplicationContext(), "right");

            }
        });



        // Back button functionality
        final Button webBack = (Button)findViewById(R.id.browser_webView_Back_Button);
        View.OnClickListener listen = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Util.playWavSound(getApplicationContext(), "wrong");

                if (view == webBack){
                    if (!peekMode) {
                        Log.d("game", "back clicked");
                        if (backSwitch) {
                            Log.d("game", "backswitch true");
                        }
                        if (mWebView.canGoBack()) {
                            Log.d("game", "GoBack true");
                        }
                        if (mWebView.canGoBack() && backSwitch) {
                            Log.d("game", "going back");
                            backSwitch = false;
                            mWebView.goBack();
                        }
                    }else{
                        targetPageText.performClick();
                    }
                }
                else if (view == targetPageText){
                    if(peekMode){
                        peekMode = false;
                        backSwitch = true;
                        mWebView.goBack();
                    }else {
                        Log.d("game", "text clicked");
                        peekMode = true;
                        mWebView.loadUrl(target_URL_full);
                    }
                }
            }
        };
        webBack.setOnClickListener(listen);
        targetPageText.setOnClickListener(listen);

        loadPop();

    }

    public void resetGame(){
        pageCount = -1;
        currentURL = "";
        startingURL = "";
        target_URL = "";
        target_URL_full = "";
        list_URL.clear();
        gameStart = false;
        gameRun = false; // might be the same as gameStart
        peekMode = false; // toggles when the user is playing or looking at target
        backSwitch = true;
        mWebView.loadUrl("http://en.wikipedia.org/wiki/Special:Random");
    }

    private void loadPop(){

        final Button btnOpenPopup = (Button)findViewById(R.id.openpopup);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                Util.playWavSound(getApplicationContext(), "select");

                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popup, null);

                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindow.dismiss();

                        Util.playWavSound(getApplicationContext(), "wrong");

                    }});
                Button reset = (Button)popupView.findViewById(R.id.pop_restart);
                reset.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v){
                    resetGame();
                }});
                //can be any resource apparently
                popupWindow.showAtLocation(findViewById(R.id.browser_webView_Text) ,
                        Gravity.CENTER, 0, 0);
            }});
    }

    //Removes Web Client default buttons and bounds the browser space to
    //our WebView activity.

    private class mWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webview, String url)
        {
            Log.d("game", "override view loading");
            if (!peekMode) {
                webview.loadUrl(url);
            }
            return true;
        }
    }

}
