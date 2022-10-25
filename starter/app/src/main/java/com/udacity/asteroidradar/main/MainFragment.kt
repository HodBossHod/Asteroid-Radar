package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment(),MenuProvider {



    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this,MainViewModelFactory(requireActivity().application)).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //New menu
        val menuHost: MenuHost =requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.navigateToAsteroidDetail.observe(viewLifecycleOwner, Observer {
            if(it != null)
            {
                findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                viewModel.finishNav()
            }
        })


//        val application= requireNotNull(this.activity).application
//        val dataSource= getDatabase(application).asteroidDao

        val adapter = AsteroidAdapter( AsteroidAdapter.AsteroidClickListener{
            viewModel.showAsteroidDetails(it)
        })
        binding.asteroidRecycler.adapter=adapter

        //ObserveAsteroids(adapter)

        return binding.root
    }

//    private fun ObserveAsteroids(adapter: AsteroidAdapter) {
//        viewModel.asteroids.observe(viewLifecycleOwner, Observer {
//            it?.let {
//                adapter.submitList(it)
//            }
//        })
//    }



    //New menu
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_overflow_menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId){
            R.id.view_saved_asteroids_menu ->viewModel.getSavedAsteroids()
            R.id.view_week_asteroids_menu->viewModel.getWeekAsteroids()
            R.id.view_today_asteroids_menu->viewModel.getTodayAsteroids()
        }
        return true
    }
}
