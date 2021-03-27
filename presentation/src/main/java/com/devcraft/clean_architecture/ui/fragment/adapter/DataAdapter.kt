package com.devcraft.clean_architecture.ui.fragment.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.devcraft.clean_architecture.R
import com.devcraft.clean_architecture.model.DetailData
import kotlinx.android.synthetic.main.one_item.view.*

class DataAdapter : BaseQuickAdapter<DetailData, BaseViewHolder>(R.layout.one_item) {

    var onItemClickListener: ((position: Int) -> Unit)? = null

    override fun convert(helper: BaseViewHolder, item: DetailData?) {
        item?.let { item ->
            helper.itemView.nameData.text = item.title
            helper.itemView.setOnClickListener {
                onItemClickListener?.invoke(helper.adapterPosition)
            }
        }
    }

    fun getDetailData(position: Int): DetailData? {
        return getItem(position)
    }
}



