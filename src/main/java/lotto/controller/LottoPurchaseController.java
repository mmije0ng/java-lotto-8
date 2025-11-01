package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.constants.MessageConstants;
import lotto.domain.Lotto;
import lotto.service.LottoGenerator;
import lotto.service.LottoPurchaseService;
import lotto.validator.PurchaseAmountValidator;

import java.util.List;

public class LottoPurchaseController {
    private final LottoPurchaseService lottoPurchaseService;
    private final LottoGenerator lottoGenerator;

    public LottoPurchaseController() {
        this.lottoPurchaseService = new LottoPurchaseService();
        this.lottoGenerator = new LottoGenerator();
    }

    public List<Lotto> processPurchase() {
        int amount = inputPurchaseAmountWithRetry();
        int purchaseCount = lottoPurchaseService.calculatePurchaseCount(amount);
        printPurchaseCount(purchaseCount);
        List<Lotto> lottos = lottoGenerator.generateLottos(purchaseCount);
        printLottos(lottos);
        return lottos;
    }

    private int inputPurchaseAmountWithRetry() {
        while (true) {
            try {
                int amount = inputPurchaseAmount();
                PurchaseAmountValidator.validate(amount);
                return amount;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int inputPurchaseAmount() {
        System.out.println(MessageConstants.REQUEST_PURCHASE_AMOUNT);
        String input = Console.readLine();
        return parseAmount(input);
    }

    private int parseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MessageConstants.ERROR_INVALID_AMOUNT_UNIT);
        }
    }

    private void printPurchaseCount(int count) {
        System.out.println(count + MessageConstants.PURCHASE_COUNT_MESSAGE);
    }

    private void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }
}

