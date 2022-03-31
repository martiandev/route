package com.route.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.ViewModelProvider
import com.route.exam.detail.viewmodel.DetailViewModel

import androidx.core.content.ContextCompat

import androidx.core.graphics.drawable.DrawableCompat
import android.content.Intent

import android.view.MenuItem







class MainActivity : AppCompatActivity() {


    lateinit var vm:DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this).get(DetailViewModel::class.java)




    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        var drawable = menu.findItem(R.id.share).icon
        drawable = DrawableCompat.wrap(drawable!!)
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.white))
        menu.findItem(R.id.share).icon = drawable
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share -> {
                val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, vm.ratingAttribute.value!!.routeMapImgUrl)
                        type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}