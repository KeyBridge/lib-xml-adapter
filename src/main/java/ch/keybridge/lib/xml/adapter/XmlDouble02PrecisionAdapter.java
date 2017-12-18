/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.lib.xml.adapter;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.lang.Double instance
 * and a simple number with two significant digits.
 *
 * @author Key Bridge LLC
 * @since v1.3.3 added 07/25/16
 */
@XmlTransient
public class XmlDouble02PrecisionAdapter extends XmlAdapter<String, Double> {

  /**
   * Unmarshal a String to a Number
   *
   * @param v the string version
   * @return the double number
   * @throws Exception if the string cannot be parsed
   */
  @Override
  public Double unmarshal(String v) throws Exception {
    try {
      return Double.valueOf(v);
    } catch (Exception exception) {
      return null;
    }
  }

  /**
   * Reduce the precision and convert the double value decimal places.
   *
   * @param v the double value
   * @return the string value
   * @throws Exception if the double value is null.
   */
  @Override
  public String marshal(Double v) throws Exception {
    /**
     * Intercept invalid double values, which may be produced by the abstract
     * properties parser.
     */
    if (v.isNaN()) {
      return null;
    }
    try {
      return new BigDecimal(v)
        .setScale(2, BigDecimal.ROUND_HALF_UP)
        .toString();
    } catch (Exception e) {
      return null;
    }
  }
}
