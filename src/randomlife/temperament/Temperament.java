package randomlife.temperament;

/**
 * 기질 부모 클래스 (담당: B)
 * - 5세 유치원 이벤트에서 고른 행동에 따라 4개 중 하나가 정해진다.
 * - 능력치가 바뀌기 직전에 applyBonus()를 거쳐서 최종 변화량이 결정된다.
 * - Choice는 지금 기질이 뭔지 몰라도 applyBonus()만 부르면 된다. (다형성)
 *
 *   Temperament t = new Curious();
 *   t.applyBonus("KNOW", 10);   → Curious의 applyBonus가 실행됨 → 15
 */
public abstract class Temperament {

    protected String name;   // 화면 표시용 ("탐구형" 등)

    /** 부르는 곳: 자식 클래스 생성자의 super(이름) */
    public Temperament(String name) {
        // TODO name 필드에 저장
        this.name = name;
    }

    /**
     * 기질 보너스 적용
     * stat  : 바뀌는 능력치 이름 (GameConfig.KNOW 등)
     * amount: 원래 변화량 (+10, -5 등)
     * 반환값 : 보너스가 적용된 최종 변화량
     * 부르는 곳: Choice.apply()
     */
    public abstract int applyBonus(String stat, int amount);

    public String getName() {
        // TODO name 반환
        return name;
    }
}
