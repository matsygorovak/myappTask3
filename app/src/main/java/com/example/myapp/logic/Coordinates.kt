package com.example.myapp.logic

data class Box(val x: Int?, val y: Int?) {

    fun move(vector: Vector): Box {
        return Box(vector.vX + this.x!!, vector.vY + this.y!!)
    }
}