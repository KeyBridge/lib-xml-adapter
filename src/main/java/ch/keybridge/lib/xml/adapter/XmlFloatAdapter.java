/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.lib.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter for java.lang.Float.
 *
 * @author Key Bridge LLC
 * @since v1.4.0 added 07/15/18 to support XmlMapAdapter
 */
public class XmlFloatAdapter extends XmlAdapter<String, Float> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Float unmarshal(String v) throws Exception {
    return Float.valueOf(v);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String marshal(Float v) throws Exception {
    if (v == null) {
      return null;
    }
    return v.toString();
  }
}
