package lotto.constants;

public class MessageConstants {
    public static final String REQUEST_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    public static final String PURCHASE_COUNT_MESSAGE = "개를 구매했습니다.";
    public static final String ERROR_INVALID_AMOUNT_UNIT = "[ERROR] 구입 금액은 1,000원 단위여야 합니다.";
    public static final String ERROR_LOTTO_NUMBER_COUNT = "[ERROR] 로또 번호는 6개여야 합니다.";
    public static final String ERROR_LOTTO_NUMBER_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    public static final String ERROR_DUPLICATE_NUMBER = "[ERROR] 중복된 로또 번호가 존재합니다.";

    private MessageConstants() {
    }
}

