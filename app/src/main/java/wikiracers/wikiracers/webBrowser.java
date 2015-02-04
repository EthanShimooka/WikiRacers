/* WikiRacers - Web Browser Activity
 *
 *
 *
 * */
package wikiracers.wikiracers;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


/////////////////////////////////////////////
//test with git


//webBrowser class

public class webBrowser extends ActionBarActivity {

    private WebView mWebView;  //New Webview Element

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);


        //Links Activity Element to refrencable object
        mWebView = (WebView) findViewById(R.id.browser_webView_Window);
        //Sets internal JavaScript to ON
        mWebView.getSettings().setJavaScriptEnabled(true);
        //Sets Starting URL
        mWebView.loadUrl("http://www.wikipedia.com");
        mWebView.setWebViewClient(new mWebViewClient());

        Button webBack = (Button)findViewById(R.id.browser_webView_Back_Button);
        webBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mWebView.canGoBack()){
                    mWebView.goBack();
                }
            }
        });
    }


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
