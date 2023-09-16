package com.example.pulmonaryrehabilitation.exerciseNew

interface ExerciseInterface {
    val id: String
    var name: String?
    var completeStatus: String?
    val videoSrc: String?
    var description: String?
    var instruction: String?
    val duration: Int
}