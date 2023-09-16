package com.example.pulmonaryrehabilitation.member

interface Member {
    var id: String
    var isAdmin: Boolean
    var firstName: String?
    var lastName: String?
    var username: String?
    var email: String
    var steps: Int?
    var stepGoal: Int?
    var gamificationHistory: MutableMap<String, GamificationHistoryClass>?
    var usageHistory: MutableMap<String, UsageHistoryClass>?
    var stepHistory: MutableMap<String, StepHistoryClass>?
    var questionnaireHistory: MutableMap<String, QuestionnaireHistoryClass>?
    var lastQuestionnaireDate: String?
    var mMRCHistory: MutableMap<String, mMRCScaleClass>?
    var lastCATmMRCDate: String?
    var ismMRCNext: Boolean?
    var catHistory: MutableMap<String, MutableMap<CATSymptoms, Int>?>?
    var streak: String?
    var weeklyExercisePoint: String?

    override fun toString(): String
//    fun iterateGamificationHistoryMap(map: Map<String, GamificationHistoryClass>): String
//    fun iterateUsageHistoryMap(map: Map<String, UsageHistoryClass>): String
//    fun iterateStepHistoryMap(map: Map<String, StepHistoryClass>): String

    fun toMemberMap(): Map<String, Any?>
}