package randomlife.event;

import java.util.ArrayList;
import java.util.List;

import randomlife.core.Person;
import randomlife.util.InputUtil;

/**
 * 60세 자동 결과 이벤트 (담당: B)
 * - 선택지를 보여주지 않는다. 지금까지 살아온 결과(자산·관계·플래그)로 결말이 자동으로 정해진다.
 * - outcomes를 위에서부터 검사해서 조건을 처음 만족하는 1개만 적용한다.
 *   → EventData에서 조건이 까다로운 결말을 위에, 조건 없는 결말을 맨 아래에 둔다.
 */
public class AutoEvent extends LifeEvent {

    protected List<Choice> outcomes = new ArrayList<>();

    public AutoEvent(int age, String title, String description, String statKey) {
        super(age, title, description, statKey);
    }

    /** 결말 추가 (빌더). 부르는 곳: EventData */
    public AutoEvent add(Choice outcome) {
        // TODO outcomes에 추가 후 return this
        return this;
    }

    /** 부르는 곳: LifeSimulator (LifeEvent 타입으로) */
    @Override
    public void run(Person p) {
        // TODO 1. printHeader(p)
        // TODO 2. outcomes를 순서대로 돌면서 c.isAvailable(p)가 true인 첫 번째 것만 c.apply(p) 후 break
        // TODO 3. InputUtil.waitEnter()
    }
}
