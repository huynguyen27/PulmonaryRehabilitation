package com.example.pulmonaryrehabilitation.exerciseNew

class Collection(
    override val id: String,
    override val name: String?,
    override val collectionType: String?,
    override val completionProgress: String?,
    override val activityList: List<Activity>

) : CollectionInterface {

    override var doStack: ArrayDeque<Activity> = ArrayDeque()
    override var undoStack: ArrayDeque<Activity> = ArrayDeque()

    init {
        addActivityToDoStack()
    }

    override fun addActivityToDoStack() {
        for (step in activityList.reversed()) { // reverse because LIFO
            doStack.add(step)
        }
    }

    override fun getCurrentActivity(): Activity? {
        return if (!doStack.isEmpty()) {
            doStack.last()
        } else {
            null
        }
    }

    override fun goToNextActivity(): Activity? {
        return if (!doStack.isEmpty()) {
            val currentStep = doStack.removeLast()
            undoStack.add(currentStep)
            getCurrentActivity()
        } else {
            null // returns null so exercise player knows to go to next exercise
        }
    }

    override fun goToPreviousActivity(): Activity? {
        return if (!undoStack.isEmpty()) {
            val previousStep = undoStack.removeLast()
            doStack.add(previousStep)
            getCurrentActivity()
        } else {
            null // returns null so exercise player knows to go to previous exercise
        }
    }

    override fun resetStacks() {
        for (activity in activityList) {
            activity.resetStacks()
        }
        doStack = ArrayDeque()
        undoStack = ArrayDeque()
        addActivityToDoStack()
    }

    override fun toString(): String {
        return "Collection(id='$id', name=$name, collectionType=$collectionType, completionProgress=$completionProgress, activityList=$activityList, doStack=$doStack, undoStack=$undoStack)"
    }
}