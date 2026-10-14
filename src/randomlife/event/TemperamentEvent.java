package randomlife.event;

import randomlife.core.Person;
import randomlife.temperament.Active;
import randomlife.temperament.Curious;
import randomlife.temperament.Empathic;
import randomlife.temperament.Persistent;

/**
 * 5세 기질 이벤트 (담당: B)
 * - ChoiceEvent와 똑같이 동작하고, 고른 번호에 따라 기질만 추가로 정한다.
 * - 선택지 순서 고정: 0 탐구형 / 1 활동형 / 2 공감형 / 3 끈기형 (EventData 순서와 맞춰야 함)
 */
public class TemperamentEvent extends ChoiceEvent {

    public TemperamentEvent(int age, String title, String description, String statKey) {
        super(age, title, description, statKey);
    }

    /** 부르는 곳: ChoiceEvent.run() (오버라이드했으므로 이 메서드가 대신 실행됨) */
    @Override
    protected void afterChoose(Person p, int index) {
        // TODO switch (index)로
        //      0 → p.setTemperament(new Curious())
        //      1 → new Active()
        //      2 → new Empathic()
        //      그 외 → new Persistent()
    }
}
