package lotto.validator;

import lotto.constants.MessageConstants;
import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumberValidatorTest {

    @DisplayName("당첨 번호가 6개가 아니면 예외가 발생한다")
    @Test
    void validate_InvalidSize_ThrowsException() {
        assertThatThrownBy(() -> WinningNumberValidator.validate(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_LOTTO_NUMBER_COUNT);
    }

    @DisplayName("당첨 번호에 중복이 있으면 예외가 발생한다")
    @Test
    void validate_DuplicateNumbers_ThrowsException() {
        assertThatThrownBy(() -> WinningNumberValidator.validate(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_DUPLICATE_WINNING_NUMBER);
    }

    @DisplayName("당첨 번호가 1~45 범위를 벗어나면 예외가 발생한다")
    @Test
    void validate_OutOfRange_ThrowsException() {
        assertThatThrownBy(() -> WinningNumberValidator.validate(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_LOTTO_NUMBER_RANGE);

        assertThatThrownBy(() -> WinningNumberValidator.validate(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_LOTTO_NUMBER_RANGE);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    @Test
    void validateBonusNumber_DuplicateWithWinning_ThrowsException() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> WinningNumberValidator.validateBonusNumber(6, winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_BONUS_NUMBER_DUPLICATE);
    }

    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다")
    @Test
    void validateBonusNumber_OutOfRange_ThrowsException() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> WinningNumberValidator.validateBonusNumber(0, winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_LOTTO_NUMBER_RANGE);

        assertThatThrownBy(() -> WinningNumberValidator.validateBonusNumber(46, winningLotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_LOTTO_NUMBER_RANGE);
    }
}

