package com.route.exam.detail.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.route.exam.databinding.ItemImageBinding
import com.route.exam.detail.model.Data


class ImageAdapter: RecyclerView.Adapter<ImageViewHolder>{

    var images = ArrayList<Data>()

    constructor(images:List<Data>):super()
    {
        this.images.addAll(images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemImageBinding = ItemImageBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(this.images.get(position))
    }

    override fun getItemCount(): Int {
        return  this.images.size;
    }

}