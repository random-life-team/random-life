package randomlife.event;

import java.util.ArrayList;
import java.util.List;

import randomlife.core.Person;
import randomlife.util.GameConfig;
import randomlife.util.InputUtil;
import randomlife.util.StoryText;

/**
 * 선택지 하나 (담당: B)
 * - 조건(잠금), 결과 문장, 능력치 변화, 성공 확률, 연표 문장을 전부 가진다.
 * - EventData에서 아래처럼 점(.)으로 이어 붙여 만든다. (빌더 패턴: 메서드가 자기 자신을 return)
 *
 *   new Choice("대학에 진학한다", "입학식 날, 강의실은 생각보다 넓었습니다.")
 *       .needMoney(200)                          // 조건: 자산 200만원 이상
 *       .effect(GameConfig.KNOW, 10, 15)         // 지식 +10~15
 *       .effect(GameConfig.MONEY, -200, -200)    // 자산 -200
 *       .addFlag(GameConfig.FLAG_COLLEGE)        // 대학 플래그
 *       .record("대학에 입학했다");               // 연표 한 줄
 *
 * - 조건은 전부 만족해야 열린다. (AND)
 */
public class Choice {

    // ===== 화면 문구 =====
    private static final String LOCK_MONEY   = "자산 부족";
    private static final String LOCK_STAT    = "%s 부족";        // %s=능력치 한글 이름
    private static final String LOCK_COLLEGE = "대학 졸업 필요";
    private static final String LOCK_MARRIED = "결혼 필요";
    private static final String LOCK_ETC     = "조건 부족";
    private static final String NO_CHANGE    = "변화 없음";
    private static final String CHANGE_STAT  = "%s %s%d";        // "지식 +12"
    private static final String CHANGE_MONEY = "자산 %s%,d만원";  // "자산 -200만원"
    private static final String CHANGE_JOIN  = " / ";

    // ===== 기본 =====
    protected String text;          // 선택지 문장 ("대학에 진학한다")
    protected String resultText;    // 고른 뒤 결과 문장
    protected String record;        // 성공 시 연표 문장 (null이면 연표에 안 남김)

    // ===== 조건 (기본값 = 조건 없음) =====
    protected int needMoney = Integer.MIN_VALUE;   // 이 금액 이상 있어야 열림
    protected String needFlag;                     // 이 플래그가 있어야 열림
    protected String needStat;                     // 이 능력치가
    protected int needStatMin;                     // 이 값 이상이어야 열림

    // ===== 결과 =====
    // effects 한 칸 = {능력치 이름, 최소, 최대}  예) {"KNOW", "10", "15"}
    protected List<String[]> effects = new ArrayList<>();
    protected String addFlag;

    // ===== 성공/실패 (기본값 = 무조건 성공) =====
    protected int successRate = 100;
    protected String failText;
    protected String failRecord;
    protected List<String[]> failEffects = new ArrayList<>();

    // ===== 반려동물 선택지 전용 =====
    protected String petType;        // "강아지" 등 (null이면 반려동물 선택지 아님)
    protected String petDefaultName; // 이름 안 지으면 쓸 기본 이름

    /** 부르는 곳: EventData, JobData */
    public Choice(String text, String resultText) {
        // TODO text, resultText 저장
    }

    // =====================================================
    // 빌더 메서드 — 값 저장 후 return this; (그래야 점으로 계속 이어 붙일 수 있음)
    // =====================================================
    public Choice record(String record) {
        // TODO record 저장 후 return this
        return this;
    }

    public Choice needMoney(int money) {
        // TODO needMoney 저장 후 return this
        return this;
    }

    public Choice needFlag(String flag) {
        // TODO needFlag 저장 후 return this
        return this;
    }

    public Choice needStat(String stat, int min) {
        // TODO needStat, needStatMin 저장 후 return this
        return this;
    }

    public Choice addFlag(String flag) {
        // TODO addFlag 저장 후 return this
        return this;
    }

    /** 능력치 변화 추가 (min ~ max 사이 랜덤). 예) effect(GameConfig.KNOW, 10, 15) */
    public Choice effect(String stat, int min, int max) {
        // TODO effects에 new String[]{stat, "" + min, "" + max} 추가 후 return this
        return this;
    }

    /** 성공 확률 + 실패 시 문장 + 실패 시 연표. 예) success(50, "자리는 다른 사람에게...", "승진에서 떨어졌다") */
    public Choice success(int rate, String failText, String failRecord) {
        // TODO successRate, failText, failRecord 저장 후 return this
        return this;
    }

    /** 실패 시 능력치 변화 추가 */
    public Choice failEffect(String stat, int min, int max) {
        // TODO failEffects에 추가 후 return this (effect와 같은 방식)
        return this;
    }

    /** 반려동물 선택지로 만들기. 예) pet("강아지", "보리") */
    public Choice pet(String type, String defaultName) {
        // TODO petType, petDefaultName 저장 후 return this
        return this;
    }

    // =====================================================
    // 조건 검사
    // =====================================================

    /** 고를 수 있는지. 부르는 곳: ChoiceEvent, AutoEvent, JobEvent */
    public boolean isAvailable(Person p) {
        // TODO getLockReason(p)가 null이면 true
        return true;
    }

    /**
     * 잠긴 이유 (열려 있으면 null)
     * 예) "자산 부족", "대학 졸업 필요", "지식 부족"
     * 부르는 곳: ChoiceEvent.askChoice() (🔒 옆에 출력)
     */
    public String getLockReason(Person p) {
        // TODO 1. needFlag가 있는데 p.hasFlag(needFlag)가 false면 flagReason(needFlag) return
        // TODO 2. needStat이 있는데 p.getStat(needStat) < needStatMin 이면
        //         String.format(LOCK_STAT, statName(needStat)) return
        // TODO 3. p.getStat(GameConfig.MONEY) < needMoney 면 LOCK_MONEY return
        // TODO 4. 다 통과하면 null return
        return null;
    }

    // =====================================================
    // 선택 결과 적용
    // =====================================================

    /**
     * 고른 뒤 결과 적용
     * 부르는 곳: ChoiceEvent, AutoEvent, Job(오버라이드 후 super.apply)
     */
    public void apply(Person p) {
        // TODO 1. String shownResult = resultText;  String shownRecord = record;
        // TODO 2. 반려동물 선택지면(petType != null)
        //         - InputUtil.readLine(String.format(StoryText.PET_NAME_ASK, petDefaultName), true)로 이름 입력
        //         - 비어 있으면 petDefaultName 사용
        //         - p.adoptPet(petType, 이름)
        //         - shownResult = String.format(StoryText.PET_WELCOME, StoryText.josa(이름, "이", "가"))
        //         - shownRecord = String.format(StoryText.PET_RECORD, petType, StoryText.josa(이름, "을", "를"))
        // TODO 3. boolean success = GameConfig.chance(successRate);
        // TODO 4. 빈 줄 → 성공이면 shownResult, 실패면 failText 출력
        // TODO 5. 성공이면 effects, 실패면 failEffects 를 돌면서
        //         - 최소/최대는 Integer.parseInt(e[1]), Integer.parseInt(e[2])
        //         - amount = GameConfig.randomInt(최소, 최대)
        //         - 기질이 있으면 amount = p.getTemperament().applyBonus(e[0], amount)
        //         - amount가 0이면 건너뜀
        //         - p.changeStat(e[0], amount)
        //         - formatChange(e[0], amount)를 리스트에 모음
        // TODO 6. 빈 줄 → 리스트가 비었으면 NO_CHANGE, 아니면 String.join(CHANGE_JOIN, 리스트) 출력
        // TODO 7. 성공이면 addFlag가 있으면 p.addFlag(), shownRecord가 있으면 p.addRecord()
        //         실패면 failRecord가 있으면 p.addRecord(failRecord)
    }

    /** 변화 한 개를 글자로. 예) ("KNOW", 12) → "지식 +12", ("MONEY", -200) → "자산 -200만원" */
    private String formatChange(String stat, int amount) {
        // TODO 1. 부호 = amount > 0 이면 "+", 아니면 "" (음수는 숫자에 - 가 이미 붙어 있음)
        // TODO 2. MONEY면 String.format(CHANGE_MONEY, 부호, amount)
        // TODO 3. 아니면 String.format(CHANGE_STAT, statName(stat), 부호, amount)
        return "";
    }

    /** 능력치 영어 이름 → 한글. 예) "KNOW" → "지식" */
    private String statName(String stat) {
        // TODO HEALTH→"건강", HAPPY→"행복", KNOW→"지식", RELATION→"관계", 나머지→"자산"
        return "";
    }

    /** 플래그 → 잠금 이유. 예) "COLLEGE" → "대학 졸업 필요" */
    private String flagReason(String flag) {
        // TODO FLAG_COLLEGE→LOCK_COLLEGE, FLAG_MARRIED→LOCK_MARRIED, 나머지→LOCK_ETC
        return "";
    }

    public String getText() {
        // TODO text 반환
        return null;
    }
}
