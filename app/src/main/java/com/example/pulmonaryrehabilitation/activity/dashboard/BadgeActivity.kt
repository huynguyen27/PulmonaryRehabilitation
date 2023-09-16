package com.example.pulmonaryrehabilitation.activity.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.member.CurrentUser

class BadgeActivity : AppCompatActivity() {
    lateinit var badgeTextView: TextView
    lateinit var badgeImageView: ImageView
    lateinit var toDashboardButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge)

        badgeTextView = findViewById(R.id.streakTextView)
        badgeImageView = findViewById(R.id.badgeImageView)
        toDashboardButton = findViewById(R.id.toDashboardButton)

        badgeTextView.text = CurrentUser.getStreak() // ... to be continue
//        badgeImageView // ... set image according to streak, streak 1, streak 2, streak 4, streak 8 has different badge
        // if (CurrentUser.getStreak() == "1" --> draw appropriate image
        if (CurrentUser.getStreak() == "1") {
            badgeImageView.setImageResource(R.drawable.medal_streak_1)
        } else if (CurrentUser.getStreak() == "2") {
            badgeImageView.setImageResource(R.drawable.medal_streak_2)
        } else if (CurrentUser.getStreak() == "4") {
            badgeImageView.setImageResource(R.drawable.medal_streak_4)
        } else if (CurrentUser.getStreak() == "8") {
            badgeImageView.setImageResource(R.drawable.medal_streak_8)
        } else if (CurrentUser.getStreak() == "16") {
            badgeImageView.setImageResource(R.drawable.medal_streak_16)
        } else {
            badgeImageView.setImageResource(R.drawable.ic_outlet_24)
        }

        badgeImageView.background = R.drawable.fire.toDrawable()
        toDashboardButton.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}