package com.example.tp1

class Carre(p:Point, l:Int): Rectangle(p, Point(p.x+l, p.y+l)) {

    init{
        if (l<=0){
            println("Error init Carre")
            throw java.lang.IllegalArgumentException()
        }
    }

}