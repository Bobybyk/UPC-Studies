package com.example.tp1_final

class Hello {
}

fun main(Args: Array<String>) {
    println("Hello World !");
    try {
        val rects = arrayOf(
            Rectangle(),
            Rectangle(Point(5, 6), Point(8, 9)),
            //Rectangle(Point(1, 2), Point(0, 1)),
            Carre(l=5),
            Carre(Point(5,9),10),
            Carre(Point(5,9),10)
        )

        for(element in rects) {
            println(element.surface())
        }

        val filtered = rects.filter{e -> e.surface() > 10}
        for(el in filtered)
            println(el.surface())

        for(i in rects.indices) {
            for(j in i+1 until rects.size) {
                println("${rects[i]} & ${rects[j]}: ${rects[i].intersect(rects[j])}")
            }
        }

    } catch (e: IllegalArgumentException) {
        println(e)
    }
}