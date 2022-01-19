package com.example.madtictactoe
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    //Player 1=X  Player 0 = O
    var activePlayer = 1
    var gameIsActive = true
    var count = 0
    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    var winningPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    @SuppressLint("WrongViewCast")
    fun Tap(view: View) {
        val counter = view as ImageView
        val turnO =findViewById<LinearLayout>(R.id.startO)
        val turnX = findViewById<LinearLayout>(R.id.startX)
        val again = findViewById<LinearLayout>(R.id.winner)
        val tapcount = counter.tag.toString().toInt()
        turnX.visibility = View.VISIBLE
        turnO.visibility = View.INVISIBLE
        if (gameState[tapcount] == 2 && gameIsActive) {
            if (activePlayer == 1) {
                turnO.visibility = View.VISIBLE
                turnX.visibility = View.INVISIBLE
                counter.setImageResource(R.drawable.xred)
                activePlayer = 0
                count++
                gameState[tapcount] = 1
            } else {
                turnO.visibility = View.INVISIBLE
                counter.setImageResource(R.drawable.oblack)
                activePlayer = 1
                count++
                gameState[tapcount] = 0
            }
            counter.translationY = -1000f
            counter.animate().translationYBy(1000f).rotationY(1800f).duration = 1000
            for (winningposition in winningPositions) {
                if (gameState[winningposition[0]] == gameState[winningposition[1]] && gameState[winningposition[1]] == gameState[winningposition[2]] && gameState[winningposition[0]] != 2
                ) {
                    if (gameState[winningposition[0]] == 0) {
                       Snackbar.make(view, "Circle Player Wins", Snackbar.LENGTH_LONG).show()
                        again.visibility = View.VISIBLE
                        turnO.visibility = View.INVISIBLE
                        turnX.visibility = View.INVISIBLE
                    } else if (gameState[winningposition[0]] == 1) {
                        Snackbar.make(view, "X Player Wins", Snackbar.LENGTH_LONG).show()
                        again.visibility = View.VISIBLE
                        turnO.visibility = View.INVISIBLE
                        turnX.visibility = View.INVISIBLE
                    }

                    gameIsActive = false
                }
            }
        }
        if (gameIsActive && count == 9) {
            Snackbar.make(view, "DRAW", Snackbar.LENGTH_LONG).show()
            again.visibility = View.VISIBLE
            turnO.visibility = View.INVISIBLE
            turnX.visibility = View.INVISIBLE
            gameIsActive = false
        }
    }


    fun playAgain(view: View?) {
        activePlayer = 1
        gameIsActive = true
        count= 0
        val linearLayout = findViewById<LinearLayout>(R.id.winner)
        val gridLayout =
            findViewById<GridLayout>(R.id.gridLayout)
        for (i in gameState.indices) {
            gameState[i] = 2
        }
        linearLayout.visibility = View.INVISIBLE
        for (i in 0 until gridLayout.childCount) {
            (gridLayout.getChildAt(i) as ImageView).setImageResource(0) //
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}