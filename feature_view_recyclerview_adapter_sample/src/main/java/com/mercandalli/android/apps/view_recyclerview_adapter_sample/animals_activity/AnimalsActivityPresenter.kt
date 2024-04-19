package com.mercandalli.android.apps.view_recyclerview_adapter_sample.animals_activity

import com.mercandalli.android.apps.view_recyclerview_adapter_sample.cat_row_view.CatRowViewModel
import com.mercandalli.android.apps.view_recyclerview_adapter_sample.dog_row_view.DogRowViewModel

class AnimalsActivityPresenter(
    private val screen: AnimalsActivityContract.Screen
) : AnimalsActivityContract.UserAction {

    override fun onCreate() {
        updateScreen()
    }

    private fun updateScreen() {
        updateViewModels()
    }

    private fun updateViewModels() {
        screen.setViewModels(viewModels = createViewModels())
    }

    private fun createViewModels(): List<Any> {
        return listOf(
            CatRowViewModel("Pimousse"),
            CatRowViewModel("Matcha"),
            DogRowViewModel("Nelson")
        )
    }
}
