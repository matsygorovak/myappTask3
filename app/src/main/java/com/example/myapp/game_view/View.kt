package com.example.myapp.game_view

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.SecondActivity
import com.example.myapp.logic.Direction
import com.example.myapp.logic.Game
import com.example.myapp.prefs
import com.example.myapp.ui.theme.Peach
import com.example.myapp.ui.theme.emptyS

@Composable
fun View() {
    val game by remember { mutableStateOf(Game()) }
    var score by remember { mutableStateOf(0) }
    var gameOver by remember { mutableStateOf(false) }
    var color by remember { mutableStateOf(Color(0xffffffff)) }
    val mContext = LocalContext.current
    var currentNumber by rememberSaveable { mutableStateOf(0) }

    Column {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(width = 90.dp, height = 70.dp)
                    .background(Color.White)
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = "2048",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            ScoreBox(gameScore = score)

            Column {
                currentNumber = prefs.number
                HighScoreBox(highGameScore = currentNumber)

                Button(
                    onClick = {
                        score = game.score
                        game.getStartField()
                        gameOver = false
                        currentNumber = prefs.number
                        if (game.score > currentNumber) {
                            currentNumber = game.score
                            prefs.number = currentNumber

                        }
                    },
                    modifier = Modifier
                        .size(width = 100.dp, height = 30.dp)
                        .wrapContentSize(Alignment.Center),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        Peach
                    )
                ) {
                    Text(
                        fontSize = 11.sp,
                        text = "RESTART",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

        }
        if (gameOver) {
            currentNumber = prefs.number
            if (game.score > currentNumber) {
                currentNumber = game.score
                prefs.number = currentNumber

            }

            Column {
                currentNumber = prefs.number
                if (game.score > currentNumber) {
                    currentNumber = game.score
                    prefs.number = currentNumber

                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "GAME\n            OVER)",
                    color = Color.Black,
                    fontSize = 50.sp,
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(
                start = 10.dp,
                top = 16.dp,
                end = 10.dp,
                bottom = 16.dp
            ),
            content = {
                items(game.field.size) { index ->
                    val value = game.field.toList()[index].second
                    color = Game().colorOfBox(value)

                    Card(
                        colors = CardDefaults.cardColors(color),
                        modifier = Modifier
                            .size(70.dp)
                            .padding(4.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            text = if (value != null) "$value" else "",
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp,
                            color = Color.Black,
                        )
                    }
                }
            }
        )


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    game.moveField(Direction.LEFT)
                    score = game.score
                    gameOver = game.gameOver
                },
                modifier = Modifier
                    .wrapContentSize(Alignment.Center),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    emptyS
                )
            ) {
                Text(
                    fontSize = 35.sp,
                    text = "←",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Button(
                onClick = {

                    game.moveField(Direction.DOWN)
                    score = game.score
                    gameOver = game.gameOver

                },
                modifier = Modifier
                    .wrapContentSize(Alignment.Center),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    emptyS
                )
            ) {
                Text(
                    fontSize = 35.sp,
                    text = "↓",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Button(
                onClick = {
                    game.moveField(Direction.UP)
                    score = game.score
                    gameOver = game.gameOver


                },
                modifier = Modifier
                    .wrapContentSize(Alignment.Center),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    emptyS
                )
            ) {
                Text(
                    fontSize = 35.sp,
                    text = "↑",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Button(
                onClick = {
                    game.moveField(Direction.RIGHT)
                    score = game.score
                    gameOver = game.gameOver

                },
                modifier = Modifier
                    .wrapContentSize(Alignment.Center),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    emptyS
                )
            ) {
                Text(
                    fontSize = 35.sp,
                    text = "→",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
        Button(
            onClick = {
                score = game.score
                gameOver = game.gameOver

                mContext.startActivity(Intent(mContext, SecondActivity::class.java))
            },

            modifier = Modifier
                .wrapContentSize(Alignment.Center),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                emptyS
            )
        ) {
            Text(
                fontSize = 20.sp,
                text = "Menu",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}


@Composable
fun ScoreBox(gameScore: Int) {
    Box(
        modifier = Modifier
            .size(width = 90.dp, height = 70.dp)
            .background(emptyS)
            .wrapContentSize(Alignment.Center)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Text(
            text = "SCORE\n  $gameScore",
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun HighScoreBox(highGameScore: Int) {
    Box(
        modifier = Modifier
            .size(width = 100.dp, height = 40.dp)
            .background(emptyS)
            .wrapContentSize(Alignment.Center),
    ) {
        Text(
            text = "High SCORE\n  $highGameScore",
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}
