package com.devcraft.clean_architecture.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcraft.clean_architecture.R
import com.devcraft.clean_architecture.extension.navigateTo
import com.devcraft.clean_architecture.model.AllData
import com.devcraft.clean_architecture.ui.fragment.adapter.CategoriesAdapter
import com.devcraft.clean_architecture.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_categories.*
import org.koin.android.ext.android.inject

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private var selectionPositionDetailData: AllData? = null
    private val categoriesAdapter: CategoriesAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.getParcelable<AllData>("selectionPositionDetailData") != null) {
            selectionPositionDetailData = arguments?.getParcelable<AllData>("selectionPositionDetailData")
        }
        initViews()
        initListeners()
        initObservers()
    }

    fun initViews() {
        rvCategories.layoutManager = LinearLayoutManager(activity)
        categoriesAdapter.setNewData(selectionPositionDetailData?.categories)
        rvCategories.adapter = categoriesAdapter
    }

    fun initListeners() {
        categoriesAdapter.onItemClickListener = { position->
            val detailData = categoriesAdapter.getCategories(position)
            println(detailData)
            navigateTo(
                R.id.fragment_container,
                SubCategoriesFragment(),args = Bundle().apply {
                    putParcelable("detailData", detailData)
                },
                backStackTag = MainActivity::class.java.name
            )
        }
    }

    fun initObservers() {
    }

    override fun onResume() {
        super.onResume()
        if (arguments?.getParcelable<AllData>("selectionPositionDetailData") != null) {
            selectionPositionDetailData = arguments?.getParcelable<AllData>("selectionPositionDetailData")
        }
    }
}
