package com.tujuhsembilan.library.utility;

import java.math.BigDecimal;

import com.tujuhsembilan.library.abstraction.enumeration.L10n;
import com.tujuhsembilan.library.abstraction.exception.UnsupportedLocaleException;

public class CurrencyUtil {

  private CurrencyUtil() {
  }

  public static CurrencyLogicProvider of(L10n code) {
    return new CurrencyLogicProvider(code);
  }

  /**
   * A class which provide logic block for currency operation.
   */
  public static final class CurrencyLogicProvider {

    private final L10n code;

    private CurrencyLogicProvider(L10n code) {
      this.code = code;
    }

    public String getCurrencySymbol() {
      return code.getCurrency().getSymbol();
    }

    public String getCurrencyCode() {
      return code.getCurrency().getCurrencyCode();
    }

    public ValuedCurrencyLogicProvider withValue(Double value) {
      return new ValuedCurrencyLogicProvider(value);
    }

    public ValuedCurrencyLogicProvider withValue(BigDecimal value) {
      return new ValuedCurrencyLogicProvider(value);
    }

    /**
     * A class which provide logic block for currency operation with assigned value.
     */
    public final class ValuedCurrencyLogicProvider {

      private final BigDecimal value;

      private ValuedCurrencyLogicProvider(BigDecimal value) {
        this.value = value;
      }

      private ValuedCurrencyLogicProvider(Double value) {
        this.value = BigDecimal.valueOf(value);
      }

      public String asCurrencyString() {
        return code.getNumberFormat().format(value);
      }

      public String spell(L10n spelling) {
        switch (spelling) {
          case US:
            return NumberUtil.spellNumber_enUS(value.longValueExact())
                + " " + code.getCurrency().getDisplayName(spelling.getLocale());
          case ID:
            return NumberUtil.spellNumber_idID(value.longValueExact()) + " "
                + code.getCurrency().getDisplayName(spelling.getLocale());
          default:
            throw new UnsupportedLocaleException("Spelling with locale " + code.name() + " is currently not supported");
        }
      }

      public String spell() {
        return this.spell(L10n.US);
      }

    }

  }

}