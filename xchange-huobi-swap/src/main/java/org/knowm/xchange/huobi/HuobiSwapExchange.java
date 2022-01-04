package org.knowm.xchange.huobi;

import java.io.IOException;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.huobi.dto.marketdata.HuobiAsset;
import org.knowm.xchange.huobi.dto.marketdata.HuobiAssetPair;
import org.knowm.xchange.huobi.dto.marketdata.HuobiCurrencyWrapper;
import org.knowm.xchange.huobi.service.HuobiAccountService;
import org.knowm.xchange.huobi.service.HuobiMarketDataService;
import org.knowm.xchange.huobi.service.HuobiMarketDataServiceRaw;
import org.knowm.xchange.huobi.service.HuobiTradeService;

public class HuobiSwapExchange extends BaseExchange implements Exchange {

  @Override
  protected void initServices() {
    this.marketDataService = new HuobiMarketDataService(this);
    this.tradeService = new HuobiTradeService(this);
    this.accountService = new HuobiAccountService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass());
    exchangeSpecification.setSslUri("https://api.hbdm.vn");
    exchangeSpecification.setHost("api.hbdm.com");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("Huobi Swap");
    exchangeSpecification.setExchangeDescription(
        "Huobi Futures is a Chinese digital currency trading platform and exchange based in Beijing");
    return exchangeSpecification;
  }

  @Override
  public void remoteInit() throws IOException, ExchangeException {
	  //TODO: implment remote init
//    HuobiAssetPair[] assetPairs =
//        ((HuobiMarketDataServiceRaw) marketDataService).getHuobiAssetPairs();
//    HuobiAsset[] assets = ((HuobiMarketDataServiceRaw) marketDataService).getHuobiAssets();
//    HuobiCurrencyWrapper[] huobiCurrencies =
//        ((HuobiMarketDataServiceRaw) marketDataService).getHuobiCurrencies("");
//    exchangeMetaData =
//        HuobiAdapters.adaptToExchangeMetaData(
//            assetPairs, assets, exchangeMetaData, huobiCurrencies);
  }
}
