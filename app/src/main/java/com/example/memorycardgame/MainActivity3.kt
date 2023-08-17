package com.example.memorycardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {

    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<CardState>
    private var indexSelectedCard: Int? = null
    //set variables for Timer function:

    var number = 0
    var runnable: Runnable = Runnable { }
    var handler = Handler(Looper.myLooper()!!)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val button31 = findViewById<ImageButton>(R.id.imageButton11)
        val button32 = findViewById<ImageButton>(R.id.imageButton12)
        val button33 = findViewById<ImageButton>(R.id.imageButton13)
        val button34 = findViewById<ImageButton>(R.id.imageButton14)
        val button35 = findViewById<ImageButton>(R.id.imageButton15)
        val button36 = findViewById<ImageButton>(R.id.imageButton16)

//
        val images = mutableListOf(
            R.drawable.ic_baseline_5g_24, R.drawable.ic_baseline_cloud_queue_24,
            R.drawable.ic_baseline_do_not_disturb_on_24
        )

        images.addAll(images)
        images.shuffle()


        buttons = listOf(
            button31, button32, button33, button34, button35, button36,

        )

        cards = buttons.indices.map { index ->
            CardState(images[index])
        }

        startTimer()
        buttons.forEachIndexed { index, imageButton ->
            imageButton.setOnClickListener {

                updateStateOnCard(index)
                updateViewsOnClick()

               // Toast.makeText(this, "try $sum times!", Toast.LENGTH_SHORT).show()
            }



        }

    }
    private fun updateStateOnCard(position: Int) {

        val card=cards[position]

        if (card.isFaceUp){
            Toast.makeText(this,"Invalid move!", Toast.LENGTH_SHORT).show()

            return
        }
        if (indexSelectedCard==null){
            restoreCards()
            indexSelectedCard=position

        }else{
            checkForMatch(indexSelectedCard!!,position)
            indexSelectedCard=null

        }

        card.isFaceUp=!card.isFaceUp


    }

    private fun restoreCards() {
        for (card in cards){
            if (!card.isMatched){
                card.isFaceUp=false
            }
        }
    }

    private fun checkForMatch(position1: Int,position2: Int) {
        if (cards[position1].identifier==cards[position2].identifier){
            cards[position1].isMatched=true
            cards[position2].isMatched=true
        }

    }

    private  fun updateViewsOnClick() {
        var counter=0
        var sumClick=0
        cards.forEachIndexed{index, card ->
            val imageButton=buttons[index]
            if (card.isMatched){
                imageButton.alpha=0.0f
                counter++
                sumClick++
            }
            if (card.isFaceUp){
                imageButton.setImageResource(card.identifier)
                sumClick++
            }else{
                imageButton.setImageResource(R.drawable.ic_baseline_grid_4x4_24 )
                sumClick++
            }
        }

        if (counter==cards.size){
            stopTimer()
            val sum=sumClick/2
            Toast.makeText(this, "You try : $sum times!", Toast.LENGTH_SHORT).show()
        }



    }
    private fun startTimer(){
        runnable= Runnable {
            number ++
            handler.postDelayed(runnable,1000)
        }
        handler.post(runnable)
    }
    private fun stopTimer(){
        handler.removeCallbacks(runnable)
        Toast.makeText(this, "Time took: $number seconds!", Toast.LENGTH_LONG).show()
    }

    }

