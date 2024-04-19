package com.mercandalli.android.apps.view_recyclerview_adapter_sample.animals_activity

interface AnimalsActivityContract {

    interface UserAction {

        fun onCreate()
    }

    interface Screen {

        fun setViewModels(viewModels: List<Any>)
    }
}
