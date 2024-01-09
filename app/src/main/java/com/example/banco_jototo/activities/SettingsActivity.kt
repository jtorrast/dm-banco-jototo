package com.example.banco_jototo.activities

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.banco_jototo.R
import java.util.Locale

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val languagePreference: ListPreference? = findPreference("language")

            languagePreference?.setOnPreferenceChangeListener { _, newValue ->
                val selectedLanguage = newValue.toString()
                setLocale(selectedLanguage)
                true
            }
            
        }

        private fun setLocale(language: String) {
            val locale = Locale(language)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            resources.updateConfiguration(config, resources.displayMetrics)

            // Guardar la preferencia del idioma en SharedPreferences para usarla en futuras sesiones
            val preferences: SharedPreferences =
                requireActivity().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            preferences.edit().putString("language", language).apply()

            // Reiniciar la actividad para aplicar los cambios de idioma
            requireActivity().recreate()

        }
    }
}