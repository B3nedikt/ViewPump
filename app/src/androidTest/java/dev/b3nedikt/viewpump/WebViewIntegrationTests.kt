package dev.b3nedikt.viewpump

import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.DriverAtoms.webClick
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.b3nedikt.viewpump.sample.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WebViewIntegrationTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNumberPicker() {
        onWebView()
            .forceJavascriptEnabled()
            .withElement(findElement(Locator.ID, "numberPicker"))
            .perform(webClick())

    }

    @Test
    fun testDatePicker() {
        onWebView()
            .forceJavascriptEnabled()
            .withElement(findElement(Locator.ID, "datePicker"))
            .perform(webClick())

    }

    @Test
    fun testAlertDialog() {
        onWebView()
            .forceJavascriptEnabled()
            .withElement(findElement(Locator.ID, "alertDialog"))
            .perform(webClick())
    }
}