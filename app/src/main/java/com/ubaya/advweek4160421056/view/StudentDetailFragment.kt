package com.ubaya.advweek4160421056.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.advweek4160421056.R
import com.ubaya.advweek4160421056.databinding.FragmentFighterListBinding
import com.ubaya.advweek4160421056.databinding.FragmentStudentDetailBinding
import com.ubaya.advweek4160421056.databinding.FragmentStudentListBinding
import com.ubaya.advweek4160421056.viewmodel.DetailViewModel
import com.ubaya.advweek4160421056.viewmodel.FighterViewModel
import com.ubaya.advweek4160421056.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.refresh()


        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            var student = it
            binding.txtID.setText(it.id)
            binding.txtName.setText(it.name)
            binding.txtBod.setText(it.dob)
            binding.txtPhone.setText(it.phone)

            binding.btnUpdate.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotifications(student.name.toString(),
                            "A new notification created",
                            R.drawable.baseline_person_2_24)
                    }
            }
        })
    }
}