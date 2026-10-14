package randomlife.temperament;

import randomlife.util.GameConfig;

/**
 * 탐구형: 지식이 오를 때 +5 (담당: B)
 * 5세 유치원에서 "구석의 블록을 한참 만지작거린다" 를 고르면 이 기질이 된다.
 */
public class Curious extends Temperament {

    public Curious() {
        super("탐구형");
    }

    /**
     * 부르는 곳: Choice.apply() (부모 타입 Temperament로 불려도 이 메서드가 실행됨)
     */
    @Override
    public int applyBonus(String stat, int amount) {
        // TODO 1. stat이 GameConfig.KNOW 이고, amount가 0보다 크면 amount + 5 를 return
        //         (문자열 비교는 == 말고 .equals() 사용)
        // TODO 2. 아니면 amount 그대로 return
        return amount;
    }
}
