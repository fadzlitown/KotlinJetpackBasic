package com.inovasiti.kucingapp.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.inovasiti.kucingapp.R
import com.inovasiti.kucingapp.databinding.FragmentCatBinding
import com.inovasiti.kucingapp.getProgressDrawable
import com.inovasiti.kucingapp.loadImage
import com.inovasiti.kucingapp.model.CatPalette
import com.inovasiti.kucingapp.viewmodel.CatDetailViewModel
import kotlinx.android.synthetic.main.fragment_cat.*

/**
 * A simple [Fragment] subclass.
 * Use the [CatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CatFragment : Fragment() {
    private lateinit var vm: CatDetailViewModel
    private var catUID = 0L
    private lateinit var dataBinding: FragmentCatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cat, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //if arg != null
        arguments?.let {
            catUID = CatFragmentArgs.fromBundle(it).catValue.toLong()
        }
        vm = ViewModelProviders.of(this).get(CatDetailViewModel::class.java)
        vm.fetchFromDBByUid(catUID)
        vm.catLiveData.observe(viewLifecycleOwner, Observer { cat ->
            cat?.let {
                dataBinding.cat = cat

                it.imgUrl?.let {
                    setupBackgroundColor(it)
                }
            }
        })
    }

    private fun setupBackgroundColor(url: String){
        Glide
            .with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>(){
                override fun onLoadCleared(placeholder: Drawable?) {}

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate { palette ->
                            // if getRgb null, then use 0 as default value
                            val intColor = palette?.lightVibrantSwatch?.rgb ?: 0
                            val myPal = CatPalette(intColor)
                            dataBinding.palette = myPal
                        }
                }

            })
    }
}