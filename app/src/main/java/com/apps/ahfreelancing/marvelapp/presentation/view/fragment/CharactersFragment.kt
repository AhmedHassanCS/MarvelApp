package com.apps.ahfreelancing.marvelapp.presentation.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.navigation.Navigation
import com.apps.ahfreelancing.marvelapp.R
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.apps.ahfreelancing.marvelapp.presentation.adapter.CharactersAdapter
import com.apps.ahfreelancing.marvelapp.presentation.viewModel.CharactersViewModel
import com.apps.ahfreelancing.marvelapp.presentation.viewModel.ViewModelFactory
import javax.inject.Inject


class CharactersFragment : BaseFragment() {

    private lateinit var charactersRecyclerView: RecyclerView
    private lateinit var charactersAdapter: CharactersAdapter

    private lateinit var searchIcon: ImageView

    lateinit var charactersViewModel: CharactersViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CharactersViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // On Creation
        // Fragment is injected
        // ViewModel is created and attached using factory
        // LiveData is observed
        getFragmentComponent().inject(this)
        charactersViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(CharactersViewModel::class.java)
        observeViewModel(charactersViewModel)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //restoreCharacters restores any data that has been previously loaded
        //This is in case the fragment was restarted
        charactersViewModel.restoreCharacters()
        //Close Soft Input
        val imm: InputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_characters, container, false)
        charactersRecyclerView = v.findViewById(R.id.charactersRecyclerView)
        searchIcon = v.findViewById(R.id.searchIconImageView)
        searchIcon.setOnClickListener {
            Navigation.findNavController(v).navigate(
            R.id.action_characters_to_search)
        }
        setupRecycler()
        return v
    }

    private fun observeViewModel(charactersViewModel: CharactersViewModel) {
        if(!charactersViewModel.getCharacters().hasObservers()) {
            charactersViewModel.getCharacters().observe(this, Observer { dataWrapper ->
                if (dataWrapper!!.code != 200) {
                    showError(dataWrapper.message)
                    return@Observer
                }
                val startPos = charactersAdapter.characters.size - 1
                charactersAdapter.characters.addAll(startPos, dataWrapper.data)
                if(charactersViewModel.getPageNumber() == 1)
                    charactersAdapter.notifyDataSetChanged()
                else
                    charactersAdapter.notifyItemRangeInserted(startPos, dataWrapper.data.size)
            })
        }
    }

    private fun setupRecycler() {
        if(charactersRecyclerView.adapter == null) {
            charactersRecyclerView.layoutManager = LinearLayoutManager(activity)
            val initCh: ArrayList<CharacterModel> = ArrayList()
            initCh.add(CharacterModel())

            charactersAdapter = CharactersAdapter(initCh, activity as Context, ItemClickCallback())
            charactersRecyclerView.adapter = charactersAdapter

            charactersRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if ((charactersRecyclerView.layoutManager as LinearLayoutManager)
                            .findLastCompletelyVisibleItemPosition() == charactersAdapter.characters.size - 1
                    ) {
                        charactersViewModel.getNextPage()

                    }
                }
            })
        }
    }
    inner class ItemClickCallback: CharactersAdapter.ItemClickCallback  {
        override fun onItemClicked(character: CharacterModel){
            if(view != null) {
                val action = CharactersFragmentDirections.actionCharactersToDetails(character)
                Navigation.findNavController(view!!).navigate(action)
            }
        }
    }
}
