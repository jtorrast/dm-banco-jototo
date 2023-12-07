package com.example.banco_jototo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ActivityMainBinding
import com.example.banco_jototo.fragments.AccountsFragment
import com.example.banco_jototo.fragments.AccountsMovementsFragment
import com.example.banco_jototo.fragments.MainActivityFragment
import com.example.banco_jototo.pojo.Cliente
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //private lateinit var binding: ActivityMainBinding

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        drawerLayout = findViewById(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle =  ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val cliente = intent.getSerializableExtra("Cliente") as Cliente


        if (savedInstanceState == null) {
            val frgMain = MainActivityFragment.newInstance(cliente)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frgMainActivity, frgMain).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }

        /*Ahora tendremos que añadir los framents que llamemos desde el menu lateral*/


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        when(item.itemId){
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.frgMainActivity, MainActivityFragment.newInstance(cliente)).commit()
            R.id.nav_globalPosition-> supportFragmentManager.beginTransaction()
                .replace(R.id.frgMainActivity, AccountsFragment.newInstance(cliente)).commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }


}