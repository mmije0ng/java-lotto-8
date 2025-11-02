package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.constants.MessageConstants;
import lotto.domain.Lotto;
import lotto.service.LottoResultService;
import lotto.validator.WinningNumberValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoWinningController {
    private final LottoResultService lottoResultService;

    public LottoWinningController() {
        this.lottoResultService = new LottoResultService();
    }

    public WinningInput processWinningInput() {
        Lotto winningLotto = inputWinningNumbersWithRetry();
        int bonusNumber = inputBonusNumberWithRetry(winningLotto);
        return new WinningInput(winningLotto, bonusNumber);
    }

    private Lotto inputWinningNumbersWithRetry() {
        while (true) {
            try {
                String input = inputWinningNumbers();
                List<Integer> numbers = parseWinningNumbers(input);
                WinningNumberValidator.validate(numbers);
                return new Lotto(numbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String inputWinningNumbers() {
        System.out.println(MessageConstants.REQUEST_WINNING_NUMBERS);
        return Console.readLine();
    }

    private List<Integer> parseWinningNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MessageConstants.ERROR_NOT_NUMBER);
        }
    }

    private int inputBonusNumberWithRetry(Lotto winningLotto) {
        while (true) {
            try {
                String input = inputBonusNumber();
                int bonusNumber = parseBonusNumber(input);
                WinningNumberValidator.validateBonusNumber(bonusNumber, winningLotto);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String inputBonusNumber() {
        System.out.println(MessageConstants.REQUEST_BONUS_NUMBER);
        return Console.readLine();
    }

    private int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(MessageConstants.ERROR_NOT_NUMBER);
        }
    }

    public static class WinningInput {
        private final Lotto winningLotto;
        private final int bonusNumber;

        public WinningInput(Lotto winningLotto, int bonusNumber) {
            this.winningLotto = winningLotto;
            this.bonusNumber = bonusNumber;
        }

        public Lotto getWinningLotto() {
            return winningLotto;
        }

        public int getBonusNumber() {
            return bonusNumber;
        }
    }
}

