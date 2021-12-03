package com.clintonsoares.ttx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.clintonsoares.ttx.base.BaseActivity
import com.clintonsoares.ttx.base.BaseViewModel
import com.clintonsoares.ttx.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    NavController.OnDestinationChangedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavController()
    }

    private fun initNavController() {
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(R.id.homeScreen,R.id.listScreen),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        navController.addOnDestinationChangedListener(this)
        findViewById<Toolbar>(R.id.toolbar).apply {
            setUpToolbar(this,R.string.home)
        }.setupWithNavController(navController, appBarConfiguration)
    }

    override fun getVM(): BaseViewModel? = null
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        TODO("Not yet implemented")
    }
}