/*
 * Copyright 2018 Key Bridge. All rights reserved. Use is subject to license
 * terms.
 *
 * This software code is protected by Copyrights and remains the property of
 * Key Bridge and its suppliers, if any. Key Bridge reserves all rights in and to
 * Copyrights and no license is granted under Copyrights in this Software
 * License Agreement.
 *
 * Key Bridge generally licenses Copyrights for commercialization pursuant to
 * the terms of either a Standard Software Source Code License Agreement or a
 * Standard Product License Agreement. A copy of either Agreement can be
 * obtained upon request by sending an email to info@keybridgewireless.com.
 *
 * All information contained herein is the property of Key Bridge and its
 * suppliers, if any. The intellectual and technical concepts contained herein
 * are proprietary.
 */
package ch.keybridge.lib.xml.adapter;

import java.util.Calendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Key Bridge
 */
public class XmlCalendarAdapterTest {

  private XmlCalendarAdapter adapter;

  public XmlCalendarAdapterTest() {
  }

  @Before
  public void setUp() {
    this.adapter = new XmlCalendarAdapter();
  }

  @Test
  public void testCalendarAdapter() throws Exception {
    System.out.println("Test XmlCalendarAdapter ");
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.MILLISECOND, 0);
//    cal.setTimeZone(TimeZone.getTimeZone("UTC"));
//    System.out.println("Calendar\n" + cal);
    String xmlcal = adapter.marshal(cal);

//    System.out.println("xmlcal\n" + xmlcal);
    Calendar unmarshal = adapter.unmarshal(xmlcal);

    System.out.println("  start : " + cal.getTime().getTime());
    System.out.println("  finish: " + unmarshal.getTime().getTime());

//    System.out.println("start : " + cal);
//    System.out.println("finish: " + unmarshal);
    Assert.assertEquals(cal.getTime(), unmarshal.getTime());

  }

}
