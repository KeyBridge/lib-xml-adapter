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
package ch.keybridge.xml.adapter;

import ch.keybridge.xml.JaxbUtility;
import ch.keybridge.xml.adapter.map.EntrySet;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Key Bridge
 */
public class XmlMapAdapterTest {

  private XmlMapAdapter adapter;

  public XmlMapAdapterTest() {
  }

  @Before
  public void setUp() {
    this.adapter = new XmlMapAdapter();
  }

  @Test
  public void testMarshal() throws Exception {

    Map<String, Object> entryMap = new HashMap<>();

    entryMap.put("one", 1);
    entryMap.put("true", true);
    entryMap.put("false", Boolean.FALSE);
    entryMap.put("two-point-zero", 2.0);
    entryMap.put("english", Locale.ENGLISH);
    entryMap.put("local-now", LocalDateTime.now());
    entryMap.put("zoned-now", ZonedDateTime.now());
    entryMap.put("string1", "four score");
    entryMap.put("string2", "lorem ipsum");

//    entryMap.put("point", new GeometryFactory().createPoint(new Coordinate(123, 456)));
    /**
     * Calendar and Date are not mapped exactly as the millisecond component is
     * not conveyed. Calendar and Date are rounded to the nearest second.
     */
    //    entryMap.put("calendar", Calendar.getInstance(Locale.ITALY));
    EntrySet entrySet = new EntrySet();
//    entrySet.put("string1", "four score");
//    entrySet.put("string2", "lorem ipsum");

    entryMap.entrySet().forEach(e -> entrySet.put(e.getKey(), String.valueOf(e.getValue())));

//    for (Map.Entry<String, Object> entry : entryMap.entrySet()) {
//      entrySet.getEntries().add(new EntrySet.SimpleEntry(entry.getKey(), entry.getValue()));
//    }
//    entryMap.put("date", new Date());
//    EntrySet entryList = adapter.marshal(entryMap);
    String xml = JaxbUtility.marshal(entrySet);

    System.out.println(xml);

    Map<String, String> map = adapter.unmarshal(entrySet);

    for (Map.Entry<String, String> entry : map.entrySet()) {
      System.out.println("  " + entry);
    }

    EntrySet set = adapter.marshal(map);

    System.out.println(JaxbUtility.marshal(set));

    Assert.assertEquals(entrySet, set);

//    unmarshal = adapter.unmarshal(xml);
//
////    System.out.println(unmarshal);
//    for (Map.Entry<Object, Object> entry : unmarshal.entrySet()) {
//      System.out.println("  entry " + entry + " " + entry.getValue().getClass().getName());
//    }
//
//    for (Map.Entry<Object, Object> entry : unmarshal.entrySet()) {
//      Assert.assertEquals(entryMap.get(entry.getKey()), entry.getValue());
//    }
  }

}
