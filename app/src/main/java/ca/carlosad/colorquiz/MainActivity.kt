package ca.carlosad.colorquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.graphics.Color
import kotlin.random.Random
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    private lateinit var Score: TextView
    private lateinit var Question: TextView
    private lateinit var ButtonRight: Button
    private lateinit var ButtonLeft: Button
    private lateinit var randomtx: TextView

    private val arrayOfColors = Colors.values()
    private val sizeOfColorArray = arrayOfColors.size

    private var correctAnswer: Int = 0
    private var Scorex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Score = findViewById(R.id.Score)
        Question = findViewById(R.id.Question)
        ButtonRight = findViewById(R.id.buttonR)
        ButtonLeft = findViewById(R.id.buttonL)
        randomtx = findViewById(R.id.random)
    }


    enum class Colors {
        RED, BLUE, YELLOW, GREEN, WHITE, BLACK
    }

    fun RandomColors(c: Colors) = when (c) {
        Colors.YELLOW -> Color.YELLOW
        Colors.BLACK -> Color.BLACK
        Colors.RED -> Color.RED
        Colors.BLUE -> Color.BLUE
        Colors.GREEN -> Color.GREEN
        Colors.WHITE -> Color.WHITE


    }


    fun getRandomC() {
        val random = Random(System.currentTimeMillis())
        val ranNum1 = random.nextInt(sizeOfColorArray)
        var ranNum2: Int
        do {
            ranNum2 = random.nextInt(sizeOfColorArray)
        } while (ranNum1 == ranNum2)

        ButtonLeft.setBackgroundColor(RandomColors(arrayOfColors[ranNum1]))
        ButtonRight.setBackgroundColor(RandomColors(arrayOfColors[ranNum2]))

        correctAnswer = random.nextInt(2)
        randomtx.text =
            arrayOfColors[if (correctAnswer == 0) ranNum1 else ranNum2].name
                .replace("_", " ").capitalize()
    }

    override fun onStart() {
        super.onStart()

        initialize()

    }

    private fun initialize() {
        getRandomC()
        getScore(0)

    }

    fun resolve(view: View) {
        val chosen = when (view.id) {
            R.id.buttonL -> 0
            R.id.buttonR -> 1
            else -> -1
        }
        if (chosen == correctAnswer) {
            Toast.makeText(this, "correct!", Toast.LENGTH_SHORT).show()
            getScore(++Scorex)
        } else {
                getScore(--Scorex)
            Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show()
        }


        getRandomC()
    }

    fun getScore(s: Int) {
        Scorex = s
        Score.text = String.format("Score: %d", Scorex)

    }
}



