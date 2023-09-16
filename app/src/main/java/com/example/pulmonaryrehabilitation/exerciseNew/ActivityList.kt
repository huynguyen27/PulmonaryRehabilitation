package com.example.pulmonaryrehabilitation.exerciseNew

class ActivityList(
    val id: String,
    val activityList: MutableList<Activity>
) {
    override fun toString(): String {
        return "ActivityList(id='$id', activityList=$activityList)"
    }
}