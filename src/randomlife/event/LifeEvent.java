package randomlife.event;

import randomlife.core.Country;
import randomlife.core.LifeSimulator;
import randomlife.core.Person;
import randomlife.util.GameConfig;
import randomlife.util.StoryText;

/**
 * 모든 이벤트의 부모 (담당: B)
 * - LifeSimulator는 이벤트 종류(선택형/기질/직업/자동)를 몰라도 run()만 부르면 된다. (다형성)
 *
 *   LifeEvent e = new ChoiceEvent(...);   → e.run(p) 하면 ChoiceEvent의 run 실행
 *   LifeEvent e = new AutoEvent(...);     → e.run(p) 하면 AutoEvent의 run 실행
 */
public abstract class LifeEvent {

    // ===== 통계 종류 (statKey에 넣는 값) =====
    public static final String STAT_U5      = "U5";        // 5세 미만 사망
    public static final String STAT_COLLEGE = "COLLEGE";   // 대학 총취학률
    public static final String STAT_SMOKING = "SMOKING";   // 흡연율

    // ===== 화면 문구 =====
    private static final String TITLE_FORMAT = "[%d세] %s";
    private static final String MAP_FORMAT   = "[인생 지도] %s  %d세";

    protected int age;              // 이 이벤트가 나오는 나이
    protected String title;         // "진로 선택"
    protected String description;   // 상황 설명
    protected String statKey;       // 머리말에 보여줄 국가 통계 (없으면 null)

    /** 부르는 곳: 자식 클래스 생성자의 super(...) */
    public LifeEvent(int age, String title, String description, String statKey) {
        // TODO 필드 4개 저장
    }

    /**
     * 이벤트 실행 — 자식 클래스마다 다르게 구현한다.
     * 부르는 곳: LifeSimulator.playLife()
     */
    public abstract void run(Person p);

    /**
     * 이벤트 머리말 출력 (모든 이벤트 공통)
     *
     * ================================================
     * [18세] 진로 선택
     * ================================================
     * [인생 지도] ■■■□□□□  18세
     *
     * > 인도 대학 총취학률 34%        ← statKey 있을 때만
     *
     * 졸업식이 끝났습니다. ...
     *
     * 부르는 곳: 자식 클래스의 run()
     */
    protected void printHeader(Person p) {
        // TODO 1. 빈 줄, GameConfig.LINE
        // TODO 2. String.format(TITLE_FORMAT, age, title)
        // TODO 3. GameConfig.LINE
        // TODO 4. String.format(MAP_FORMAT, LifeSimulator.lifeMap(age), age)
        // TODO 5. 빈 줄
        // TODO 6. statKey가 null이 아니면 printStatLine(p) 후 빈 줄
        // TODO 7. description, 빈 줄
    }

    /**
     * 국가 통계 한 줄 출력 (세계은행 실제 자료)
     *   U5      → "> 나이지리아에서는 아이 1,000명 중 116.8명이 다섯 살을 맞이하지 못합니다."
     *             "  조영동은 맞이했습니다."
     *   COLLEGE → "> 인도 대학 총취학률 34%"
     *   SMOKING → "> 한국 15세 이상 흡연율 19.0%"
     */
    private void printStatLine(Person p) {
        // TODO 1. Country c = p.getCountry();
        // TODO 2. statKey가 STAT_U5 이면
        //         StoryText.STAT_U5 (국가 이름, c.getUnder5Text())
        //         StoryText.STAT_U5_SURVIVE (StoryText.josa(이름, "은", "는"))  두 줄 출력
        // TODO 3. STAT_COLLEGE 이면 StoryText.STAT_COLLEGE (국가 이름, c.getCollegeRate())
        // TODO 4. STAT_SMOKING 이면 StoryText.STAT_SMOKING (국가 이름, String.format("%.1f", c.getSmokingRate()))
    }

    public int getAge() {
        // TODO age 반환
        return 0;
    }

    public String getTitle() {
        // TODO title 반환
        return null;
    }
}
