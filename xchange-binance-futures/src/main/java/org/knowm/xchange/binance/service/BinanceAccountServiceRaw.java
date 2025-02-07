package org.knowm.xchange.binance.service;

import static org.knowm.xchange.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

import java.io.IOException;

import org.knowm.xchange.binance.BinanceAuthenticated;
import org.knowm.xchange.binance.BinanceFuturesExchange;
import org.knowm.xchange.binance.dto.BinanceException;
import org.knowm.xchange.binance.dto.account.BinanceAccountInformation;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.utils.Assert;

public class BinanceAccountServiceRaw extends BinanceBaseService {

  public BinanceAccountServiceRaw(
      BinanceFuturesExchange exchange,
      BinanceAuthenticated binance,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, binance, resilienceRegistries);
  }

  public BinanceAccountInformation account() throws BinanceException, IOException {
	String accountApiVersion = exchange.getExchangeSpecification().getExchangeSpecificParameters().containsKey("account-api-version")?exchange.getExchangeSpecification().getExchangeSpecificParameters().get("account-api-version").toString():null;
	Assert.notNull(accountApiVersion, "Account API version property (account-api-version) is mandatory");
    return decorateApiCall(
            () -> binance.account(accountApiVersion, getRecvWindow(), getTimestampFactory(), apiKey, signatureCreator))
        .withRetry(retry("account"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 5)
        .call();
  }

//  public WithdrawResponse withdraw(String coin, String address, BigDecimal amount)
//      throws IOException, BinanceException {
//    // the name parameter seams to be mandatory
//    String name = address.length() <= 10 ? address : address.substring(0, 10);
//    return withdraw(coin, address, null, amount, name);
//  }

//  public WithdrawResponse withdraw(
//      String coin, String address, String addressTag, BigDecimal amount)
//      throws IOException, BinanceException {
//    // the name parameter seams to be mandatory
//    String name = address.length() <= 10 ? address : address.substring(0, 10);
//    return withdraw(coin, address, addressTag, amount, name);
//  }

//  private WithdrawResponse withdraw(
//      String coin, String address, String addressTag, BigDecimal amount, String name)
//      throws IOException, BinanceException {
//    return decorateApiCall(
//            () ->
//                binance.withdraw(
//                    coin,
//                    address,
//                    addressTag,
//                    amount,
//                    name,
//                    getRecvWindow(),
//                    getTimestampFactory(),
//                    apiKey,
//                    signatureCreator))
//        .withRetry(retry("withdraw", NON_IDEMPOTENT_CALLS_RETRY_CONFIG_NAME))
//        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 5)
//        .call();
//  }

//  public DepositAddress requestDepositAddress(Currency currency) throws IOException {
//    return decorateApiCall(
//            () ->
//                binance.depositAddress(
//                    BinanceAdapters.toSymbol(currency),
//                    getRecvWindow(),
//                    getTimestampFactory(),
//                    apiKey,
//                    signatureCreator))
//        .withRetry(retry("depositAddress"))
//        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
//        .call();
//  }

//  public Map<String, AssetDetail> requestAssetDetail() throws IOException {
//    return decorateApiCall(
//            () ->
//                binance.assetDetail(
//                    getRecvWindow(), getTimestampFactory(), apiKey, signatureCreator))
//        .withRetry(retry("assetDetail"))
//        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
//        .call();
//  }
//
//  public List<BinanceDeposit> depositHistory(String asset, Long startTime, Long endTime)
//      throws BinanceException, IOException {
//    return decorateApiCall(
//            () ->
//                binance.depositHistory(
//                    asset,
//                    startTime,
//                    endTime,
//                    getRecvWindow(),
//                    getTimestampFactory(),
//                    apiKey,
//                    signatureCreator))
//        .withRetry(retry("depositHistory"))
//        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
//        .call();
//  }
//
//  public List<BinanceWithdraw> withdrawHistory(String asset, Long startTime, Long endTime)
//      throws BinanceException, IOException {
//    return decorateApiCall(
//            () ->
//                binance.withdrawHistory(
//                    asset,
//                    startTime,
//                    endTime,
//                    getRecvWindow(),
//                    getTimestampFactory(),
//                    apiKey,
//                    signatureCreator))
//        .withRetry(retry("withdrawHistory"))
//        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
//        .call();
//  }

//  public List<AssetDividendResponse.AssetDividend> getAssetDividend(Long startTime, Long endTime)
//      throws BinanceException, IOException {
//    return getAssetDividend("", startTime, endTime);
//  }

//  public List<AssetDividendResponse.AssetDividend> getAssetDividend(
//      String asset, Long startTime, Long endTime) throws BinanceException, IOException {
//    return decorateApiCall(
//            () ->
//                binance.assetDividend(
//                    asset,
//                    startTime,
//                    endTime,
//                    getRecvWindow(),
//                    getTimestampFactory(),
//                    super.apiKey,
//                    super.signatureCreator))
//        .withRetry(retry("assetDividend"))
//        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
//        .call()
//        .getData();
//  }
//
//  public List<TransferHistory> getTransferHistory(
//      String fromEmail, Long startTime, Long endTime, Integer page, Integer limit)
//      throws BinanceException, IOException {
//    return decorateApiCall(
//            () ->
//                binance.transferHistory(
//                    fromEmail,
//                    startTime,
//                    endTime,
//                    page,
//                    limit,
//                    getRecvWindow(),
//                    getTimestampFactory(),
//                    super.apiKey,
//                    super.signatureCreator))
//        .withRetry(retry("transferHistory"))
//        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
//        .call();
//  }
//
//  public List<TransferSubUserHistory> getSubUserHistory(
//      String asset, Integer type, Long startTime, Long endTime, Integer limit)
//      throws BinanceException, IOException {
//    return decorateApiCall(
//            () ->
//                binance.transferSubUserHistory(
//                    asset,
//                    type,
//                    startTime,
//                    endTime,
//                    limit,
//                    getRecvWindow(),
//                    getTimestampFactory(),
//                    super.apiKey,
//                    super.signatureCreator))
//        .withRetry(retry("transferSubUserHistory"))
//        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
//        .call();
//  }
}
