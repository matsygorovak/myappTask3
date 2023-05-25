package com.example.myapp.logic

data class Vector(val vX: Int, val vY: Int)

enum class Direction {
    UP, DOWN, RIGHT, LEFT;

    fun getVector(): Vector {
        return when (this){
            UP -> Vector(-1, 0)
            DOWN -> Vector(1, 0)
            RIGHT -> Vector(0,1)
            else -> Vector(0,-1)
        }
    }


}