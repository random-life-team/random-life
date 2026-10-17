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
    protected String removeFlag;   // 성공 시 지울 플래그 (예: 이혼 → MARRIED 제거)

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
        this.text = text;
        this.resultText = resultText;
    }

    // =====================================================
    // 빌더 메서드 — 값 저장 후 return this; (그래야 점으로 계속 이어 붙일 수 있음)
    // =====================================================
    public Choice record(String record) {
        this.record = record;
        return this;
    }

    public Choice needMoney(int money) {
        this.needMoney = money;
        return this;
    }

    public Choice needFlag(String flag) {
        this.needFlag = flag;
        return this;
    }

    public Choice needStat(String stat, int min) {
        this.needStat = stat;
        this.needStatMin = min;
        return this;
    }

    public Choice addFlag(String flag) {
        this.addFlag = flag;
        return this;
    }
    /** 성공 시 플래그 지우기. 예) removeFlag(GameConfig.FLAG_MARRIED) */
    public Choice removeFlag(String flag) {
        this.removeFlag = flag;
        return this;
    }

    /** 능력치 변화 추가 (min ~ max 사이 랜덤). 예) effect(GameConfig.KNOW, 10, 15) */
    public Choice effect(String stat, int min, int max) {
        effects.add(new String[]{stat, "" + min, "" + max});
        return this;
    }

    /** 성공 확률 + 실패 시 문장 + 실패 시 연표. 예) success(50, "자리는 다른 사람에게...", "승진에서 떨어졌다") */
    public Choice success(int rate, String failText, String failRecord) {
        this.successRate = rate;
        this.failText = failText;
        this.failRecord = failRecord;
        return this;
    }

    /** 실패 시 능력치 변화 추가 */
    public Choice failEffect(String stat, int min, int max) {
        failEffects.add(new String[]{stat, "" + min, "" + max});
        return this;
    }

    /** 반려동물 선택지로 만들기. 예) pet("강아지", "보리") */
    public Choice pet(String type, String defaultName) {
        this.petType = type;
        this.petDefaultName = defaultName;
        return this;
    }

    // =====================================================
    // 조건 검사
    // =====================================================

    /** 고를 수 있는지. 부르는 곳: ChoiceEvent, AutoEvent, JobEvent */
    public boolean isAvailable(Person p) {
        return getLockReason(p) == null;
    }

    /**
     * 잠긴 이유 (열려 있으면 null)
     * 예) "자산 부족", "대학 졸업 필요", "지식 부족"
     * 부르는 곳: ChoiceEvent.askChoice() (🔒 옆에 출력)
     */
    public String getLockReason(Person p) {
        // 1. 플래그 조건
        if (needFlag != null && !p.hasFlag(needFlag)) {
            return flagReason(needFlag);
        }
        // 2. 능력치 조건
        if (needStat != null && p.getStat(needStat) < needStatMin) {
            return String.format(LOCK_STAT, statName(needStat));
        }
        // 3. 자산 조건 (기본값 Integer.MIN_VALUE라 조건 없으면 항상 통과)
        if (p.getStat(GameConfig.MONEY) < needMoney) {
            return LOCK_MONEY;
        }
        // 4. 전부 통과
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
        String shownResult = resultText;
        String shownRecord = record;

        // 반려동물 선택지면 이름을 받고 입양
        if (petType != null) {
            String name = InputUtil.readLine(String.format(StoryText.PET_NAME_ASK, petDefaultName), true);
            if (name.isEmpty()) {
                name = petDefaultName;
            }
            p.adoptPet(petType, name);
            shownResult = String.format(StoryText.PET_WELCOME, StoryText.josa(name, "이", "가"));
            shownRecord = String.format(StoryText.PET_RECORD, petType, StoryText.josa(name, "을", "를"));
        }

        // 성공 여부 (successRate 기본값 100 → 무조건 성공)
        boolean success = GameConfig.chance(successRate);

        System.out.println();
        System.out.println(success ? shownResult : failText);

        // 성공/실패에 맞는 능력치 변화 적용
        List<String[]> targetEffects = success ? effects : failEffects;
        List<String> changes = new ArrayList<>();

        for (String[] e : targetEffects) {
            String stat = e[0];
            int min = Integer.parseInt(e[1]);
            int max = Integer.parseInt(e[2]);
            int amount = GameConfig.randomInt(min, max);

            // 기질 보너스 (5세 이전엔 기질이 없음 → null 체크)
            if (p.getTemperament() != null) {
                amount = p.getTemperament().applyBonus(stat, amount);
            }
            if (amount == 0) {
                continue;
            }

            p.changeStat(stat, amount);
            changes.add(formatChange(stat, amount));
        }

        System.out.println();
        if (changes.isEmpty()) {
            System.out.println(NO_CHANGE);
        } else {
            System.out.println(String.join(CHANGE_JOIN, changes));
        }

        // 플래그 · 연표
        if (success) {
            if (addFlag != null) {
                p.addFlag(addFlag);
            }
            if (removeFlag != null) {        // ← 추가
                p.removeFlag(removeFlag);    // ← 추가
            }
            if (shownRecord != null) {
                p.addRecord(shownRecord);
            }
        } else {
            if (failRecord != null) {
                p.addRecord(failRecord);
            }
        }
    }

    /** 변화 한 개를 글자로. 예) ("KNOW", 12) → "지식 +12", ("MONEY", -200) → "자산 -200만원" */
    private String formatChange(String stat, int amount) {
        String sign = amount > 0 ? "+" : "";   // 음수는 숫자에 - 가 이미 붙어 있음

        if (GameConfig.MONEY.equals(stat)) {
            return String.format(CHANGE_MONEY, sign, amount);
        }
        return String.format(CHANGE_STAT, statName(stat), sign, amount);
    }

    /** 능력치 영어 이름 → 한글. 예) "KNOW" → "지식" */
    private String statName(String stat) {
        if (GameConfig.HEALTH.equals(stat)) {
            return "건강";
        } else if (GameConfig.HAPPY.equals(stat)) {
            return "행복";
        } else if (GameConfig.KNOW.equals(stat)) {
            return "지식";
        } else if (GameConfig.RELATION.equals(stat)) {
            return "관계";
        }
        return "자산";
    }

    /** 플래그 → 잠금 이유. 예) "COLLEGE" → "대학 졸업 필요" */
    private String flagReason(String flag) {
        if (GameConfig.FLAG_COLLEGE.equals(flag)) {
            return LOCK_COLLEGE;
        } else if (GameConfig.FLAG_MARRIED.equals(flag)) {
            return LOCK_MARRIED;
        }
        return LOCK_ETC;
    }

    public String getText() {
        return text;
    }
}
