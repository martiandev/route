package com.route.exam.detail.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.route.exam.databinding.FragmentHorizontalListBinding
import com.route.exam.detail.view.adapter.POIAdapter

class POIFragment:BaseFragment() {

    lateinit var binding: FragmentHorizontalListBinding
    lateinit var adapter:POIAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHorizontalListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title = "Places of Interest"
        binding.rvItems.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvItems.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.HORIZONTAL
            )
        )

    }

    override fun setObserver() {
        vm!!.pois.observe(viewLifecycleOwner, Observer {
            if(it!=null)
            {
                Log.i("POIA","DATA:"+it)
                this.adapter = POIAdapter(it)
                this.binding.rvItems.adapter = adapter
            }

        })
    }

    override fun loadDetails() {
        vm!!.getPOIs()
    }
}