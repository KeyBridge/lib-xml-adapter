/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter for java.lang.Boolean.
 *
 * @author Key Bridge LLC
 * @since v1.4.0 added 07/15/18 to support XmlMapAdapter
 */
public class XmlBooleanAdapter extends XmlAdapter<String, Boolean> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Boolean unmarshal(String v) {
    return Boolean.valueOf(v);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String marshal(Boolean v) {
    if (v == null) {
      return null;
    }
    return v.toString();
  }
}
