package com.example.tp01

class Carre(p: Point = Point(0,0), l: Int): Rectangle(p,Point(p.x+l,p.y+l)) {
    init {
        if(l <= 0)
            throw IllegalArgumentException();
    }
}