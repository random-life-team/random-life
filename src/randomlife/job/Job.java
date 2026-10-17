package randomlife.job;

import randomlife.core.Person;
import randomlife.event.Choice;

/**
 * 직업 하나 (담당: C)
 * - Choice를 상속 → 조건(잠금), 능력치 변화, 연표 기능을 그대로 물려받는다.
 * - 다른 점은 딱 하나: 고르면 Person의 직업이 정해진다. → apply()만 오버라이드
 */
public class Job extends Choice {

    /** 부르는 곳: JobData.job() */
    public Job(String name, String resultText) {
        super(name, resultText);
    }

    /** 부르는 곳: JobEvent.run() */
    @Override
    public void apply(Person p) {
        // TODO 1. p.setJob(text)   (text = 직업 이름, 부모 Choice의 protected 필드라 바로 쓸 수 있음)
        // TODO 2. super.apply(p)   (나머지 결과 처리는 부모 것을 그대로 사용)
        p.setJob(text);
        super.apply(p);

    }
}
