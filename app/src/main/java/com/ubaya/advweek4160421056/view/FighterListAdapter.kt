package com.ubaya.advweek4160421056.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.advweek4160421056.databinding.FighterListItemBinding
import com.ubaya.advweek4160421056.model.Fighter

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
        holder.binding.txtSpeed.text = fighterList[position].max_speed
        holder.binding.txtYear.text = fighterList[position].year.toString()

        var weaponry = ""

        for (arm in fighterList){
            weaponry = arm.armament.toString()
            holder.binding.txtArmament.text = weaponry
        }
        holder.binding.txtLength.text = fighterList[position].dimension?.length
        holder.binding.txtWingspan.text = fighterList[position].dimension?.wingspan
        holder.binding.txtHeight.text = fighterList[position].dimension?.height.toString()
    }


}