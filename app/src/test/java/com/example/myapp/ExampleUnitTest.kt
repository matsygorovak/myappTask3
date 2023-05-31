package com.example.myapp

import com.example.myapp.logic.Box
import com.example.myapp.logic.Direction
import com.example.myapp.logic.Game
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testCreateRandomBox() {
        val game = Game()
        game.field[Box(0, 0)] = null
        game.field[Box(0, 1)] = null
        game.field[Box(0, 2)] = 2
        val randomBox = game.createRandomBox()
        assertNotNull(randomBox)
        assertTrue(game.field.containsKey(randomBox!!.first))
        assertTrue(randomBox.second == 2 || randomBox.second == 4)
    }

    @Test
    fun `checkGameOver when empty box list is empty and different numbers`() {
        val game = Game()
        game.field = mutableMapOf(
            Box(0,0) to 2,
            Box(1,0) to 4,
            Box(0,1) to 8,
            Box(1,1) to 16,
            Box(0,2) to 32,
            Box(1,2) to 64,
            Box(2,0) to 128,
            Box(2,1) to 256,
            Box(2,2) to 512,
            Box(2,3) to 1024,
            Box(1,3) to 2048,
            Box(0,3) to 2,
            Box(3,0) to 2,
            Box(3,1) to 4,
            Box(3,2) to 8,
            Box(3,3) to 4
        )
        game.checkGameOver()
        assertEquals(true, game.gameOver)
    }

    @Test
    fun `checkGameOver when empty box list is empty`() {
        val game = Game()
        game.field = mutableMapOf(
            Box(0,0) to 2,
            Box(1,0) to 4,
            Box(0,1) to 8,
            Box(1,1) to 16,
            Box(0,2) to 32,
            Box(1,2) to 64,
            Box(2,0) to 32,
            Box(2,1) to 4,
            Box(2,2) to 16,
            Box(2,3) to 8,
            Box(1,3) to 2,
            Box(0,3) to 2,
            Box(3,0) to 4,
            Box(3,1) to 4,
            Box(3,2) to 4,
            Box(3,3) to 4
        )
        game.checkGameOver()
        assertEquals(false, game.gameOver)
    }

    @Test
    fun getEmptyCellsTest() {
        val game = Game()
        val gameField = game.field
        val expected = game.getEmptyBoxs()
        val result = mutableListOf<Box>()
        gameField.forEach { (box, value) ->
            if (value == null) result.add(box)
        }
        assertEquals(expected, result)
    }
    @Test
    fun testMoveField() {
        val game = Game()
        game.field[Box(1, 0)] = 4
        game.field[Box(2, 0)] = 2
        game.field[Box(3, 0)] = 2
        game.moveField(Direction.DOWN)
        assertEquals(4, game.field[Box(3,0)] )
    }

    @Test
    fun testMoveField2() {
        val game = Game()
        game.field[Box(0, 1)] = 16
        game.field[Box(0, 2)] = 16
        game.field[Box(3, 1)] = 4
        game.field[Box(3, 0)] = 4
        game.moveField(Direction.LEFT)
        assertEquals(32, game.field[Box(0,0)] )
        assertEquals(8, game.field[Box(3,0)] )

    }
}