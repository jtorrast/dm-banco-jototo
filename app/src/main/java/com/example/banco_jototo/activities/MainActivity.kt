package com.example.banco_jototo.activities

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.preference.PreferenceManager
import com.example.banco_jototo.R
import com.example.banco_jototo.databinding.ActivityMainBinding
import com.example.banco_jototo.fragments.AccountsFragment
import com.example.banco_jototo.fragments.AccountsListener
import com.example.banco_jototo.fragments.AccountsMovementsFragment
import com.example.banco_jototo.fragments.MainActivityFragment
import com.example.banco_jototo.fragments.MovementsListener
import com.example.banco_jototo.pojo.Cliente
import com.example.banco_jototo.pojo.Cuenta
import com.example.banco_jototo.pojo.Movimiento
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, AccountsListener, MovementsListener {

    //private lateinit var binding: ActivityMainBinding

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this, R.raw.audio)


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

    }

    override fun onResume() {
        super.onResume()
        val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
        Log.i("MainActivity", "Reproducir musica: "+pref.getBoolean("reproducirMusica", false))

        if (pref.getBoolean("reproducirMusica", false)){
            mediaPlayer?.start()
        }


    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        when(item.itemId){
            R.id.nav_home ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frgMainActivity, MainActivityFragment.newInstance(cliente)).commit()

            }
            R.id.nav_globalPosition ->{
                val frgMainAccounts = AccountsFragment.newInstance(cliente)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frgMainActivity, frgMainAccounts).commit()
                frgMainAccounts.setListener(this)
            }
            R.id.nav_settings ->{
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_logout->{
                System.exit(0)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onCuentaSeleccionada(cuenta: Cuenta) {
        val frgMovements = AccountsMovementsFragment.newInstance(cuenta)

        Log.i("Configuración de pantalla", "Valor de screenLayout: ${resources.configuration.screenLayout}")

        //el valor 268435796 equivale a la pantalla de la tablet
        if (resources.configuration.screenLayout == 268435796){
            Log.i("Dispositivo", "tablet")
            frgMovements.setListener(this)

            //tablet
            supportFragmentManager.beginTransaction().
            replace(R.id.frgMovimiento, frgMovements).commit()

        }else{
            //Pantalla movil, cambiamos actividad
            val intent = Intent(this, GlobalPositionDetailsActivity::class.java)
            intent.putExtra("Cuenta", cuenta)
            startActivity(intent)
        }
    }

    override fun onMovimientoSeleccionado(movimiento: Movimiento) {
        Log.i("Global Position", movimiento.getDescripcion().toString())
        val dialogView = layoutInflater.inflate(R.layout.dialog_movement, null)

        val formateador = SimpleDateFormat("dd/MM/yyyy")

        val textoInfo = dialogView.findViewById<TextView>(R.id.textInfo)
        textoInfo.text = "Id: ${movimiento.getId()} \n" +
                "Descripcion: ${movimiento.getDescripcion()} \n" +
                "Fecha: ${formateador.format(movimiento.getFechaOperacion())}"

        MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .setBackground(getDrawable(R.color.azul_oscuro_medio))
            .setPositiveButton(resources.getString(R.string.accept), DialogInterface.OnClickListener { dialog, i ->
                dialog.cancel()
            })
            .setCancelable(false)
            .show()
    }


}