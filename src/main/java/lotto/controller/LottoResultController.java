package lotto.controller;

import lotto.constants.MessageConstants;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.service.LottoResultService;

import java.util.List;
import java.util.Map;

public class LottoResultController {
    private final LottoResultService lottoResultService;

    public LottoResultController() {
        this.lottoResultService = new LottoResultService();
    }

    public void printResults(List<Lotto> lottos, Lotto winningLotto, int bonusNumber, int purchaseAmount) {
        Map<Rank, Integer> results = lottoResultService.calculateResults(lottos, winningLotto, bonusNumber);
        long totalPrize = lottoResultService.calculateTotalPrize(results);
        double profitRate = lottoResultService.calculateProfitRate(totalPrize, purchaseAmount);
        printStatistics(results);
        printProfitRate(profitRate);
    }

    private void printStatistics(Map<Rank, Integer> results) {
        System.out.println(MessageConstants.STATISTICS_HEADER);
        System.out.println(MessageConstants.STATISTICS_DIVIDER);
        printRankResult(results, Rank.FIFTH);
        printRankResult(results, Rank.FOURTH);
        printRankResult(results, Rank.THIRD);
        printRankResult(results, Rank.SECOND);
        printRankResult(results, Rank.FIRST);
    }

    private void printRankResult(Map<Rank, Integer> results, Rank rank) {
        int count = results.get(rank);
        if (!rank.getDescription().isEmpty()) {
            System.out.println(rank.getDescription() + " - " + count + "개");
        }
    }

    private void printProfitRate(double profitRate) {
        System.out.println(String.format(MessageConstants.PROFIT_RATE_MESSAGE, profitRate));
    }
}

