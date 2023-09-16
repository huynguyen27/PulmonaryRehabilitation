package com.example.pulmonaryrehabilitation.activity.questionnaire

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.dashboard.DashboardActivity
import com.example.pulmonaryrehabilitation.member.CAT
import com.example.pulmonaryrehabilitation.member.CurrentUser

class CATActivity : AppCompatActivity() {
    private val LOG_TAG = "CATActivity"

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat_questionnaire)

        val submitAnswer = findViewById<Button>(R.id.submitcat)
        val total = findViewById<TextView>(R.id.totalscore)

        val coughGroup = findViewById<RadioGroup>(R.id.coughAnswerGroup)
        val phlegmGroup = findViewById<RadioGroup>(R.id.phlegmAnswerGroup)
        val chestTightnessGroup = findViewById<RadioGroup>(R.id.chestTightnessAnswerGroup)
        val breathlessnessGroup = findViewById<RadioGroup>(R.id.breathlessnessAnswerGroup)
        val activitiesGroup = findViewById<RadioGroup>(R.id.activitiesAnswerGroup)
        val confidenceGroup = findViewById<RadioGroup>(R.id.confidenceAnswerGroup)
        val sleepGroup = findViewById<RadioGroup>(R.id.sleepAnswerGroup)
        val energyGroup = findViewById<RadioGroup>(R.id.energyAnswerGroup)

        submitAnswer.setOnClickListener {
            val checkedCoughAnswer = coughGroup.checkedRadioButtonId
            val coughAnswer = getValue(checkedCoughAnswer)
            CAT.setCough(coughAnswer)

            val checkedPhlegmAnswer = phlegmGroup.checkedRadioButtonId
            val phlegmAnswer = getValue(checkedPhlegmAnswer)
            CAT.setPhlegm(phlegmAnswer)

            val checkedChestAnswer = chestTightnessGroup.checkedRadioButtonId
            val chestAnswer = getValue(checkedChestAnswer)
            CAT.setChestTightness(chestAnswer)

            val checkedBreathAnswer = breathlessnessGroup.checkedRadioButtonId
            val breathAnswer = getValue(checkedBreathAnswer)
            CAT.setBreathlessness(breathAnswer)

            val checkedActivitiesAnswer = activitiesGroup.checkedRadioButtonId
            val activitiesAnswer = getValue(checkedActivitiesAnswer)
            CAT.setActivities(activitiesAnswer)

            val checkedConfidenceAnswer = confidenceGroup.checkedRadioButtonId
            val confidenceAnswer = getValue(checkedConfidenceAnswer)
            CAT.setConfidence(confidenceAnswer)

            val checkedSleepAnswer = sleepGroup.checkedRadioButtonId
            val sleepAnswer = getValue(checkedSleepAnswer)
            CAT.setSleep(sleepAnswer)

            val checkedEnergyAnswer = energyGroup.checkedRadioButtonId
            val energyAnswer = getValue(checkedEnergyAnswer)
            CAT.setEnergy(energyAnswer)

            addToFirebase()
            total.text = "Total Score: ${CAT.getTotal()}"

            Handler().postDelayed({
                startActivity(Intent(this@CATActivity, DashboardActivity::class.java))
            }, 2000)
        }
    }

    fun getValue(id: Int): Int {
        Log.d(LOG_TAG, "Button id: $id")
        return when (id) {
            R.id.cough0, R.id.phlegm0, R.id.chestTightness0, R.id.breathlessness0, R.id.activities0, R.id.confidence0, R.id.sleep0, R.id.energy0 -> return 0
            R.id.cough1, R.id.phlegm1, R.id.chestTightness1, R.id.breathlessness1, R.id.activities1, R.id.confidence1, R.id.sleep1, R.id.energy1 -> return 1
            R.id.cough2, R.id.phlegm2, R.id.chestTightness2, R.id.breathlessness2, R.id.activities2, R.id.confidence2, R.id.sleep2, R.id.energy2 -> return 2
            R.id.cough3, R.id.phlegm3, R.id.chestTightness3, R.id.breathlessness3, R.id.activities3, R.id.confidence3, R.id.sleep3, R.id.energy3 -> return 3
            R.id.cough4, R.id.phlegm4, R.id.chestTightness4, R.id.breathlessness4, R.id.activities4, R.id.confidence4, R.id.sleep4, R.id.energy4 -> return 4
            R.id.cough5, R.id.phlegm5, R.id.chestTightness5, R.id.breathlessness5, R.id.activities5, R.id.confidence5, R.id.sleep5, R.id.energy5 -> return 5
            else -> -1
        }
    }

    private fun addToFirebase() {
        CurrentUser.addCatHistory(CAT.getCAT())
    }
}