package lotto.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoPurchaseServiceTest {

    @DisplayName("1,000원으로 정확히 구매 가능한 로또 개수를 계산한다")
    @Test
    void calculatePurchaseCount_ValidAmount_ReturnsCorrectCount() {
        LottoPurchaseService service = new LottoPurchaseService();

        assertThat(service.calculatePurchaseCount(1000)).isEqualTo(1);
        assertThat(service.calculatePurchaseCount(5000)).isEqualTo(5);
        assertThat(service.calculatePurchaseCount(10000)).isEqualTo(10);
        assertThat(service.calculatePurchaseCount(14000)).isEqualTo(14);
    }
}

