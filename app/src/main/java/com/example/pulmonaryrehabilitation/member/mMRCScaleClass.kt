package com.example.pulmonaryrehabilitation.member

import android.util.Log
import org.junit.Assert

class mMRCScaleClass(
    override var grade: Int
) : mMRCScale {
    private val LOG_TAG = "mMMRCScaleClass"

    init {
        Assert.assertTrue("grade needs to be between 0 and 4", grade in 0..4)
    }

    override fun gradeInt(): Int? {
        Log.d(LOG_TAG, "gradeInt() invoked, grade is $grade")
        return grade
    }

    override fun toString(): String {
        Log.d(LOG_TAG, "toString() invoked, grade is $grade")
        return "{grade=$grade}"
    }
}