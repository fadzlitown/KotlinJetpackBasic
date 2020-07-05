package com.inovasiti.kucingapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.inovasiti.kucingapp.R
import com.inovasiti.kucingapp.model.CatSiam
import kotlinx.android.synthetic.main.item_cat.view.*

class CatListAdapter(val catsList: ArrayList<CatSiam>) : RecyclerView.Adapter<CatListAdapter.CatViewHolder>() {

    //update the list
    fun updateCatList(newList : List<CatSiam>){
        catsList.clear()
        catsList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_cat, parent,false);
        // need to return CatViewHolder
        return CatViewHolder(view)
    }

    override fun getItemCount() = catsList.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.v.name.text = catsList[position].catSiam
        holder.v.lifespan.text = catsList[position].lifeSpan
        holder.v.setOnClickListener {
//            val bundle = ListFragmentDirections.goToCatScreen(catsList[position])
            val bundle = ListFragmentDirections.goToCatScreen(catsList[position].siamId?.toInt()!!)
            Navigation.findNavController(it).navigate(bundle)
        }
    };

    class CatViewHolder(var v: View) : RecyclerView.ViewHolder(v)

}