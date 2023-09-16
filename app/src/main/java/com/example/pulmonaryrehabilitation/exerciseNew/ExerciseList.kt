package com.example.pulmonaryrehabilitation.exerciseNew

class ExerciseList(
    val id: String,
    val exerciseList: MutableList<Exercise>
) {
    override fun toString(): String {
        return "ExerciseList(id='$id', exerciseList=$exerciseList)"
    }
}