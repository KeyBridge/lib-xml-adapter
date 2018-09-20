/*
 * Copyright 2015 Caulfield IP Holdings (Caulfield) and affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * Software Code is protected by copyright. Caulfield hereby
 * reserves all rights and copyrights and no license is
 * granted under said copyrights in this Software License Agreement.
 * Caulfield generally licenses software for commercialization
 * pursuant to the terms of either a Standard Software Source Code
 * License Agreement or a Standard Product License Agreement.
 * A copy of these agreements may be obtained by sending a request
 * via email to info@caufield.org.
 */
package ch.keybridge.lib.xml.adapter;

import ch.keybridge.lib.xml.TextEncodingUtility;
import java.util.Map;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * XmlAdapter implementation to marshal and unmarshal MAP instances of STRING
 * value pairs to a URL-encoded string.
 *
 * @author Key Bridge LLC
 * @since WSIF 7.4.2 - created 11/17/15 supporting AExtensible
 * @since 1.3.1 - updated 06/02/16 to produce a list of Entries instead of
 * URL-encoded text. Update 07/28/17 to ensure non-null ArrayList.
 * @since v1.5.0 reintroduced into lib-xml-adapter
 */
public class XmlMapStringAdapter extends XmlAdapter<String, Map<String, String>> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<String, String> unmarshal(String v) throws Exception {
    return TextEncodingUtility.decodeKVMap(v);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String marshal(Map<String, String> v) throws Exception {
    return TextEncodingUtility.encodeKVMap(v);
  }

}
