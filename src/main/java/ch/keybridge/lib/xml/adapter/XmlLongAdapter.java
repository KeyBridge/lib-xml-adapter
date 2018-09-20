/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.lib.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter for java.lang.Long.
 *
 * @author Key Bridge LLC
 * @since v1.4.0 added 07/15/18 to support XmlMapAdapter
 */
public class XmlLongAdapter extends XmlAdapter<String, Long> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Long unmarshal(String v) throws Exception {
    return Long.valueOf(v);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String marshal(Long v) throws Exception {
    if (v == null) {
      return null;
    }
    return v.toString();
  }
}
