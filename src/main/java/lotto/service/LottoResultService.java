package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResultService {
    public Map<Rank, Integer> calculateResults(List<Lotto> lottos, Lotto winningLotto, int bonusNumber) {
        Map<Rank, Integer> results = initializeResults();
        for (Lotto lotto : lottos) {
            Rank rank = determineRank(lotto, winningLotto, bonusNumber);
            results.put(rank, results.get(rank) + 1);
        }
        return results;
    }

    private Map<Rank, Integer> initializeResults() {
        Map<Rank, Integer> results = new HashMap<>();
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }
        return results;
    }

    private Rank determineRank(Lotto lotto, Lotto winningLotto, int bonusNumber) {
        int matchCount = lotto.countMatchingNumbers(winningLotto);
        boolean hasBonus = isBonusApplicable(matchCount, lotto, bonusNumber);
        return Rank.valueOf(matchCount, hasBonus);
    }

    private boolean isBonusApplicable(int matchCount, Lotto lotto, int bonusNumber) {
        if (matchCount != 5) {
            return false;
        }
        return lotto.containsNumber(bonusNumber);
    }

    public long calculateTotalPrize(Map<Rank, Integer> results) {
        return results.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public double calculateProfitRate(long totalPrize, int purchaseAmount) {
        if (purchaseAmount == 0) {
            return 0.0;
        }
        double rate = (double) totalPrize / purchaseAmount * 100;
        return Math.round(rate * 10.0) / 10.0;
    }
}

