package com.example.pulmonaryrehabilitation.activity.splash

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.questionnaire.QuestionnaireActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class GeneralSatisfactionQuestionnaireActivityInstrumentationTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(QuestionnaireActivity::class.java)

    @Test
    fun generalSatisfactionQuestionnaireActivityTest() {
        val textView = onView(
            allOf(
                withId(R.id.questionnaireMessage), withText("I have a question for you"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.questionnaireQuestion),
                withText("Please rate your satisfaction with the exercises"),
                withParent(withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Please rate your satisfaction with the exercises")))

        val radioButton = onView(
            allOf(
                withId(R.id.terribleAnswer), withText("Terrible"),
                withParent(
                    allOf(
                        withId(R.id.answer_group),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton.check(matches(isDisplayed()))

        val radioButton2 = onView(
            allOf(
                withId(R.id.badAnswer), withText("Bad"),
                withParent(
                    allOf(
                        withId(R.id.answer_group),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton2.check(matches(isDisplayed()))

        val radioButton3 = onView(
            allOf(
                withId(R.id.okAnswer), withText("OK"),
                withParent(
                    allOf(
                        withId(R.id.answer_group),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton3.check(matches(isDisplayed()))

        val radioButton4 = onView(
            allOf(
                withId(R.id.goodAnswer), withText("Good"),
                withParent(
                    allOf(
                        withId(R.id.answer_group),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton4.check(matches(isDisplayed()))

        val radioButton5 = onView(
            allOf(
                withId(R.id.greatAnswer), withText("Great"),
                withParent(
                    allOf(
                        withId(R.id.answer_group),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton5.check(matches(isDisplayed()))

        val button = onView(
            allOf(
                withId(R.id.submitQuestionnaire), withText("SUBMIT"),
                withParent(withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))
        button.check(matches(isClickable()))

        val radioGroup = onView(
            allOf(
                withId(R.id.answer_group),
                withParent(withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))),
                isDisplayed()
            )
        )
        radioGroup.check(matches(isDisplayed()))
    }
}
