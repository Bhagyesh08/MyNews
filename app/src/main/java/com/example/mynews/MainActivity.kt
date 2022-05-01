package com.example.mynews

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    private var articles= mutableListOf<Article>()
    var num=1
    var totalResults=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter= NewsAdapter(this@MainActivity,articles)
        rr.adapter=adapter
        rr.layoutManager=LinearLayoutManager(this@MainActivity)
        getnews()
    }
    private fun getnews(){

        val news=Service.newsInstance.getHeadlines("in",1)

news.enqueue(object :retrofit2.Callback<News>{
    override fun onFailure(call: Call<News>, t:Throwable){
Log.d("Bhagyesh","Error",t)
    }
    override fun onResponse(call:Call<News>,response: Response<News>){
val news=response.body()
    if(news!=null){
totalResults=news.totalResults

        Log.d("Bhagyesh",news.toString())
articles.addAll(news.articles)
        adapter.notifyDataSetChanged()
    }
    }
})
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
      menuInflater.inflate(R.menu.nav_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       val id=item.itemId
        if(id==R.id.about){
startActivity(Intent(this,AboutActivity::class.java))
        }
      else if(id==R.id.exit){
val builder=MaterialAlertDialogBuilder(this)
            builder.setTitle("Exit")
                .setMessage("Do you want to close app?")
                .setPositiveButton("Yes"){_, _ ->
                    exitProcess(1)
                }
                .setNegativeButton("No"){dialog, _ ->
                    dialog.dismiss()
                }
            val customDialog=builder.create()
            customDialog.show()
            customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED)
            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)

        }
        return super.onOptionsItemSelected(item)
    }
}


