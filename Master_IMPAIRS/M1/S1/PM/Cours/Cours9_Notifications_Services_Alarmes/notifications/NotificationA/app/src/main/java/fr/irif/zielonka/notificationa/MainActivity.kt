package fr.irif.zielonka.notificationa

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import fr.irif.zielonka.notificationa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val CHANNEL_ID = "message urgent"
    val notificationManager by lazy { getSystemService(NOTIFICATION_SERVICE) as NotificationManager }
    val pendingFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        PendingIntent.FLAG_IMMUTABLE
    else
        PendingIntent.FLAG_UPDATE_CURRENT

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        createNotificationChannel()
    }

    fun supprimerNotification(view: View) {
        /* récuperer id de la notification à supprimer */
        val id = binding.idCancel.text.toString().toInt()
        notificationManager.cancel(id)
    }

    fun envoyerNotification(view: View) {
        /* recupérer le message à addicher dans la notification */
        val message = binding.message.text.toString()

        /* intent à enoyer quand l'utilisateur clique sur la notification dans le
           notification drawer. Ici, sélectionner la notification
            cliquer sur la noti
         */
        val intent = Intent(this, ActiviteC::class.java)

/*
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addParentStack(ActiviteC::class.java)
        stackBuilder.addNextIntent(intent)
*/
        /* préparer PendingIntent qui contient l'intent */
        val pendingIntent = PendingIntent.getActivity(
            this, 1, intent,
            pendingFlag
        )

        /* préparer la notification
         * setLargeIcon() est facultatif est sa valeur doit être un bitmap
         * si l'image n'est pas en forme de bitmap il faut utiliser
         * BitmapFactory pour le transformer en bitmap
         */
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("lancer une autre activité")
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.small)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.large))
            .build()

        /* récupere id de la notification */
        val notId = binding.idSend.text.toString().toInt()

        /* envoyer la notification */
        notificationManager.notify(
            notId,
            notification
        )  /*notId - the ID de la Notification, utilisé pour faire cancel()*/

    }

    /* les notifications doivent posséder un channel */
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "private channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "private channel" }

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel)
        }
    }
}