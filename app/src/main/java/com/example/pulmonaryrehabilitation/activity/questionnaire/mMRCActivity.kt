package com.example.pulmonaryrehabilitation.activity.questionnaire

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.dashboard.DashboardActivity
import com.example.pulmonaryrehabilitation.member.CurrentUser

class mMRCActivity : AppCompatActivity() {
    private val LOG_TAG = "mMRCActivity"

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mmrc_questionnaire)

        val submitAnswer = findViewById<Button>(R.id.submitQuestionnaire)
        val answerGroup = findViewById<RadioGroup>(R.id.mmrc_answer_group)

        submitAnswer.setOnClickListener {
            val checkedAnswerID = answerGroup.checkedRadioButtonId
            addAnswerToFirebaseDB(getValue(checkedAnswerID))

            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun getValue(id: Int): Int {
        Log.d(LOG_TAG, id.toString())
        return when (id) {
            R.id.zero -> return 0
            R.id.one -> return 1
            R.id.two -> return 2
            R.id.three -> return 3
            R.id.four -> return 4
            else -> -1
        }
    }

    private fun addAnswerToFirebaseDB(answer: Int) {
        CurrentUser.addmMRCHistory(answer)
    }
}