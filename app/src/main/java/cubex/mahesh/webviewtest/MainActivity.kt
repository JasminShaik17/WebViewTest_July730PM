package cubex.mahesh.webviewtest

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var pDialog :ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pDialog = ProgressDialog(this)
        pDialog!!.setMessage("Please wait Page is Loading")
        wview.webViewClient = object:WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pDialog!!.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pDialog!!.dismiss()
            }
        }

            wview.settings.javaScriptEnabled = true
            wview.settings.builtInZoomControls = true
            wview.addJavascriptInterface(this,"and7amjuly")
    } // onCreate
    fun load(v:View){
        when(v.id){
            R.id.srch ->wview.loadUrl(et1.text.toString())
            R.id.fb -> wview.loadUrl("http://www.facebook.com")
            R.id.google -> wview.loadUrl("http://www.google.com")
            R.id.ytube -> wview.loadUrl("http://www.youtube.com")
            R.id.html -> wview.loadUrl("file:///android_asset/login.html")
        }
    }

    @JavascriptInterface
    fun displayMsg(email:String,pass:String)
    {
        Toast.makeText(this,
 email+"\n"+pass,Toast.LENGTH_LONG).show()
    }

}
