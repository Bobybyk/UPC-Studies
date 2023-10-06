package com.example.tp1_final

open class Rectangle(val p: Point = Point(0,0),
                     val q: Point = Point(1,1)) {

    init {
        if(q.x < p.x || q.y < p.y)
            throw IllegalArgumentException();
    }

    override fun toString(): String = "p=$p q=$q";

    fun surface(): Int {
        return (q.y-p.y) * (q.x - p.x)
    }

    fun intersect(r: Rectangle): Boolean {
        return (r.q.x >= this.p.x &&
                r.q.y >= this.p.y &&
                r.p.x <= this.q.x &&
                r.p.y <= this.q.y);
    }
}