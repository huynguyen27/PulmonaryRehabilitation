package com.example.pulmonaryrehabilitation.activity.splash

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.questionnaire.CATActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CATActivityInstrumentationTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(CATActivity::class.java)

    @Test
    fun cATActivityInstrumentationTest() {
        val materialRadioButton = onView(
            allOf(
                withId(R.id.cough0), withText("0 (\"I never cough\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.coughAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            4
                        )
                    ),
                    0
                )
            )
        )
        materialRadioButton.perform(scrollTo(), click())

        val materialRadioButton2 = onView(
            allOf(
                withId(R.id.cough1), withText("1"),
                childAtPosition(
                    allOf(
                        withId(R.id.coughAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            4
                        )
                    ),
                    1
                )
            )
        )
        materialRadioButton2.perform(scrollTo(), click())

        val materialRadioButton3 = onView(
            allOf(
                withId(R.id.cough2), withText("2"),
                childAtPosition(
                    allOf(
                        withId(R.id.coughAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            4
                        )
                    ),
                    2
                )
            )
        )
        materialRadioButton3.perform(scrollTo(), click())

        val materialRadioButton4 = onView(
            allOf(
                withId(R.id.cough3), withText("3"),
                childAtPosition(
                    allOf(
                        withId(R.id.coughAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            4
                        )
                    ),
                    3
                )
            )
        )
        materialRadioButton4.perform(scrollTo(), click())

        val materialRadioButton5 = onView(
            allOf(
                withId(R.id.cough4), withText("4"),
                childAtPosition(
                    allOf(
                        withId(R.id.coughAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            4
                        )
                    ),
                    4
                )
            )
        )
        materialRadioButton5.perform(scrollTo(), click())

        val materialRadioButton6 = onView(
            allOf(
                withId(R.id.cough5), withText("5 (\"I cough all the time\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.coughAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            4
                        )
                    ),
                    5
                )
            )
        )
        materialRadioButton6.perform(scrollTo(), click())

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
                withId(R.id.questionnaireQuestion), withText("Please rate your COPD symptoms"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Please rate your COPD symptoms")))

        val textView3 = onView(
            allOf(
                withId(R.id.questionnaireQuestion), withText("Please rate your COPD symptoms"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Please rate your COPD symptoms")))

        val materialRadioButton7 = onView(
            allOf(
                withId(R.id.phlegm0),
                withText("0 (\"I have no phlegm (mucus) in my chest at all\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.phlegmAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            6
                        )
                    ),
                    0
                )
            )
        )
        materialRadioButton7.perform(scrollTo(), click())

        val materialRadioButton8 = onView(
            allOf(
                withId(R.id.phlegm1), withText("1"),
                childAtPosition(
                    allOf(
                        withId(R.id.phlegmAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            6
                        )
                    ),
                    1
                )
            )
        )
        materialRadioButton8.perform(scrollTo(), click())

        val materialRadioButton9 = onView(
            allOf(
                withId(R.id.phlegm2), withText("2"),
                childAtPosition(
                    allOf(
                        withId(R.id.phlegmAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            6
                        )
                    ),
                    2
                )
            )
        )
        materialRadioButton9.perform(scrollTo(), click())

        val materialRadioButton10 = onView(
            allOf(
                withId(R.id.phlegm3), withText("3"),
                childAtPosition(
                    allOf(
                        withId(R.id.phlegmAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            6
                        )
                    ),
                    3
                )
            )
        )
        materialRadioButton10.perform(scrollTo(), click())

        val materialRadioButton11 = onView(
            allOf(
                withId(R.id.phlegm4), withText("4"),
                childAtPosition(
                    allOf(
                        withId(R.id.phlegmAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            6
                        )
                    ),
                    4
                )
            )
        )
        materialRadioButton11.perform(scrollTo(), click())

        val materialRadioButton12 = onView(
            allOf(
                withId(R.id.phlegm5),
                withText("5 (\"My chest is completely full of phlegm (mucus)\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.phlegmAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            6
                        )
                    ),
                    5
                )
            )
        )
        materialRadioButton12.perform(scrollTo(), click())

        val materialRadioButton13 = onView(
            allOf(
                withId(R.id.chestTightness0),
                withText("0 (\"My chest does not feel tight at all\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.chestTightnessAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            8
                        )
                    ),
                    0
                )
            )
        )
        materialRadioButton13.perform(scrollTo(), click())

        val materialRadioButton14 = onView(
            allOf(
                withId(R.id.chestTightness1), withText("1"),
                childAtPosition(
                    allOf(
                        withId(R.id.chestTightnessAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            8
                        )
                    ),
                    1
                )
            )
        )
        materialRadioButton14.perform(scrollTo(), click())

        val materialRadioButton15 = onView(
            allOf(
                withId(R.id.chestTightness2), withText("2"),
                childAtPosition(
                    allOf(
                        withId(R.id.chestTightnessAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            8
                        )
                    ),
                    2
                )
            )
        )
        materialRadioButton15.perform(scrollTo(), click())

        val materialRadioButton16 = onView(
            allOf(
                withId(R.id.chestTightness3), withText("3"),
                childAtPosition(
                    allOf(
                        withId(R.id.chestTightnessAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            8
                        )
                    ),
                    3
                )
            )
        )
        materialRadioButton16.perform(scrollTo(), click())

        val materialRadioButton17 = onView(
            allOf(
                withId(R.id.chestTightness4), withText("4"),
                childAtPosition(
                    allOf(
                        withId(R.id.chestTightnessAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            8
                        )
                    ),
                    4
                )
            )
        )
        materialRadioButton17.perform(scrollTo(), click())

        val materialRadioButton18 = onView(
            allOf(
                withId(R.id.chestTightness5), withText("5 (\"My chest feels very tight\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.chestTightnessAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            8
                        )
                    ),
                    5
                )
            )
        )
        materialRadioButton18.perform(scrollTo(), click())

        val materialRadioButton19 = onView(
            allOf(
                withId(R.id.breathlessness0),
                withText("0 (\"When I walk up a hill or one flight of stairs I am not breathless\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.breathlessnessAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            10
                        )
                    ),
                    0
                )
            )
        )
        materialRadioButton19.perform(scrollTo(), click())

        val materialRadioButton20 = onView(
            allOf(
                withId(R.id.breathlessness1), withText("1"),
                childAtPosition(
                    allOf(
                        withId(R.id.breathlessnessAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            10
                        )
                    ),
                    1
                )
            )
        )
        materialRadioButton20.perform(scrollTo(), click())

        val materialRadioButton21 = onView(
            allOf(
                withId(R.id.breathlessness2), withText("2"),
                childAtPosition(
                    allOf(
                        withId(R.id.breathlessnessAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            10
                        )
                    ),
                    2
                )
            )
        )
        materialRadioButton21.perform(scrollTo(), click())

        val materialRadioButton22 = onView(
            allOf(
                withId(R.id.breathlessness3), withText("3"),
                childAtPosition(
                    allOf(
                        withId(R.id.breathlessnessAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            10
                        )
                    ),
                    3
                )
            )
        )
        materialRadioButton22.perform(scrollTo(), click())

        val materialRadioButton23 = onView(
            allOf(
                withId(R.id.breathlessness4), withText("4"),
                childAtPosition(
                    allOf(
                        withId(R.id.breathlessnessAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            10
                        )
                    ),
                    4
                )
            )
        )
        materialRadioButton23.perform(scrollTo(), click())

        val materialRadioButton24 = onView(
            allOf(
                withId(R.id.breathlessness5),
                withText("5 (\"When I walk up a hill or one flight of stairs I am very breathless\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.breathlessnessAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            10
                        )
                    ),
                    5
                )
            )
        )
        materialRadioButton24.perform(scrollTo(), click())

        val materialRadioButton25 = onView(
            allOf(
                withId(R.id.activities0),
                withText("0 (\"I am not limited doing any activities at home\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.activitiesAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            12
                        )
                    ),
                    0
                )
            )
        )
        materialRadioButton25.perform(scrollTo(), click())

        val materialRadioButton26 = onView(
            allOf(
                withId(R.id.activities1), withText("1"),
                childAtPosition(
                    allOf(
                        withId(R.id.activitiesAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            12
                        )
                    ),
                    1
                )
            )
        )
        materialRadioButton26.perform(scrollTo(), click())

        val materialRadioButton27 = onView(
            allOf(
                withId(R.id.activities2), withText("2"),
                childAtPosition(
                    allOf(
                        withId(R.id.activitiesAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            12
                        )
                    ),
                    2
                )
            )
        )
        materialRadioButton27.perform(scrollTo(), click())

        val materialRadioButton28 = onView(
            allOf(
                withId(R.id.activities3), withText("3"),
                childAtPosition(
                    allOf(
                        withId(R.id.activitiesAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            12
                        )
                    ),
                    3
                )
            )
        )
        materialRadioButton28.perform(scrollTo(), click())

        val materialRadioButton29 = onView(
            allOf(
                withId(R.id.activities4), withText("4"),
                childAtPosition(
                    allOf(
                        withId(R.id.activitiesAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            12
                        )
                    ),
                    4
                )
            )
        )
        materialRadioButton29.perform(scrollTo(), click())

        val materialRadioButton30 = onView(
            allOf(
                withId(R.id.activities5),
                withText("5 (\"I am very limited doing activities at home\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.activitiesAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            12
                        )
                    ),
                    5
                )
            )
        )
        materialRadioButton30.perform(scrollTo(), click())

        val materialRadioButton31 = onView(
            allOf(
                withId(R.id.confidence0),
                withText("0 (\"I am confident leaving my home despite my lung condition\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.confidenceAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            14
                        )
                    ),
                    0
                )
            )
        )
        materialRadioButton31.perform(scrollTo(), click())

        val materialRadioButton32 = onView(
            allOf(
                withId(R.id.confidence1), withText("1"),
                childAtPosition(
                    allOf(
                        withId(R.id.confidenceAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            14
                        )
                    ),
                    1
                )
            )
        )
        materialRadioButton32.perform(scrollTo(), click())

        val materialRadioButton33 = onView(
            allOf(
                withId(R.id.confidence2), withText("2"),
                childAtPosition(
                    allOf(
                        withId(R.id.confidenceAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            14
                        )
                    ),
                    2
                )
            )
        )
        materialRadioButton33.perform(scrollTo(), click())

        val materialRadioButton34 = onView(
            allOf(
                withId(R.id.confidence3), withText("3"),
                childAtPosition(
                    allOf(
                        withId(R.id.confidenceAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            14
                        )
                    ),
                    3
                )
            )
        )
        materialRadioButton34.perform(scrollTo(), click())

        val materialRadioButton35 = onView(
            allOf(
                withId(R.id.confidence4), withText("4"),
                childAtPosition(
                    allOf(
                        withId(R.id.confidenceAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            14
                        )
                    ),
                    4
                )
            )
        )
        materialRadioButton35.perform(scrollTo(), click())

        val materialRadioButton36 = onView(
            allOf(
                withId(R.id.confidence5),
                withText("5 (\"I am not at all confident leaving my home despite my lung condition\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.confidenceAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            14
                        )
                    ),
                    5
                )
            )
        )
        materialRadioButton36.perform(scrollTo(), click())

        val materialRadioButton37 = onView(
            allOf(
                withId(R.id.sleep0), withText("0 (\"I sleep soundly\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.sleepAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            16
                        )
                    ),
                    0
                )
            )
        )
        materialRadioButton37.perform(scrollTo(), click())

        val materialRadioButton38 = onView(
            allOf(
                withId(R.id.sleep1), withText("1"),
                childAtPosition(
                    allOf(
                        withId(R.id.sleepAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            16
                        )
                    ),
                    1
                )
            )
        )
        materialRadioButton38.perform(scrollTo(), click())

        val materialRadioButton39 = onView(
            allOf(
                withId(R.id.sleep2), withText("2"),
                childAtPosition(
                    allOf(
                        withId(R.id.sleepAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            16
                        )
                    ),
                    2
                )
            )
        )
        materialRadioButton39.perform(scrollTo(), click())

        val materialRadioButton40 = onView(
            allOf(
                withId(R.id.sleep3), withText("3"),
                childAtPosition(
                    allOf(
                        withId(R.id.sleepAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            16
                        )
                    ),
                    3
                )
            )
        )
        materialRadioButton40.perform(scrollTo(), click())

        val materialRadioButton41 = onView(
            allOf(
                withId(R.id.sleep4), withText("4"),
                childAtPosition(
                    allOf(
                        withId(R.id.sleepAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            16
                        )
                    ),
                    4
                )
            )
        )
        materialRadioButton41.perform(scrollTo(), click())

        val materialRadioButton42 = onView(
            allOf(
                withId(R.id.sleep5),
                withText("5 (\"I do not sleep soundly because of my lungcondition\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.sleepAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            16
                        )
                    ),
                    5
                )
            )
        )
        materialRadioButton42.perform(scrollTo(), click())

        val materialRadioButton43 = onView(
            allOf(
                withId(R.id.energy0), withText("0 (\"I have lots of energy\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.energyAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            18
                        )
                    ),
                    0
                )
            )
        )
        materialRadioButton43.perform(scrollTo(), click())

        val materialRadioButton44 = onView(
            allOf(
                withId(R.id.energy1), withText("1"),
                childAtPosition(
                    allOf(
                        withId(R.id.energyAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            18
                        )
                    ),
                    1
                )
            )
        )
        materialRadioButton44.perform(scrollTo(), click())

        val materialRadioButton45 = onView(
            allOf(
                withId(R.id.energy2), withText("2"),
                childAtPosition(
                    allOf(
                        withId(R.id.energyAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            18
                        )
                    ),
                    2
                )
            )
        )
        materialRadioButton45.perform(scrollTo(), click())

        val materialRadioButton46 = onView(
            allOf(
                withId(R.id.energy3), withText("3"),
                childAtPosition(
                    allOf(
                        withId(R.id.energyAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            18
                        )
                    ),
                    3
                )
            )
        )
        materialRadioButton46.perform(scrollTo(), click())

        val materialRadioButton47 = onView(
            allOf(
                withId(R.id.energy4), withText("4"),
                childAtPosition(
                    allOf(
                        withId(R.id.energyAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            18
                        )
                    ),
                    4
                )
            )
        )
        materialRadioButton47.perform(scrollTo(), click())

        val materialRadioButton48 = onView(
            allOf(
                withId(R.id.energy5), withText("5 (\"I have no energy at all\")"),
                childAtPosition(
                    allOf(
                        withId(R.id.energyAnswerGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            18
                        )
                    ),
                    5
                )
            )
        )
        materialRadioButton48.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>,
        position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                    view == parent.getChildAt(position)
            }
        }
    }
}
