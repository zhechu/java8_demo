package demo.api;

import org.junit.Test;

import java.util.Currency;
import java.util.Locale;

public class CurrencyTest {

  @Test
  public void getInstance() {
    Currency currency = Currency.getInstance(Locale.CHINA);
    System.out.println(currency.getCurrencyCode());
    System.out.println(currency.getDisplayName());
    System.out.println(currency.getDefaultFractionDigits());
    System.out.println(currency.getSymbol());
  }

}
