/*
 * Copyright 2015 Key Bridge and affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * Software Code is protected by copyright. Key Bridge hereby
 * reserves all rights and copyrights and no license is
 * granted under said copyrights in this Software License Agreement.
 * Caulfield generally licenses software for commercialization
 * pursuant to the terms of either a Standard Software Source Code
 * License Agreement or a Standard Product License Agreement.
 * A copy of these agreements may be obtained by sending a request
 * via email to information@keybridgewireless.com.
 */
package ch.keybridge.lib.xml.adapter;

import ch.keybridge.lib.xml.adapter.XmlDateAdapter;
import ch.keybridge.lib.xml.adapter.XmlDateTimeAdapter;
import java.text.ParseException;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author Key Bridge LLC
 */
public class XmlDateTimeAdapterTest extends TestCase {

  public XmlDateTimeAdapterTest(String testName) {
    super(testName);
  }

  public void testDate() throws ParseException {
    System.out.println("DATE");
    Date now = new Date();
    System.out.println("  now " + now);

    String marshall = new XmlDateAdapter().marshal(now);
    System.out.println("  marshall: " + marshall);

    Date unmarshall = new XmlDateAdapter().unmarshal(marshall);
    System.out.println("  unmarshall " + unmarshall);
  }

  public void testDateTime() throws ParseException {
    System.out.println("DATE-TIME");
    Date now = new Date();
    System.out.println("  now " + now);

    String marshall = new XmlDateTimeAdapter().marshal(now);
    System.out.println("  marshall: " + marshall);

    Date unmarshall = new XmlDateTimeAdapter().unmarshal(marshall);
    System.out.println("  unmarshall " + unmarshall);
  }

}
