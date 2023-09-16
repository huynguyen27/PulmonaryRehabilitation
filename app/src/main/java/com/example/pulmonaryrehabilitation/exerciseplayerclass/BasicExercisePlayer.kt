package com.example.pulmonaryrehabilitation.exerciseplayerclass

import com.example.pulmonaryrehabilitation.ExerciseCollections.ExerciseCollection
import com.example.pulmonaryrehabilitation.Exercises.Exercise
import com.example.pulmonaryrehabilitation.Exercises.Steps.ExerciseStep
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue

class BasicExercisePlayer(
    override val exerciseRoutine: ExerciseCollection,
) : ExercisePlayer {
    override val exercisePlayerName: String = exerciseRoutine.collectionName
    init {
        assertNotNull(exerciseRoutine)
    }

    override fun play(): MutableList<String> {
        val tempList = mutableListOf<String>()
        val exercises = exerciseRoutine.exercises
        for (exercise in exercises.iterator()) {
            // println(exercise.exerciseName)
            tempList.add(exercise.exerciseName)
            for (step in exercise.exerciseSteps.iterator()) {
                // println(step.stepTitle)
                tempList.add(step.stepTitle)
            }
        }
        return tempList
    }

    override fun playTimer(duration: Int) {
        assertTrue("duration must be a positive integer greater than zero", duration > 0)
        assertFalse("duration must be a positive integer greater than zero", duration == 0)
//        val timer = object : CountDownTimer(duration.toLong(), 1000) {
//
//            override fun onTick(millisUntilFinished: Long) {
//                println("seconds remaining: " + millisUntilFinished / 1000)
//            }
//
//            override fun onFinish() {
//                println("done!")
//            }
//        }
//        timer.start()
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun goToNextExercise(): Exercise? {
        exerciseRoutine.goToNextExercise()
        return exerciseRoutine.getCurrentExercise()
    }

    override fun goToPreviousExercise(): Exercise? {
        exerciseRoutine.goToPreviousExercise()
        return exerciseRoutine.getCurrentExercise()
    }

    override fun goToNextStep(): ExerciseStep? {
        if (exerciseRoutine.getCurrentExercise()?.goToNextStep() == null) {
            exerciseRoutine.goToNextExercise()
            return exerciseRoutine.getCurrentExercise()?.getCurrentStep()
        }
        return exerciseRoutine.getCurrentExercise()?.getCurrentStep()
    }

    override fun goToPreviousStep(): ExerciseStep? {
        if (exerciseRoutine.getCurrentExercise()?.goToPreviousStep() == null) {
            exerciseRoutine.goToPreviousExercise()
            // should this go to the last step of the previous exercise, or the start of the previous exercise
//            exerciseRoutine.getCurrentExercise()?.resetStacks()
            exerciseRoutine.getCurrentExercise()?.goToPreviousStep() // goes to the last step of the previous exercise
        }
        val test = exerciseRoutine.getCurrentExercise()

        return exerciseRoutine.getCurrentExercise()?.getCurrentStep()
    }

    override fun resetExercises() {
        exerciseRoutine.resetStacks()
    }

    override fun resetSteps() {
        exerciseRoutine.getCurrentExercise()?.resetStacks()
    }
}