package com.mercandalli.android.apps.view_recyclerview_adapter_sample.cat_row_view

class CatRowViewPresenter(
    private val screen: CatRowViewContract.Screen
) : CatRowViewContract.UserAction {

    private var viewModel: CatRowViewModel? = null

    override fun onViewModelSet(viewModel: CatRowViewModel?) {
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
