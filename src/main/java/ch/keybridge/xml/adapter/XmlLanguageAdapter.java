/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.keybridge.xml.adapter;

import java.util.Locale;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Java XML adapter to translate between a standard java.util.Locale instance
 * and the language code.
 *
 * @author Key Bridge LLC
 * @since 3.0.0 added 02/17/19
 *
 * @author jesse
 */
public class XmlLanguageAdapter extends XmlAdapter<String, Locale> {

  @Override
  public Locale unmarshal(String v) {
    if (v != null) {
      for (Locale locale : Locale.getAvailableLocales()) {
        if (locale.getLanguage().equals(new Locale(v).getLanguage())) {
          return locale;
        }
      }
    }
    return null;
  }

  @Override
  public String marshal(Locale v) {
    return v == null ? null : v.getLanguage();
  }

}
