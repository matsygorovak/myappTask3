package com.example.myapp.logic

import androidx.compose.ui.graphics.Color
import com.example.myapp.ui.theme.*

class Game constructor(
    var gameOver: Boolean = false,
    var field: MutableMap<Box, Int?> = mutableMapOf(),
    var score: Int = 0,
    var highScore: Int = 0,
) {
    init {
        getStartField()
        gameOver = false
        score = 0
    }

    fun moveField(direction: Direction) {
        if (!gameOver) {
            val start = field.toMap()
            field.forEach { (box, value) ->
                if (value != null) {
                    val nextBox = nextBox(direction, box)
                    if (field[nextBox] == value && nextBox != box) {
                        field[nextBox] = value.times(2)
                        score += field[nextBox]!!
                        field[box] = null
                    } else {
                        field[box] = null
                        field[nextBox] = value
                    }
                }
            }
            field = overwriteField()
            if (start != field) {
                val newBox = createRandomBox()
                field[newBox!!.first] = newBox.second
            }
            field = overwriteField()
            checkGameOver()
        }
    }


    private fun checkGameOver() {
        var check = true
        if (getEmptyBoxs().isEmpty()) {
            field.forEach { (box, value) ->
                for (i in goAllDirections(box)) {
                    if (box != i && value == field[i]) {
                        check = false
                    }
                }
            }
            if (check) {
                if (score > highScore) {
                    highScore = score
                }
                gameOver = true
            }
        }
    }


    private fun nextBox(direction: Direction, box: Box): Box {
        val vector = direction.getVector()
        var next = box.move(vector)
        var result = box
        while (next.x!! in 0..3 && next.y!! in 0..3 && field[next] == null) {
            result = next
            next = next.move(vector)
        }
        if (field[next] != null && field[next] == field[box]) {
            result = next
        }
        return result
    }

    private fun goAllDirections(box: Box): List<Box> {
        val allBoxes = mutableListOf<Box>()
        enumValues<Direction>().forEach {
            allBoxes.add(nextBox(it, box))
        }
        return allBoxes
    }

    private fun createRandomBox(): Pair<Box, Int>? {
        val emptyBoxes = getEmptyBoxs()
        return if (emptyBoxes.isEmpty()) null
        else {
            val randomValue = listOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 4).random()
            val randomBox = emptyBoxes.random()
            return Pair(randomBox, randomValue)
        }
    }

    fun getStartField() {
        gameOver = false
        //чистое поле
        for (x in 0 until 4) {
            for (y in 0 until 4) {
                val boxToField = Box(x, y)
                field[boxToField] = null
            }
        }
        //начальные элементы
        val box1 = createRandomBox()
        val box2 = createRandomBox()
        field[box1!!.first] = box1.second
        field[box2!!.first] = box2.second
        field = overwriteField()
        score = 0
    }

    private fun overwriteField(): MutableMap<Box, Int?> {
        val overwriteField = mutableMapOf<Box, Int?>()
        val oldField = field.toList()
        for (i in oldField.indices) {
            overwriteField[oldField[i].first] = oldField[i].second
        }
        return overwriteField
    }

    private fun getEmptyBoxs(): List<Box> {
        val emptyBoxs = ArrayList<Box>()
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                val box = Box(i, j)
                if (field[box] == null) {
                    emptyBoxs.add(box)
                }
            }
        }
        return emptyBoxs
    }

    fun colorOfBox(number: Int?): Color {
        return when (number) {
            2 -> S2
            4 -> S4
            8 -> S8
            16 -> S16
            32 -> S32
            64 -> S64
            128 -> S128
            256 -> S256
            512 -> S512
            1024 -> S1024
            2048 -> S2048
            else -> emptyS
        }
    }
}



