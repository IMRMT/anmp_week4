package com.ubaya.advweek4160421056.view

import android.R.id
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.advweek4160421056.databinding.StudentListItemBinding
import com.ubaya.advweek4160421056.model.Student
import java.lang.Exception

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener
{
    class StudentViewHolder(var binding: StudentListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
//        holder.binding.txtID.text = studentList[position].id
//        holder.binding.txtName.text = studentList[position].name
//
//        holder.binding.btnDetail.setOnClickListener {
//            val id = holder.binding.txtID.text.toString()
//            val action = StudentListFragmentDirections.actionStudentDetail(id)
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception ->
//            exception.printStackTrace()
//        }
//        picasso.build().load(studentList[position].photoUrl)
//            .into(holder.binding.imageView, object:Callback {
//                override fun onSuccess() {
//                    holder.binding.progressImage.visibility = View.INVISIBLE
//                    holder.binding.imageView.visibility = View.VISIBLE
//                }
//
//                override fun onError(e: Exception?) {
//                    Log.d("Picasso_error", e.toString())
//                }
//            })
        holder.binding.student = studentList[position]
        holder.binding.listener = this
    }

    override fun onButtonDetailClick(v: View) {
        val id = v.tag.toString()
        val action = StudentListFragmentDirections.actionStudentDetail(id)
        Navigation.findNavController(v).navigate(action)
    }

}
