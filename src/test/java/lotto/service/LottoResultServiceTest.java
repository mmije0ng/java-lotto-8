package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultServiceTest {

    @DisplayName("로또 결과를 계산한다")
    @Test
    void calculateResults_MultipleLottos_ReturnsCorrectResults() {
        LottoResultService service = new LottoResultService();
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 7, 8, 9)),
                new Lotto(List.of(1, 2, 3, 4, 7, 8))
        );
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Map<Rank, Integer> results = service.calculateResults(lottos, winningLotto, bonusNumber);

        assertThat(results.get(Rank.FIRST)).isEqualTo(1);
        assertThat(results.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(results.get(Rank.FOURTH)).isEqualTo(1);
    }

    @DisplayName("보너스 번호가 포함된 경우 2등으로 판별한다")
    @Test
    void calculateResults_WithBonusNumber_ReturnsSecondRank() {
        LottoResultService service = new LottoResultService();
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Map<Rank, Integer> results = service.calculateResults(lottos, winningLotto, bonusNumber);

        assertThat(results.get(Rank.SECOND)).isEqualTo(1);
    }

    @DisplayName("총 당첨 금액을 계산한다")
    @Test
    void calculateTotalPrize_MultipleRanks_ReturnsCorrectTotal() {
        LottoResultService service = new LottoResultService();
        Map<Rank, Integer> results = Map.of(
                Rank.FIRST, 1,
                Rank.FIFTH, 2,
                Rank.NONE, 0
        );

        long totalPrize = service.calculateTotalPrize(results);

        assertThat(totalPrize).isEqualTo(2_000_000_000 + 5_000 * 2);
    }

    @DisplayName("수익률을 계산한다")
    @Test
    void calculateProfitRate_ValidInput_ReturnsCorrectRate() {
        LottoResultService service = new LottoResultService();

        double profitRate = service.calculateProfitRate(5_000, 10_000);

        assertThat(profitRate).isEqualTo(50.0);
    }

    @DisplayName("수익률을 소수점 둘째 자리에서 반올림한다")
    @Test
    void calculateProfitRate_DecimalValue_RoundsToFirstDecimal() {
        LottoResultService service = new LottoResultService();

        double profitRate = service.calculateProfitRate(6_250, 10_000);

        assertThat(profitRate).isEqualTo(62.5);
    }
}

