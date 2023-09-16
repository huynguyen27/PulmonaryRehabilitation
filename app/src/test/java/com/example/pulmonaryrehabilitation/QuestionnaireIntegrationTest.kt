package com.example.pulmonaryrehabilitation

import android.annotation.SuppressLint
import android.util.Log
import com.example.pulmonaryrehabilitation.member.CAT
import com.example.pulmonaryrehabilitation.member.CurrentUser
import com.example.pulmonaryrehabilitation.member.MemberClass
import com.example.pulmonaryrehabilitation.member.ModelObject
import io.mockk.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class QuestionnaireIntegrationTest {

    @Before
    fun setup() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
    }
    @SuppressLint("CheckResult")
    @Test
    fun testingCurrentUser() {
        val MockMember = MemberClass(
            "1", true, null, null, null,
            "example@admin.com",
            null,
            null,
            null,
            null,
            null,
            mutableMapOf(
                "1647158603" to ModelObject.defaultQuestionnaireHistory(),
            ),
            "1647158603"
        )
        mockkObject(CurrentUser)
        CurrentUser.setData(MockMember)

        Assert.assertEquals(CurrentUser.daysSinceLastQuestionnaire(CurrentUser.getLastQuestionnaireDate(), 3), true)
    }

    /**
     * Integration test for getMMRCHistory and addmMRCHistory to manipulate for testing
     * This test should get the MMRC history and compare every time a questionnaire is submitted
     */
    @Test
    fun testGetMMRCHistoryQuestionnaire() {
        val MockMember = MemberClass(
            "1", true, null, null, null,
            "example@admin.com",
            null, null, null, null, null,
            mutableMapOf(
                "1647158603" to ModelObject.defaultQuestionnaireHistory(),
            ),
            "1647158603", null, null,
            mutableMapOf(
                "000" to ModelObject.defaultmMRCHistory(),
            ),
            null, null, null
        )
        mockkObject(CurrentUser)
        CurrentUser.setData(MockMember)

        Assert.assertEquals(CurrentUser.getmMRCHistory().toString(), "{000={grade=4}}")
        CurrentUser.addmMRCHistory(3)
        Assert.assertEquals(CurrentUser.getmMRCHistory().toString(), "{000={grade=4}, ${CurrentUser.getmMRCHistory().toList().get(1).first}={grade=3}}")
        CurrentUser.addmMRCHistory(2)
        Assert.assertEquals(
            CurrentUser.getmMRCHistory().toString(),
            "{000={grade=4}, " +
                "${CurrentUser.getmMRCHistory().toList().get(1).first}={grade=3}, " +
                "${CurrentUser.getmMRCHistory().toList().get(2).first}={grade=2}}"
        )
    }

    /**
     * Integration test for getIsmMRCNext, addCatHistory, and addmMRCHistory to manipulate for testing
     * This test will have a default value of false for ismMRCNext so a function can call addCatHistory
     *  Afterwards, the ismMRCNext value should switch to true. To turn it back to false, addmMRCHistory
     *  is called and will switch getIsmMRCNext back to false
     */
    @Test
    fun testGetIsmMRCNextQuestionnaire() {
        val MockMember = MemberClass(
            "1", true, null, null, null,
            "example@admin.com",
            null, null, null, null, null,
            mutableMapOf(
                "1647158603" to ModelObject.defaultQuestionnaireHistory(),
            ),
            "1647158603", null, null, null,
            null, false, null,
        )
        mockkObject(CurrentUser)
        CurrentUser.setData(MockMember)

        Assert.assertEquals(CurrentUser.getIsmMRCNext(), false)
        CurrentUser.addCatHistory(CAT.getCAT())
        Assert.assertEquals(CurrentUser.getIsmMRCNext(), true)
        CurrentUser.addmMRCHistory(0)
        Assert.assertEquals(CurrentUser.getIsmMRCNext(), false)
    }

    /**
     * Integration test for set methods from CAT, getCatHistory(), and addCatHistory()
     * This test should be checking if the set methods from CAT are working, alongside with getCatHistory()
     *  and addCatHistory()
     */
    @Test
    fun testSetAndGetCatMethodsAndAddCaTHistory() {
        val MockMember = MemberClass(
            "1", true, null, null, null,
            "example@admin.com",
            null, null, null, null, null,
            mutableMapOf(
                "1647158603" to ModelObject.defaultQuestionnaireHistory(),
            ),
            "1647158603", null, null, null,
            "16471586232342", null, null,
        )
        mockkObject(CurrentUser)
        CurrentUser.setData(MockMember)

        Assert.assertEquals(CurrentUser.getLastCATmMRCDate().toString(), "16471586232342")
        CAT.setActivities(1)
        CurrentUser.addCatHistory(CAT.getCAT())
        Assert.assertEquals(
            CurrentUser.getCATHistory().toString(),
            "{${CurrentUser.getCATHistory().toList().get(0).first}={TOTAL=1, ACTIVITIES=1}}"
        )
        CAT.setBreathlessness(2)
        CAT.setPhlegm(3)
        CAT.setEnergy(4)
        CAT.setSleep(1)
        CAT.setCough(5)
        CurrentUser.addCatHistory(CAT.getCAT())
        Assert.assertEquals(
            CurrentUser.getCATHistory().toString(),
            "{${CurrentUser.getCATHistory().toList().get(0).first}={TOTAL=16, ACTIVITIES=1, " +
                "BREATHLESSNESS=2, PHLEGM=3, ENERGY=4, SLEEP=1, COUGH=5}, " +
                "${CurrentUser.getCATHistory().toList().get(1).first}={TOTAL=16, " +
                "ACTIVITIES=1, BREATHLESSNESS=2, PHLEGM=3, ENERGY=4, SLEEP=1, COUGH=5}}"
        )
    }
}