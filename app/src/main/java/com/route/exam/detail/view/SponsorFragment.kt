package com.route.exam.detail.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.route.exam.databinding.FragmentAttributeBinding
import com.route.exam.databinding.FragmentSponsorBinding

class SponsorFragment:BaseFragment(){

    lateinit var binding: FragmentSponsorBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSponsorBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun setObserver() {
        vm!!.group.observe(viewLifecycleOwner, Observer {
            if(it!=null)
            {
                Log.i("SPONSOR","ATTRIBUTE:"+it!!.attribute!!.logo)
                binding.attribute = it!!.attribute

            }
        })
    }

    override fun loadDetails() {
        vm!!.getGroup()
    }
}