package fr.irif.zielonka.simplealarm

import android.app.*
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat


class MyService : Service() {
    private val CHANNEL_ID = "channel"
    lateinit var  mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent): IBinder? {
        TODO()
    }

    override fun onCreate(){
        super.onCreate()

        mediaPlayer = MediaPlayer().apply {
            setVolume(1.0F, 1.0F)
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_ALARM).build()
            )
        }

        createNotificationChannel()
    }

    /* this function is used in order an NotificationCannel */
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, "channel_name", importance)
            channel.description = "channel_description"
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        /* arrêter si l'action dans l'intent le demande */
        if( intent.action == "stop" ){
            mediaPlayer.stop() /* arrêter le mediaPlayer */
            mediaPlayer.release() // libérer les ressources
            stopSelf() // arrêter le service lui-même
            return START_NOT_STICKY
        }

        if( !intent.action.equals("start") ){
            Log.d("MyService", "l'action inconnue")
            return START_NOT_STICKY
        }
        /* quand l'utilisateur clique sur le bouton de l'alarme le intent suivant sera envoyé vers le service */
        val serviceIntent = Intent(this, MyService::class.java).apply{
            action="stop"
        }
        val servicePendingIntent = PendingIntent.getService(this, 1, serviceIntent, PendingIntent.FLAG_IMMUTABLE)

        /* intent envoyé quand l'utilisateur touche sur la notification */
        val activityPendingIntent = PendingIntent.getActivity(this, 1,
            Intent(this,
            MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE)

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("musique")
            .setContentText("jouer la musique")
            .setSmallIcon(R.drawable.small)
            .setContentIntent( activityPendingIntent ) /* activer une activité */
            .addAction(R.drawable.small, "arrêter la musique", servicePendingIntent)
            .setAutoCancel(true)
            .build()

        Log.d("MyService", "passer en mode foreground")
        startForeground(1, notification)

        if( intent.data == null )
            return START_NOT_STICKY


        /* un listener exécuté quand la musique termine */
        mediaPlayer.setOnCompletionListener {
            //libérérer les ressouces de MediaPlayer
            mediaPlayer.release()
            //arrêter le service foreground et enlever la notification
            //qui permet arrêter le service
            stopForeground(STOP_FOREGROUND_REMOVE)
            Log.d("MyService", "onCompletionListener")
        }

        /* quand  le mediaPlayer est prêt à jouer le faire démarrer */
        mediaPlayer.apply {
            setDataSource( this@MyService, intent.data!! )
            setOnPreparedListener{
                it.start()
            }
            prepareAsync()
        }

        return START_NOT_STICKY
    }


    override fun onDestroy() {
        /* arêter la musique et libérer les resources de MediaPlayer */
        mediaPlayer.release()
        Log.d("MyService", "OnDestroy")
        super.onDestroy()
    }
}


