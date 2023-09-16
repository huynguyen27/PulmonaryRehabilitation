package com.example.pulmonaryrehabilitation.exerciseNew

interface CollectionInterface {
    val id: String
    val name: String? // WarmUp - Main Activity 1,2 - Breathing Session
    val collectionType: String? // WarmUp - Main Activity 1,2 - Breathing Session
    val completionProgress: String?
    val activityList: List<Activity>
    var doStack: ArrayDeque<Activity>
    var undoStack: ArrayDeque<Activity>

    fun addActivityToDoStack()
    fun getCurrentActivity(): Activity?
    fun goToNextActivity(): Activity?
    fun goToPreviousActivity(): Activity?
    fun resetStacks()
}