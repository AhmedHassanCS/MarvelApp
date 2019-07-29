package com.apps.ahfreelancing.marvelapp.presentation.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.apps.ahfreelancing.marvelapp.presentation.dagger.component.DaggerFragmentComponent
import com.apps.ahfreelancing.marvelapp.presentation.dagger.component.FragmentComponent
import com.apps.ahfreelancing.marvelapp.presentation.dagger.module.DataModule

/**
 * Created by Ahmed Hassan on 7/27/2019.
 *
 * BaseFragment is created for all the other fragments to extend from
 * It have a main common feature that is dagger component and its building
 *
 */
abstract class BaseFragment : Fragment(){

    private lateinit var fragmentComponent: FragmentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
    }

    private fun initDagger() {
        //initialize the dagger activity component
        fragmentComponent = DaggerFragmentComponent
            .builder()
            .dataModule(DataModule(activity!!))
            .build()
    }

    protected fun getFragmentComponent() : FragmentComponent = fragmentComponent

    protected fun showError(msg: String){
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }

}