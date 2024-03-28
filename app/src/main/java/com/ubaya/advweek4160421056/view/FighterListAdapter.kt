package com.ubaya.advweek4160421056.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ubaya.advweek4160421056.databinding.FighterListItemBinding
import com.ubaya.advweek4160421056.databinding.StudentListItemBinding
import com.ubaya.advweek4160421056.model.Fighter
import com.ubaya.advweek4160421056.model.Student

class FighterListAdapter (val fighterList:ArrayList<Fighter>):RecyclerView.Adapter<FighterListAdapter.FighterViewHolder>(){
    class FighterViewHolder(var binding: FighterListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FighterViewHolder {
        val binding = FighterListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return FighterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return fighterList.size
    }

    fun updatefighterList(newFighterList: ArrayList<Fighter>) {
        fighterList.clear()
        fighterList.addAll(newFighterList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FighterViewHolder, position: Int) {
        holder.binding.txtModel.text = fighterList[position].model
        holder.binding.txtManufacturer.text = fighterList[position].manufacturer
        holder.binding.txtSpeed.text = fighterList[position].max_speeed
        holder.binding.txtYear.text = fighterList[position].year.toString()



        holder.binding.txtLength.text = fighterList[position].dimension?.length.toString() + " m"
        holder.binding.txtWingspan.text = fighterList[position].dimension?.wingspan.toString() + " m"
        holder.binding.txtHeight.text = fighterList[position].dimension?.height.toString() + " m"
    }


}