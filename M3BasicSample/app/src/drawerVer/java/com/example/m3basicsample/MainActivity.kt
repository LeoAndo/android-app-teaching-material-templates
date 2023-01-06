package com.example.m3basicsample

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.m3basicsample.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.FirstFragment, R.id.SecondFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }
        observeDestinations(navView, navController)
    }

    private fun observeDestinations(navView: NavigationView, navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.FirstFragment, R.id.SecondFragment -> {
                    navView.isVisible = true
                }
                else -> {
                    navView.isVisible = false
                    Log.d(TAG, "observeDestinations: navigatorName " + destination.navigatorName)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val localeList = when (item.itemId) {
            R.id.language_settings_system -> LocaleListCompat.getEmptyLocaleList()
            R.id.language_settings_ja -> LocaleListCompat.forLanguageTags("ja")
            R.id.language_settings_ko -> LocaleListCompat.forLanguageTags("ko")
            R.id.language_settings_zh -> LocaleListCompat.forLanguageTags("zh")
            else -> null
        }
        localeList?.let { AppCompatDelegate.setApplicationLocales(it) }
        return if (localeList != null) true else super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val ret = navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        Log.d(TAG, "onSupportNavigateUp: ret $ret")
        return ret
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}