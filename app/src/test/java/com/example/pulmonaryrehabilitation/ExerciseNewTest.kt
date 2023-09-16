package com.example.pulmonaryrehabilitation

import android.util.Log
import com.example.pulmonaryrehabilitation.exerciseNew.Default
import com.example.pulmonaryrehabilitation.exerciseNew.Exercise
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ExerciseNewTest {
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
    fun testInitialization() {
        val testCollection = Exercise("id", "name", "status", "source", "description", "instruction", 1)

        Assert.assertEquals("Test id", testCollection.id, "id")
        Assert.assertEquals("Test name", testCollection.name, "name")
        Assert.assertEquals("Test completeStatus", testCollection.completeStatus, "status")
        Assert.assertEquals("Test videoSrc", testCollection.videoSrc, "source")
        Assert.assertEquals("Test description", testCollection.description, "description")
        Assert.assertEquals("Test instruction", testCollection.instruction, "instruction")
        Assert.assertEquals("Test duration", testCollection.duration, 1)
    }
    @Test
    fun testDefaults() {
        val def1 = Default.defaultExercise1()

        Assert.assertEquals("Test id", def1.id, "01")
        Assert.assertEquals("Test name", def1.name, "defaultExercise1")
        Assert.assertEquals("Test completeStatus", def1.completeStatus, "0")
        Assert.assertEquals("Test ", def1.videoSrc, "-sH1jMhc6UY")
        Assert.assertEquals("Test ", def1.description, "description")
        Assert.assertEquals("Test ", def1.instruction, "instruction")
        Assert.assertEquals("Test ", def1.duration, 10)

        val act1 = Default.defaultActivity1()

        Assert.assertEquals("Test id", act1.id, "01")
        Assert.assertEquals("Test id", act1.name, "defaultActivity1")
        Assert.assertEquals("Test activityType", act1.activityType, "1")
        Assert.assertEquals("Test id", act1.completionProgress, "0")
        Assert.assertEquals("Test description", act1.description, "description")
        Assert.assertEquals("Test id", act1.currentExercise, 0)
    }
}
