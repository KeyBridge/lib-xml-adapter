/*
 * Copyright 2017 Key Bridge. All rights reserved.
 * Use is subject to license terms.
 *
 * This software code is protected by Copyrights and remains the property of
 * Key Bridge and its suppliers, if any.
 * Key Bridge reserves all rights in and to Copyrights and
 * no license is granted under Copyrights in this Software License Agreement.
 *
 * Key Bridge generally licenses Copyrights for commercialization pursuant to
 * the terms of either a Standard Software Source Code License Agreement or a
 * Standard Product License Agreement. A copy of either Agreement can be
 * obtained upon request from: info@keybridgewireless.com
 *
 * All information contained herein is the property of {project.organization!user}
 * and its suppliers, if any. The intellectual and technical concepts contained
 * herein are proprietary.
 */
package ch.keybridge.lib.xml;

import java.io.IOException;
import javax.xml.bind.JAXBException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Key Bridge
 */
public class JaxbUtilityTest {

  private Address address;

  public JaxbUtilityTest() {
  }

  @Before
  public void setUp() {
    address = Address.getInstance("10101 Boolean Blvd.", "Binary", "IO", "10010", "OI");
  }

  @Test
  public void testToFromXML() throws JAXBException, Exception {
//    Address address = Address.getInstance("10101 Boolean Blvd.", "Binary", "IO", "10010", "OI");
    String xml = JaxbUtility.marshal(address);
    Address x = JaxbUtility.unmarshal(xml, Address.class);
    Assert.assertEquals(x, address);
  }

  @Test
  public void testJSONSerialization() throws IOException {
    String json = JaxbUtility.toJson(address);
    System.out.println("JSON: ");
    System.out.println(json);

    Address a = JaxbUtility.fromJson(json, Address.class);

    System.out.println("read: " + a);

  }

}
