package com.example.stresscoping.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.stresscoping.R
import com.example.stresscoping.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private var menuState = MenuState.None

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun clearMenu() {
        menuState = MenuState.None
        invalidateOptionsMenu()
    }

    fun invalidateStressCopingListDeleteMenu() {
        menuState = MenuState.StressCopingListDelete
        invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        when (menuState) {
            MenuState.None -> {
                menu?.clear()
            }

            MenuState.StressCopingListDelete -> {
                menuInflater.inflate(R.menu.menu_stress_coping_list_delete, menu)
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val currentFragment =
            navHostFragment.childFragmentManager.primaryNavigationFragment as? StressCopingListFragment
        if (currentFragment !is StressCopingListFragment) return super.onOptionsItemSelected(item)
        return when (item.itemId) {
            R.id.menu_item_delete -> {
                currentFragment.deleteStressCopings()
                true
            }

            R.id.menu_item_cancel -> {
                currentFragment.changeListStateToColumn()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}