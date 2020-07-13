package com.inovasiti.kucingapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.inovasiti.kucingapp.R
import com.inovasiti.kucingapp.databinding.ItemCatBinding
import com.inovasiti.kucingapp.getProgressDrawable
import com.inovasiti.kucingapp.loadImage
import com.inovasiti.kucingapp.model.CatSiam
import kotlinx.android.synthetic.main.item_cat.view.*

class CatListAdapter(val catsList: ArrayList<CatSiam>) :
    RecyclerView.Adapter<CatListAdapter.CatViewHolder>(), CatBindingListener {

    //update the list
    fun updateCatList(newList: List<CatSiam>) {
        catsList.clear()
        catsList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.item_cat, parent,false);

        val view = DataBindingUtil.inflate<ItemCatBinding>(inflater, R.layout.item_cat, parent, false)
        // need to return CatViewHolder
        return CatViewHolder(view)
    }

    override fun getItemCount() = catsList.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
//        holder.v.name.text = catsList[position].catName
//        holder.v.lifespan.text = catsList[position].lifeSpan
//        holder.v.setOnClickListener {
//            val bundle = ListFragmentDirections.goToCatScreen(catsList[position].uuid.toInt())
//            Navigation.findNavController(it).navigate(bundle)
//        }
//        holder.v.imageView.loadImage(catsList[position].imgUrl, getProgressDrawable(holder.v.root))

        holder.v.cat = catsList[position]
        holder.v.listener = this    //set listen to the listener in this class
    };

    //event from layout onClick. this override from CatBindingListener interfaces
    override fun onClickListner(v: View) {
            val bundle = ListFragmentDirections.goToCatScreen(v.catId.text.toString().toInt())
            Navigation.findNavController(v).navigate(bundle)
    }

    class CatViewHolder(var v: ItemCatBinding) : RecyclerView.ViewHolder(v.root)



}