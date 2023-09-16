package com.example.pulmonaryrehabilitation.exerciseNew

import android.util.Log

object Default {
    fun defaultExercise1(): Exercise {
        Log.d("LOG_TAG", "Default.defaultExercise1() invoked")
        return Exercise(
            "01", "defaultExercise1", "0", "-sH1jMhc6UY", "description", "instruction", 10
        )
    }

    fun defaultExercise2(): Exercise {
        Log.d("LOG_TAG", "Default.defaultExercise2() invoked")
        return Exercise(
            "02", "defaultExercise2", "0", "-sH1jMhc6UY", "description", "instruction", 10
        )
    }

    fun defaultActivity1(): Activity {
        Log.d("LOG_TAG", "Default.defaultActivity1 invoked")
        return Activity(
            "01",
            "defaultActivity1",
            "1",
            mutableListOf(defaultExercise1(), defaultExercise2()),
            "0",
            "description", 0
        )
    }

    fun defaultActivity2(): Activity {
        Log.d("LOG_TAG", "Default.defaultActivity2() invoked")
        return Activity(
            "02",
            "defaultActivity2",
            "2",
            mutableListOf(defaultExercise1(), defaultExercise2()),
            "0",
            "description", 0
        )
    }

    fun defaultCollection(): Collection {
        return Collection(
            "0", "defaultCollection", "collectionType", "completionProgress",
            listOf(
                defaultActivity1(), defaultActivity2()
            )
        )
    }
}