package com.felix.felixstore.ui.main.search

import android.os.Bundle
import com.felix.lib_arch.mvpvm.BaseMvpFragment

class SearchFragment :
    BaseMvpFragment<SearchView, SearchViewModel, SearchPresenterImpl, SearchDelegate>() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.getDefApplist()
    }
}