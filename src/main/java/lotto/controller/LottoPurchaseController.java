package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.constants.MessageConstants;
import lotto.service.LottoPurchaseService;
import lotto.validator.PurchaseAmountValidator;

public class LottoPurchaseController {
    private final LottoPurchaseService lottoPurchaseService;

    public LottoPurchaseController() {
        this.lottoPurchaseService = new LottoPurchaseService();
    }

    public int processPurchase() {
        int amount = inputPurchaseAmount();
        PurchaseAmountValidator.validate(amount);
        int purchaseCount = lottoPurchaseService.calculatePurchaseCount(amount);
        printPurchaseCount(purchaseCount);
        return purchaseCount;
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
}

