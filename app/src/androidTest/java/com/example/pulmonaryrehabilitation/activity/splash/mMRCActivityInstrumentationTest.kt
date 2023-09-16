package com.example.pulmonaryrehabilitation.activity.splash

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.questionnaire.mMRCActivity
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class mMRCActivityInstrumentationTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(mMRCActivity::class.java)

    @Test
    fun mMRCActivityInstrumentationTest() {
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
                withText("Please rate your shortness of breath"),
                withParent(withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Please rate your shortness of breath")))

        val radioGroup = onView(
            allOf(
                withId(R.id.mmrc_answer_group),
                withParent(withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))),
                isDisplayed()
            )
        )
        radioGroup.check(matches(isDisplayed()))

        val radioButton = onView(
            allOf(
                withId(R.id.zero), withText("0 - Breathlessness only on strenuous exercise"),
                withParent(
                    allOf(
                        withId(R.id.mmrc_answer_group),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton.check(matches(isDisplayed()))

        val radioButton2 = onView(
            allOf(
                withId(R.id.one),
                withText("1 - Breathless when hurrying on the level or walking up a slight hill"),
                withParent(
                    allOf(
                        withId(R.id.mmrc_answer_group),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton2.check(matches(isDisplayed()))

        val radioButton3 = onView(
            allOf(
                withId(R.id.two),
                withText("2 - Walks slower than people of the same age on the level due to shortness of breath or need to stop for breath when walking at own pace "),
                withParent(
                    allOf(
                        withId(R.id.mmrc_answer_group),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton3.check(matches(isDisplayed()))

        val radioButton4 = onView(
            allOf(
                withId(R.id.three),
                withText("3 - Short of breath after walking few minutes on the level or about 100 yards(90m) "),
                withParent(
                    allOf(
                        withId(R.id.mmrc_answer_group),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        radioButton4.check(matches(isDisplayed()))

        val radioButton5 = onView(
            allOf(
                withId(R.id.four),
                withText("4 - Too breathless to leave house or breathless when dressing or undressing"),
                withParent(
                    allOf(
                        withId(R.id.mmrc_answer_group),
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
        button.check(matches(isClickable()))
        button.check(matches(isDisplayed()))
    }
}
