package com.mercandalli.android.apps.view_recyclerview_adapter_sample.cat_row_view

interface CatRowViewContract {

    interface UserAction {

        fun onViewModelSet(viewModel: CatRowViewModel?)
    }

    interface Screen {

        fun setName(text: String)
    }
}
