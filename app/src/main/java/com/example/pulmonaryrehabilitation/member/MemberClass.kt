package com.example.pulmonaryrehabilitation.member

import com.google.firebase.database.Exclude

// create Member class to read from and write to the database
class MemberClass(
    override var id: String = "",
    override var isAdmin: Boolean = false,
    override var firstName: String? = "",
    override var lastName: String? = "",
    override var username: String? = "",
    override var email: String = "",
    override var steps: Int?,
    override var stepGoal: Int?,
    override var gamificationHistory: MutableMap<String, GamificationHistoryClass>?,
    override var usageHistory: MutableMap<String, UsageHistoryClass>?,
    override var stepHistory: MutableMap<String, StepHistoryClass>?,
    override var questionnaireHistory: MutableMap<String, QuestionnaireHistoryClass>?,
    override var lastQuestionnaireDate: String?,
    override var streak: String? = "",
    override var weeklyExercisePoint: String? = "",
    override var mMRCHistory: MutableMap<String, mMRCScaleClass>? = null,
    override var lastCATmMRCDate: String? = null,
    override var ismMRCNext: Boolean? = true,
    override var catHistory: MutableMap<String, MutableMap<CATSymptoms, Int>?>? = null,

) : Member {

    override fun toString(): String {
        // TODO update this
        return "MemberClass(id=$id, isAdmin=$isAdmin, firstName=$firstName, lastName=$lastName," +
            " username=$username," +
            " email=$email, steps=$steps, stepGoal=$stepGoal, gamificationHistory=$gamificationHistory, " +
            "usageHistory=$usageHistory, stepHistory=$stepHistory) ," +
            "questionnaireHistory=$questionnaireHistory, lastQuestionnaireDate=$lastQuestionnaireDate"
    }

    @Exclude
    override fun toMemberMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "isAdmin" to isAdmin,
            "firstName" to firstName,
            "lastName" to lastName,
            "username" to username,
            "email" to email,
            "steps" to steps,
            "stepGoal" to stepGoal,
            "gamificationHistory" to gamificationHistory,
            "streak" to streak,
            "weeklyExercisePoint" to weeklyExercisePoint,
            "ismMRCNext" to ismMRCNext
//            "usageHistory" to usageHistory,
//            "stepHistory" to stepHistory,
//            "questionnaireHistory" to questionnaireHistory
        )
    }
}