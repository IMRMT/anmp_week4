package com.ubaya.advweek4160421056.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.advweek4160421056.R
import com.ubaya.advweek4160421056.databinding.FragmentFighterListBinding
import com.ubaya.advweek4160421056.databinding.FragmentStudentDetailBinding
import com.ubaya.advweek4160421056.databinding.FragmentStudentListBinding
import com.ubaya.advweek4160421056.databinding.StudentListItemBinding
import com.ubaya.advweek4160421056.model.Student
import com.ubaya.advweek4160421056.viewmodel.DetailViewModel
import com.ubaya.advweek4160421056.viewmodel.FighterViewModel
import com.ubaya.advweek4160421056.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment() : Fragment(), ButtonDetailClickListener {
    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.student = Student("","","","","https://picsum.photos/200/300")
        if(arguments!=null){
            val student_id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.refresh(student_id)
            observeViewModel()

        }


        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
//            var student = it
//            if(arguments != null) {
//                val id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
//                binding.txtID.setText(id)
//            }
//            binding.txtName.setText(it.name)
//            binding.txtBod.setText(it.dob)
//            binding.txtPhone.setText(it.phone)
//
//            binding.btnUpdate?.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotifications(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.baseline_person_2_24)
//                    }
//            }
            binding.student = it
            binding.listener = this
        })
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentDetailFragmentDirections.actionStudentList()
        Navigation.findNavController(v).navigate(action)
        Toast.makeText(getActivity(), "Updated",
            Toast.LENGTH_LONG).show()
    }
}