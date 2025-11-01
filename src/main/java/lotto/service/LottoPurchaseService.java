package lotto.service;

import lotto.constants.LottoConstants;

public class LottoPurchaseService {
    public int calculatePurchaseCount(int amount) {
        return amount / LottoConstants.LOTTO_PRICE;
    }
}

