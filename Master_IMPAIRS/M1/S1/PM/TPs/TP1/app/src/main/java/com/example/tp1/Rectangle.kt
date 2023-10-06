package com.example.tp1

open class Rectangle(val p: Point=Point(0,0), val q: Point=Point(1,1)) {

    init {
        if ( !(p.x < q.x && p.y < q.y)){
            println("Error init Rectangle")
            throw java.lang.IllegalArgumentException()
        }
    }

    override fun toString(): String {
        return "p=$${p} q=$${q}"
    }
}