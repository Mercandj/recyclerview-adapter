package com.mercandalli.android.apps.view_recyclerview_adapter_sample.dog_row_view

import com.mercandalli.android.apps.view_recyclerview_adapter_sample.cat_row_view.CatRowViewModel

interface DogRowViewContract {

    interface UserAction {

        fun onViewModelSet(viewModel: DogRowViewModel?)
    }

    interface Screen {

        fun setName(text: String)
    }
}
