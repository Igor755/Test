package com.devcraft.clean_architecture.ui.fragment.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.devcraft.clean_architecture.R
import com.devcraft.clean_architecture.model.AllData
import kotlinx.android.synthetic.main.one_item.view.*

class DataAdapterNew : BaseQuickAdapter<AllData, BaseViewHolder>(R.layout.one_item) {

    var onItemClickListener: ((position: Int) -> Unit)? = null

    override fun convert(helper: BaseViewHolder, item: AllData?) {
        item?.let { item ->
            helper.itemView.nameData.text = item.title
            helper.itemView.setOnClickListener {
                onItemClickListener?.invoke(helper.adapterPosition)
            }
        }
    }

    fun getDetailData(position: Int): AllData? {
        return getItem(position)
    }
}



