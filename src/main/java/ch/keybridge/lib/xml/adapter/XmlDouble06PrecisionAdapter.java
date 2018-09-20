/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.lib.xml.adapter;

import java.math.BigDecimal;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.lang.Double instance
 * and a simple number with six significant digits.
 *
 * @author Key Bridge LLC
 * @since v1.3.3 added 07/25/16
 */
public class XmlDouble06PrecisionAdapter extends XmlAdapter<Double, Double> {

  /**
   * Unmarshal a String to a Number
   *
   * @param v the string version
   * @return the double number
   * @throws Exception if the string cannot be parsed
   */
  @Override
  public Double unmarshal(Double v) throws Exception {
    return v;
  }

  /**
   * Reduce the precision and convert the double value decimal places.
   *
   * @param v the double value
   * @return the string value
   * @throws Exception if the double value is null.
   */
  @Override
  public Double marshal(Double v) throws Exception {
    try {
      if (v == null || v.isNaN()) {
        return null;
      }
      return new BigDecimal(v).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
    } catch (Exception e) {
      return null;
    }
  }
}
