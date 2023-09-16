package com.example.pulmonaryrehabilitation.activity.dashboard

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.ExercisePlayerActivity
import com.example.pulmonaryrehabilitation.activity.login.EmailLoginActivity
import com.example.pulmonaryrehabilitation.activity.questionnaire.CATActivity
import com.example.pulmonaryrehabilitation.activity.questionnaire.mMRCActivity
import com.example.pulmonaryrehabilitation.activity.streaks.StreakActivity
import com.example.pulmonaryrehabilitation.member.CurrentUser
import com.example.pulmonaryrehabilitation.pdf.PDFGenerator
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity(), SensorEventListener {
    private val LOG_TAG = "DashboardActivity"
    lateinit var exerciseCollection: ImageView
    lateinit var streakTextView: TextView
    lateinit var stepCountTextView: TextView
    lateinit var stepGoalTextView: TextView
    lateinit var diseaseTextView: TextView
    lateinit var collectionNameTextView: TextView
    lateinit var collectionDescriptionTextView: TextView
    lateinit var collectionTimeTextView: TextView
    lateinit var collectionView: ConstraintLayout
    lateinit var messageTextView: TextView
//    lateinit var menuImageView: ImageView
    private var sensorManager: SensorManager? = null
    private var isRunning = false
    private var totalSteps = 0f
    private var previousTotalSteps = 0f
    private var previousLastLoginDate = ""
    lateinit var createPDFReport: ImageView
    lateinit var spinner: Spinner

    @RequiresApi(Build.VERSION_CODES.Q)
    lateinit var streakBlock: ImageView
    private val questionnaireLimitForDaysNotDone: Long = 7
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }
        val currentUserId = CurrentUser.getUserId()
        Log.d("DashBoardActivity", currentUserId)

        setContentView(R.layout.activity_dashboard2)

        exerciseCollection = findViewById(R.id.greenBackground)
        createPDFReport = findViewById(R.id.brownBackground)
        exerciseCollection.setOnClickListener {
            if (CurrentUser.daysSinceLastQuestionnaire(CurrentUser.getLastCATmMRCDate(), questionnaireLimitForDaysNotDone) && CurrentUser.getIsmMRCNext()) {
                goTomMRC()
            } else if (CurrentUser.daysSinceLastQuestionnaire(CurrentUser.getLastCATmMRCDate(), questionnaireLimitForDaysNotDone) && !CurrentUser.getIsmMRCNext()) {
                goToCAT()
            } else {
                goToExerciseCollection()
            }
        }
        createPDFReport.setOnClickListener {
            val pdf = PDFGenerator
            pdf.createPDF().addOnSuccessListener {
                Log.i(LOG_TAG, "Report Generated Successfully: ${PDFGenerator.stringFilePath}")
                Toast.makeText(this, "Report Generated Successfully: ${PDFGenerator.stringFilePath}", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { exception ->
                Log.e(LOG_TAG, "Error creating PDF report ${exception.message}")
                Toast.makeText(this, "Error creating PDF report ${exception.message}", Toast.LENGTH_LONG).show()
            }
        }
        streakTextView = findViewById(R.id.dashboardStreakTextView)
        streakBlock = findViewById(R.id.streakBlock)
        streakBlock.setOnClickListener {
            goToBadgeView()
        }
        stepCountTextView = findViewById(R.id.dashboardStepsTextView)
        stepGoalTextView = findViewById(R.id.dashboardStepsGoalTextView)
        diseaseTextView = findViewById(R.id.dashboardDiseaseTextView)
        collectionNameTextView = findViewById(R.id.dashboardCollectionNameTextView)
        collectionDescriptionTextView = findViewById(R.id.dashboardCollectionDescription)
        collectionTimeTextView = findViewById(R.id.dashboardCollectionTime)
        messageTextView = findViewById(R.id.dashboardWelcomeMessage)
//        menuImageView = findViewById(R.id.dashboardKebabMenu)
//        menuImageView.setOnClickListener {
//            goToMenu()
//        }

        collectionView = findViewById(R.id.dashboardCollection)

        // Checks if the permission is granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACTIVITY_RECOGNITION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            Toast.makeText(this, "Step counter feature cannot work without allowing permissions", Toast.LENGTH_SHORT).show()
        }

        // If permission isn't already granted, request the permission
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION),
            100
        )

        // show users steps and streaks
        initializeWelcomeMessage()
        initializeStreaks()
        initializeCollection()

        loadData()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        spinner = findViewById(R.id.dashboardKebabMenu)
        val items = arrayOf("", "Logout")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // handle item selection
                val selectedItem = parent.getItemAtPosition(position) as String
                if (selectedItem == "Logout") {
                    logoutToLogin()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // handle nothing selected
            }
        }
    }

    /**
     * This function displays a welcome text followed by the current user's username
     */
    fun initializeWelcomeMessage() {
        messageTextView.text = "Welcome, ${CurrentUser.getUsername()}"
    }

    /**
     * Display user streaks on Dashboard
     */
    fun initializeStreaks() {
        val userStreaks: String = CurrentUser.getStreak()
        streakTextView.text = userStreaks
    }

    /*
    Grab the collection information and populate the fields
     */
    fun initializeCollection() {
        diseaseTextView.text = "COPD"
        collectionNameTextView.text = "Week 2: Day 1"
        collectionDescriptionTextView.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed congue fermentum libero, et accumsan nisi laoreet in. Vestibulum tempor, quam vel ultrices ornare."
        collectionTimeTextView.text = "37m"
    }

    /*
    If there is no collection to do (for example they have already done one today) you can hide this
     */
    fun setCollectionVisibility(isVisible: Boolean) {
        collectionView.isVisible = isVisible
    }
    /*
        This will grab the exercise collection and send it to the exercise player
     */
    fun goToExerciseCollection() {
//        Log.i(LOG_TAG, "Intent to ExercisePlayerTapViewActivity")
        Log.i(LOG_TAG, "Intent to ExercisePlayerActivity")
//        val intent = Intent(this, ExercisePlayerTapViewActivity :: class.java)
//        CurrentCollection.resetCurrentCollection()
        val intent = Intent(this, ExercisePlayerActivity :: class.java)
        startActivity(intent)
    }

    /*
        This will intent to mMRC
     */
    fun goTomMRC() {
        Log.i(LOG_TAG, "Intent to mMRCActivity")
        val intent = Intent(this, mMRCActivity :: class.java)
        startActivity(intent)
    }

    /*
        This will intent to CAT
     */
    fun goToCAT() {
        Log.i(LOG_TAG, "Intent to CATActivity")
        val intent = Intent(this, CATActivity :: class.java)
        startActivity(intent)
    }

    /*

     */
    fun goToMenu() {
        Log.d(LOG_TAG, "Menu")
        // TODO
    }

    fun goToBadgeView() {
        val intent = Intent(this@DashboardActivity, StreakActivity :: class.java)
        startActivity(intent)
    }

    fun logoutToLogin() {
        Log.d(LOG_TAG, "Logout")
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, EmailLoginActivity :: class.java)
        startActivity(intent)
        finish()
    }
    override fun onBackPressed() {
        val intent = Intent()
        intent.action = Intent.ACTION_MAIN
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
    }

    /**
     * This function is called when the activity is resumed and sets the 'isRunning' flag to true.
     * It checks if the device has a step counter sensor, and if so, registers this activity as a
     * listener for updates from the sensor. If there is no sensor detected, it displays a toast
     * message to the user.
     *
     * Precondition:
     *  sensorManager should not be null
     *  device should have a sensor available
     *
     * Post-condition:
     *  isRunning flag should be set to true
     *  onSensorChanged() should be called with updates from the sensor
     *  if no step count sensor is available, a toast message will be displayed to the phone
     */
    override fun onResume() {
        Log.d("StepCounterActivity", "onResume() invoked")
        super.onResume()
        isRunning = true
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor == null) {
            // This will give a toast message to the user if there is no sensor in the device
            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show()
        } else {
            // Rate suitable for the user interface
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    /**
     * This function is called whenever there is an update from the step counter sensor. It
     * calculates the current number of steps taken since the last update and displays this value on
     * the dashboard screen.
     *
     * @param event The SensorEvent object containing the sensor data.
     *
     * Precondition:
     *  The 'isRunning' flag should be true.
     *
     * Post-condition:
     *  The 'stepsTaken' TextView should display the current number of steps taken since the last
     *  update.
     */
    override fun onSensorChanged(event: SensorEvent?) {
        Log.d("StepCounterActivity", "onSensorChanged() invoked")
        var stepsTaken = findViewById<TextView>(R.id.dashboardStepsTextView)

        if (isRunning) {
            totalSteps = event!!.values[0]

            // Current steps are calculated by the difference of total steps and previous steps
            val currentSteps = totalSteps.toInt() - previousTotalSteps.toInt()

            // It will show the current steps to the dashboard
            stepsTaken.text = ("$currentSteps")

            // if the previous login date is not the same as today, then that means we are on a new
            // day and can reset the step counter
            if (previousLastLoginDate != CurrentUser.getCurrentDate()) {
                resetSteps()
            }
        }
    }

    /**
     * When called, upload the current step counter to the database, and then reset the counter to 0
     *
     * Post-condition:
     *  Write to the database in 'stepHistory' before setting the 'stepsTaken' to 0
     *  Resets the number of steps the user has taken to 0
     */
    private fun resetSteps() {
        Log.d("StepCounterActivity", "resetSteps() invoked")
        val stepsTaken = findViewById<TextView>(R.id.dashboardStepsTextView)
        CurrentUser.addStepHistory(stepsTaken.text.toString())

        previousTotalSteps = totalSteps
        previousLastLoginDate = CurrentUser.getCurrentDate()
        stepsTaken.text = "0"

        saveData()
    }

    /**
     * Uses the SharedPreferences API to store the previous total number of steps taken by the user.
     *
     * Precondition:
     * The 'previousTotalSteps' variable should contain a valid float value representing the
     * previous total number of steps taken by the user.
     *
     * Post-condition:
     * The 'previousTotalSteps' value should be saved to persistent storage using the
     * SharedPreferences API.
     */
    private fun saveData() {
        Log.d("StepCounterActivity", "saveData() invoked")
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Save the current step count as a float value in "stepKey" key
        editor.putFloat("stepKey", previousTotalSteps)

        // Save the current date/time as a string value in the "lastSaveDateKey" key
        editor.putString("lastSaveDateKey", CurrentUser.getCurrentDate())

        editor.apply()
    }

    /**
     * Loads previously saved step count and last login date/time from SharedPreferences.
     * The step count is saved under the key "stepKey" and the last login date/time is saved under
     * the key "lastSaveDateKey". If there is no previously saved data for the last login date/time,
     * the previousLastLoginDate variable is set to null.
     *
     * Precondition:
     *  The SharedPreferences file named "myPrefs" should exist and contain a float value associated
     *   with the "stepKey" key, and a
     *
     * Post-condition:
     *  The 'previousTotalSteps' variable should contain the float value retrieved from the "key1"
     *  key in the "myPrefs" SharedPreferences file. If the SharedPreferences file or key does not
     *  exist, the 'previousTotalSteps' variable will be initialized to 0.0.
     */
    private fun loadData() {
        Log.d("StepCounterActivity", "loadData() invoked")
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences.getFloat("stepKey", 0f)

        // Load the last save date/time string value from the "lastSaveDate" key
        val lastSaveDate = sharedPreferences.getString("lastSaveDateKey", null)

        previousTotalSteps = savedNumber
        if (lastSaveDate != null) {
            previousLastLoginDate = lastSaveDate
        }
    }

    /**
     * A function that is required for using SensorEventListener
     */
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // We do not have to write anything in this function for this app
    }
}