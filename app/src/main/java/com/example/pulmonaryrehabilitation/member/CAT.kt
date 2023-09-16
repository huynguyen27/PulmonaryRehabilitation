package com.example.pulmonaryrehabilitation.member

import android.util.Log
import org.junit.Assert

object CAT {
    private var cat: MutableMap<CATSymptoms, Int>? = mutableMapOf()
    private const val LOG_TAG: String = "CurrentUser"

    init {
        cat?.set(CATSymptoms.TOTAL, 0)
    }

    /**
     * Sets the COUGH symptom score
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     * - The `score` parameter must be an integer between 0 and 5.
     *
     * Postconditions:
     * - The cough symptom value in the `cat` object will be updated with the provided `score`.
     * - The total score will be updated, `score` will be added to total
     */
    fun setCough(score: Int) {
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        cat?.set(CATSymptoms.COUGH, score)
        setTotal(score)
        Log.d(LOG_TAG, "Set COUGH score to $score")
    }

    /**
     * Sets the PHLEGM symptom score
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     * - The `score` parameter must be an integer between 0 and 5.
     *
     * Postconditions:
     * - The cough symptom value in the `cat` object will be updated with the provided `score`.
     * - The total score will be updated, `score` will be added to total
     */
    fun setPhlegm(score: Int) {
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        cat?.set(CATSymptoms.PHLEGM, score)
        setTotal(score)
        Log.d(LOG_TAG, "Set PHLEGM score to $score")
    }

    /**
     * Sets the CHEST_TIGHTNESS symptom score
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     * - The `score` parameter must be an integer between 0 and 5.
     *
     * Postconditions:
     * - The cough symptom value in the `cat` object will be updated with the provided `score`.
     * - The total score will be updated, `score` will be added to total
     */
    fun setChestTightness(score: Int) {
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        cat?.set(CATSymptoms.CHEST_TIGHTNESS, score)
        setTotal(score)
        Log.d(LOG_TAG, "Set CHEST_TIGHTNESS score to $score")
    }

    /**
     * Sets the BREATHLESSNESS symptom score
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     * - The `score` parameter must be an integer between 0 and 5.
     *
     * Postconditions:
     * - The cough symptom value in the `cat` object will be updated with the provided `score`.
     * - The total score will be updated, `score` will be added to total
     */
    fun setBreathlessness(score: Int) {
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        cat?.set(CATSymptoms.BREATHLESSNESS, score)
        setTotal(score)
        Log.d(LOG_TAG, "Set BREATHLESSNESS score to $score")
    }

    /**
     * Sets the ACTIVITIES symptom score
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     * - The `score` parameter must be an integer between 0 and 5.
     *
     * Postconditions:
     * - The cough symptom value in the `cat` object will be updated with the provided `score`.
     * - The total score will be updated, `score` will be added to total
     */
    fun setActivities(score: Int) {
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        cat?.set(CATSymptoms.ACTIVITIES, score)
        setTotal(score)
        Log.d(LOG_TAG, "Set ACTIVITIES score to $score")
    }

    /**
     * Sets the CONFIDENCE symptom score
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     * - The `score` parameter must be an integer between 0 and 5.
     *
     * Postconditions:
     * - The cough symptom value in the `cat` object will be updated with the provided `score`.
     * - The total score will be updated, `score` will be added to total
     */
    fun setConfidence(score: Int) {
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        cat?.set(CATSymptoms.CONFIDENCE, score)
        setTotal(score)
        Log.d(LOG_TAG, "Set CONFIDENCE score to $score")
    }

    /**
     * Sets the SLEEP symptom score
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     * - The `score` parameter must be an integer between 0 and 5.
     *
     * Postconditions:
     * - The cough symptom value in the `cat` object will be updated with the provided `score`.
     * - The total score will be updated, `score` will be added to total
     */
    fun setSleep(score: Int) {
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        cat?.set(CATSymptoms.SLEEP, score)
        setTotal(score)
        Log.d(LOG_TAG, "Set SLEEP score to $score")
    }

    /**
     * Sets the ENERGY symptom score
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     * - The `score` parameter must be an integer between 0 and 5.
     *
     * Postconditions:
     * - The cough symptom value in the `cat` object will be updated with the provided `score`.
     * - The total score will be updated, `score` will be added to total
     */
    fun setEnergy(score: Int) {
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        cat?.set(CATSymptoms.ENERGY, score)
        setTotal(score)
        Log.d(LOG_TAG, "Set ENERGY score to $score")
    }

    private fun setTotal(score: Int) {
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        val total = cat?.get(CATSymptoms.TOTAL)
        if (total != null) {
            cat?.set(CATSymptoms.TOTAL, total + score)
        }
    }

    /**
     * Returns the `cat` object containing the CAT symptom scores.
     *
     * Preconditions:
     * - The `cat` object must be initialized before calling this function.
     *
     * Postconditions:
     * - If the `cat` object is set, it will be returned as a mutable map.
     * - If the `cat` object is not set, null will be returned.
     *
     * @return The `cat` object as a mutable map, or null if it is not set.
     */
    fun getCAT(): MutableMap<CATSymptoms, Int>? {
        if (cat == null) {
            Log.w(LOG_TAG, "Cannot get CAT object. CAT object is null.")
            return null
        }
        return cat
    }

    /**
     * Returns the COUGH symptom score.
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     *
     * Postconditions:
     * - If the cough symptom value in the `cat` object is set, it will be returned as an integer.
     * - If the cough symptom value in the `cat` object is not set, null will be returned.
     *
     * @return The cough score, an integer between 0 and 5, or null if the score is not set.
     */
    fun getCough(): Int? {
        if (cat == null) {
            Log.w(LOG_TAG, "Cannot get cough score. CAT object is null.")
            return null
        }
        val score = cat?.get(CATSymptoms.COUGH)
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        return score
    }

    /**
     * Returns the PHLEGM symptom score.
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     *
     * Postconditions:
     * - If the PHLEGM symptom value in the `cat` object is set, it will be returned as an integer.
     * - If the PHLEGM symptom value in the `cat` object is not set, null will be returned.
     *
     * @return The PHLEGM score, an integer between 0 and 5, or null if the score is not set.
     */
    fun getPhlegm(): Int? {
        if (cat == null) {
            Log.w(LOG_TAG, "Cannot get phlegm score. CAT object is null.")
            return null
        }
        val score = cat?.get(CATSymptoms.PHLEGM)
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        return score
    }

    /**
     * Returns the CHEST_TIGHTNESS symptom score.
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     *
     * Postconditions:
     * - If the CHEST_TIGHTNESS symptom value in the `cat` object is set, it will be returned as an integer.
     * - If the CHEST_TIGHTNESS symptom value in the `cat` object is not set, null will be returned.
     *
     * @return The CHEST_TIGHTNESS score, an integer between 0 and 5, or null if the score is not set.
     */
    fun getChestTightness(): Int? {
        if (cat == null) {
            Log.w(LOG_TAG, "Cannot get chest tightness score. CAT object is null.")
            return null
        }
        val score = cat?.get(CATSymptoms.CHEST_TIGHTNESS)
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        return score
    }

    /**
     * Returns the BREATHLESSNESS symptom score.
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     *
     * Postconditions:
     * - If the BREATHLESSNESS symptom value in the `cat` object is set, it will be returned as an integer.
     * - If the BREATHLESSNESS symptom value in the `cat` object is not set, null will be returned.
     *
     * @return The BREATHLESSNESS score, an integer between 0 and 5, or null if the score is not set.
     */
    fun getBreathlessness(): Int? {
        if (cat == null) {
            Log.w(LOG_TAG, "Cannot get breathlessness score. CAT object is null.")
            return null
        }
        val score = cat?.get(CATSymptoms.BREATHLESSNESS)
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        return score
    }

    /**
     * Returns the ACTIVITIES symptom score.
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     *
     * Postconditions:
     * - If the ACTIVITIES symptom value in the `cat` object is set, it will be returned as an integer.
     * - If the ACTIVITIES symptom value in the `cat` object is not set, null will be returned.
     *
     * @return The ACTIVITIES score, an integer between 0 and 5, or null if the score is not set.
     */
    fun getActivities(): Int? {
        if (cat == null) {
            Log.w(LOG_TAG, "Cannot get activities score. CAT object is null.")
            return null
        }
        val score = cat?.get(CATSymptoms.ACTIVITIES)
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        return score
    }

    /**
     * Returns the CONFIDENCE symptom score.
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     *
     * Postconditions:
     * - If the CONFIDENCE symptom value in the `cat` object is set, it will be returned as an integer.
     * - If the CONFIDENCE symptom value in the `cat` object is not set, null will be returned.
     *
     * @return The CONFIDENCE score, an integer between 0 and 5, or null if the score is not set.
     */
    fun getConfidence(): Int? {
        if (cat == null) {
            Log.w(LOG_TAG, "Cannot get confidence score. CAT object is null.")
            return null
        }
        val score = cat?.get(CATSymptoms.CONFIDENCE)
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        return score
    }

    /**
     * Returns the SLEEP symptom score.
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     *
     * Postconditions:
     * - If the SLEEP symptom value in the `cat` object is set, it will be returned as an integer.
     * - If the SLEEP symptom value in the `cat` object is not set, null will be returned.
     *
     * @return The SLEEP score, an integer between 0 and 5, or null if the score is not set.
     */
    fun getSleep(): Int? {
        if (cat == null) {
            Log.w(LOG_TAG, "Cannot get sleep score. CAT object is null.")
            return null
        }
        val score = cat?.get(CATSymptoms.SLEEP)
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        return score
    }

    /**
     * Returns the ENERGY symptom score.
     *
     * Preconditions:
     * - The `cat` object must be initialized and not null before calling this function.
     *
     * Postconditions:
     * - If the ENERGY symptom value in the `cat` object is set, it will be returned as an integer.
     * - If the ENERGY symptom value in the `cat` object is not set, null will be returned.
     *
     * @return The ENERGY score, an integer between 0 and 5, or null if the score is not set.
     */
    fun getEnergy(): Int? {
        if (cat == null) {
            Log.w(LOG_TAG, "Cannot get energy score. CAT object is null.")
            return null
        }
        val score = cat?.get(CATSymptoms.ENERGY)
        Assert.assertTrue("Score should be between 0 and 5", score in 0..5)
        return score
    }

    fun getTotal(): Int? {
        if (cat == null) {
            Log.w(LOG_TAG, "Cannot get energy score. CAT object is null.")
            return null
        }
        val score = cat?.get(CATSymptoms.TOTAL)
        Assert.assertTrue("Score should be between 0 and 40", score in 0..40)
        return score
    }
}