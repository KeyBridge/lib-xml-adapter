/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.lib.xml.adapter;

import java.text.ParseException;
import java.time.Period;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard Period instance and a
 * String.
 *
 * @author Key Bridge LLC 10/13/17
 * @since v1.0.3
 */
public class XmlPeriodAdapter extends XmlAdapter<String, Period> {

  public XmlPeriodAdapter() {
  }

  /**
   * Obtains a Period from a text string such as PnDTnHnMn.nS. This will parse a
   * textual representation of a duration, including the string produced by
   * toString(). The formats accepted are based on the ISO-8601 duration format
   * PnDTnHnMn.nS with days considered to be exactly 24 hours.
   *
   * @param v The date-time datatype string, formatted in the UTC time zone.
   * @return a Date instance, normalized to UTC, null if the input string is
   *         null or empty.
   * @throws ParseException if the datatype string fails to parse
   */
  @Override
  public Period unmarshal(String v) throws ParseException {
    return v != null ? Period.parse(v) : null;
  }

  /**
   * Write a string representation of this duration using ISO-8601 seconds based
   * representation, such as PT8H6M12.345S. The format of the returned string
   * will be PTnHnMnS, where n is the relevant hours, minutes or seconds part of
   * the duration. Any fractional seconds are placed after a decimal point i the
   * seconds section. If a section has a zero value, it is omitted. The hours,
   * minutes and seconds will all have the same sign.
   *
   * @param v the Date instance, formatted in the UTC time zone
   * @return a patterned date string, null if the input calendar is null
   */
  @Override
  public String marshal(Period v) {
    return v != null ? v.toString() : null;
  }
}
