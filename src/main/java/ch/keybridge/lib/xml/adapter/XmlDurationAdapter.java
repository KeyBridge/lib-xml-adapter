/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.lib.xml.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.TimeZone;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.util.Date instance and
 * the ISO 8601 Date and time format.
 * <p>
 * The International Standard for the representation of dates and times is ISO
 * 8601. ISO 8601 describes an internationally accepted way to represent dates
 * and times using numbers.
 * <p>
 * For example, September 27, 2012 is represented as 2012-09-27 according to the
 * pattern <code>YYYY-MM-DD</code>.
 * <p>
 * ISO 8601 describes a large number of date/time formats. For example it
 * defines Basic Format, without punctuation, and Extended Format, with
 * punctuation, and it allows elements to be omitted. Different standards may
 * need different levels of granularity in the date and time.
 * <p>
 * This profile defines a complete date plus time in UTC (Coordinated Universal
 * Time).
 *
 * @see
 * <a href="http://docs.oracle.com/javase/7/docs/api/java/text/DateFormat.html">DateFormat</a>
 * @see <a href="http://www.w3.org/TR/NOTE-datetime">W3C Date and Time
 * Formats</a>
 * @author Key Bridge LLC
 * @since v1.1.1 added 10/22/15
 */
@XmlTransient
public class XmlDurationAdapter extends XmlAdapter<String, Duration> {

  /**
   * A complete ISO 8601 Date Time configuration.
   * <p>
   * Different standards may need different levels of granularity in the date
   * and time. ISO 8601 defines six levels. Standards that reference this
   * profile should specify one or more of these granularities. If a given
   * standard allows more than one granularity, it should specify the meaning of
   * the dates and times with reduced precision, for example, the result of
   * comparing two dates with different precisions.
   * <p>
   * This implementation supports version 5: Complete date plus hours, minutes
   * and seconds. {@code YYYY-MM-DDThh:mm:ssTZD} (e.g.
   * 1997-07-16T19:20:30+01:00).
   * <p>
   * Times are expressed in UTC (Coordinated Universal Time), with a special UTC
   * designator ("Z").
   */
  private static final String PATTERN_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  /**
   * UTC (Coordinated Universal Time)
   */
  private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
  /**
   * A date time formatter for the above declared pattern.
   */
  private final SimpleDateFormat dateFormatter = new SimpleDateFormat(PATTERN_DATE_TIME);

  public XmlDurationAdapter() {
    dateFormatter.setTimeZone(UTC);
  }

  /**
   * Obtains a Duration from a text string such as PnDTnHnMn.nS. This will parse
   * a textual representation of a duration, including the string produced by
   * toString(). The formats accepted are based on the ISO-8601 duration format
   * PnDTnHnMn.nS with days considered to be exactly 24 hours.
   *
   * @param v The date-time datatype string, formatted in the UTC time zone.
   * @return a Date instance, normalized to UTC, null if the input string is
   *         null or empty.
   * @throws ParseException if the datatype string fails to parse
   */
  @Override
  public Duration unmarshal(String v) throws ParseException {
    return v != null ? Duration.parse(v) : null;
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
  public String marshal(Duration v) {
    return v != null ? v.toString() : null;
  }
}
