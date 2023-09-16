package com.example.pulmonaryrehabilitation.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.dashboard.BadgeActivity
import com.example.pulmonaryrehabilitation.activity.dashboard.DashboardActivity
import com.example.pulmonaryrehabilitation.activity.questionnaire.QuestionnaireActivity
import com.example.pulmonaryrehabilitation.exerciseNew.Activity
import com.example.pulmonaryrehabilitation.exerciseNew.CurrentCollection
import com.example.pulmonaryrehabilitation.exerciseNew.Exercise
import com.example.pulmonaryrehabilitation.member.CurrentUser
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlin.math.abs

class ExercisePlayerActivity : AppCompatActivity() {

    val secondInMiliseconds: Long = 1000

    private val questionnaireLimitForDaysNotDone: Long = 3

    private lateinit var timer: CountDownTimer
    private lateinit var detector: GestureDetectorCompat
    private var timerRunning: Boolean = false
    lateinit var exerciseName: TextView
    lateinit var exerciseInstruction: TextView
    lateinit var progressBar: ProgressBar
    lateinit var youTubePlayerView: YouTubePlayerView
    lateinit var youtubePlayer: YouTubePlayer
    lateinit var activityName: TextView
    lateinit var timeLeftTextView: TextView
    lateinit var timerStartButton: Button
    lateinit var timerResetButton: Button
    lateinit var timerStopButton: Button
    lateinit var timerContinueButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Exercise Player", "Timer Step Started")

        setContentView(R.layout.activity_exercise_player)
        detector = GestureDetectorCompat(this, SwipeListener()) // detects user swipes
        // detector functionality comes from the innerclass SwipeListener()

        // Initialize views
        exerciseName = findViewById(R.id.exerciseName)
        exerciseInstruction = findViewById(R.id.exerciseInstruction)
        progressBar = findViewById(R.id.timerProgressBar)
        youTubePlayerView = findViewById(R.id.youtube_player_view)
        activityName = findViewById(R.id.activityName)
        timeLeftTextView = findViewById(R.id.timeLeftTextView)
        timerStartButton = findViewById(R.id.timerStartButton)
        timerResetButton = findViewById(R.id.timerResetButton)
        timerStopButton = findViewById(R.id.timerStopButton)
        timerContinueButton = findViewById(R.id.timerContinueButton)

        timerStartButton.setOnClickListener {
            startTimer()
            timerRunning = true
            updateProgressBarWithData()
        }
        timerResetButton.setOnClickListener {
            if (timerRunning) {
                timer.cancel()
                timerRunning = false
            }
            updateProgressBarWithData()
            timeLeftTextView.text =
                (getCurrentExercise()!!.duration - progressBar.progress).toString()
        }
        timerStopButton.setOnClickListener {
            if (timerRunning) {
                timer.cancel()
                timerRunning = false
            }
        }
        timerContinueButton.setOnClickListener {
            continueTimer()
            timerRunning = true
        }

        startStep()
    }

    fun continueTimer() {
        if (!timerRunning) {
            timer = object : CountDownTimer(getCurrentExercise()!!.duration.toLong() * secondInMiliseconds, secondInMiliseconds) {
                override fun onTick(millisUntilFinished: Long) {
                    // update the progress bar
                    progressBar.progress++
                    timeLeftTextView.text = (getCurrentExercise()!!.duration - progressBar.progress).toString()
                    if (progressBar.progress == getCurrentExercise()!!.duration) {
                        endExercise()
                    }
                }

                override fun onFinish() {
                }
            }.start()
        }
    }

    fun startTimer() {
        if (!timerRunning) {
            timer = object : CountDownTimer(getCurrentExercise()!!.duration.toLong() * secondInMiliseconds, secondInMiliseconds) {
                override fun onTick(millisUntilFinished: Long) {
                    // update the progress bar
                    progressBar.progress++
                    timeLeftTextView.text = (getCurrentExercise()!!.duration - progressBar.progress).toString()
                }

                override fun onFinish() {
                    endExercise()
                }
            }.start()
        }
    }

    private fun playVideo(videoID: String) {
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoID, 0F)
            }
        })
    }

    /*
    onTouchEvent Specification
    This overrides the default onTouchEvent to add swipe detection
    If the interaction wasn't a swipe it defaults to the built in function
    Pre-Condition: A user touch event
    Post-Condition: Boolean that signifies the input was successfully recieved
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            if (detector.onTouchEvent(event)) {
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    /*
    startStep Specification
    This grabs the data from the current step and displays it via the textfields and progress bar
    It then double checks that this is a timer step and then starts the timer
    Pre-Condition: The current step is a Timer Step
    Post-Condition: Update the UI and Start the timer
     */
    fun startStep() {
        CurrentCollection.resetCurrentCollection()
        updateLabelsWithData()
        updateProgressBarWithData()
        val exercise = getCurrentExercise()
        if (exercise?.javaClass?.kotlin == Exercise::class) {
            CurrentCollection.data.getCurrentActivity()?.getCurrentExercise()?.videoSrc?.let {
                playVideo(
                    it
                )
            }
        }
    }

    /*
    endStep Specification
    This decides what to do after the step is completed
    Pre-Condition: Timer ended
    Post-Condition: Goes to next step, sees if it's a timer step or a tap step
    If a tap set it goes to the Tap Activity
    If not, it refreshes the current view with the new data
     */
    fun endExercise() {
        val activity = getCurrentActivity()
        if (activity != null) {
            if (activity.currentExercise == activity.exerciseList.lastIndex) {
                CurrentCollection.data.goToNextActivity()
            } else {
                CurrentCollection.data.getCurrentActivity()?.goToNextExercise()
            }
        }

        changeExercise()
    }

    /*
    changeStep Specification
    Looks at the current step and decides if it needs to change activities or refresh the current one
    Pre-Condition: None
    Post-Condition: Show the next step in the current activity or the appropriate one
     */
    fun changeExercise() {
        // stop the video and timer in case they're still running
        if (timerRunning) {
            timer.cancel()
            timerRunning = false
            updateProgressBarWithData()
            timeLeftTextView.text = (getCurrentExercise()!!.duration - progressBar.progress).toString()
        }

        val exercise = getCurrentExercise()
        if (exercise == null && CurrentUser.daysSinceLastQuestionnaire(
                CurrentUser.getLastQuestionnaireDate(),
                questionnaireLimitForDaysNotDone
            )
        ) {
            Log.i("Change Step", "No new step, end routine (From timer step)")

            CurrentCollection.data.name?.let { CurrentUser.addUsageHistory(it) }
            // Saves the collection name in Firebase when finished
            val intent = Intent(this@ExercisePlayerActivity, QuestionnaireActivity::class.java)
            startActivity(intent)
            finish()
        } else if (exercise == null && !(
            CurrentUser.daysSinceLastQuestionnaire(
                    CurrentUser.getLastQuestionnaireDate(),
                    questionnaireLimitForDaysNotDone
                )
            )
        ) {
            Log.i("Change Step", "No new step, end routine (From timer step)")
            CurrentUser.updateStreakAndPoint()
            CurrentCollection.data.name?.let { CurrentUser.addUsageHistory(it) }
            if (CurrentUser.getWeeklyExercisePoint() == "3" && (CurrentUser.getStreak() == "1" || CurrentUser.getStreak() == "2" || CurrentUser.getStreak() == "4" || CurrentUser.getStreak() == "8" || CurrentUser.getStreak() == "16")) {
                val intent = Intent(this@ExercisePlayerActivity, BadgeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                CurrentUser.updateStreakAndPoint()
                val intent =
                    Intent(this@ExercisePlayerActivity, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            Log.i("Change Step", "New step is timer step (Currently timer step)")
            startStep()
        }
    }
    /*
     */

    /*
    updateLabelsWithData Specification
    Pre-Condition: None
    Post-Condition: Updates the labels in the view with the current steps data
     */
    private fun updateLabelsWithData() {
        val exercise = getCurrentExercise()
        exerciseName.text = exercise?.name
        exerciseInstruction.text = exercise?.instruction
        activityName.text = CurrentCollection.data.getCurrentActivity()?.name
        timeLeftTextView.text = exercise?.duration.toString()
    }

    /*
    updateProgressBarWithData Specification
    Pre-Condition: None
    Post-Condition: Changes the maximum of the progress bar to the duration of the exercise
     */
    private fun updateProgressBarWithData() {
        val exercise = getCurrentExercise()
        if (exercise?.javaClass?.kotlin == Exercise::class) {
            progressBar.max = exercise.duration
            progressBar.progress = 0
        }
    }

    /*
    getCurrentStep Specification
    Provides a shortcut to the current step so no need to navigate through the objects
    Pre-Condition: None
    Post-Condition: Returns null (if no current step) or the current step
     */
    private fun getCurrentExercise(): Exercise? {
//        return ExercisePlayerObject.exercise.exerciseRoutine.getCurrentExercise()?.getCurrentStep()
        return CurrentCollection.data.getCurrentActivity()?.getCurrentExercise()
    }

    /*
 getCurrentStep Specification
 Provides a shortcut to the current step so no need to navigate through the objects
 Pre-Condition: None
 Post-Condition: Returns null (if no current step) or the current step
  */
    private fun getCurrentActivity(): Activity? {
//        return ExercisePlayerObject.exercise.exerciseRoutine.getCurrentExercise()?.getCurrentStep()
        return CurrentCollection.data.getCurrentActivity()
    }

    /*
    swipeRight/swipeLeft Specifications
    Goes to the previous or next step respectively
    Pre-Condition: None
    Post-Condition: Changes the current step and updates the UI
     */
    fun swipeRight() {
        Log.i("User Interaction", "Swipe to previous step (TimerExercisePlayer)")
        val activity = getCurrentActivity()
        if (activity != null) {
            if (activity.currentExercise == 0) {
                CurrentCollection.data.goToPreviousActivity()
            } else {
                CurrentCollection.data.getCurrentActivity()?.goToPreviousExercise()
            }
        }
        changeExercise()
    }

    fun swipeLeft() {
        Log.i("User Interaction", "Swipe to next step (TimerExercisePlayer)")
        val activity = getCurrentActivity()
        if (activity != null) {
            if (activity.currentExercise == activity.exerciseList.lastIndex) {
                CurrentCollection.data.goToNextActivity()
            } else {
                CurrentCollection.data.getCurrentActivity()?.goToNextExercise()
            }
        }
        changeExercise()
    }

    /*
    SwipeListener Specification
    This is an inner class whose sole purpose is to add right and left swipes to the activity
     */
    inner class SwipeListener : GestureDetector.SimpleOnGestureListener() {
        private val movementThreshold = 100
        override fun onFling(
            downEvent: MotionEvent,
            moveEvent: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val diffX = moveEvent.x.minus(downEvent.x)
            val diffY = moveEvent.y.minus(downEvent.y)

            if (abs(diffX) > abs(diffY)) {
                // left or right swipe
                if (abs(diffX) > movementThreshold) { // 100 is the threshold for movement
                    if (diffX > 0) {
                        // right swipe
                        this@ExercisePlayerActivity.swipeRight()
                    } else {
                        // left swipe
                        this@ExercisePlayerActivity.swipeLeft()
                    }
                    Log.i("Swipe Gesture", "Successful Swipe Gesture")
                } else {
                    Log.i("Swipe Gesture", "Swipe was not long enough (${Math.abs(diffX)})")
                    return super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }
            return super.onFling(downEvent, moveEvent, velocityX, velocityY)
        }
    }

    override fun onDestroy() {
        Log.i("ExercisePLayerActivity", "onDestroy")
        super.onDestroy()
        youTubePlayerView.release()
    }
}