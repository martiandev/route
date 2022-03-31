package com.route.exam.detail.view.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.route.exam.databinding.ItemPoiBinding
import com.route.exam.detail.model.Data

class POIViewHolder(var binding: ItemPoiBinding) : RecyclerView.ViewHolder(binding.root)
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
        binding.description =data.attribute!!.description
        binding.name =data!!.attribute!!.name
        binding.categoryName =data.relationships!!.category!!.cat!!.data.attribute!!.name
        binding.url =data.relationships!!.mainPicture!!.image.data.attribute!!.url
    }


}