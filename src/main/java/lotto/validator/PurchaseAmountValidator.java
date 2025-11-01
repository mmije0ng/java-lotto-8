package lotto.validator;

import lotto.constants.LottoConstants;
import lotto.constants.MessageConstants;

public class PurchaseAmountValidator {
    public static void validate(int amount) {
        validateAmountUnit(amount);
    }

    private static void validateAmountUnit(int amount) {
        if (isInvalidAmountUnit(amount)) {
            throw new IllegalArgumentException(MessageConstants.ERROR_INVALID_AMOUNT_UNIT);
        }
    }

    private static boolean isInvalidAmountUnit(int amount) {
        return amount < LottoConstants.MIN_PURCHASE_AMOUNT
                || amount % LottoConstants.LOTTO_PRICE != 0;
    }
}

