package com.apps.ahfreelancing.marvelapp.presentation.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation
import com.apps.ahfreelancing.marvelapp.R
/**
 * This Activity is holds a Navigation Host Fragment in its layout
 * All navigation of the application is done through this NavHostFragment
 * Using Navigation graph called nav_graph.xml
 *
 * */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.fragment).navigateUp()
    }

}