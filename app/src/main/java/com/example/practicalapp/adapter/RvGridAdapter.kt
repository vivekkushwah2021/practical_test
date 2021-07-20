package com.example.practicalapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.practicalapp.R
import com.example.practicalapp.model.GridModel

class RvGridAdapter(val mContext:Context,val list:ArrayList<GridModel>,val viewClicked:(Int)->Unit): RecyclerView.Adapter<RvGridAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvGridAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_grid_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvGridAdapter.MyViewHolder, position: Int) {
        holder.view.setBackgroundColor(ContextCompat.getColor(mContext,list[position].color))
        holder.view.setOnClickListener {
            if (list[position].color != R.color.white && list[position].color != R.color.blue){
                viewClicked(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val view:View = itemView.findViewById(R.id.view)
    }

}