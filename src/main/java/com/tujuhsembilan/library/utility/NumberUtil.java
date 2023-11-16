package com.tujuhsembilan.library.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumberUtil {

  public static String spellNumber_enUS(Long val) {
    final String[] nom = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve" };
    final String[] nomSpec = { "zero", "one", "twen", "thir", "four", "fif", "six", "seven", "eigh", "nine" };
    final String and = " and ";

    if (val < 0) {
      return "negative " + spellNumber_enUS(Math.abs(val));
    } else if (val >= 0 && val <= 12) {
      return nom[val.intValue()];
    } else if (val <= 99) {
      boolean teen = (val <= 19);
      return nomSpec[(teen) ? (val.intValue() % 10) : (val.intValue() / 10)]
          + ((teen) ? "teen" : ("ty" + ((val % 10 > 0) ? (" " + spellNumber_enUS(val % 10)) : (""))));
    } else if (val <= 999) {
      return nom[val.intValue() / 100] + " hundred" + ((val % 100 > 0) ? (and + spellNumber_enUS(val % 100)) : (""));
    } else if (val <= 999_999) {
      return spellNumber_enUS(val / 1_000) + " thousand"
          + ((val % 1_000 > 0) ? (and + spellNumber_enUS(val % 1_000)) : (""));
    } else if (val <= 999_999_999) {
      return spellNumber_enUS(val / 1_000_000) + " million"
          + ((val % 1_000_000 > 0) ? (and + spellNumber_enUS(val % 1_000_000)) : (""));
    } else if (val <= 999_999_999_999L) {
      return spellNumber_enUS(val / 1_000_000_000L) + " billion"
          + ((val % 1_000_000_000L > 0) ? (and + spellNumber_enUS(val % 1_000_000_000L)) : (""));
    } else if (val <= 999_999_999_999_999L) {
      return spellNumber_enUS(val / 1_000_000_000_000L) + " trillion"
          + ((val % 1_000_000_000_000L > 0) ? (and + spellNumber_enUS(val % 1_000_000_000_000L)) : (""));
    } else if (val <= 999_999_999_999_999_999L) {
      return spellNumber_enUS(val / 1_000_000_000_000_000L) + " quadrillion"
          + ((val % 1_000_000_000_000_000L > 0) ? (and + spellNumber_enUS(val % 1_000_000_000_000_000L)) : (""));
    } else {
      return "";
    }
  }

  public static String spellNumber_idID(Long val) {
    final String[] nom = { "nol", "satu", "dua", "tiga", "empat", "lima", "enam", "tujuh", "delapan", "sembilan",
        "sepuluh", "sebelas" };

    if (val < 0) {
      return "negatif " + spellNumber_idID(Math.abs(val));
    } else if (val >= 0 && val < 12) {
      return nom[val.intValue()];
    } else if (val <= 19) {
      return nom[val.intValue() % 10] + " belas";
    } else if (val <= 99) {
      return nom[val.intValue() / 10] + " puluh" + ((val % 10 > 0) ? (" " + nom[val.intValue() % 10]) : "");
    } else if (val <= 199) {
      return "seratus" + ((val % 100 > 0) ? (" " + spellNumber_idID(val % 100)) : "");
    } else if (val <= 999) {
      return nom[val.intValue() / 100] + " ratus" + ((val % 100 > 0) ? (" " + spellNumber_idID(val % 100)) : "");
    } else if (val <= 1_999) {
      return "seribu" + ((val % 1_000 > 0) ? (" " + spellNumber_idID(val % 1_000)) : "");
    } else if (val <= 999_999) {
      return spellNumber_idID(val / 1_000) + " ribu"
          + ((val % 1_000 > 0) ? (" " + spellNumber_idID(val % 1_000)) : "");
    } else if (val <= 999_999_999) {
      return spellNumber_idID(val / 1_000_000) + " juta"
          + ((val % 1_000_000 > 0) ? (" " + spellNumber_idID(val % 1_000_000)) : "");
    } else if (val <= 999_999_999_999L) {
      return spellNumber_idID(val / 1_000_000_000L) + " miliar"
          + ((val % 1_000_000_000L > 0) ? (" " + spellNumber_idID(val % 1_000_000_000L)) : "");
    } else if (val <= 999_999_999_999_999L) {
      return spellNumber_idID(val / 1_000_000_000_000L) + " triliun"
          + ((val % 1_000_000_000_000L > 0) ? (" " + spellNumber_idID(val % 1_000_000_000_000L)) : "");
    } else if (val <= 999_999_999_999_999_999L) {
      return spellNumber_idID(val / 1_000_000_000_000_000L) + " quadriliun"
          + ((val % 1_000_000_000_000_000L > 0) ? (" " + spellNumber_idID(val % 1_000_000_000_000_000L))
              : "");
    } else {
      return "";
    }
  }

}