package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.constants.LottoConstants;
import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {
    public List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lotto lotto = generateLotto();
            lottos.add(lotto);
        }
        return lottos;
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                LottoConstants.MIN_LOTTO_NUMBER,
                LottoConstants.MAX_LOTTO_NUMBER,
                6
        );
        return new Lotto(numbers);
    }
}

