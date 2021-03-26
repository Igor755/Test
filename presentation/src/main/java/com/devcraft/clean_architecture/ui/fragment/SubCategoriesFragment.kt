package com.devcraft.clean_architecture.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcraft.clean_architecture.R
import com.devcraft.clean_architecture.model.AllData
import com.devcraft.clean_architecture.model.Categories
import com.devcraft.clean_architecture.ui.fragment.adapter.SubCategoriesAdapter
import com.devcraft.clean_architecture.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_subcategories.*
import kotlinx.android.synthetic.main.toolbar_offer.*
import org.koin.android.ext.android.inject

class SubCategoriesFragment : Fragment(R.layout.fragment_subcategories) {

    private var selectionPositionDetailData: Categories? = null
    private val subCategoriesAdapter: SubCategoriesAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments?.getParcelable<Categories>("selectionPositionDetailData") != null) {
            selectionPositionDetailData = arguments?.getParcelable<Categories>("selectionPositionDetailData")
        }
        initViews()
        initListeners()
        initObservers()
    }

    fun initViews() {
        val activity = activity as MainActivity
        activity.ivToolbarBack.visibility = View.VISIBLE
        activity.tvToolbarName.text = resources.getString(R.string.back)
        rvSubCategories.layoutManager = LinearLayoutManager(activity)
        subCategoriesAdapter.setNewData(selectionPositionDetailData?.subCategories)
        rvSubCategories.adapter = subCategoriesAdapter
    }

    fun initListeners() {
        val activity = activity as MainActivity
        activity.ivToolbarBack.setOnClickListener {
            activity.onBackPressed()
        }
    }

    fun initObservers() {
    }

    override fun onResume() {
        super.onResume()
        if (arguments?.getParcelable<Categories>("selectionPositionDetailData") != null) {
            selectionPositionDetailData = arguments?.getParcelable<Categories>("selectionPositionDetailData")
        }
    }
}
