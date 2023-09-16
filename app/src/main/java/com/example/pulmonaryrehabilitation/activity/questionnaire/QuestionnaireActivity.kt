package com.example.pulmonaryrehabilitation.activity.questionnaire

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.dashboard.DashboardActivity
import com.example.pulmonaryrehabilitation.member.CurrentUser

class QuestionnaireActivity : AppCompatActivity() {
    private val LOG_TAG = "QuestionnaireActivity"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire2)
        Log.d(LOG_TAG, CurrentUser.toString())

        val submitAnswer = findViewById<Button>(R.id.submitQuestionnaire)
        val answerGroup = findViewById<RadioGroup>(R.id.answer_group)

        submitAnswer.setOnClickListener {
            val checkedAnswerID = answerGroup.checkedRadioButtonId
            val checkedAnswer = findViewById<RadioButton>(checkedAnswerID)
            val answerString = "${checkedAnswer.text}"
            addAnswerToFirebaseDB(answerString)

            val intent = Intent(this, DashboardActivity :: class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun addAnswerToFirebaseDB(answer: String) {
        val question = "Please rate your satisfaction with the exercises"
        CurrentUser.addQuestionnaireHistory(question, answer)
        Log.d(LOG_TAG, "current questionnaire history - " + CurrentUser.getQuestionnaireHistory().toString())
    }
}