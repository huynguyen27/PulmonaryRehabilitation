package com.example.pulmonaryrehabilitation
import android.util.Log
import com.example.pulmonaryrehabilitation.member.CurrentUser
import com.example.pulmonaryrehabilitation.member.MemberClass
import com.example.pulmonaryrehabilitation.member.ModelObject
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
class CurrentUserTest {

    @Before
    fun setUp() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
    }

    @Test
    fun testingCurrentUser() {

        val testUser = MemberClass(
            "1", true, "Georfe", "Scrunkle", "1", "example@admin.com",
            1, 5000,
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
            null,
        )
        CurrentUser.setData(testUser)
        assertEquals(CurrentUser.getFirstName(), "Georfe")
        assertEquals(CurrentUser.getLastName(), "Scrunkle")
        assertEquals(CurrentUser.getStepGoal(), 5000)
        assertEquals(CurrentUser.getLastQuestionnaireDate(), null)
    }

    @Test
    fun getMondayTest() {

        assertEquals(CurrentUser.getMonday("2023-03-08T16:29:03.790Z"), "2023-03-06")
        assertEquals(CurrentUser.getMonday("2023-03-06T16:29:03.790Z"), "2023-03-06")
        assertEquals(CurrentUser.getMonday("2023-03-12T16:29:03.790Z"), "2023-03-06")
        assertEquals(CurrentUser.getMonday("2023-03-20T07:06:19.954Z"), "2023-03-20")
        assertEquals(CurrentUser.getMonday("2023-07-14T00:53:19.954Z"), "2023-07-10")
        assertEquals(CurrentUser.getMonday("2022-07-31T20:39:59.999Z"), "2022-07-25")
        assertEquals(CurrentUser.getMonday("1991-02-16T01:11:06.666Z"), "1991-02-11")
        assertEquals(CurrentUser.getMonday("2040-06-02T03:57:02.222Z"), "2040-05-28")
    }
    @Test
    fun getConvertDate() {

        assertEquals(CurrentUser.convertDate("1678292943790"), "2023-03-08T16:29:03.790Z")
        assertEquals(CurrentUser.convertDate("1679295979954"), "2023-03-20T07:06:19.954Z")
        assertEquals(CurrentUser.convertDate("1689295999954"), "2023-07-14T00:53:19.954Z")
        assertEquals(CurrentUser.convertDate("1659299999999"), "2022-07-31T20:39:59.999Z")
        assertEquals(CurrentUser.convertDate("1666666666666"), "2022-10-25T02:57:46.666Z")
        assertEquals(CurrentUser.convertDate("666666666666"), "1991-02-16T01:11:06.666Z")
        assertEquals(CurrentUser.convertDate("2222222222222"), "2040-06-02T03:57:02.222Z")
    }
    @Test
    fun getNextMondayTest() {

        assertEquals(CurrentUser.getNextMonday("2023-03-08T16:29:03.790Z"), "2023-03-13")
        assertEquals(CurrentUser.getNextMonday("2023-03-06T16:29:03.790Z"), "2023-03-13")
        assertEquals(CurrentUser.getNextMonday("2023-03-12T16:29:03.790Z"), "2023-03-13")
        assertEquals(CurrentUser.getNextMonday("2023-03-20T07:06:19.954Z"), "2023-03-27")
        assertEquals(CurrentUser.getNextMonday("2023-07-14T00:53:19.954Z"), "2023-07-17")
        assertEquals(CurrentUser.getNextMonday("2022-07-31T20:39:59.999Z"), "2022-08-01")
        assertEquals(CurrentUser.getNextMonday("1991-02-16T01:11:06.666Z"), "1991-02-18")
        assertEquals(CurrentUser.getNextMonday("2040-06-02T03:57:02.222Z"), "2040-06-04")
    }

    @Test
    fun parseDateTest() {
        assertEquals(CurrentUser.parseDate(1680132847112), "03-29-2023")
        assertEquals(CurrentUser.parseDate(994312800000), "07-05-2001")
        assertEquals(CurrentUser.parseDate(1955944800000), "12-25-2031")
    }
}