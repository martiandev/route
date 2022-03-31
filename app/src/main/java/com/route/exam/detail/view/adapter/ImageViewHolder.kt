package com.route.exam.detail.view.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.route.exam.databinding.ItemImageBinding
import com.route.exam.detail.model.Data

class ImageViewHolder(var binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)
{

    companion object{
        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(view: ImageView, image: String?) {
            Glide.with(view.context)
                .load(image)
                .into(view)
        }
    }
    fun bind(data: Data)
    {
        binding.url =data.attribute!!.url
    }


}