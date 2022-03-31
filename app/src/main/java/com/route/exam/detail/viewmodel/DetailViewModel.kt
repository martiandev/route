package com.route.exam.detail.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.exam.util.FileReader
import kotlinx.coroutines.launch
import com.google.gson.Gson
import com.route.exam.detail.model.*


class DetailViewModel: ViewModel() {
//

    var details:MutableLiveData<DetailResponse?> = MutableLiveData<DetailResponse?>()
    var startPoint:MutableLiveData<Data?> = MutableLiveData<Data?>()
    var group:MutableLiveData<Data?> = MutableLiveData<Data?>()
    var ratingAttribute:MutableLiveData<Attribute?> = MutableLiveData<Attribute?>()
    var more:MutableLiveData<Boolean?> = MutableLiveData<Boolean?>()
    var images:MutableLiveData<List<Data>?> = MutableLiveData<List<Data>?>()
    var pois:MutableLiveData<List<Data>?> = MutableLiveData<List<Data>?>()

    fun getDetails(context:Context,filename:String)
    {
        viewModelScope.launch {
            val gson = Gson()
            val result = FileReader.readFile(context,filename)
            if(result!=null)
            {
                details.postValue(gson.fromJson(result!!, DetailResponse::class.java))
            }
            else
            {
                details.postValue(null)
            }

        }
    }

    fun getStartPoint()
    {
        viewModelScope.launch {
            if (details.value != null) {
                var routeId = details!!.value!!.data.relationships!!.startPoint!!.data.id
                details!!.value!!.included.forEach {
                    if (it.id!!.equals(routeId)) {
                        startPoint.postValue(it)
                    }
                }

            }
        }
    }
    fun getRatingAttribute()
    {
        viewModelScope.launch {
            if (details.value != null) {
                ratingAttribute.postValue(details!!.value!!.data!!.attribute)
            }
        }
    }

    fun seeMore()
    {
        viewModelScope.launch {
            more.postValue(when(more.value==null)
            {
                true->false
                false->!more!!.value!!
            })
        }
    }

    fun getImages()
    {
        viewModelScope.launch {
            var results:ArrayList<Data> = ArrayList()
            if (details.value != null) {
                details!!.value!!.data.relationships!!.images!!.data.forEach { data ->
                    details!!.value!!.included.forEach {
                        if(it.id.equals(data.id))
                        {
                            results.add(it)
                        }
                    }
                }
                images.postValue(results)
           }
        }
    }
    fun getGroup()
    {
        viewModelScope.launch {
            if (details.value != null) {

                    details!!.value!!.included.forEach {
                        if(it.id.equals(details!!.value!!.data.relationships!!.group!!.data!!.id))
                        {
                            group.postValue(it)
                        }
                    }


            }
        }
    }
    fun getPOIs()
    {
        viewModelScope.launch {
            var results:ArrayList<Data> = ArrayList()
            var temp:ArrayList<Data> = ArrayList()
            if (details.value != null) {
                details!!.value!!.data.relationships!!.routePoints!!.data.forEach { data ->
                    details!!.value!!.included.filter { it.type.equals(Data.TYPE_ROUTE_POINTS)}.forEach { included ->
                        if(included.id.equals(data.id))
                        {

                            if(included.relationships!!.poi!!.data!=null)
                            {
                                temp.add(included)
                            }
                        }
                    }
                }
                temp.forEach { t ->
                    details!!.value!!.included.filter { it.type.equals(Data.TYPE_POIS)}.forEach { included ->

                        if(included.id!!.equals(t.relationships!!.poi!!.data.id))
                        {

                            if(included.relationships!!.mainPicture!!.data!=null)
                            {

                                details!!.value!!.included.filter { it.type.equals(Data.TYPE_IMAGES)}.forEach {
                                    if(included.relationships!!.mainPicture!!.data!!.id.equals(it.id))
                                    {
                                        included.relationships.mainPicture!!.image = Image(it)
                                    }
                                }
                                details!!.value!!.included.filter { it.type.equals(Data.TYPE_CATEGORIES)}.forEach {
                                    if(included.relationships!!.category!!.data!!.id.equals(it.id))
                                    {
                                        included.relationships.category!!.cat = Category(it,null)
                                    }
                                }
                                results.add(included)
                            }

                        }

                    }
                }
                pois.postValue(results)
            }
        }
    }




}