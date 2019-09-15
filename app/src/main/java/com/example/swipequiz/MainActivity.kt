package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_place.*

class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }



    private fun initView() {
        rvSwipeQuiz.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvSwipeQuiz.adapter = questionAdapter

        for (i in Question.QUIZ_QUESTIONS.indices) {
            questions.add(Question(Question.QUIZ_QUESTIONS[i]))
            rvSwipeQuiz.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }

        createItemTouchHelper().attachToRecyclerView(rvSwipeQuiz)
    }

    private fun checkAnswer(position: Int, answer: String) {
        if(answer === Question.QUIZ_ANSWERS[position]) {
            onCorrectAnswer()
        } else {
            onIncorrectAnswer()
        }
    }

    private fun onCorrectAnswer() {
        Snackbar.make(tvQuestion, "Correct", Snackbar.LENGTH_SHORT).show()
    }

    private fun onIncorrectAnswer() {
        Snackbar.make(tvQuestion, "Incorrect", Snackbar.LENGTH_SHORT).show()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if(direction == 4) {
                    checkAnswer(position, "true")
                } else {
                    checkAnswer(position, "false")
                }
                questionAdapter.notifyItemChanged(viewHolder.adapterPosition)
            }

        }
        return ItemTouchHelper(callback)
    }
}
