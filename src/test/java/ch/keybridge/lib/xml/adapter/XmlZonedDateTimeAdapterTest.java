/*
 * Copyright 2019 Key Bridge. All rights reserved. Use is subject to license
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

import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Key Bridge
 */
public class XmlZonedDateTimeAdapterTest {

  public XmlZonedDateTimeAdapterTest() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testSomeMethod() throws ParseException {

    ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));

    XmlZonedDateTimeAdapter adapter = new XmlZonedDateTimeAdapter();

    String xml = adapter.marshal(now);

    ZonedDateTime now2 = adapter.unmarshal(xml);

    Assert.assertEquals(now, now2);

    System.out.println("XmlZonedDateTimeAdapterTest OK");
  }

}
