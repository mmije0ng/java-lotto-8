package lotto.domain;

import lotto.constants.MessageConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다")
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_LOTTO_NUMBER_COUNT);
    }

    @DisplayName("로또 번호의 개수가 6개 미만이면 예외가 발생한다")
    @Test
    void 로또_번호의_개수가_6개_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_LOTTO_NUMBER_COUNT);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_DUPLICATE_NUMBER);
    }

    @DisplayName("로또 번호가 1 미만이면 예외가 발생한다")
    @Test
    void 로또_번호가_1_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_LOTTO_NUMBER_RANGE);
    }

    @DisplayName("로또 번호가 45 초과이면 예외가 발생한다")
    @Test
    void 로또_번호가_45_초과이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageConstants.ERROR_LOTTO_NUMBER_RANGE);
    }

    @DisplayName("유효한 로또 번호로 생성하면 예외가 발생하지 않는다")
    @Test
    void 유효한_로또_번호로_생성하면_예외가_발생하지_않는다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 45));
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @DisplayName("로또 번호가 오름차순으로 정렬되어 반환된다")
    @Test
    void 로또_번호가_오름차순으로_정렬되어_반환된다() {
        Lotto lotto = new Lotto(List.of(45, 1, 30, 10, 5, 20));
        assertThat(lotto.getNumbers()).containsExactly(1, 5, 10, 20, 30, 45);
    }

    @DisplayName("두 로또 간 일치하는 번호의 개수를 계산한다")
    @Test
    void 두_로또_간_일치하는_번호의_개수를_계산한다() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 7, 8, 9));

        assertThat(lotto1.countMatchingNumbers(lotto2)).isEqualTo(3);
    }

    @DisplayName("특정 번호를 포함하는지 확인한다")
    @Test
    void 특정_번호를_포함하는지_확인한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.containsNumber(3)).isTrue();
        assertThat(lotto.containsNumber(7)).isFalse();
    }
}

