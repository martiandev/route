package com.route.exam.detail.view

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.route.exam.databinding.FragmentAttributeBinding
import android.net.Uri

import android.content.Intent
import android.util.Log
import android.widget.TextView


class AttributeFragment:BaseFragment() {

    lateinit var binding: FragmentAttributeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAttributeBinding.inflate(inflater, container, false)
        binding.seeMore = false
        return binding.root
    }


    override fun setObserver() {
        binding.vm = vm
        vm!!.ratingAttribute.observe(viewLifecycleOwner, Observer {
            binding.attribute = it!!
            var time:String = ""
            Log.d("TIME:","TOTAL:"+it.totalTime!!)

            var hours = it.totalTime!!/(60*60)
            Log.d("TIME:","HOURS:"+hours)
            var minutes = (it.totalTime!!%(60*60))/(60)
            Log.d("TIME:","Minutes:"+minutes)

            time = hours.toInt().toString()+":"+when(minutes>9){
                true->minutes.toInt().toString()
                false->"0"+(minutes.toInt()).toString()
            }
            binding.time = time
        })
        vm!!.more.observe(viewLifecycleOwner, Observer {
            binding.seeMore = it
        })
        vm!!.group.observe(viewLifecycleOwner, Observer {
            binding.group = it
            var spanTextBuilder = SpannableStringBuilder(it!!.attribute!!.name)
            spanTextBuilder!!.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    Log.d("CLICKED","CLICKED")
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(it!!.attribute!!.website)
                    startActivity(i)
                }
            },0, spanTextBuilder!!.length, 0)
            binding.tvLink.setText(spanTextBuilder, TextView.BufferType.SPANNABLE)
            binding.tvLink.setOnClickListener { view->
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(when(it!!.attribute!!.website!!.startsWith("http")){
                    true->it!!.attribute!!.website
                    false->"https://www.route.nl"+it!!.attribute!!.website}))
                startActivity(browserIntent)
            }
        })
    }

    override fun loadDetails() {
        vm!!.getRatingAttribute()
        vm!!.getGroup()
    }

}