package com.greenzhabs.arc.ui.login;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.greenzhabs.arc.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

/** Saurav on 20-11-2017. */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

  @Rule
  public IntentsTestRule<LoginActivity> activityTestRule =
      new IntentsTestRule<>(LoginActivity.class);

  @Test
  public void loginWithValidUsernameAndPassword() {
    onView(withId(R.id.edtUsername)).perform(ViewActions.clearText());
    onView(withId(R.id.edtPassword)).perform(ViewActions.clearText());
    onView(withId(R.id.edtUsername)).perform(typeText("test@gmail.com"));
    onView(withId(R.id.edtPassword)).perform(typeText("Test1234"), closeSoftKeyboard());
    onView(withId(R.id.btnLogin)).perform(ViewActions.click());

    /*Context context = InstrumentationRegistry.getContext();
    Intents.intended(IntentMatchers.hasComponent(new ComponentName(context, PostActivity.class)));*/
  }

  @Test
  public void loginWithInValidUsernameAndPassword() {
    onView(withId(R.id.edtUsername)).perform(ViewActions.clearText());
    onView(withId(R.id.edtPassword)).perform(ViewActions.clearText());
    onView(withId(R.id.edtUsername)).perform(typeText("test1@gmail.com"));
    onView(withId(R.id.edtPassword)).perform(typeText("Test1235"), closeSoftKeyboard());
    onView(withId(R.id.btnLogin)).perform(ViewActions.click());

    onView(withText("Invalid username/password"))
        .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
        .check(matches(isDisplayed()));
  }

  @Test
  public void loginWithNullUsername() {
    onView(withId(R.id.edtUsername)).perform(ViewActions.clearText());
    onView(withId(R.id.edtPassword)).perform(ViewActions.clearText());
    onView(withId(R.id.btnLogin)).perform(ViewActions.click());
    onView(withText("Enter valid email address"))
        .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
        .check(matches(isDisplayed()));
  }

  @Test
  public void loginWithNullPassword() {
    onView(withId(R.id.edtUsername)).perform(ViewActions.clearText());
    onView(withId(R.id.edtPassword)).perform(ViewActions.clearText());
    onView(withId(R.id.edtUsername)).perform(typeText("test@gmail.com"));
    onView(withId(R.id.btnLogin)).perform(ViewActions.click());
    onView(withText("Enter valid password"))
        .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
        .check(matches(isDisplayed()));
  }

  @Test
  public void loginWithValidateUsername() {
    onView(withId(R.id.edtUsername)).perform(ViewActions.clearText());
    onView(withId(R.id.edtPassword)).perform(ViewActions.clearText());
    onView(withId(R.id.edtUsername)).perform(typeText("test"), closeSoftKeyboard());
    onView(withId(R.id.edtPassword)).perform(typeText("Test1234"), closeSoftKeyboard());
    onView(withId(R.id.btnLogin)).perform(ViewActions.click());
    onView(withText("Enter valid email address"))
        .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
        .check(matches(isDisplayed()));
  }

  @Test
  public void loginWithValidatePassword() {
    onView(withId(R.id.edtUsername)).perform(ViewActions.clearText());
    onView(withId(R.id.edtPassword)).perform(ViewActions.clearText());
    onView(withId(R.id.edtUsername)).perform(typeText("test@gmail.com"), closeSoftKeyboard());
    onView(withId(R.id.edtPassword)).perform(typeText("Test"), closeSoftKeyboard());
    onView(withId(R.id.btnLogin)).perform(ViewActions.click());
    onView(withText("Enter valid password"))
        .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
        .check(matches(isDisplayed()));
  }
}
