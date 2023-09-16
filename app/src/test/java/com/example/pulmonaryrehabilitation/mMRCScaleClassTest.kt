package com.example.pulmonaryrehabilitation

import android.util.Log
import com.example.pulmonaryrehabilitation.member.mMRCScaleClass
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class mMRCScaleClassTest {

    @Before
    fun setup() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
    }

    @Test
    fun gradeInt() {
        val mockmMRC1 = mMRCScaleClass(3)
        assertEquals(mockmMRC1.gradeInt(), 3)
        val mockmMRC2 = mMRCScaleClass(4)
        assertEquals(mockmMRC2.gradeInt(), 4)
    }

    @Test
    fun testToString() {
        val mockmMRC1 = mMRCScaleClass(2)
        assertEquals("{grade=2}", mockmMRC1.toString())
        val mockmMRC2 = mMRCScaleClass(0)
        assertEquals("{grade=0}", mockmMRC2.toString())
        val mockmMRC3 = mMRCScaleClass(1)
        assertEquals("{grade=1}", mockmMRC3.toString())
    }
}