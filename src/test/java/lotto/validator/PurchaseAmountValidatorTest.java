package lotto.validator;

import lotto.constants.MessageConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PurchaseAmountValidatorTest {

    @DisplayName("1,000원 미만의 금액이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 500, 999})
    void validate_InvalidAmountLessThanMin_ThrowsException(int amount) {
        assertThatThrownBy(() -> PurchaseAmountValidator.validate(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_INVALID_AMOUNT_UNIT);
    }

    @DisplayName("1,000원으로 나누어 떨어지지 않는 금액이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {1001, 1500, 1999, 2500, 9999})
    void validate_InvalidAmountNotDivisible_ThrowsException(int amount) {
        assertThatThrownBy(() -> PurchaseAmountValidator.validate(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_INVALID_AMOUNT_UNIT);
    }

    @DisplayName("1,000원 단위의 유효한 금액이면 예외가 발생하지 않는다")
    @Test
    void validate_ValidAmount_NoException() {
        PurchaseAmountValidator.validate(1000);
        PurchaseAmountValidator.validate(5000);
        PurchaseAmountValidator.validate(10000);
        PurchaseAmountValidator.validate(100000);
    }
}

