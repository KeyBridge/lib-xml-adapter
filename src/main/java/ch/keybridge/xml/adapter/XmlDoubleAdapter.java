/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.xml.adapter;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter for java.lang.Double.
 *
 * @author Key Bridge LLC
 * @since v1.4.0 added 07/15/18 to support XmlMapAdapter
 */
@XmlTransient
public class XmlDoubleAdapter extends XmlAdapter<String, Double> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Double unmarshal(String v) throws Exception {
    return Double.valueOf(v);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String marshal(Double v) throws Exception {
    if (v == null) {
      return null;
    }
    return v.toString();
  }
}
