package com.example.pulmonaryrehabilitation.cat

import android.util.Log
import com.example.pulmonaryrehabilitation.member.CAT
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CATTest {

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
    fun setCough() {
        CAT.setCough(0)
        Assert.assertEquals(CAT.getCough(), 0)
    }

    @Test
    fun setPhlegm() {
        CAT.setPhlegm(1)
        Assert.assertEquals(CAT.getPhlegm(), 1)
    }

    @Test
    fun setChestTightness() {
        CAT.setChestTightness(2)
        Assert.assertEquals(CAT.getChestTightness(), 2)
    }

    @Test
    fun setBreathlessness() {
        CAT.setBreathlessness(3)
        Assert.assertEquals(CAT.getBreathlessness(), 3)
    }

    @Test
    fun setActivities() {
        CAT.setActivities(4)
        Assert.assertEquals(CAT.getActivities(), 4)
    }

    @Test
    fun setConfidence() {
        CAT.setConfidence(5)
        Assert.assertEquals(CAT.getConfidence(), 5)
    }

    @Test
    fun setSleep() {
        CAT.setSleep(1)
        Assert.assertEquals(CAT.getSleep(), 1)
    }

    @Test
    fun setEnergy() {
        CAT.setEnergy(0)
        Assert.assertEquals(CAT.getEnergy(), 0)
        println(CAT.getTotal())
    }

    @Test
    fun getCough() {
        CAT.setCough(5)
        Assert.assertEquals(CAT.getCough(), 5)
        CAT.setCough(4)
        Assert.assertEquals(CAT.getCough(), 4)
        CAT.setCough(3)
        Assert.assertEquals(CAT.getCough(), 3)
        CAT.setCough(2)
        Assert.assertEquals(CAT.getCough(), 2)
        CAT.setCough(1)
        Assert.assertEquals(CAT.getCough(), 1)
        CAT.setCough(0)
        Assert.assertEquals(CAT.getCough(), 0)
    }

    @Test
    fun getPhlegm() {
        CAT.setPhlegm(5)
        Assert.assertEquals(CAT.getPhlegm(), 5)
        CAT.setPhlegm(4)
        Assert.assertEquals(CAT.getPhlegm(), 4)
        CAT.setPhlegm(3)
        Assert.assertEquals(CAT.getPhlegm(), 3)
        CAT.setPhlegm(2)
        Assert.assertEquals(CAT.getPhlegm(), 2)
        CAT.setPhlegm(1)
        Assert.assertEquals(CAT.getPhlegm(), 1)
        CAT.setPhlegm(0)
        Assert.assertEquals(CAT.getPhlegm(), 0)
    }

    @Test
    fun getChestTightness() {
        CAT.setChestTightness(5)
        Assert.assertEquals(CAT.getChestTightness(), 5)
        CAT.setChestTightness(4)
        Assert.assertEquals(CAT.getChestTightness(), 4)
        CAT.setChestTightness(3)
        Assert.assertEquals(CAT.getChestTightness(), 3)
        CAT.setChestTightness(2)
        Assert.assertEquals(CAT.getChestTightness(), 2)
        CAT.setChestTightness(1)
        Assert.assertEquals(CAT.getChestTightness(), 1)
        CAT.setChestTightness(0)
        Assert.assertEquals(CAT.getChestTightness(), 0)
    }

    @Test
    fun getBreathlessness() {
        CAT.setBreathlessness(5)
        Assert.assertEquals(CAT.getBreathlessness(), 5)
        CAT.setBreathlessness(4)
        Assert.assertEquals(CAT.getBreathlessness(), 4)
        CAT.setBreathlessness(3)
        Assert.assertEquals(CAT.getBreathlessness(), 3)
        CAT.setBreathlessness(2)
        Assert.assertEquals(CAT.getBreathlessness(), 2)
        CAT.setBreathlessness(1)
        Assert.assertEquals(CAT.getBreathlessness(), 1)
        CAT.setBreathlessness(0)
        Assert.assertEquals(CAT.getBreathlessness(), 0)
    }

    @Test
    fun getActivities() {
        CAT.setActivities(5)
        Assert.assertEquals(CAT.getActivities(), 5)
        CAT.setActivities(4)
        Assert.assertEquals(CAT.getActivities(), 4)
        CAT.setActivities(3)
        Assert.assertEquals(CAT.getActivities(), 3)
        CAT.setActivities(2)
        Assert.assertEquals(CAT.getActivities(), 2)
        CAT.setActivities(1)
        Assert.assertEquals(CAT.getActivities(), 1)
        CAT.setActivities(0)
        Assert.assertEquals(CAT.getActivities(), 0)
    }

    @Test
    fun getConfidence() {
        CAT.setConfidence(5)
        Assert.assertEquals(CAT.getConfidence(), 5)
        CAT.setConfidence(4)
        Assert.assertEquals(CAT.getConfidence(), 4)
        CAT.setConfidence(3)
        Assert.assertEquals(CAT.getConfidence(), 3)
        CAT.setConfidence(2)
        Assert.assertEquals(CAT.getConfidence(), 2)
        CAT.setConfidence(1)
        Assert.assertEquals(CAT.getConfidence(), 1)
        CAT.setConfidence(0)
        Assert.assertEquals(CAT.getConfidence(), 0)
    }

    @Test
    fun getSleep() {
        CAT.setSleep(5)
        Assert.assertEquals(CAT.getSleep(), 5)
        CAT.setSleep(4)
        Assert.assertEquals(CAT.getSleep(), 4)
        CAT.setSleep(3)
        Assert.assertEquals(CAT.getSleep(), 3)
        CAT.setSleep(2)
        Assert.assertEquals(CAT.getSleep(), 2)
        CAT.setSleep(1)
        Assert.assertEquals(CAT.getSleep(), 1)
        CAT.setSleep(0)
        Assert.assertEquals(CAT.getSleep(), 0)
    }

    @Test
    fun getEnergy() {
        CAT.setEnergy(5)
        Assert.assertEquals(CAT.getEnergy(), 5)
        CAT.setEnergy(4)
        Assert.assertEquals(CAT.getEnergy(), 4)
        CAT.setEnergy(3)
        Assert.assertEquals(CAT.getEnergy(), 3)
        CAT.setEnergy(2)
        Assert.assertEquals(CAT.getEnergy(), 2)
        CAT.setEnergy(1)
        Assert.assertEquals(CAT.getEnergy(), 1)
        CAT.setEnergy(0)
        Assert.assertEquals(CAT.getEnergy(), 0)
    }
}