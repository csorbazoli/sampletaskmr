/** This class was generated by GenTest@Mobacar */
package com.arvoia.sampletask.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ErrorInfoTest {

  /**
   * If the class has a default constructor, then you don't need to instantiate it manually.
   * InjectMocks annotation will do it.<br/>
   * Otherwise use the {@link #setupTest()} method for creating the underTest object.
   */
  @InjectMocks
  private ErrorInfo underTest;

  @Before
  public void setupTest() {
    // prepare test
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetCode() {
    // given
    int value = 1;
    underTest.setCode(value);
    // when
    int actual = underTest.getCode();
    // then
    assertEquals(value, actual);
  }
  
  @Test
  public void testGetDescription()  {
    // given
    String value = "test";
    underTest.setDescription(value);
    // when
    String actual = underTest.getDescription();
    // then
    assertEquals(value, actual);
  }
  
}
