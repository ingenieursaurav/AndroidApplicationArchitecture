package com.greenzhabs.arc.util;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Date;

/**
 * Saurav on 11-11-2017.<br>
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilTest {
  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
  @InjectMocks private Util util;

  @Test
  public void isNotLegalPassword() throws Exception {
    Assert.assertFalse("Empty Password", util.isLegalPassword(null));
    Assert.assertFalse("Empty Password", util.isLegalPassword(""));
    Assert.assertFalse("Empty Password", util.isLegalPassword(" "));
    Assert.assertFalse("Invalid password", util.isLegalPassword("test"));
    Assert.assertFalse("Invalid password", util.isLegalPassword("Test12"));
  }

  @Test
  public void isLegalPassword() throws Exception {
    Assert.assertTrue("Valid password", util.isLegalPassword("Test1234"));
    Assert.assertTrue("Valid password", util.isLegalPassword("mrAj6621@home"));
  }

  @Test
  public void formatDateString() {
    Date date = null;
    System.out.println(util.format(new Date(), "dd MMM yy, h:m a"));
    System.out.println(util.format(date, "dd MMM yy, h:m a"));
  }

  @Test
  public void stringToInt() {
    Assert.assertEquals(3, util.toInt("3"));
    Assert.assertEquals(0, util.toInt("3.35"));
    Assert.assertEquals(0, util.toInt(null));
  }

  @Test
  public void stringToDouble() {
    Assert.assertEquals(3.35, util.toDouble("3.35"), 2);
    Assert.assertEquals(Double.NaN, util.toDouble(null), 1);
  }

  @Test
  public void stringToFloat() {
    Assert.assertEquals(3.35, util.toFloat("3.35"), 2);
    Assert.assertEquals(Float.NaN, util.toFloat(null), 1);
  }

  @Test
  public void stringToLong() {
    Assert.assertEquals(3, util.toLong("3"));
    Assert.assertEquals(0, util.toLong("3.35"));
    Assert.assertEquals(0, util.toLong(null));
  }
}
