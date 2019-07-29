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
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import com.apps.ahfreelancing.marvelapp.R
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterMediaModel
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.apps.ahfreelancing.marvelapp.domain.model.DataWrapperModel
import com.apps.ahfreelancing.marvelapp.presentation.adapter.MediaAdapter
import com.apps.ahfreelancing.marvelapp.presentation.utilities.WebUtility
import com.apps.ahfreelancing.marvelapp.presentation.viewModel.CharacterDetailsViewModel
import com.apps.ahfreelancing.marvelapp.presentation.viewModel.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject


class CharacterDetailsFragment : BaseFragment() {

    private lateinit var characterModel: CharacterModel

    //UI Widgets
    private lateinit var thumbnailImageView: ImageView
    private lateinit var navBackButton: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var descTextView: TextView
    private lateinit var comicsRecyclerView: RecyclerView
    private lateinit var seriesRecyclerView: RecyclerView
    private lateinit var storiesRecyclerView: RecyclerView
    private lateinit var eventsRecyclerView: RecyclerView
    private lateinit var detailTextView: TextView
    private lateinit var wikiTextView: TextView
    private lateinit var comicTextView: TextView
    private lateinit var comicsProgressBar: ProgressBar
    private lateinit var seriesProgressBar: ProgressBar
    private lateinit var storiesProgressBar: ProgressBar
    private lateinit var eventsProgressBar: ProgressBar

    //Recyclers adapters
    private lateinit var comicsAdapter: MediaAdapter
    private lateinit var seriesAdapter: MediaAdapter
    private lateinit var storiesAdapter: MediaAdapter
    private lateinit var eventsAdapter: MediaAdapter

    //Errors widgets (by default hidden)
    private lateinit var comicsErrorTextView: TextView
    private lateinit var seriesErrorTextView: TextView
    private lateinit var storiesErrorTextView: TextView
    private lateinit var eventsErrorTextView: TextView

    private lateinit var characterViewModel: CharacterDetailsViewModel

    //Factory to create the view model and inject its dependencies
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CharacterDetailsViewModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Get character model sent by CharactersFragment
        characterModel = CharacterDetailsFragmentArgs.fromBundle(arguments).character

        //Inject the fragment to inject dependencies to the factory of the ViewModel
        getFragmentComponent().inject(this)
        characterViewModel = ViewModelProviders.of(this, viewModelFactory).get(CharacterDetailsViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_character_details, container, false)
        //Binds UI components to the corresponding variables
        bindViews(v)
        //Binds data with UI components
        bindData()
        bindActions()
        setupRecyclers()
        observeViewModel()
        return v
    }

    private fun bindViews(view: View) {
        thumbnailImageView = view.findViewById(R.id.thumbnailImageView)
        navBackButton = view.findViewById(R.id.navBackButton)
        nameTextView = view.findViewById(R.id.nameTextView)
        descTextView = view.findViewById(R.id.descTextView)
        comicsRecyclerView = view.findViewById(R.id.comicsRecyclerView)
        seriesRecyclerView = view.findViewById(R.id.seriesRecyclerView)
        storiesRecyclerView = view.findViewById(R.id.storiesRecyclerView)
        eventsRecyclerView = view.findViewById(R.id.eventsRecyclerView)
        detailTextView = view.findViewById(R.id.detailTextView)
        wikiTextView = view.findViewById(R.id.wikiTextView)
        comicTextView = view.findViewById(R.id.comicTextView)
        comicsProgressBar = view.findViewById(R.id.comicsProgressBar)
        seriesProgressBar = view.findViewById(R.id.seriesProgressBar)
        storiesProgressBar = view.findViewById(R.id.storiesProgressBar)
        eventsProgressBar = view.findViewById(R.id.eventsProgressBar)
        comicsErrorTextView = view.findViewById(R.id.comicsErrorTextView)
        seriesErrorTextView = view.findViewById(R.id.seriesErrorTextView)
        storiesErrorTextView = view.findViewById(R.id.storiesErrorTextView)
        eventsErrorTextView = view.findViewById(R.id.eventsErrorTextView)
    }

    private fun bindData() {
        //Load thumbnail using picasso
        Picasso.with(activity)
            .load(characterModel.thumbnail)
            .into(thumbnailImageView)

        nameTextView.text = characterModel.name

        //If description is blank, the default 'No Description' text will be displayed
        if (characterModel.description.isNotBlank())
            descTextView.text = characterModel.description
    }

    private fun bindActions() {
        navBackButton.setOnClickListener { if (view != null) Navigation.findNavController(view!!).navigateUp(); }

        detailTextView.setOnClickListener { WebUtility.openURL(activity!!, characterModel.detailUrl) }

        wikiTextView.setOnClickListener { WebUtility.openURL(activity!!, characterModel.wikiUrl) }

        comicTextView.setOnClickListener { WebUtility.openURL(activity!!, characterModel.comicUrl) }

    }

    private fun setupRecyclers() {
        //All layout managers are LinearLayoutManager with horizontal orientation
        comicsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        seriesRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        storiesRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        eventsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        comicsAdapter = MediaAdapter(ArrayList(emptyList()), activity as Context)
        comicsRecyclerView.adapter = comicsAdapter

        seriesAdapter = MediaAdapter(ArrayList(emptyList()), activity as Context)
        seriesRecyclerView.adapter = seriesAdapter

        storiesAdapter = MediaAdapter(ArrayList(emptyList()), activity as Context)
        storiesRecyclerView.adapter = storiesAdapter

        eventsAdapter = MediaAdapter(ArrayList(emptyList()), activity as Context)
        eventsRecyclerView.adapter = eventsAdapter

    }

    private fun observeViewModel() {
        characterViewModel.getMedia(characterModel.id).observe(this, Observer { dataWrapper ->
            disableProgressBars()
            if (dataWrapper!!.code != 200) {
                showError(dataWrapper.message)
                return@Observer
            }
            updateMediaAdapters(dataWrapper)
            handleEmpties(dataWrapper)
        })
    }

    private fun disableProgressBars(){
        comicsProgressBar.visibility = View.GONE
        seriesProgressBar.visibility = View.GONE
        storiesProgressBar.visibility = View.GONE
        eventsProgressBar.visibility = View.GONE
    }

    //updates adapters to update ui
    private fun updateMediaAdapters(dataWrapper: DataWrapperModel<CharacterMediaModel>){
        comicsAdapter.media.clear()
        comicsAdapter.media.addAll(dataWrapper.data.comics)
        comicsAdapter.notifyDataSetChanged()

        seriesAdapter.media.clear()
        seriesAdapter.media.addAll(dataWrapper.data.series)
        seriesAdapter.notifyDataSetChanged()

        storiesAdapter.media.clear()
        storiesAdapter.media.addAll(dataWrapper.data.stories)
        storiesAdapter.notifyDataSetChanged()

        eventsAdapter.media.clear()
        eventsAdapter.media.addAll(dataWrapper.data.events)
        eventsAdapter.notifyDataSetChanged()
    }

    private fun handleEmpties(dataWrapper: DataWrapperModel<CharacterMediaModel>){
        if(dataWrapper.data.comics.isEmpty())
            comicsErrorTextView.visibility = View.VISIBLE

        if(dataWrapper.data.series.isEmpty())
            seriesErrorTextView.visibility = View.VISIBLE

        if(dataWrapper.data.stories.isEmpty())
            storiesErrorTextView.visibility = View.VISIBLE

        if(dataWrapper.data.events.isEmpty())
            eventsErrorTextView.visibility = View.VISIBLE
    }
}
