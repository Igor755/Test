package com.devcraft.clean_architecture.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcraft.clean_architecture.R
import com.devcraft.clean_architecture.adapter.DataAdapter
import com.devcraft.clean_architecture.ui.vm.MainViewModel
import com.devcraft.domain.model.Result
import kotlinx.android.synthetic.main.fragment_data.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DataFragment : Fragment(R.layout.fragment_data) {

    private val dataAdapter: DataAdapter by inject()
    private val mainViewModel: MainViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initObservers()
    }

    fun initViews() {
        rvProducts.layoutManager = LinearLayoutManager(activity)
        rvProducts.adapter = dataAdapter
    }

    fun initListeners() {

    }

    fun initObservers() {
        mainViewModel.getTastingsFromServer()
        mainViewModel.setLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    it.data?.let { tastingResponse ->
                        println(tastingResponse)
                    }
                }
                is Result.Error ->
                    mainViewModel.getTastingsFromServer()
            }
        })
    }
}

