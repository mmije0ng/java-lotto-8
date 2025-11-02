package lotto.domain;

import lotto.constants.MessageConstants;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000, "6개 일치 (2,000,000,000원)"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    THIRD(5, false, 1_500_000, "5개 일치 (1,500,000원)"),
    FOURTH(4, false, 50_000, "4개 일치 (50,000원)"),
    FIFTH(3, false, 5_000, "3개 일치 (5,000원)"),
    NONE(0, false, 0, "");

    private final int matchCount;
    private final boolean hasBonus;
    private final int prize;
    private final String description;

    Rank(int matchCount, boolean hasBonus, int prize, String description) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.prize = prize;
        this.description = description;
    }

    public static Rank valueOf(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount && rank.hasBonus == hasBonus)
                .findFirst()
                .orElse(NONE);
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean hasBonus() {
        return hasBonus;
    }

    public String getDescription() {
        return description;
    }
}
