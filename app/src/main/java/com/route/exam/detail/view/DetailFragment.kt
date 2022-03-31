package com.route.exam.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.route.exam.R
import com.route.exam.databinding.FragmentDetailBinding
import com.route.exam.util.AppConstant

class DetailFragment: BaseFragment() {

    lateinit var binding:FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setObserver()
    {
        vm!!.details.observe(viewLifecycleOwner, Observer {
            binding.url = it!!.data!!.attribute!!.routeMapImgUrl
            binding.routeName = it!!.data!!.attribute!!.name
            vm!!.getStartPoint()
        });
        vm!!.startPoint.observe(viewLifecycleOwner, Observer {
            var address = it!!.attribute!!.street
            address = addComma(address!!)+it!!.attribute!!.state
            address = addComma(address!!)+it!!.attribute!!.city
            address = addComma(address!!)+it!!.attribute!!.country
            binding.startRouteName = address
            attachFragments()
        });


    }

    override fun loadDetails()
    {

        vm!!.getDetails(requireContext(), AppConstant.DATA_FILE)

    }

    fun attachFragments()
    {
        addFragment(AttributeFragment(), R.id.linRatingFragment,"Rating")
        addFragment(ImageFragment(), R.id.imageFragment,"Image")
        addFragment(POIFragment(), R.id.poiFragment,"POI")
        addFragment(SponsorFragment(), R.id.sponsorFragment,"Sponsor")
    }

}