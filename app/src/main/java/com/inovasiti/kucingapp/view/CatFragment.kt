package com.inovasiti.kucingapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.inovasiti.kucingapp.R
import com.inovasiti.kucingapp.databinding.FragmentCatBinding
import kotlinx.android.synthetic.main.fragment_cat.*

/**
 * A simple [Fragment] subclass.
 * Use the [CatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CatFragment : Fragment() {

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
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //if arg != null
        arguments?.let {
            catUID = CatFragmentArgs.fromBundle(it).catValue
            textView2.text = "Args "+catUID.toString()
        }

        floatingActionButton2.setOnClickListener {
            //ListFragmentDirections class came from gradle plugin: "androidx.navigation"
            // actionListFragmentToCatFragment came from navigation "arrow" graph
            val action = CatFragmentDirections.actionCatFragmentToListFragment();
            Navigation.findNavController(it).navigate(action)
        }
    }
}