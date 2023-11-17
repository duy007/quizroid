package edu.uw.ischool.dnh7.quizdroid;

import android.app.Activity;
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.uw.ischool.dnh7.quizdroid.databinding.PreferencesBinding

class PreferenceActivity : AppCompatActivity() {

    private lateinit var binding: PreferencesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val prefs = this.getPreferences(Context.MODE_PRIVATE)
        val url = prefs.getString("url", "http://tednewardsandbox.site44.com/questions.json")
        val minute = prefs.getInt("minute", 3)
        binding.urlInput.setText(url)
        binding.minuteInput.setText(minute.toString())
        binding.preferenceSave.setOnClickListener {
            val prefsEditor = prefs.edit()
            prefsEditor.putString("url", binding.urlInput.text.toString())
            prefsEditor.putInt("minute", binding.minuteInput.text.toString().toInt())
            prefsEditor.apply()
            Toast.makeText(this, "Preferences saved", Toast.LENGTH_SHORT).show()
            this.startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
