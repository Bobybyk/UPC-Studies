package fr.irif.zielonka.musicservice

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MusicService : Service() {

    companion object {
        public const val START_ACTION = "fr.irif.zielonka.musicservice.start"
        public const val STOP_ACTION = "fr.irif.zielonka.musicservice.start"
        public const val PAUSE_ACTION = "fr.irif.zielonka.musicservice.pause"
    }
    var state = "idle"

    private var mediaPlayer: MediaPlayer? = null

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        //mediaPlayer = MediaPlayer.create(this, R.raw.sample)
        mediaPlayer = MediaPlayer().apply {
            setVolume(1.0F, 1.0F)
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_ALARM).build()
            )
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //return super.onStartCommand(intent, flags, startId)

        when (intent?.action) {
            START_ACTION -> {
                if( state == "paused"){
                    Log.d("mediaPlayer", "pause -> start")
                    mediaPlayer?.start()
                    state="started"
                    return START_NOT_STICKY
                }

                if (intent.data == null)
                    return START_NOT_STICKY

            //    state="started"
                //mediaPlayer?.setDataSource(this, intent.data!!)

                if( state=="stopped"){
                    state="preparing"
                    mediaPlayer?.apply{
                        setOnPreparedListener { it.start()
                            state="started"
                        }
                        prepareAsync()
                        state="prepared"
                    }
                }

                mediaPlayer?.apply {
                    setDataSource(this@MusicService, intent.data!!)
                    state="initialized"
                    setOnPreparedListener { it.start()
                    state="started"
                    }  /* listener appelé quand prepareAsync() retourne
                                                           *  le mediaPlayer prêt à démarrer */
                    setOnCompletionListener {   /* le listener appelé quand le mediaPlayer termine de jouer */
                        release()  //libérer le ressources de MediaPlayer
                        mediaPlayer = null
                        state="stopped"
                    }
                    prepareAsync()
                    state="prepared"

                }
            }
            STOP_ACTION -> {
                stopSelf()
                state = "stopped"
            }
            PAUSE_ACTION -> {
                Log.d("ServiceMusique", "pause")
                mediaPlayer?.pause()
                state="paused"
            }
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        /* arêter la musique et libérer les resources de MediaPlayer */
        mediaPlayer?.release()
        mediaPlayer = null
    }
}