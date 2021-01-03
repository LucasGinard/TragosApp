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
import com.example.tragosapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navControlador:NavController
    private lateinit var viewBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbarCustom)
        supportActionBar?.setIcon(R.drawable.icon)


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
        viewBinding.switchLight.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                supportActionBar?.setIcon(R.drawable.iconwhite);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                viewBinding.switchLight.isChecked = true
                viewBinding.navHostFragment.background = (ContextCompat.getDrawable(
                    this,
                    R.drawable.background_dark
                ))
                val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
                editor.putBoolean("night_mode", true)
                editor.commit()
                viewBinding.ivSwitch.setImageDrawable(getDrawable(R.drawable.switch_light_true))
            } else {
                supportActionBar?.setIcon(R.drawable.icon)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                viewBinding.switchLight.isChecked = false
                viewBinding.navHostFragment.background = (ContextCompat.getDrawable(
                    this,
                    R.drawable.background_white
                ))
                val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
                editor.putBoolean("night_mode", false)
                editor.commit()
                viewBinding.ivSwitch.setImageDrawable(getDrawable(R.drawable.switch_light_false))
            }
        })
    }
}