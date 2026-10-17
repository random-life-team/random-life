package randomlife.core;

/**
 * 가정환경 3종 (담당: C / 대체: A)
 * - enum = 정해진 값 몇 개만 쓸 수 있는 특별한 클래스
 * - FamilyType.values() 하면 [RICH, NORMAL, POOR] 배열이 나온다 → 랜덤으로 하나 고르기 좋음
 */
public enum FamilyType {

    //     화면 표시   시작 자산(만원)
    RICH  ("넉넉한",   3000),
    NORMAL("평범한",   1000),
    POOR  ("어려운",    100);

    private String label;
    private int startMoney;

    /** enum 생성자는 위의 RICH(...), NORMAL(...) 줄이 자동으로 부른다 */
    FamilyType(String label, int startMoney) {
        // TODO 필드 2개에 매개변수 값 저장
        this.label = label;
        this.startMoney = startMoney;
    }

    public String getLabel() {
        // TODO label 반환
        return label;
    }

    public int getStartMoney() {
        // TODO startMoney 반환
        return startMoney;
    }
}
