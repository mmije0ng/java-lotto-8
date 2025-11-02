package lotto.constants;

public class MessageConstants {
    public static final String REQUEST_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    public static final String PURCHASE_COUNT_MESSAGE = "개를 구매했습니다.";
    public static final String REQUEST_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    public static final String REQUEST_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    public static final String STATISTICS_HEADER = "당첨 통계";
    public static final String STATISTICS_DIVIDER = "---";
    public static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.1f%%입니다.";
    public static final String ERROR_INVALID_AMOUNT_UNIT = "[ERROR] 구입 금액은 1,000원 단위여야 합니다.";
    public static final String ERROR_LOTTO_NUMBER_COUNT = "[ERROR] 로또 번호는 6개여야 합니다.";
    public static final String ERROR_LOTTO_NUMBER_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    public static final String ERROR_DUPLICATE_NUMBER = "[ERROR] 중복된 로또 번호가 존재합니다.";
    public static final String ERROR_NOT_NUMBER = "[ERROR] 로또 번호는 숫자여야 합니다.";
    public static final String ERROR_DUPLICATE_WINNING_NUMBER = "[ERROR] 중복된 당첨 번호가 존재합니다.";
    public static final String ERROR_BONUS_NUMBER_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    private MessageConstants() {
    }
}
