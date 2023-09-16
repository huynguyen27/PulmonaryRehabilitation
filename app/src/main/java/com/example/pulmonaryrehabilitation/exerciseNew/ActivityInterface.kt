package com.example.pulmonaryrehabilitation.exerciseNew

interface ActivityInterface {
    val id: String
    val name: String? // WarmUp - Main Activity 1,2 - Breathing Session
    val activityType: String? // WarmUp - Main Activity 1,2 - Breathing Session
    val completionProgress: String?
    val description: String?

    val exerciseList: MutableList<Exercise>

    fun getCurrentExercise(): Exercise?
    fun goToNextExercise(): Exercise?
    fun goToPreviousExercise(): Exercise?
    fun resetStacks()
}