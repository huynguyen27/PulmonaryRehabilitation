package com.example.pulmonaryrehabilitation.database

import android.annotation.SuppressLint
import android.util.Log
import com.example.pulmonaryrehabilitation.member.*
import com.google.android.gms.tasks.Task
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert

class DatabaseMethod : DatabaseInterface {
    private val LOG_TAG: String = "DatabaseMethod"

    // INDIVIDUAL USER METHODS
    /**
     * Fetches user data for the given user ID and sets data for the CurrentUser object.
     *
     * Pre-condition: `id` must be a valid, non-empty string representing a user ID.
     * Post-condition: On success, CurrentUser's data will be set with the  fetched data.
     *                 On failure, an error will be logged.
     *
     * @param id The user ID to fetch data for.
     */
    fun getUserDataFor(id: String): Task<MemberClass?> {
        val database = Firebase.database
        val myRef = database.getReference("Member/$id")
        val task = myRef.get().continueWith { task ->
            if (task.isSuccessful) {
                Log.i(LOG_TAG, "Successfully fetched user data from database")
                val userData = (task.result?.value as Map<String, Any?>?)?.let { it1 ->
                    convertFirebaseDataToMember(it1)
                }
                CurrentUser.setData(userData)
                return@continueWith userData
            } else {
                Log.e(LOG_TAG, "error- cannot get user data from database")
                throw task.exception!!
            }
        }
        return task
    }

    /**
     * Converts a given Firebase data map to a MemberClass object.
     *
     * Pre-condition: The provided data map must contain all keys except firstName, lastname, username, which are handled by firebase authentication, and don’t need to be stored in the database currently
     * Post-condition: Returns a MemberClass object containing the data from the input map.
     * @param data A map containing the Firebase data to be converted into a MemberClass object.
     * @return A MemberClass object
     **/
    private fun convertFirebaseDataToMember(data: Map<String, Any?>): MemberClass? {
        Log.d("convertFirebaseDataToMember", data.toString())
        val member = MemberClass(
            data["id"] as String,
            data["isAdmin"] as Boolean,
            data["firstName"] as String?,
            data["lastName"] as String?,
            data["username"] as String?,
            data["email"] as String,
            (data["steps"] as Long?)?.toInt(),
            (data["stepGoal"] as Long?)?.toInt(),
            data["gamificationHistory"] as MutableMap<String, GamificationHistoryClass>?,
            data["usageHistory"] as MutableMap<String, UsageHistoryClass>?,
            data["stepHistory"] as MutableMap<String, StepHistoryClass>?,
            data["questionnaireHistory"] as MutableMap<String, QuestionnaireHistoryClass>?,
            data["lastQuestionnaireDate"] as String?,
            data["streak"] as String?,
            data["weeklyExercisePoint"] as String?,
            data["mMRCHistory"] as MutableMap<String, mMRCScaleClass>?,
            data["lastCATmMRCDate"] as String?,
            data["ismMRCNext"] as Boolean?,
            data["catHistory"] as MutableMap<String, MutableMap<CATSymptoms, Int>?>?,
        )
        return member
    }

    /**
     * Updates the weekly exercise amount for a user with the given user ID. (Mon-Mon)
     *
     * Pre-condition: `id` must be a valid, non-empty string representing a user ID.
     *                `newWeeklyExercisePoint` must be a non-null string.
     * Post-condition: On success, the weekly exercise amount for the user with the given ID will be updated in the database.
     *                 On failure, an error will be logged.
     *
     * @param id The user ID whose weekly exercise amount needs to be updated.
     * @param newWeeklyExercisePoint The new weekly exercise amount value.
     */
    @SuppressLint("RestrictedApi")
    fun updateWeeklyExercisePoint(id: String, newWeeklyExercisePoint: String) {
        try {
            // assertion the new streak is a valid streak
            Assert.assertNotNull(
                "DatabaseMethod.updateWeeklyExercisePoint invoked:" +
                    "User ID is null",
                id
            )
            Assert.assertNotNull(
                "DatabaseMethod.updateWeeklyExercisePoint invoked:" +
                    "User newWeeklyExercisePoint is null",
                newWeeklyExercisePoint
            )

            Log.d(LOG_TAG, "DatabaseMethod.updateWeeklyExercisePoint invoked")

            val database = Firebase.database
            val myReference = database.getReference("Member/$id/weeklyExercisePoint")
            myReference.setValue(newWeeklyExercisePoint)
            // assertion that the streak was updated (is there a completion handler for updating?)
        } catch (exception: Exception) {
            Log.e("Error", "Exception encountered in DatabaseMethod.updateWeeklyExercisePoint", exception)
        }
    }

    /**
     * Updates the user's streak in Firebase based on the provided user ID and new streak value.
     *
     * Pre-condition: `id` must be a valid, non-empty string representing a user ID.
     *                `newStreak` must be a non-null string representing the new streak value.
     * Post-condition: On success, the user's streak value in Firebase is updated with the given `newStreak`.
     *                 On failure, an error message is logged, and the Firebase database remains unchanged.
     *
     * @param id The user ID for which the streak value needs to be updated.
     * @param newStreak The new streak value to be set for the user.
     */
    @SuppressLint("RestrictedApi")
    fun updateStreak(id: String, newStreak: String) {
        try {
            // assertion the new streak is a valid streak
            Assert.assertNotNull(
                "DatabaseMethod.updateStreak invoked:" +
                    "User ID is null",
                id
            )
            Assert.assertNotNull(
                "DatabaseMethod.updateStreak invoked:" +
                    "User newStreak is null",
                newStreak
            )

            Log.d(LOG_TAG, "DatabaseMethod.updateStreak invoked")

            val database = Firebase.database
            val myReference = database.getReference("Member/$id/streak")
            myReference.setValue(newStreak)
            // assertion that the streak was updated (is there a completion handler for updating?)
        } catch (exception: Exception) {
            Log.e("Error", "Exception encountered in DatabaseMethod.updateStreak()", exception)
        }
    }

    /**
     * A set of functions to update different user attributes in the Firebase database.
     * Each function updates a specific attribute of the user object in the Firebase database
     * under the path "Member/{user_id}/{attribute}".
     *
     * Pre-Condition:
     * - `id`: A valid, non-empty string representing a user ID.
     * - `newValue`: A non-null value of the appropriate type (String, Int, Boolean, or Map) to replace the existing value in the Firebase database.
     *
     *
     * Post-Condition:
     * On success, the specified user attribute in the Firebase database is updated with the provided `newValue`.
     */
    fun updateFirstNameFor(id: String, newName: String) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/firstName") // this is the path
        // directly to the item in Firebase
        myReference.setValue(newName)
    }
    fun updateLastNameFor(id: String, newName: String) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/lastName")
        myReference.setValue(newName)
    }
    fun updateStepCountGoalFor(id: String, newGoal: Int) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/stepGoal")
        myReference.setValue(newGoal)
    }
    fun updateAdminStatusFor(id: String, newStatus: Boolean) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/isAdmin")
        myReference.setValue(newStatus)
    }
    fun updateStepHistoryFor(id: String, newHistory: Map<String, StepHistoryClass>) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/stepHistory")
        val childName: String = newHistory.keys.toString().substring(1, newHistory.keys.toString().length - 1)
        newHistory.values.forEach {
            myReference.child(childName).setValue(it)
        }
    }
    fun updateQuestionnaireHistoryFor(
        id: String,
        newHistory: Map<String, QuestionnaireHistoryClass>
    ) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/questionnaireHistory")
        val childName: String = newHistory.keys.toString().substring(1, newHistory.keys.toString().length - 1)
        newHistory.values.forEach {
            myReference.child(childName).setValue(it)
        }
        val lastQuestionnaireRef = database.getReference("Member/$id/lastQuestionnaireDate")
        lastQuestionnaireRef.setValue(childName)
    }

    fun updatemMRCHistoryFor(id: String, newHistory: Map<String, mMRCScaleClass>) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/mMRCHistory")
        val childName: String = newHistory.keys.toString().substring(1, newHistory.keys.toString().length - 1)
        newHistory.values.forEach {
            myReference.child(childName).setValue(it)
        }
        val lastCATmMRCDateRef = database.getReference("Member/$id/lastCATmMRCDate")
        lastCATmMRCDateRef.setValue(childName)
        val ismMRCNextRef = database.getReference("Member/$id/ismMRCNext")
        ismMRCNextRef.setValue(false)
    }

    fun updateCatHistoryFor(id: String, newHistory: Map<String, MutableMap<CATSymptoms, Int>?>) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/catHistory")
        val childName: String = newHistory.keys.toString().substring(1, newHistory.keys.toString().length - 1)
        newHistory.values.forEach {
            myReference.child(childName).setValue(it.toString())
        }
        val lastCATmMRCDateRef = database.getReference("Member/$id/lastCATmMRCDate")
        lastCATmMRCDateRef.setValue(childName)
        val ismMRCNextRef = database.getReference("Member/$id/ismMRCNext")
        ismMRCNextRef.setValue(true)
    }

    fun updateUsageHistoryFor(id: String, newHistory: Map<String, UsageHistoryClass>) {
        try {
            val database = Firebase.database
            val myReference = database.getReference("Member/$id/usageHistory")
            val childName: String = newHistory.keys.toString().substring(1, newHistory.keys.toString().length - 1)
            newHistory.values.forEach {
                myReference.child(childName).setValue(it)
            }
        } catch (exception: Exception) {
            Log.e("Error", "Exception encountered in updateUsageHistoryFor()", exception)
        }
    }
    fun updateGamificationHistory(id: String, newHistory: Map<String, GamificationHistoryClass>) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/gamificationHistory")
        val childName: String = newHistory.keys.toString().substring(1, newHistory.keys.toString().length - 1)
        newHistory.values.forEach {
            myReference.child(childName).setValue(it)
        }
    }
}