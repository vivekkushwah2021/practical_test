package com.example.practicalapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicalapp.R
import com.example.practicalapp.model.UserModel
import com.google.android.material.textview.MaterialTextView
import de.hdodenhof.circleimageview.CircleImageView

class RvUserAdapter(val context:Context,val list:ArrayList<UserModel.Data>): RecyclerView.Adapter<RvUserAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvUserAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_user_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvUserAdapter.MyViewHolder, position: Int) {
        holder.name.setText(list[position].first_name + " "+list[position].last_name)
        Glide.with(context).load(list[position].avatar).into(holder.image)
        holder.email.setText(list[position].email)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name:MaterialTextView = itemView.findViewById(R.id.tvName)
        val email:MaterialTextView = itemView.findViewById(R.id.tvEmail)
        val image:CircleImageView = itemView.findViewById(R.id.img)
    }
}