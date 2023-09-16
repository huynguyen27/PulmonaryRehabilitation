package com.example.pulmonaryrehabilitation.activity.splash
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.login.EmailLoginActivity
import com.example.pulmonaryrehabilitation.exerciseNew.*
import com.example.pulmonaryrehabilitation.exerciseNew.Collection
import java.io.BufferedReader
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // initialize all data
        init()

        // hide action bar
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

// transitioning from splashActivity to EmailLoginActivity with 2000ms delayed
        Handler().postDelayed({
            startActivity(Intent(this, EmailLoginActivity::class.java))
        }, 2000)
    }

    private fun init() {
        importAllExercise()
        importAllExerciseList()
        importAllActivity()
        importAllActivityList()
        importAllCollection()
//        Log.d("CurrentCollection", CurrentCollection.getTodayCollection().toString())
    }

    fun importAllExercise() {
//        Log.d("MainActivity","MainActivity.importAllExercise() - cooldown-exercise.csv")
        var bufferReader = BufferedReader(assets.open("exercise/breathing-exercise.csv").reader())
        var csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "name") {
                val exercise = Exercise(
                    id = record.get(2),
                    name = record.get(0),
                    completeStatus = record.get(3),
                    videoSrc = record.get(1),
                    description = record.get(4),
                    instruction = record.get(5),
                    duration = record.get(6).toInt()
                )
                CurrentCollection.breathingExercise.add(exercise)
                Log.d("Breathing Exercise", "$exercise")
            }
        }

//        Log.d("MainActivity","MainActivity.importAllExercise() - cooldown-exercise.csv")
        bufferReader = BufferedReader(assets.open("exercise/cooldown-exercise.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val exercise = Exercise(
                    id = record.get(0),
                    name = record.get(1),
                    completeStatus = record.get(2),
                    videoSrc = record.get(3),
                    description = record.get(4),
                    instruction = record.get(5),
                    duration = record.get(6).toInt()
                )
                CurrentCollection.cooldownExercise.add(exercise)
                Log.d("Cool-down Exercise", "$exercise")
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllExercise() - cooldown-exercise.csv")
        bufferReader = BufferedReader(assets.open("exercise/superset-exercise.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val exercise = Exercise(
                    id = record.get(0),
                    name = record.get(1),
                    completeStatus = record.get(2),
                    videoSrc = record.get(3),
                    description = record.get(4),
                    instruction = record.get(5),
                    duration = record.get(6).toInt()
                )
                CurrentCollection.supersetExercise.add(exercise)
                Log.d("Superset Exercise", "$exercise")
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllExercise() - warmup-exercise.csv")
        bufferReader = BufferedReader(assets.open("exercise/warmup-exercise.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(6) != "duration") {
                val exercise = Exercise(
                    id = record.get(2),
                    name = record.get(0),
                    completeStatus = record.get(3),
                    videoSrc = record.get(1),
                    description = record.get(4),
                    instruction = record.get(5),
                    duration = record.get(6).toInt()
                )
                CurrentCollection.warmupExercise.add(exercise)
                Log.d("Warmup Exercise", "$exercise")
            }
        }
    }

    fun importAllExerciseList() {
        //        Log.d("MainActivity","MainActivity.importAllExerciseList() - sat-activity-list.csv")
        var bufferReader =
            BufferedReader(assets.open("exerciseList/breathing-exercise-list.csv").reader())
        var csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = mutableListOf<Exercise>()
                for (i in 1..3) {
                    CurrentCollection.getExerciseById(record.get(i))?.let { list.add(it) }
                }
                val exerciseList = ExerciseList(
                    id = record.get(0),
                    exerciseList = list
                )
                CurrentCollection.breathingExerciseList.add(exerciseList)
                Log.d("Breathing ExerciseList", "$exerciseList")
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllExerciseList() - cooldown-exercise-list.csv")
        bufferReader =
            BufferedReader(assets.open("exerciseList/cooldown-exercise-list.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = mutableListOf<Exercise>()
                for (i in 1..6) {
                    CurrentCollection.getExerciseById(record.get(i))?.let { list.add(it) }
                }
                val exerciseList = ExerciseList(
                    id = record.get(0),
                    exerciseList = list
                )
                CurrentCollection.cooldownExerciseList.add(exerciseList)
                Log.d("Cooldown ExerciseList", "$exerciseList")
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllExerciseList() - superset-exercise-list.csv")
        bufferReader =
            BufferedReader(assets.open("exerciseList/superset-exercise-list.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = mutableListOf<Exercise>()
                for (i in 1..5) {
                    CurrentCollection.getExerciseById(record.get(i))?.let { list.add(it) }
                }
                val exerciseList = ExerciseList(
                    id = record.get(0),
                    exerciseList = list
                )
                CurrentCollection.supersetExerciseList.add(exerciseList)
                Log.d("SuperSet ExerciseList", "$exerciseList")
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllExerciseList() - warmup-exercise-list.csv")
        bufferReader =
            BufferedReader(assets.open("exerciseList/warmup-exercise-list.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = mutableListOf<Exercise>()
                for (i in 1..4) {
                    CurrentCollection.getExerciseById(record.get(i))?.let { list.add(it) }
                }
                val exerciseList = ExerciseList(
                    id = record.get(0),
                    exerciseList = list
                )
                CurrentCollection.warmupExerciseList.add(exerciseList)
                Log.d("Warmup ExerciseList", "$exerciseList")
            }
        }
    }

    fun importAllActivity() {
        //        Log.d("MainActivity","MainActivity.importAllExerciseList() - breathing-activity.csv")
        var bufferReader =
            BufferedReader(assets.open("activity/breathing-activity.csv").reader())
        var csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = CurrentCollection.getExerciseListById(record.get(3))?.exerciseList
                if (list != null) {
                    val activity = Activity(
                        id = record.get(0),
                        name = record.get(1),
                        activityType = record.get(2),
                        exerciseList = list,
                        completionProgress = record.get(4),
                        description = record.get(5),
                        currentExercise = record.get(6).toInt()
                    )
                    CurrentCollection.breathingActivity.add(activity)
                    Log.d("Breathing Activity", "$activity")
                }
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllExerciseList() - cooldown-activity.csv")
        bufferReader =
            BufferedReader(assets.open("activity/cooldown-activity.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = CurrentCollection.getExerciseListById(record.get(3))?.exerciseList
                if (list != null) {

                    val activity = Activity(
                        id = record.get(0),
                        name = record.get(1),
                        activityType = record.get(2),
                        exerciseList = list,
                        completionProgress = record.get(4),
                        description = record.get(5),
                        currentExercise = record.get(6).toInt()
                    )
                    CurrentCollection.cooldownActivity.add(activity)
                    Log.d("Cooldown Activity", "$activity")
                }
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllExerciseList() - endurance-activity.csv")
        bufferReader =
            BufferedReader(assets.open("activity/endurance-activity.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = CurrentCollection.getExerciseListById(record.get(3))?.exerciseList
                if (list != null) {

                    val activity = Activity(
                        id = record.get(0),
                        name = record.get(1),
                        activityType = record.get(2),
                        exerciseList = list,
                        completionProgress = record.get(4),
                        description = record.get(5),
                        currentExercise = record.get(6).toInt()
                    )
                    CurrentCollection.enduranceActivity.add(activity)
                    Log.d("Endurance Activity", "$activity")
                }
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllExerciseList() - strength-activity.csv")
        bufferReader =
            BufferedReader(assets.open("activity/strength-activity.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list =
                    CurrentCollection.getExerciseListById(record.get(3))?.exerciseList
                if (list != null) {

                    val activity = Activity(
                        id = record.get(0),
                        name = record.get(1),
                        activityType = record.get(2),
                        exerciseList = list,
                        completionProgress = record.get(4),
                        description = record.get(5),
                        currentExercise = record.get(6).toInt()
                    )
                    CurrentCollection.strengthActivity.add(activity)
                    Log.d("Strength Activity", "$activity")
                }
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllExerciseList() - warmup-activity.csv")
        bufferReader =
            BufferedReader(assets.open("activity/warmup-activity.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list =
                    CurrentCollection.getExerciseListById(record.get(3))?.exerciseList
                if (list != null) {

                    val activity = Activity(
                        id = record.get(0),
                        name = record.get(1),
                        activityType = record.get(2),
                        exerciseList = list,
                        completionProgress = record.get(4),
                        description = record.get(5),
                        currentExercise = record.get(6).toInt()
                    )
                    CurrentCollection.warmupActivity.add(activity)
                    Log.d("Warmup Activity", "$activity")
                }
            }
        }
    }

    fun importAllActivityList() {
        //        Log.d("MainActivity","MainActivity.importAllActivityList() - sat-activity-list.csv")
        var bufferReader =
            BufferedReader(assets.open("activityList/sat-activity-list.csv").reader())
        var csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = mutableListOf<Activity>()
                for (i in 1..1) {
                    CurrentCollection.getActivityById(record.get(i))?.let { list.add(it) }
                }
                val activityList = ActivityList(
                    id = record.get(0),
                    activityList = list
                )
                CurrentCollection.satActivityList.add(activityList)
                Log.d("Sat ActivityList", "$activityList")
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllActivityList() - mwf-activity-list.csv")
        bufferReader =
            BufferedReader(assets.open("activityList/mwf-activity-list.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = mutableListOf<Activity>()
                for (i in 1..4) {
                    CurrentCollection.getActivityById(record.get(i))?.let { list.add(it) }
                }
                val activityList = ActivityList(
                    id = record.get(0),
                    activityList = list
                )
                CurrentCollection.monWedFriActivityList.add(activityList)
                Log.d("MonWedFri ActivityList", "$activityList")
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllActivityList() - tt-activity-list.csv")
        bufferReader =
            BufferedReader(assets.open("activityList/tt-activity-list.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = mutableListOf<Activity>()
                for (i in 1..4) {
                    CurrentCollection.getActivityById(record.get(i))?.let { list.add(it) }
                }
                val activityList = ActivityList(
                    id = record.get(0),
                    activityList = list
                )
                CurrentCollection.tueThuActivityList.add(activityList)
                Log.d("TueThu ActivityList", "$activityList")
            }
        }
    }

    fun importAllCollection() {
        //        Log.d("MainActivity","MainActivity.importAllCollection() - mwf-collection.csv")
        var bufferReader =
            BufferedReader(assets.open("collection/mwf-collection.csv").reader())
        var csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = CurrentCollection.getActivityListById(record.get(4))?.activityList
                if (list != null) {
                    val collection = Collection(
                        id = record.get(0),
                        name = record.get(1),
                        collectionType = record.get(2),
                        completionProgress = record.get(3),
                        activityList = list
                    )
                    CurrentCollection.monWedFriCollection.add(collection)
                    Log.d("MonWedFri Collection", "$collection")
                }
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllCollection() - sat-collection.csv")
        bufferReader =
            BufferedReader(assets.open("collection/sat-collection.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = CurrentCollection.getActivityListById(record.get(4))?.activityList
                if (list != null) {
                    val collection = Collection(
                        id = record.get(0),
                        name = record.get(1),
                        collectionType = record.get(2),
                        completionProgress = record.get(3),
                        activityList = list
                    )
                    CurrentCollection.satCollection.add(collection)
                    Log.d("Sat Collection", "$collection")
                }
            }
        }

        //        Log.d("MainActivity","MainActivity.importAllCollection() - tt-collection.csv")
        bufferReader =
            BufferedReader(assets.open("collection/tt-collection.csv").reader())
        csvParser = CSVParser.parse(bufferReader, CSVFormat.DEFAULT)
        for (record in csvParser) {
            if (record.get(0) != "id") {
                val list = CurrentCollection.getActivityListById(record.get(4))?.activityList
                if (list != null) {
                    val collection = Collection(
                        id = record.get(0),
                        name = record.get(1),
                        collectionType = record.get(2),
                        completionProgress = record.get(3),
                        activityList = list
                    )
                    CurrentCollection.tueThuCollection.add(collection)
                    Log.d("Tue Collection", "$collection")
                }
            }
        }
    }
}