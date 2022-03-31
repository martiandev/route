package com.route.exam.detail.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.route.exam.detail.viewmodel.DetailViewModel

open abstract class BaseFragment:Fragment() {

    var vm: DetailViewModel? = null

    companion object{
        @JvmStatic
        @BindingAdapter("routeImage")
        fun loadImage(view: ImageView, routeImage: String?) {
            Log.d("IMAGE:","Image:"+routeImage)
            Glide.with(view.context)
                .load(routeImage)
                .into(view)

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        loadDetails()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        vm = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)
    }

    fun addFragment(fragment: BaseFragment,container:Int,tag:String)
    {
        childFragmentManager.beginTransaction().add(container,fragment,tag).commit()
    }

    fun addComma(raw:String):String
    {
        if(raw.length>0)
        {
            return raw+", "
        }
        return raw
    }

    abstract fun setObserver()
    abstract fun loadDetails()

}