package randomlife.event;

import java.util.ArrayList;
import java.util.List;

import randomlife.core.Person;
import randomlife.util.GameConfig;
import randomlife.util.InputUtil;

/**
 * 선택지를 고르는 일반 이벤트 (담당: B)
 * - TemperamentEvent, JobEvent가 이 클래스를 상속해서 일부만 바꿔 쓴다.
 */
public class ChoiceEvent extends LifeEvent {

    // ===== 화면 문구 =====
    private static final String CHOICE_FORMAT = "%d. %s";
    private static final String LOCK_FORMAT   = "      %s %s";   // 아이콘, 이유 → "      🔒 자산 부족"

    protected List<Choice> choices = new ArrayList<>();

    public ChoiceEvent(int age, String title, String description, String statKey) {
        super(age, title, description, statKey);
    }

    /** 선택지 추가 (빌더). 부르는 곳: EventData */
    public ChoiceEvent add(Choice c) {
        choices.add(c);
        return this;
    }

    /**
     * 실행 순서: 머리말 → 선택지 고르기 → (자식용 추가 동작) → 결과 적용
     * 부르는 곳: LifeSimulator (LifeEvent 타입으로)
     */
    @Override
    public void run(Person p) {
        printHeader(p);
        int index = askChoice(p, choices);
        afterChoose(p, index);
        choices.get(index).apply(p);
    }

    /**
     * 선택지 출력 + 잠긴 번호 막기
     *   1. 대학에 진학한다
     *   2. 해외로 유학을 떠난다      🔒 자산 부족
     * 반환값: 고른 선택지의 인덱스 (0부터!)
     * 부르는 곳: run(), JobEvent.run()
     * List<? extends Choice> = Choice 또는 Choice의 자식(Job) 리스트 둘 다 받을 수 있다는 뜻
     */
    protected int askChoice(Person p, List<? extends Choice> list) {
        boolean[] locked = new boolean[list.size()];

        for (int i = 0; i < list.size(); i++) {
            Choice c = list.get(i);
            String reason = c.getLockReason(p);

            String line = String.format(CHOICE_FORMAT, i + 1, c.getText());
            if (reason != null) {
                line += String.format(LOCK_FORMAT, GameConfig.LOCK_ICON, reason);
            }
            System.out.println(line);

            locked[i] = (reason != null);
        }

        // InputUtil은 1부터 시작하는 번호를 주므로 -1 해서 인덱스로
        return InputUtil.readChoice(locked) - 1;
    }

    /**
     * 선택 직후 추가 동작 (기본은 아무것도 안 함)
     * 자식 클래스가 필요하면 오버라이드한다. → TemperamentEvent 참고
     */
    protected void afterChoose(Person p, int index) {
    }
}
