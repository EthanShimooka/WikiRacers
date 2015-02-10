/* WikiRacers - Web Browser Activity
 *
 *
 *
 * */
package wikiracers.wikiracers;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/////////////////////////////////////////////
//test with git


//webBrowser class

public class webBrowser extends ActionBarActivity {

    private WebView mWebView;  //New Webview Element
    static int pageCount = 0;
    static String currentURL = "";
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
                    Toast.makeText(getApplicationContext(), url + " ~ " + String.valueOf(pageCount), Toast.LENGTH_SHORT).show();
                    countText.setText(String.valueOf(pageCount));
                    backSwitch = true;
                }
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                backSwitch = false; //to prevent cancelling a load
                super.onPageStarted(view,url,favicon);
            }
        });
        // Back button functionality
        //TODO: make sure pressing doesn't increase count (test more)
        ImageButton webBack = (ImageButton)findViewById(R.id.browser_webView_Back_Button);
        webBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mWebView.canGoBack() && backSwitch){
                    backSwitch = false;
                    mWebView.goBack();
                    pageCount--;
                    countText.setText(String.valueOf(pageCount));
                }
            }
        });

        //a settings button
        //not yet on layout
        ImageButton settingButton = (ImageButton)findViewById(R.id.settingButton);
        settingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "setting button", Toast.LENGTH_SHORT).show();
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




/////////////// JUNK I DON'T WANT TO REMOVE JUST YET////////////////////////////
////////////////////////////////////////////////////////////////////////////////

/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack())
        {
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
*/

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    */
}
