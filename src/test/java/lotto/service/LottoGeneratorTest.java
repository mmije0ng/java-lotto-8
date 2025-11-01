package lotto.service;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    @DisplayName("지정한 개수만큼 로또를 생성한다")
    @Test
    void generateLottos_SpecifiedCount_ReturnsCorrectSize() {
        LottoGenerator generator = new LottoGenerator();
        List<Lotto> lottos = generator.generateLottos(5);

        assertThat(lottos).hasSize(5);
    }

    @DisplayName("생성된 로또는 각각 6개의 번호를 가지고 있다")
    @Test
    void generateLottos_ValidCount_EachHasSixNumbers() {
        LottoGenerator generator = new LottoGenerator();
        List<Lotto> lottos = generator.generateLottos(3);

        for (Lotto lotto : lottos) {
            assertThat(lotto.getNumbers()).hasSize(6);
        }
    }

    @DisplayName("생성된 로또 번호는 1부터 45 사이의 숫자이다")
    @Test
    void generateLottos_ValidRange_NumbersInRange() {
        LottoGenerator generator = new LottoGenerator();
        List<Lotto> lottos = generator.generateLottos(3);

        for (Lotto lotto : lottos) {
            for (int number : lotto.getNumbers()) {
                assertThat(number).isBetween(1, 45);
            }
        }
    }

    @DisplayName("생성된 로또 번호는 오름차순으로 정렬되어 있다")
    @Test
    void generateLottos_ValidGeneration_NumbersSorted() {
        LottoGenerator generator = new LottoGenerator();
        List<Lotto> lottos = generator.generateLottos(3);

        for (Lotto lotto : lottos) {
            List<Integer> numbers = lotto.getNumbers();
            for (int i = 1; i < numbers.size(); i++) {
                assertThat(numbers.get(i - 1)).isLessThan(numbers.get(i));
            }
        }
    }

    @DisplayName("생성된 각 로또는 중복된 번호가 없다")
    @Test
    void generateLottos_ValidGeneration_NoDuplicateNumbers() {
        LottoGenerator generator = new LottoGenerator();
        List<Lotto> lottos = generator.generateLottos(3);

        for (Lotto lotto : lottos) {
            List<Integer> numbers = lotto.getNumbers();
            long distinctCount = numbers.stream()
                    .distinct()
                    .count();
            assertThat(distinctCount).isEqualTo(6);
        }
    }
}

