package com.example.tragosapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navControlador:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarCustom)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navControlador = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this,navControlador)
        UI()
    }



    override fun onSupportNavigateUp(): Boolean {
        return navControlador.navigateUp()
    }

    private fun UI(){
        var sharedPreferences = this?.getSharedPreferences("night", 0)
        switchLight.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchLight.isChecked = true
                nav_host_fragment.background = (ContextCompat.getDrawable(
                    this,
                    R.drawable.background_dark
                ))
                val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
                editor.putBoolean("night_mode", true)
                editor.commit()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchLight.isChecked = false
                nav_host_fragment.background = (ContextCompat.getDrawable(
                    this,
                    R.drawable.background_white
                ))
                val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
                editor.putBoolean("night_mode", false)
                editor.commit()
            }
        })
    }
}