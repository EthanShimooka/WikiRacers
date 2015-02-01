/* WikiRacers - Web Browser Activity
 *
 *
 *
 * */
package wikiracers.wikiracers;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


/////////////////////////////////////////////



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




    }


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
}
