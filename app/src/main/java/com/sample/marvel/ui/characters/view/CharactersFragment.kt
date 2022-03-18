package com.sample.marvel.ui.characters.view

import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.marvel.R
import com.sample.marvel.framework.extensions.makeGone
import com.sample.marvel.framework.extensions.makeVisible
import com.sample.marvel.ui.DataWrapper
import com.sample.marvel.ui.DataWrapper.Status.*
import com.sample.marvel.ui.characters.model.CharacterUiModel
import com.sample.marvel.ui.characters.viewmodel.CharactersViewModel
import kotlinx.android.synthetic.main.characters_fragment.*
import kotlinx.android.synthetic.main.layout_loading_characters.*
import kotlinx.android.synthetic.main.layout_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

    private val characterViewModel: CharactersViewModel by viewModel()
    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter(listOf(), ITEM_CHARACTER)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.characters_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(characterViewModel) {
            observeCharactersChanges().subscribe(::renderCharactersData)
            observeSearchChanges().subscribe(::renderSearchData)
        }
        characterViewModel.getCharacters()
    }

    private fun renderCharactersData(dataWrapper: DataWrapper<List<CharacterUiModel>>) {
        when (dataWrapper.status) {
            LOADING -> showLoading()
            ERROR -> {
                hideLoading()
                handleError(dataWrapper.message)
            }
            SUCCESS -> {
                hideLoading()
                if (dataWrapper.data.isNullOrEmpty()) {
                    hideContent()
                } else {
                    showContent()
                    setupAdapter(dataWrapper.data)
                }
            }
        }
    }

    private fun renderSearchData(dataWrapper: DataWrapper<List<CharacterUiModel>>) {
        when (dataWrapper.status) {
            LOADING -> {
                showSearchLayout()
                hideSearchContent()
                showSearchLoading()
            }
            ERROR -> {
                hideSearchLoading()
                hideSearchContent()
                handleSearchError(dataWrapper.message)
            }
            SUCCESS -> {
                hideSearchLoading()
                if (dataWrapper.data.isNullOrEmpty()) {
                    hideSearchContent()
                    handleSearchError(getString(R.string.no_data_found))
                } else {
                    showSearchContent()
                    setupSearchAdapter(dataWrapper.data)
                }
            }
        }
    }


    private fun setupSearchAdapter(characterList: List<CharacterUiModel>) {
        val adapter = CharactersAdapter(characterList, ITEM_SEARCH)
        search_recycler_view.layoutManager = LinearLayoutManager(context)
        search_recycler_view.adapter = adapter
    }


    private fun showSearchLoading() {
        progress_bar.makeVisible()
    }

    private fun hideSearchLoading() {
        progress_bar.makeGone()
    }

    private fun handleSearchError(message: String?) {
        no_search_data_tv.makeVisible()
        no_search_data_tv.text =
            if (message == null || message.isEmpty()) getString(R.string.no_data_found) else message
    }

    private fun showSearchLayout() {
        search_layout.makeVisible()
    }

    private fun hideSearchLayout() {
        search_layout.makeGone()
    }

    private fun showSearchContent() {
        search_recycler_view.makeVisible()
        no_search_data_tv.makeGone()
    }

    private fun hideSearchContent() {
        search_recycler_view.makeGone()
    }

    private fun hideContent() {
        characters_recycler.makeGone()
    }

    private fun setupAdapter(data: List<CharacterUiModel>) {
        characters_recycler.adapter = adapter
        characters_recycler.layoutManager = LinearLayoutManager(context)
        adapter.updateList(data)
    }

    private fun showContent() {
        characters_recycler.makeVisible()
    }

    private fun handleError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun hideLoading() {
        shimmer_loading_layout.stopShimmer()
        shimmer_loading_layout.makeGone()
    }

    private fun showLoading() {
        hideContent()
        shimmer_loading_layout.startShimmer()
        shimmer_loading_layout.makeVisible()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        setupSearchView(searchView)
        setSearchItemListeners(menuItem, searchView)
        setSearchViewListener(searchView)
    }

    private fun setupSearchView(
        searchView: SearchView
    ) {
        val searchManager = context?.getSystemService(SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.queryHint = getString(R.string.hint_search)
    }

    private fun setSearchViewListener(
        searchView: SearchView
    ) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.trim().isNotEmpty()) {
                    searchCharacters(newText)
                }
                return true
            }
        })
    }

    private fun setSearchItemListeners(menuItem: MenuItem, searchView: SearchView) {
        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                hideSearchLayout()
                searchView.onActionViewExpanded()
                return true
            }
        })
    }


    private fun searchCharacters(query: String) {
        characterViewModel.getCharacters(query)
    }
}