package com.route.exam.detail.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.route.exam.databinding.ItemPoiBinding
import com.route.exam.detail.model.Data


class POIAdapter: RecyclerView.Adapter<POIViewHolder>{

    var pois = ArrayList<Data>()

    constructor(pois:List<Data>):super()
    {
        this.pois.addAll(pois)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): POIViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemPoiBinding = ItemPoiBinding.inflate(layoutInflater, parent, false)
        return POIViewHolder(binding)
    }

    override fun onBindViewHolder(holder: POIViewHolder, position: Int) {
        holder.bind(this.pois.get(position))
    }

    override fun getItemCount(): Int {
        return  this.pois.size;
    }

}