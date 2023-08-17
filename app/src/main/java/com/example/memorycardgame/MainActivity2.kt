package com.example.memorycardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.Toast


class MainActivity2 : AppCompatActivity() {

private lateinit var buttons:List<ImageButton>
private  lateinit var cards:List<CardState>
private var indexSelectedCard:Int?=null
    //set variables for Timer function:

    var number=0
    var runnable:Runnable= Runnable {  }
    var handler= Handler(Looper.myLooper()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        // declare variable for cards grid and create base logic

        val button21 = findViewById<ImageButton>(R.id.imageButton1)
        val button22 = findViewById<ImageButton>(R.id.imageButton2)
        val button23 = findViewById<ImageButton>(R.id.imageButton3)
        val button24 = findViewById<ImageButton>(R.id.imageButton4)
        val button25 = findViewById<ImageButton>(R.id.imageButton5)
        val button26 = findViewById<ImageButton>(R.id.imageButton6)
        val button27 = findViewById<ImageButton>(R.id.imageButton7)
        val button28 = findViewById<ImageButton>(R.id.imageButton8)
        val button29 = findViewById<ImageButton>(R.id.imageButton9)
        val button210 = findViewById<ImageButton>(R.id.imageButton10)

//create mutable list of image icons then shuffle this list

        val images= mutableListOf(R.drawable.ic_baseline_5g_24,R.drawable.ic_baseline_cloud_queue_24,
            R.drawable.ic_baseline_do_not_disturb_on_24,R.drawable.ic_baseline_folder_24,
            R.drawable.ic_baseline_favorite_border_24)

        images.addAll(images)
        images.shuffle()

        buttons= listOf(button21,button22,button23,button24,button25,button26,
            button27,button28,button29,button210)

              cards=buttons.indices.map { index->
            CardState(images[index])
        }
        startTimer()

       buttons.forEachIndexed { index, imageButton -> imageButton.setOnClickListener {

           updateStateOnCard(index)
           updateViewsOnClick()

           }



       }
          }




    private fun updateStateOnCard(position: Int) {

                val card=cards[position]

                if (card.isFaceUp){
                    Toast.makeText(this,"Invalid move!",Toast.LENGTH_SHORT).show()

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






