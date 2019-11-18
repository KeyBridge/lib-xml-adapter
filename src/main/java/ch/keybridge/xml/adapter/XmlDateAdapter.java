/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.xml.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.util.Date instance and
 * the ISO 8601 Date format.
 * <p>
 * The International Standard for the representation of dates and times is ISO
 * 8601. ISO 8601 describes an internationally accepted way to represent dates
 * and times using numbers.
 * <p>
 * ISO 8601 describes a large number of date/time formats. For example it
 * defines Basic Format, without punctuation, and Extended Format, with
 * punctuation, and it allows elements to be omitted. Different standards may
 * need different levels of granularity in the date and time.
 * <p>
 * This profile defines a complete date format according to the pattern
 * <code>YYYY-MM-DD</code>. For example, September 27, 2012 is represented as
 * 2012-09-27.
 *
 * @see
 * <a href="http://docs.oracle.com/javase/7/docs/api/java/text/DateFormat.html">DateFormat</a>
 * @see <a href="http://www.w3.org/TR/NOTE-datetime">W3C Date and Time
 * Formats</a>
 * @author Key Bridge LLC
 * @since v1.1.1 added 10/22/15
 */
public class XmlDateAdapter extends XmlAdapter<String, Date> {

  /**
   * A ISO 8601 Date configuration.
   */
  private static final String PATTERN_DATE = "yyyy-MM-dd";
  /**
   * UTC (Coordinated Universal Time)
   */
  private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
  /**
   * A date time formatter for the above declared pattern.
   */
  private final SimpleDateFormat dateFormatter = new SimpleDateFormat(PATTERN_DATE);

  public XmlDateAdapter() {
    dateFormatter.setTimeZone(UTC);
  }

  /**
   * Unmarshal a DATE string into a Date instance.
   * <p>
   * An example input value would be, for example: "2001-10-26"
   *
   * @param v The date datatype string in the UTC time zone
   * @return a Date instance, normalized to UTC, null if the input string is
   *         null or empty.
   */
  @Override
  public Date unmarshal(String v) {
    try {
      return v != null ? dateFormatter.parse(v) : null;
    } catch (ParseException parseException) {
      return null;
    }
  }

  /**
   * Write an Date to the ISO 8601 DATE format.
   * <p>
   * An example output value would be, for example: "2001-10-26"
   *
   * @param v the Date instance in the UTC time zone
   * @return a patterned date string, null if the input calendar is null
   */
  @Override
  public String marshal(Date v) {
    return v != null ? dateFormatter.format(v) : null;
  }
}
