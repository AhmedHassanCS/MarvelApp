package com.apps.ahfreelancing.marvelapp.presentation.view.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.apps.ahfreelancing.marvelapp.R
import com.apps.ahfreelancing.marvelapp.presentation.adapter.ResultsAdapter
import com.apps.ahfreelancing.marvelapp.presentation.viewModel.SearchViewModel
import com.apps.ahfreelancing.marvelapp.presentation.viewModel.ViewModelFactory
import javax.inject.Inject

class SearchFragment  : BaseFragment(){

    private lateinit var resultsRecyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var cancelTextView: TextView

    private lateinit var resultsAdapter: ResultsAdapter

    private lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<SearchViewModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getFragmentComponent().inject(this)
        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)

        resultsRecyclerView = view.findViewById(R.id.searchRecyclerView)
        setupRecycler()

        searchEditText = view.findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener(SearchTextListener())

        cancelTextView = view.findViewById(R.id.cancelSearchText)
        cancelTextView.setOnClickListener { Navigation.findNavController(view).navigateUp(); }

        return  view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSearchEditText()
        observeViewModel(searchViewModel)
    }
    private fun setupSearchEditText() {
        searchEditText.requestFocus()
        val imm: InputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT)

    }

    private fun observeViewModel(searchViewModel: SearchViewModel) {
        searchViewModel.getCharacters(searchEditText.text.toString()).observe(this, Observer { dataWrapper ->
            if (dataWrapper!!.code != 200) {
                showError(dataWrapper.message)
                return@Observer
            }
            resultsAdapter.characters.clear()
            resultsAdapter.characters.addAll(dataWrapper.data)
            resultsAdapter.notifyDataSetChanged()
        })
    }

    private fun setupRecycler(){
        resultsRecyclerView.layoutManager = LinearLayoutManager(activity)

        resultsAdapter = ResultsAdapter(ArrayList(emptyList()), activity as Context)
        resultsRecyclerView.adapter = resultsAdapter

    }

     private inner class SearchTextListener : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            searchViewModel.updateSearch(s.toString())
        }
    }
}
