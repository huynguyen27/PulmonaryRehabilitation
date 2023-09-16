package com.example.pulmonaryrehabilitation.exerciseNew

import android.util.Log
import org.junit.Assert

class Activity(
    override val id: String,
    override var name: String?,
    override val activityType: String?,
    override val exerciseList: MutableList<Exercise>,
    override var completionProgress: String?,
    override var description: String?,
    var currentExercise: Int
) : ActivityInterface {

    init {
        currentExercise = 0
    }

    override fun getCurrentExercise(): Exercise? {
        Log.d("LOG_TAG", "Activity.getCurrentExercise() invoked")
        return exerciseList[currentExercise]
    }

    override fun goToNextExercise(): Exercise? {
        Log.d("LOG_TAG", "Activity.goToNextExercise() invoked")
        currentExercise++
        if (currentExercise <= exerciseList.lastIndex) {
            return exerciseList[currentExercise]
        }
        currentExercise = exerciseList.lastIndex // stay at last index to avoid code crashing
        return null
    }

    override fun goToPreviousExercise(): Exercise? {
        Log.d("LOG_TAG", "Activity.goToPreviousExercise() invoked")
        currentExercise--
        if (currentExercise >= 0) {
            return exerciseList[currentExercise]
        }
        currentExercise = 0 // stay at first index to avoid code crashing
        return null
    }

    override fun resetStacks() {
        Log.d("LOG_TAG", "Activity.resetStacks() invoked")
        currentExercise = 0
        Assert.assertEquals(
            "Activity.resetStacks: Current Exercise should be 0 but isn't.",
            0, currentExercise
        )
    }

    override fun toString(): String {
        return "Activity(id='$id', name=$name, activityType=$activityType, exerciseList=$exerciseList, completionProgress=$completionProgress, description=$description)"
    }
}