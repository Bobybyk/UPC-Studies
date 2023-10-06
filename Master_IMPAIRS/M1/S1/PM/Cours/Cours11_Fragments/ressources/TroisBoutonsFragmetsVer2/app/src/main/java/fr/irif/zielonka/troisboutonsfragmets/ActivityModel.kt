package fr.irif.zielonka.troisboutonsfragmets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityModel :  ViewModel() {

    val compteurs = MutableLiveData( mutableListOf(0,0,0))
    var indexOnFrameLayout : Int = 0
    var indexOffFrameLayout : Int = 0

}