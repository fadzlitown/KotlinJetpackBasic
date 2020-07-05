package com.inovasiti.kucingapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.inovasiti.kucingapp.R
import com.inovasiti.kucingapp.databinding.FragmentCatBinding
import com.inovasiti.kucingapp.viewmodel.CatDetailViewModel
import kotlinx.android.synthetic.main.fragment_cat.*

/**
 * A simple [Fragment] subclass.
 * Use the [CatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CatFragment : Fragment() {
    private lateinit var vm: CatDetailViewModel
    private var catUID = 0
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
        vm = ViewModelProviders.of(this).get(CatDetailViewModel::class.java)
        vm.addCat()
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //if arg != null
        arguments?.let {
            catUID = CatFragmentArgs.fromBundle(it).catValue
        }

        vm.catLiveData.observe(viewLifecycleOwner, Observer { cat ->
            cat?.let {
                catName.text = cat.catSiam
                catPurpose.text = cat.bredFor
                catLifespan.text = cat.lifeSpan
                catTemperament.text = cat.temperament
                val id = requireContext().resources
                    .getIdentifier(cat.imgUrl, "drawable", requireContext().packageName)
                catImage.setImageResource(id)
            }
        })
    }
}