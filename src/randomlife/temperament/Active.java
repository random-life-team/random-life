package randomlife.temperament;

import randomlife.util.GameConfig;

/**
 * 활동형: 건강이 오를 때 +5 (담당: B)
 * 5세 유치원에서 "마당으로 뛰쳐나간다" 를 고르면 이 기질이 된다.
 */
public class Active extends Temperament {

    public Active() {
        super("활동형");
    }

    /**
     * 부르는 곳: Choice.apply() (부모 타입 Temperament로 불려도 이 메서드가 실행됨)
     */
    @Override
    public int applyBonus(String stat, int amount) {
        // TODO 1. stat이 GameConfig.HEALTH 이고, amount가 0보다 크면 amount + 5 를 return
        // TODO 2. 아니면 amount 그대로 return
        return amount;
    }
}
