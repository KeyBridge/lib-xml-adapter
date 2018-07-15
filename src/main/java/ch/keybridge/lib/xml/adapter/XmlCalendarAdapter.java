/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.lib.xml.adapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter for java.lang.Calendar.
 * <p>
 * Note: the input and output do not match exactly as sub-second values are
 * discarded.
 *
 * @author Key Bridge LLC
 * @since v1.4.0 added 07/15/18 to support XmlMapAdapter
 */
@XmlTransient
public class XmlCalendarAdapter extends XmlAdapter<String, Calendar> {

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

  /**
   * {@inheritDoc}
   */
  @Override
  public Calendar unmarshal(String v) throws Exception {
    if (v == null) {
      return null;
    }
    Calendar c = Calendar.getInstance();
    c.set(Calendar.MILLISECOND, 0);
    c.setTime(dateFormatter.parse(v));
    return c;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String marshal(Calendar v) throws Exception {
    return v != null ? dateFormatter.format(v.getTime()) : null;
  }
}
