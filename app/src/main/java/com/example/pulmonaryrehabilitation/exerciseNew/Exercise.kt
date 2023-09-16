package com.example.pulmonaryrehabilitation.exerciseNew

class Exercise(
    override val id: String,
    override var name: String?,
    override var completeStatus: String?,
    override val videoSrc: String?,
    override var description: String?,
    override var instruction: String?,
    override val duration: Int

) : ExerciseInterface {
    override fun toString(): String {
        return "Exercise(id='$id', name=$name, completeStatus=$completeStatus, videoSrc=$videoSrc, description=$description, instruction=$instruction, duration=$duration)"
    }
}