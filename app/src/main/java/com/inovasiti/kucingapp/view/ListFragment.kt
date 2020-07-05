package com.inovasiti.kucingapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.inovasiti.kucingapp.R
import com.inovasiti.kucingapp.databinding.FragmentListBinding
import com.inovasiti.kucingapp.viewmodel.CatListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
     private lateinit var dataBinding: FragmentListBinding
    private lateinit var vm : CatListViewModel
    private val mAdapter = CatListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProviders.of(this).get(CatListViewModel::class.java)
        vm.refresh()

        rv.apply {
            layoutManager = LinearLayoutManager(view.context)
            //mcm rv adapter.setAdapter(mAdapter)
            adapter = mAdapter
        }
        observeViewModel()
    }

    fun observeViewModel(){
        //viewLifecycleOwner : Get a {@link LifecycleOwner} that represents the {@link #getView() Fragment's View
        vm.cats.observe(viewLifecycleOwner, Observer { cats ->
            // cats!=null
                cats?.let {
                    //when the cats are coming , show RV & update the new cats
                    rv.visibility = View.VISIBLE
                    mAdapter.updateCatList(cats)
                }
        })

        vm.catLoadError.observe(viewLifecycleOwner, Observer { isError ->
            //isError check nullable types
            isError?.let {
                error.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        vm.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            //isLoading check nullable types
            isLoading?.let {
                //if it true or false
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if(it){
                    error.visibility = View.GONE
                    rv.visibility = View.GONE
                }
            }
        })
    }
}