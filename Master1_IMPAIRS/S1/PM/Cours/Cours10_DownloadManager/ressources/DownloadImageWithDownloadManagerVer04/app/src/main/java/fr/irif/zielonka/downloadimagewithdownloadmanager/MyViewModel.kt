package fr.irif.zielonka.downloadimagewithdownloadmanager

import android.app.Application
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.io.File
import java.io.FileNotFoundException
import kotlin.concurrent.thread

class MyViewModel(application: Application) : AndroidViewModel(application) {

    /* récupérer le DownloadService */
    val downloadManager = //getApplication<Application>()
        application.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    /* liste des url des images à téléchrger */
    lateinit var urlListe: List<String>

    var mapListPositionToChecked: MutableMap<Int, Boolean> = mutableMapOf()

    // map pour les couples <id de téléchargement , url de téléchargement >
    var mapIdToUrl: MutableMap<Long, String> = mutableMapOf()

    var mapIdToFile: MutableMap<Long, String> = mutableMapOf()

    val bitmapList = mutableListOf<Bitmap>()
    val liveBitmapList: MutableLiveData<List<Bitmap>> =
        MutableLiveData(bitmapList)

    /* receiver : le BroadcastReceiver qui sera activé par le DownloadManager
    * une fois le téléchargement  terminé
     */
    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {

            /* trouver la reference de téléchargement */
            val idDownload: Long = p1?.getLongExtra(
                DownloadManager.EXTRA_DOWNLOAD_ID, -1
            ) ?: -1

            if (idDownload == -1L) {
                Log.d("Receiver", "something wrong with idDownload")
            }

            /* vérifier si la référence se trouve sur la liste de nos téléchargement
            *  nécessaire si d'autres téléchargement par d'autres applications */
            if (idDownload !in mapIdToUrl.keys) {
                Log.d("idDownload", "$idDownload  n'est pas sur la liste de telech")
                return
            }


            //transformer l'image téléchargé en un Bitmap dans un  nouveau thread
            // parce que cette opération peut prendre beacoup de temps
            thread {
                val file = File(
                    getApplication<Application>().getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                    mapIdToFile[idDownload]
                )

                if (file == null) {
                    Log.d("fichier null ", "${mapIdToFile[idDownload]}  ${mapIdToUrl[idDownload]}")
                    return@thread
                }
                // Log.d( "fichier dans ", "$uri")
                Log.d("fichier", "${file.absolutePath}")
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)

                /* plusieurs thread peuvent essayer ajouter les bitmaps en même temps
                *  faut synchroniser
                 */

                if (bitmap == null) {
                    Log.d("fichier null ", "${mapIdToFile[idDownload]}  ${mapIdToUrl[idDownload]}")
                    return@thread
                }
                synchronized(bitmapList) {
                    bitmapList.add(bitmap)
                    //le contenu de bitmapList a changé, il faut la remettre dans LiveData pour que l'observer reagisse
                    liveBitmapList.postValue(bitmapList)
                }
            }
        }
    }


    val cancelReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {

            /* trouver les references de téléchargement à supprimer */
            val reference = p1?.getLongArrayExtra(
                DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS
            )
            Log.d("Remove downloads ", "from notifications")
            if (reference == null) {
                Log.d("remove", "tab id null")
                return
            }
            if (reference.size == 0) {
                Log.d("remove", "tab ids taille 0")
                return
            }

            thread {
                downloadManager.remove(*reference)
                Log.d("remove download size = ", "${reference.size}")
            }
        }
    }


    fun fetchBitmaps() {
        /* preparer les filtres pour BroadcastReceiver */
        val filter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        val cancelFilter = IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED)

        /* enregistrer les BroadcastReceivers */
        getApplication<Application>().registerReceiver(receiver, filter)
        getApplication<Application>().registerReceiver(cancelReceiver, cancelFilter)

        var i = 1
        for (adr in urlListe) {
            /* fabriquer Uri de telechargement*/
            val uri = Uri.parse(adr)

            var nomFichier = "image" + i + ".jpeg"
            //val file = File( getApplication<Application>().getExternalFilesDir( Environment.DIRECTORY_PICTURES ), "image." + i + "jpeg")
            i++

            if( adr in mapIdToUrl.values)
                return

            val request = DownloadManager.Request(uri)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationInExternalFilesDir(
                    getApplication<Application>(),
                    Environment.DIRECTORY_PICTURES,
                    nomFichier
                )
            val idLoad: Long = downloadManager.enqueue(request)

            mapIdToFile[idLoad] = nomFichier
            /* ajouter <id, adresse> de téléchargement à la liste */
            //idListe.add(id)
            mapIdToUrl[idLoad] = adr
        }
    }

    /* onCleared() called when the ViewModel no longer used */
    override fun onCleared() {
        super.onCleared()
        /* unregister les BroadcastReceivers */
        getApplication<Application>().unregisterReceiver(receiver)
        getApplication<Application>().unregisterReceiver(cancelReceiver)


    }
}