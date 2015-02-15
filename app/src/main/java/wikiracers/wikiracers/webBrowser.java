/* WikiRacers - Web Browser Activity
 *
 *
 *
 * */
package wikiracers.wikiracers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/////////////////////////////////////////////
//test with git


//webBrowser class

public class webBrowser extends Activity {

    private WebView mWebView;  //New Webview Element
    static int pageCount = 0;
    static String currentURL = "";
    static String startingURL = "";
    static String target_URL = "";
    static Boolean backSwitch = true; //acts as a switch for the back button (prevents spamming for -1 counts)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);
        final TextView countText = (TextView) findViewById(R.id.textView2);


        //Links Activity Element to refrencable object
        mWebView = (WebView) findViewById(R.id.browser_webView_Window);
        //Sets internal JavaScript to ON
        mWebView.getSettings().setJavaScriptEnabled(true);
        //Sets Starting URL
        mWebView.loadUrl("http://en.wikipedia.org/wiki/Special:Random");
        mWebView.setWebViewClient(new mWebViewClient(){

            //counts when a page is loaded completely
            //used instead of onPageStarted for counting reasons (redirections, etc.)
            //TODO: make sure the startup doesn't start past 0
            //TODO: make sure the count doesn't go up when page load fails
            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view, url);
                if (!url.equals(currentURL)) {
                    currentURL = url;
                    pageCount++;
                    Toast.makeText(getApplicationContext(), url + " ~ " + String.valueOf(pageCount) + "target:" + target_URL + " start:" + startingURL, Toast.LENGTH_LONG).show();
                    countText.setText(String.valueOf(pageCount));
                    backSwitch = true;
                    if (get_page_title(url).equals(target_URL)){
                        TextView url_target = (TextView) findViewById(R.id.browser_webView_Text);
                        url_target.setText("Winner");
                    }
                    if(startingURL.equals("") && !url.equals("http://en.m.wikipedia.org/wiki/Special:Random")){
                        mWebView.loadUrl("http://en.m.wikipedia.org/wiki/Special:Random");
                        startingURL = url;
                    }
                    else if(target_URL.equals("") && !url.equals("http://en.m.wikipedia.org/wiki/Special:Random")){
                        TextView url_target;
                        url_target = (TextView)findViewById(R.id.browser_webView_Text);
                        url_target.setText(get_page_title(url));
                        target_URL = get_page_title(url);
                        mWebView.loadUrl(startingURL);
                        pageCount = 0;
                    }
                }
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                backSwitch = false; //to prevent cancelling a load
                super.onPageStarted(view,url,favicon);
            }
        });
        // Back button functionality
        Button webBack = (Button)findViewById(R.id.browser_webView_Back_Button);
        webBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mWebView.canGoBack() && backSwitch){
                    backSwitch = false;
                    mWebView.goBack();
                }
            }
        });
    }

    //Removes Web Client default buttons and bounds the browser space to
    //our WebView activity.

    private class mWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView webview, String url)
        {
            webview.loadUrl(url);
            return true;
        }
    }


    public String get_page_title(String url){
        //Gets everything after the final / in the Url aka the page_title
        int get_last_slash = url.lastIndexOf('/');
        String page_title = url.substring(get_last_slash+1);
        return page_title;
    }

}
