package lotto;

import lotto.controller.LottoPurchaseController;
import lotto.controller.LottoResultController;
import lotto.controller.LottoWinningController;
import lotto.domain.Lotto;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        LottoPurchaseController purchaseController = new LottoPurchaseController();
        LottoPurchaseController.PurchaseResult purchaseResult = purchaseController.processPurchase();
        System.out.println();

        LottoWinningController winningController = new LottoWinningController();
        LottoWinningController.WinningInput winningInput = winningController.processWinningInput();
        System.out.println();

        LottoResultController resultController = new LottoResultController();
        resultController.printResults(purchaseResult.getLottos(), winningInput.getWinningLotto(), winningInput.getBonusNumber(), purchaseResult.getAmount());
    }
}
