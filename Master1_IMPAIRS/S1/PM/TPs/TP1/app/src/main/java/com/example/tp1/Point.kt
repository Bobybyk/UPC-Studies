package com.example.tp1

fun main(args: Array<String>){
    println(Point(10 ,11))
    try {
        val listRectangle: Array<Rectangle> = arrayOf(
            Rectangle(),
            Rectangle(q=Point(2, 2)),
            Rectangle(p=Point(2, 2)),
            Rectangle(Point(2, 2), Point(2, 2)),
            Rectangle(q=Point(2, 2), p=Point(2, 2))
        )
        println(listRectangle.joinToString("\n"))
    }catch (_: Exception){}
    val listRC: Array<Rectangle> = arrayOf(
        Rectangle(),
        Rectangle(q=Point(2, 2)),
        Carre(Point(1, 1) ,1)
    )
    println(listRC.joinToString("\n"))
}

data class Point(val x: Int, val y: Int) {

}
