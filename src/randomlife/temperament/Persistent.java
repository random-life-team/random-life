package randomlife.temperament;

import randomlife.util.GameConfig;

/**
 * 끈기형: 자산을 뺀 능력치가 깎일 때 절반만 깎임 (담당: B)
 * 5세 유치원에서 "넘어져도 울지 않고 다시 일어난다" 를 고르면 이 기질이 된다.
 */
public class Persistent extends Temperament {

    public Persistent() {
        super("끈기형");
    }

    /**
     * 부르는 곳: Choice.apply() (부모 타입 Temperament로 불려도 이 메서드가 실행됨)
     */
    @Override
    public int applyBonus(String stat, int amount) {
        // TODO 1. stat이 GameConfig.MONEY 가 아니고, amount가 0보다 작으면 amount / 2 를 return
        //         예) -10 → -5
        if(!GameConfig.MONEY.equals(stat)&&amount < 0){
            return amount/ 2;
        }
        // TODO 2. 아니면 amount 그대로 return
        return amount;
    }
}
