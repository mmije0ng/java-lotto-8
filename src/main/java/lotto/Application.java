package lotto;

import lotto.controller.LottoPurchaseController;

public class Application {
    public static void main(String[] args) {
        LottoPurchaseController controller = new LottoPurchaseController();
        controller.processPurchase();
    }
}
