package com.apps.ahfreelancing.marvelapp.presentation.view.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.Navigation

import com.apps.ahfreelancing.marvelapp.R
import com.apps.ahfreelancing.marvelapp.domain.model.MediaModel
import com.apps.ahfreelancing.marvelapp.presentation.adapter.MediaOpenListAdapter


class MediaListFragment : Fragment() {

    lateinit var mediaRecyclerView: RecyclerView
    lateinit var closeButton: ImageView

    lateinit var mediaList: List<MediaModel>

    lateinit var mediaAdapter: MediaOpenListAdapter

    lateinit var layoutManager: LinearLayoutManager

    var initPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaList = MediaListFragmentArgs.fromBundle(arguments).media
        initPosition = MediaListFragmentArgs.fromBundle(arguments).position
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View =  inflater.inflate(R.layout.fragment_media_list, container, false)
        mediaRecyclerView = v.findViewById(R.id.medialRecyclerView)
        closeButton = v.findViewById(R.id.closeBtn)
        closeButton.setOnClickListener { Navigation.findNavController(v).navigateUp(); }
        setupRecycler()
        return v
    }

    private fun setupRecycler(){
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mediaRecyclerView.layoutManager = layoutManager

        mediaAdapter = MediaOpenListAdapter(mediaList, activity as Context)
        mediaRecyclerView.adapter = mediaAdapter

        layoutManager.scrollToPosition(initPosition)
    }

}
