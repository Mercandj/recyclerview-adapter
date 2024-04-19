package com.mercandalli.android.apps.view_recyclerview_adapter_sample.cat_row_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercandalli.android.sdk.view_recyclerview_adapter.AbsListItemAdapterDelegate

class CatRowViewAdapterDelegate :
    AbsListItemAdapterDelegate<Any, Any, CatRowViewAdapterDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: List<Any>, position: Int) = item is CatRowViewModel

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = CatRowView(parent.context)
        view.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: List<Any>) {
        viewHolder.view.setViewModel(item as CatRowViewModel)
    }

    class ViewHolder(val view: CatRowView) : RecyclerView.ViewHolder(view)
}
