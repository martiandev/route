package com.route.exam.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.route.exam.databinding.FragmentAttributeBinding

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
        })
        vm!!.more.observe(viewLifecycleOwner, Observer {
            binding.seeMore = it
        })
    }

    override fun loadDetails() {
        vm!!.getRatingAttribute()
    }

}