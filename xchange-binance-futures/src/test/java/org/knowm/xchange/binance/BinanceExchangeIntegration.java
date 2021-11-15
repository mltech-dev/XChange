package org.knowm.xchange.binance;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.binance.dto.meta.BinanceSystemStatus;
import org.knowm.xchange.binance.service.BinanceAccountService;

public class BinanceExchangeIntegration {
  protected static BinanceFuturesExchange exchange;

  @BeforeClass
  public static void beforeClass() throws Exception {
    createExchange();
  }

  @Test
  public void testSytemStatus() throws IOException {
    assumeProduction();
    BinanceSystemStatus systemStatus =
        ((BinanceAccountService) exchange.getAccountService()).getSystemStatus();
    assertThat(systemStatus).isNotNull();
    assertThat(systemStatus.getStatus()).isNotEmpty();
  }

  protected static void createExchange() throws Exception {
    exchange = ExchangeFactory.INSTANCE.createExchangeWithoutSpecification(BinanceFuturesExchange.class);
    ExchangeSpecification spec = exchange.getDefaultExchangeSpecification();
    boolean useSandbox =
        Boolean.parseBoolean(
            System.getProperty(
                BinanceFuturesExchange.SPECIFIC_PARAM_USE_SANDBOX, Boolean.FALSE.toString()));
    spec.setExchangeSpecificParametersItem(BinanceFuturesExchange.SPECIFIC_PARAM_USE_SANDBOX, useSandbox);
    exchange.applySpecification(spec);
  }

  protected void assumeProduction() {
    Assume.assumeFalse("Using sandbox", exchange.usingSandbox());
  }
}
