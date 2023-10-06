package fr.irif.zielonka.simplealarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import fr.irif.zielonka.getUriFromRes
import fr.irif.zielonka.simplealarm.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

  val binding by lazy{  ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun setAlarm(view: View) {
        var sec = 0

        sec = try {
            binding.edit.text.toString().toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "NumberFormatException", Toast.LENGTH_SHORT).show()
            Log.d("MainActivity", "NumberFormatException")
            return
        }
        binding.edit.text.clear()

        if (sec <= 0) {
            Toast.makeText(this, "délais négatif ", Toast.LENGTH_SHORT).show()
            return
        }

        /*preparer le PendingIntent à envoyer vers AlarmManager*/
        val serviceIntent = Intent(this, MyService::class.java)
            .apply {
                action = "start"
                data = getUriFromRes( this@MainActivity, R.raw.sample )
            }

        val pendingIntent = PendingIntent.getService(this, 0, serviceIntent, PendingIntent.FLAG_IMMUTABLE)

        Log.d("amorcer alarme", "$sec secondes")

        /* calculer le moment de demarrage d'alarme */
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.SECOND, sec)
        //amorcer Alarme
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }
}

