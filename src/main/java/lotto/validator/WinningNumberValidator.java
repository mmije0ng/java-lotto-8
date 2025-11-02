package lotto.validator;

import lotto.constants.LottoConstants;
import lotto.constants.MessageConstants;
import lotto.domain.Lotto;

import java.util.List;

public class WinningNumberValidator {
    public static void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(MessageConstants.ERROR_LOTTO_NUMBER_COUNT);
        }
    }

    private static void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (isOutOfRange(number)) {
                throw new IllegalArgumentException(MessageConstants.ERROR_LOTTO_NUMBER_RANGE);
            }
        }
    }

    private static boolean isOutOfRange(int number) {
        return number < LottoConstants.MIN_LOTTO_NUMBER || number > LottoConstants.MAX_LOTTO_NUMBER;
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (hasDuplicate(numbers)) {
            throw new IllegalArgumentException(MessageConstants.ERROR_DUPLICATE_WINNING_NUMBER);
        }
    }

    private static boolean hasDuplicate(List<Integer> numbers) {
        return numbers.stream().distinct().count() != numbers.size();
    }

    public static void validateBonusNumber(int bonusNumber, Lotto winningLotto) {
        validateBonusRange(bonusNumber);
        validateBonusDuplicate(bonusNumber, winningLotto);
    }

    private static void validateBonusRange(int bonusNumber) {
        if (isOutOfRange(bonusNumber)) {
            throw new IllegalArgumentException(MessageConstants.ERROR_LOTTO_NUMBER_RANGE);
        }
    }

    private static void validateBonusDuplicate(int bonusNumber, Lotto winningLotto) {
        if (winningLotto.containsNumber(bonusNumber)) {
            throw new IllegalArgumentException(MessageConstants.ERROR_BONUS_NUMBER_DUPLICATE);
        }
    }
}

