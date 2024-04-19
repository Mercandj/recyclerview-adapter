package com.mercandalli.android.apps.view_recyclerview_adapter_sample.dog_row_view

class DogRowViewPresenter(
    private val screen: DogRowViewContract.Screen
) : DogRowViewContract.UserAction {

    private var viewModel: DogRowViewModel? = null

    override fun onViewModelSet(viewModel: DogRowViewModel?) {
        if (this.viewModel == viewModel) {
            return
        }
        this.viewModel = viewModel
        updateViewModel()
    }

    private fun updateViewModel() {
        updateName()
    }

    private fun updateName() {
        screen.setName(text = createName())
    }

    private fun createName(): String {
        return viewModel?.name ?: ""
    }
}
