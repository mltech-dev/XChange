package org.knowm.xchange.huobi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.huobi.dto.marketdata.HuobiAsset;
import org.knowm.xchange.huobi.dto.marketdata.HuobiAssetPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import si.mazi.rescu.SynchronizedValueFactory;

public class HuobiUtils {
  private static Logger logger = LoggerFactory.getLogger(HuobiUtils.class);

  private static Map<String, CurrencyPair> assetPairMap = new HashMap<String, CurrencyPair>();
  private static Map<CurrencyPair, String> assetPairMapReverse =
      new HashMap<CurrencyPair, String>();
  private static Map<String, Currency> assetMap = new HashMap<String, Currency>();
  private static Map<Currency, String> assetMapReverse = new HashMap<Currency, String>();

  private HuobiUtils() {}

  public static String createHuobiInstument(CurrencyPair pair) {
	  return pair.base.toString().toLowerCase();
  }
  
  public static String createHuobiInstumentForOpenOrders(CurrencyPair pair) {
	  return pair.base.toString().toLowerCase() +"-"+ pair.counter.toString().toLowerCase(); 
  }


  public static String createUTCDate(SynchronizedValueFactory<Long> nonce) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return dateFormat.format(new Date(nonce.createValue()));
  }

  public static String createUTCDate(Date date) {
    if (date == null) {
      return null;
    }
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(date);
  }

  public static void setHuobiAssets(HuobiAsset[] huobiAssets) {
    for (HuobiAsset entry : huobiAssets) {
      assetMap.put(entry.getAsset(), Currency.getInstance(entry.getAsset()));
      assetMapReverse.put(Currency.getInstance(entry.getAsset()), entry.getAsset());
    }
  }

  public static void setHuobiAssetPairs(HuobiAssetPair[] huobiAssetPairs) {
    for (HuobiAssetPair entry : huobiAssetPairs) {
      CurrencyPair pair =
          new CurrencyPair(
              translateHuobiCurrencyCode(entry.getBaseCurrency()),
              translateHuobiCurrencyCode(entry.getQuoteCurrency()));
      if (pair.base != null && pair.counter != null) {
        assetPairMap.put(entry.getKey(), pair);
        assetPairMapReverse.put(pair, entry.getKey());
      }
    }
  }

  public static Currency translateHuobiCurrencyCode(String currencyIn) {
    Currency currencyOut = assetMap.get(currencyIn);
    if (currencyOut == null) {
      logger.error("Huobi does not support the currency code " + currencyIn);
      return null;
    }
    return currencyOut.getCommonlyUsedCurrency();
  }

  public static CurrencyPair translateHuobiCurrencyPair(String currencyPairIn) {
	  if(!currencyPairIn.contains("/")) {
		  logger.error("Currency Code not supported : {}",currencyPairIn);
		  return null;
	  } else {
		  String [] pair = currencyPairIn.split("/");
		  return new CurrencyPair(pair[0], pair[1]);
	  }
  }
}
