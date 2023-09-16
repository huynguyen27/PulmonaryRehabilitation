package com.example.pulmonaryrehabilitation
import com.example.pulmonaryrehabilitation.member.MemberClass
import com.example.pulmonaryrehabilitation.member.ModelObject
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class MemberClassTest {
    // NOTE: This is out of date, needs to be updated.
    private val memberClass = MemberClass(
        "1", true, "Georfe", "Scrunkle", "user1",
        "example@admin.com", 0, 5000,

        mutableMapOf("Feb 19 9am" to ModelObject.defaultGamificationHistory()),

        mutableMapOf(
            "Feb 17 10am" to ModelObject.defaultUsageHistory(),
            "Feb 17 11am" to ModelObject.defaultUsageHistory()
        ),

        mutableMapOf("Feb 17 11am" to ModelObject.defaultStepHistory()),

        mutableMapOf(
            "Feb 16 10am" to ModelObject.defaultQuestionnaireHistory(),
            "Feb 19 33am" to ModelObject.defaultQuestionnaireHistory()
        ),
        ""
    )

    @Test
    fun testToString() {
        assertEquals(
            "MemberClass(id=1, isAdmin=true, firstName=Georfe, " +
                "lastName=Scrunkle, username=user1, email=example@admin.com, steps=0, stepGoal=5000, " +
                "gamificationHistory={Feb 19 9am={itemname='game1', itemname2='game2'}}," +
                " usageHistory={Feb 17 10am={itemname='usage1', itemname2='usage2'}, " +
                "Feb 17 11am={itemname='usage1', itemname2='usage2'}}, " +
                "stepHistory={Feb 17 11am={stepCount='200000'}}) ," +
                "questionnaireHistory={Feb 16 10am={question='I am a question', " +
                "answer='I am their answer'}, Feb 19 33am={question='I am a question', " +
                "answer='I am their answer'}}, lastQuestionnaireDate=",
            memberClass.toString()
        )
    }

    @Test
    fun testToMemberMap() {
        val gamificationMap =
            mutableMapOf("Gamification" to ModelObject.defaultGamificationHistory())
        val usageMap = mutableMapOf("Usage" to ModelObject.defaultUsageHistory())
        val stepMap = mutableMapOf("Steps" to ModelObject.defaultStepHistory())
        val questionMap = mutableMapOf("Questionnaire" to ModelObject.defaultQuestionnaireHistory())

        val test = MemberClass(
            "1", true, "Test", "Case",
            "TestCase", "testcase@email.com", 1, 300, gamificationMap,
            usageMap, stepMap, questionMap, "Feb 19 9am", "0", "2", null, null,
            true, null

        )

        val testMap = mutableMapOf<String, Any>(
            "id" to "1", "isAdmin" to true, "firstName" to "Test",
            "lastName" to "Case", "username" to "TestCase", "email" to "testcase@email.com",
            "steps" to 1,
            "stepGoal" to 300, "gamificationHistory" to gamificationMap,
            "streak" to "0", "weeklyExercisePoint" to "2", "ismMRCNext" to true
        )

        Assert.assertEquals(testMap, test.toMemberMap())
    }
}