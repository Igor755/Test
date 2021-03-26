package com.devcraft.clean_architecture.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcraft.clean_architecture.R
import com.devcraft.clean_architecture.ui.fragment.adapter.DataAdapterOld
import com.devcraft.clean_architecture.extension.navigateTo
import com.devcraft.clean_architecture.ui.fragment.adapter.DataAdapterNew
import com.devcraft.clean_architecture.ui.main.MainActivity
import com.devcraft.clean_architecture.ui.vm.MainViewModel
import com.devcraft.domain.model.Result
import kotlinx.android.synthetic.main.fragment_data.*
import kotlinx.android.synthetic.main.toolbar_offer.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DataFragment : Fragment(R.layout.fragment_data) {

   // private val dataAdapter: DataAdapterOld by inject()
    private val dataAdapter1: DataAdapterNew by inject()
    private val mainViewModel: MainViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        initObservers()
    }

    fun initViews() {
        val activity = activity as MainActivity
        activity.ivToolbarBack.visibility = View.GONE
        activity.tvToolbarName.text = resources.getString(R.string.app_name)
        rvProducts.layoutManager = LinearLayoutManager(activity)
        rvProducts.adapter = dataAdapter1
    }

    fun initListeners() {
        dataAdapter1.onItemClickListener = { position->
            val detailData = dataAdapter1.getDetailData(position)
            println(detailData)
            navigateTo(
                R.id.fragment_container,
                CategoriesFragment(),args = Bundle().apply {
                    putParcelable("selectionPositionDetailData", detailData)
                },
                backStackTag = MainActivity::class.java.name
            )
        }
    }

    fun initObservers() {
        mainViewModel.getTastingsFromServer()
        mainViewModel.setLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    it.data?.let { tastingResponse ->
                        //println(tastingResponse)
                        //dataAdapter1.items = tastingResponse.data
                        dataAdapter1.setNewData(tastingResponse.data)
                    }
                }
                is Result.Error ->
                    mainViewModel.getTastingsFromServer()
            }
        })
    }
}

