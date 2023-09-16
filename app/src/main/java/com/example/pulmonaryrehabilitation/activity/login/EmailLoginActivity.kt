package com.example.pulmonaryrehabilitation.activity.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.dashboard.DashboardActivity
import com.example.pulmonaryrehabilitation.activity.questionnaire.mMRCActivity
import com.example.pulmonaryrehabilitation.database.DatabaseMethod
import com.example.pulmonaryrehabilitation.member.CurrentUser
import com.google.firebase.auth.FirebaseAuth

class EmailLoginActivity : AppCompatActivity() {
    var email = ""
    var password = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)
        // hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }
        // variables achieved from user input
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.LogIn)
        val register = findViewById<TextView>(R.id.register)

        // transitioning from EmailLoginActivity to EmailRegisterActivity when click register
        register.setOnClickListener {
            val intent = Intent(this, EmailRegisterActivity :: class.java)
            startActivity(intent)
            finish()
        }

        // read the input from email and password EditText with authentication
        loginButton.setOnClickListener {
            email = emailEditText.text.toString()
            password = passwordEditText.text.toString()
//            successfully get input from email and password from front end (need authentication from back end)
            authentication(email, password)
        }
    }

    // passed manual testing
    // check if the user's input if it already on the database, if so allow users to sign in
    private fun authentication(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            // log in sing firebase
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    // If log in  was successfully done
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this, "You are logged in successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        DatabaseMethod().getUserDataFor(CurrentUser.getUserId()).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("EmailLoginActivity", "user firstname - " + CurrentUser.getFirstName())

                                val newUser = intent.getBooleanExtra("new", false)
                                Log.d("EmailLoginActivity", "Is this a new user - $newUser")

                                if (newUser) {
                                    Log.d("EmailLoginActivity", "intent to mMRC")
                                    val intent1 = Intent(this@EmailLoginActivity, mMRCActivity :: class.java)
                                    startActivity(intent1)
                                    finish()
                                } else {
                                    // login is successful takes user to main menu
                                    val intent = Intent(this@EmailLoginActivity, DashboardActivity :: class.java)
                                    // gets rid of extra layer of activities in stack
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    // send user inputs to dashboard activity
                                    intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                    intent.putExtra("email_id", email)
                                    // transitioning to dashboard activity
                                    startActivity(intent)
                                    finish()
                                }
                            }
                        }
                    } else {
                        // If log in was not successful shows error message
                        Toast.makeText(
                            this, task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
        }
    }
}