package com.ubaya.advweek4160421056.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.advweek4160421056.R
import com.ubaya.advweek4160421056.databinding.FragmentFighterListBinding
import com.ubaya.advweek4160421056.viewmodel.FighterViewModel
import com.ubaya.advweek4160421056.viewmodel.ListViewModel

class FighterListFragment : Fragment() {
    private lateinit var binding: FragmentFighterListBinding
    private lateinit var viewModel: FighterViewModel
    private val fighterListAdapter  = FighterListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFighterListBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FighterViewModel::class.java)
        viewModel.refresh()

        binding.recViewFighter.layoutManager = LinearLayoutManager(context)
        binding.recViewFighter.adapter = fighterListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.fightersLD.observe(viewLifecycleOwner, Observer {
            fighterListAdapter.updatefighterList(it)
        })
    }
}