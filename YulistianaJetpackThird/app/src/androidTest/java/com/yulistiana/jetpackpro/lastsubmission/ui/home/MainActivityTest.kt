package com.yulistiana.jetpackpro.lastsubmission.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.yulistiana.jetpackpro.lastsubmission.R
import com.yulistiana.jetpackpro.lastsubmission.ui.start.LoginActivity
import com.yulistiana.jetpackpro.lastsubmission.utils.IdlingResource
import org.junit.*
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(LoginActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.espressoTestIdlingResource)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun MovieAndTvShow() {
        Espresso.onView(withId(R.id.inputemail)).perform(
            ViewActions.typeText("yulisCantik@abcd.com"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(withId(R.id.inputpassword)).perform(
            ViewActions.typeText("saya suka kamu"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(withId(R.id.btnLogin)).perform(click())
        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))

        Espresso.onView(withId(R.id.tvshow_navigation)).perform(click())
        Espresso.onView(withId(R.id.rv_tvshows))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tvshows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))

        Espresso.onView(withId(R.id.movie_navigation)).perform(click())
    }

    @Test
    fun ViewTest() {
        Espresso.onView(withId(R.id.inputemail)).perform(
            ViewActions.typeText("yulisCantik@abcd.com"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(withId(R.id.inputpassword)).perform(
            ViewActions.typeText("saya suka kamu"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(withId(R.id.btnLogin)).perform(click())
        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.tvshow_navigation)).perform(click())
        Espresso.onView(withId(R.id.rv_tvshows))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.favorite_navigation)).perform(click())
        Espresso.onView(withText(R.string.tab_movie)).perform(click())
        Espresso.onView(withText(R.string.tab_tvshow)).perform(click())

        Espresso.onView(withId(R.id.movie_navigation)).perform(click())
    }

    @Test
    fun InsertAndUpdateFavorite() {

        Espresso.onView(withId(R.id.inputemail)).perform(
            ViewActions.typeText("yulisCantik@abcd.com"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(withId(R.id.inputpassword)).perform(
            ViewActions.typeText("saya suka kamu"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(withId(R.id.btnLogin)).perform(click())
        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        Espresso.onView(withId(R.id.favorite)).perform(click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.tvshow_navigation)).perform(click())
        Espresso.onView(withId(R.id.rv_tvshows))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        Espresso.onView(withId(R.id.favorite)).perform(click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.favorite_navigation)).perform(click())
        Espresso.onView(withId(R.id.rv_movies_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.favorite)).perform(click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.favorite_navigation)).perform(click())
        Espresso.onView(withText(R.string.tab_tvshow)).perform(click())
        Espresso.onView(withId(R.id.rv_tvshows_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.favorite)).perform(click())
        Espresso.pressBack()

    }

    @Test
    fun DetailMovie() {

        Espresso.onView(withId(R.id.inputemail)).perform(
            ViewActions.typeText("yulisCantik@abcd.com"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(withId(R.id.inputpassword)).perform(
            ViewActions.typeText("saya suka kamu"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(withId(R.id.btnLogin)).perform(click())
        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        Espresso.onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4,
                    click()
                ))

        Espresso.onView(withId(R.id.img_detail_hightlight))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.img_poster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()
    }

    @Test
    fun DetailTvShow() {

        Espresso.onView(withId(R.id.inputemail)).perform(
            ViewActions.typeText("yulisCantik@abcd.com"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(withId(R.id.inputpassword)).perform(
            ViewActions.typeText("saya suka kamu"),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(withId(R.id.btnLogin)).perform(click())
        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.tvshow_navigation)).perform(click())
        Espresso.onView(withId(R.id.rv_tvshows))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tvshows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        Espresso.onView(withId(R.id.rv_tvshows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4,
                    click()
                ))

        Espresso.onView(withId(R.id.img_detail_hightlight))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.img_poster))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()
    }

}