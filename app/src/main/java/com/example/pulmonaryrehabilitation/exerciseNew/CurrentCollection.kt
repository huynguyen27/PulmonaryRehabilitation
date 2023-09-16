package com.example.pulmonaryrehabilitation.exerciseNew

import android.util.Log
import com.example.pulmonaryrehabilitation.member.CurrentUser
import java.time.Instant
import java.time.LocalDate

object CurrentCollection {
    lateinit var data: Collection

    val breathingExercise = mutableListOf<Exercise>() // breathing
    val warmupExercise = mutableListOf<Exercise>() // warm up
    val supersetExercise = mutableListOf<Exercise>() // super set
    val cooldownExercise = mutableListOf<Exercise>() // cool-down

    val breathingExerciseList = mutableListOf<ExerciseList>() // breathing Exercise List
    val warmupExerciseList = mutableListOf<ExerciseList>() // warm up Exercise List
    val supersetExerciseList = mutableListOf<ExerciseList>() // super set Exercise List
    val cooldownExerciseList = mutableListOf<ExerciseList>() // cool-down Exercise List

    val breathingActivity = mutableListOf<Activity>() // breathing
    val warmupActivity = mutableListOf<Activity>() // warm up
    val enduranceActivity = mutableListOf<Activity>() // endurance super set
    val strengthActivity = mutableListOf<Activity>() // strength super set
    val cooldownActivity = mutableListOf<Activity>() // cool-down

    val satActivityList = mutableListOf<ActivityList>() // breathing Activity List
    val monWedFriActivityList = mutableListOf<ActivityList>() // 2/4/6 Activity List
    val tueThuActivityList = mutableListOf<ActivityList>() // 3/5 Activity List

    val satCollection = mutableListOf<Collection>() // breathing Collection List
    val monWedFriCollection = mutableListOf<Collection>() // 2/4/6 Collection List
    val tueThuCollection = mutableListOf<Collection>() // 3/5 Collection List

    /*
    resetCurrentCollection Specification
    This function resets the data exercise collection
    Pre-Condition: None
    Post-Condition: None
    */
    fun resetCurrentCollection() {
        data = getTodayCollection()!!
        // if the user finished every exercises in that day
        if (data.getCurrentActivity()?.getCurrentExercise() == null) {
            data.resetStacks()
        }
    }

    /*
    getTodayCollection Specification
    This function returns the collection of exercises based on user's current week
    counting from the first week (first time the user finish an exercise)
    Pre-Condition: None
    Post-Condition: None
    */
    fun getTodayCollection(): Collection? {
        Log.d("CurrentCollection", "CurrentCollection.getTodayCollection() invoked")
        var currentWeek = getCurrentWeekNumber()
        val currentDayOfWeek = LocalDate.now().dayOfWeek.value

        // if more than 12 week then reset exercise schedule
        if (currentWeek > 12) {
            currentWeek %= 12
        }

        when (currentDayOfWeek) {
            // endurance collection on Monday/Wednesday/Friday
            1, 3, 5 -> return monWedFriCollection[currentWeek - 1]
            // strength collection on Tuesday/Thursday
            2, 4 -> return tueThuCollection[currentWeek - 1]
            // breath collection on Saturday
            6 -> return satCollection[currentWeek - 1]
            // sunday or error -> return null, no collection
            else -> return null
        }
    }

    /*
    getCurrentWeekNumber Specification
    This function returns the current week number in respect to the first week the user finish the exercise
    Pre-Condition: None
    Post-Condition: None
    */
    fun getCurrentWeekNumber(): Int {
        return (
            getCurrentWeekMonday(LocalDate.now()).dayOfYear - getCurrentWeekMonday(
                getFirstExerciseDate()
            ).dayOfYear
            ) / 7 + 1
    }

    /*
    getCurrentWeekMonday Specification
    This function returns the current monday of the week of the input date
    Pre-Condition: date can not be null
    Post-Condition: None
    */
    fun getCurrentWeekMonday(date: LocalDate): LocalDate {
        return date.minusDays(date.dayOfWeek.value.toLong() - 1)
    }

    /*
    getFirstExerciseDate Specification
    This function returns the date of the user finished the first exercise
    Pre-Condition: None
    Post-Condition: None
    */
    fun getFirstExerciseDate(): LocalDate {
        // get the earliest usage history date
        val first = convertLongDateToStringDate(CurrentUser.getUsageHistory().keys.minOf { it })
        return LocalDate.of(
            first.substring(0, 4).toInt(),
            first.substring(5, 7).toInt(),
            first.substring(8, 10).toInt()
        )
    }

    /*
    getLastExerciseDate Specification
    This function returns the date of the user finished the last exercise
    Pre-Condition: None
    Post-Condition: None
    */
    fun getLastExerciseDate(): LocalDate {
        // get the latest usage history date
        val last = convertLongDateToStringDate(CurrentUser.getUsageHistory().keys.maxOf { it })
        return LocalDate.of(
            last.substring(0, 4).toInt(),
            last.substring(5, 7).toInt(),
            last.substring(8, 10).toInt()
        )
    }

    /*
    convertLongDateToStringDate Specification
    This function converts the input date in Long format to String format of "dd/mm/yyyy"
    Pre-Condition: date can not be null
    Post-Condition: None
    */
    fun convertLongDateToStringDate(date: String): String {
        Log.d("CurrentCollection", "CurrentCollection.convertDate() invoked - $date")
        return Instant.ofEpochMilli(date.toLong()).toString().substring(0, 10)
    }

    /*
    getExerciseById Specification
    This function the exercise based on queried id
    Pre-Condition: id can not be null
    Post-Condition: None
    */
    fun getExerciseById(id: String): Exercise? {
        Log.d("CurrentCollection", "CurrentCollection.getExerciseById() for exercise $id invoked")
        var exercises = mutableListOf<Exercise>()
        when (id.substring(0, 1)) {
            "b" -> exercises = breathingExercise
            "w" -> exercises = warmupExercise
            "e" -> exercises = supersetExercise
            "s" -> exercises = supersetExercise
            "c" -> exercises = cooldownExercise
        }
        for (ex in exercises) {
            if (ex.id == id) {
                return ex
            }
        }

        return null
    }

    /*
    getExerciseListById Specification
    This function the exercise lists based on queried id
    Pre-Condition: id can not be null
    Post-Condition: None
    */
    fun getExerciseListById(id: String): ExerciseList? {
        Log.d(
            "CurrentCollection",
            "CurrentCollection.getExerciseListById() for exerciseList $id invoked"
        )
        var exerciseList = mutableListOf<ExerciseList>()
        when (id.substring(0, 2)) {
            "bl" -> exerciseList = breathingExerciseList
            "wl" -> exerciseList = warmupExerciseList
            "el" -> exerciseList = supersetExerciseList
            "sl" -> exerciseList = supersetExerciseList
            "cl" -> exerciseList = cooldownExerciseList
        }
        for (exl in exerciseList) {
            if (exl.id == id) {
                return exl
            }
        }
        return null
    }

    /*
    getActivityById Specification
    This function the activity based on queried id
    Pre-Condition: id can not be null
    Post-Condition: None
    */
    fun getActivityById(id: String): Activity? {
        Log.d("CurrentCollection", "CurrentCollection.getActivityById() for activity $id invoked")
        var activities = mutableListOf<Activity>()
        when (id.substring(0, 2)) {
            "ba" -> activities = breathingActivity
            "wa" -> activities = warmupActivity
            "ea" -> activities = enduranceActivity
            "sa" -> activities = strengthActivity
            "ca" -> activities = cooldownActivity
        }

        for (activity in activities) {
            if (activity.id == id) {
                return activity
            }
        }
        return null
    }

    /*
    getActivityListById Specification
    This function the activity lists based on queried id
    Pre-Condition: id can not be null
    Post-Condition: None
    */
    fun getActivityListById(id: String): ActivityList? {
        Log.d(
            "CurrentCollection",
            "CurrentCollection.getActivityListById() for ActivityList $id invoked"
        )
        var activityList = mutableListOf<ActivityList>()
        when (id.substring(0, 2)) {
            "tt" -> activityList = tueThuActivityList
            "mw" -> activityList = monWedFriActivityList
            "sa" -> activityList = satActivityList
        }
        for (acl in activityList) {
            if (acl.id == id) {
                return acl
            }
        }
        return null
    }
}
