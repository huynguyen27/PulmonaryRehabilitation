package com.example.pulmonaryrehabilitation.member

interface mMRCScale {
    var grade: Int

    fun gradeInt(): Int?
    override fun toString(): String
}