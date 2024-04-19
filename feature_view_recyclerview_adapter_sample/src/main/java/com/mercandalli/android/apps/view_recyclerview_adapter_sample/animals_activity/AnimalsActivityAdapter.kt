package com.mercandalli.android.apps.view_recyclerview_adapter_sample.animals_activity

import com.mercandalli.android.apps.view_recyclerview_adapter_sample.cat_row_view.CatRowViewAdapterDelegate
import com.mercandalli.android.apps.view_recyclerview_adapter_sample.dog_row_view.DogRowViewAdapterDelegate
import com.mercandalli.android.sdk.view_recyclerview_adapter.ListDelegationAdapter

class AnimalsActivityAdapter : ListDelegationAdapter<List<Any>>() {

    init {
        delegatesManager.addDelegate(CatRowViewAdapterDelegate())
            .addDelegate(DogRowViewAdapterDelegate())
    }

    fun setViewModels(viewModels: List<Any>) {
        if (items == viewModels) {
            return
        }
        items = viewModels
        notifyDataSetChanged()
    }
}
