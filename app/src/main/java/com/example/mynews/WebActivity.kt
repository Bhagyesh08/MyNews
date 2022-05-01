package com.example.mynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val url=intent.getStringExtra("URL")
if(url!=null){
   web.settings.javaScriptEnabled=true
    web.settings.userAgentString="Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"
   web.webViewClient=object :WebViewClient(){
       override fun onPageFinished(view: WebView?, url: String?) {
           super.onPageFinished(view, url)
           progressBar.visibility= View.GONE
           web.visibility=View.VISIBLE
       }
   }
    web.loadUrl(url)
}
    }
}