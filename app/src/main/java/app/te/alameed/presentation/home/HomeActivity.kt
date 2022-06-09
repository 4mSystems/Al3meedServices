package app.te.alameed.presentation.home

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import app.te.alameed.presentation.base.BaseActivity
import app.te.alameed.databinding.ActivityHomeBinding
import app.te.alameed.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var nav: NavController

    override
    fun getLayoutId() = R.layout.activity_home

    override
    fun setUpBottomNavigation() {
        setUpNavigationWithGraphs()
    }

    private fun setUpNavigationWithGraphs() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_host_container) as NavHostFragment
        nav = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home_fragment
            )
        )
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(nav, appBarConfiguration)
        navChangeListener()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun navChangeListener() {
        nav.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.splashFragment) {
                binding.toolbar.visibility = View.GONE
            } else {
                binding.toolbar.visibility = View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return nav.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(nav) || super.onOptionsItemSelected(item)
    }


}