package randomlife.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import randomlife.event.EventData;
import randomlife.event.LifeEvent;
import randomlife.util.GameConfig;
import randomlife.util.InputUtil;
import randomlife.util.StoryText;

/**
 * 인생 진행 관리 (담당: A)
 * - 게임 전체 흐름: 시작 화면 → 출생 → 나이별 이벤트 → 노년 → 죽음 → 결산 → 다시 하기
 * - 이벤트는 LifeEvent 타입으로만 다룬다. 종류는 몰라도 run()만 부르면 됨 (다형성)
 */
public class LifeSimulator {

    // ===== 화면 문구 =====
    private static final String TITLE        = "                 RANDOM LIFE";
    private static final String SUBTITLE     = "                 80억 분의 1";
    private static final String NAME_ASK     = "당신의 이름을 정해주세요.";
    private static final String BIRTH_SEARCH = "%s, 당신이 태어날 곳을 정하는 중";   // %s=이름
    private static final String BIRTH_DOT    = ".";
    private static final String BIRTH_TITLE  = "                  출생 신고";
    private static final String BIRTH_NAME    = "이름      : %s";
    private static final String BIRTH_COUNTRY = "출생 국가 : %s";
    private static final String BIRTH_FAMILY  = "가정환경  : %s 가정";
    private static final String BIRTH_STATUS  = "[타고난 것]";
    private static final String BIRTH_START   = "당신의 삶이 시작됩니다.";
    private static final String PAUSE_TITLE  = "⏸️  잠시 멈춤 — %d세";
    private static final String PAUSE_MAP    = "[인생 지도] %s  %d세";
    private static final String PAUSE_NOW    = "[지금의 %s]";
    private static final String PAUSE_SKIP   = "(엔터를 누르면 건너뜁니다)";
    private static final String AGAIN_ASK    = "다른 누군가의 삶을 살아보시겠습니까?";
    private static final String AGAIN_MENU   = "1. 예   2. 아니오";
    private static final String GOODBYE      = "모든 삶은 한 번뿐이었습니다. 안녕히.";

    /**
     * 게임 시작 (다시 하기 반복 포함)
     * 부르는 곳: Application.main()
     */
    public void start() {
        // TODO 1. while (true) 반복
        // TODO 2. LINE, TITLE, SUBTITLE, LINE, 빈 줄 → StoryText.INTRO 한 줄씩 → 빈 줄, THIN_LINE
        // TODO 3. String name = InputUtil.readLine(NAME_ASK, false);
        // TODO 4. Person p = new Person(name);
        // TODO 5. printBirth(p) → playLife(p) → new Ending().printResult(p)
        // TODO 6. 빈 줄, AGAIN_ASK, AGAIN_MENU 출력 → InputUtil.readInt(1, 2)
        //         2면 빈 줄, GOODBYE 출력 후 return
    }

    /**
     * 출생 화면
     *   조영동, 당신이 태어날 곳을 정하는 중...   (점이 PAUSE_DELAY 간격으로 하나씩)
     *   ================================================
     *                     출생 신고
     *   ================================================
     *   이름      : 조영동
     *   출생 국가 : 🇳🇬 나이지리아
     *   가정환경  : 어려운 가정
     *
     *   [타고난 것]
     *   (statusText)
     *   ================================================
     *   당신의 삶이 시작됩니다.
     */
    private void printBirth(Person p) {
        // TODO 1. 빈 줄 → System.out.print(String.format(BIRTH_SEARCH, 이름))  (print! 줄바꿈 X)
        // TODO 2. 3번 반복: GameConfig.delay(GameConfig.PAUSE_DELAY) 후 System.out.print(BIRTH_DOT)
        // TODO 3. 빈 줄 두 번 → 위 예시대로 출력 (국가는 getDisplayName(), 가정환경은 getLabel())
        // TODO 4. InputUtil.waitEnter()
    }

    /**
     * 인생 전체 진행 (게임의 심장)
     * 흐름:
     *   [영아 사망 체크] → 7개 구간 반복 { 수명 체크 → 일상 문장 → 이벤트 2개 → 질병 → 노화 → 멈춤 } → 노년 → 죽음
     */
    private void playLife(Person p) {
        // TODO 1. checkInfantDeath(p)가 true면
        //         - p.setAge(GameConfig.randomInt(0, 4))
        //         - 빈 줄, String.format(StoryText.DEATH_INFANT, 나이) 출력
        //         - p.addRecord(StoryText.INFANT_RECORD) → lastWords(p) → return
        // TODO 2. List<LifeEvent> events = EventData.getAll();  int[] ages = GameConfig.STAGE_AGES;
        // TODO 3. for (int i = 0; i < ages.length; i++) 반복. age = ages[i], prevAge = (i == 0 ? 0 : ages[i - 1])
        //   3-1. age가 수명(p.getLifespan())보다 크면 → 이 구간에 오기 전에 죽음
        //        - 반려동물 체크: String pet = petFarewell(p, prevAge, 수명) → null 아니면 출력
        //        - p.setAge(수명) → 빈 줄, DEATH_LIFESPAN(나이, 이름) 출력
        //        - p.addRecord(StoryText.DEATH_RECORD) → lastWords(p) → return
        //   3-2. i > 0 이면 printDaily(p, i - 1, prevAge, age)
        //   3-3. p.setAge(age)
        //   3-4. events 중 e.getAge() == age 인 것만 e.run(p)
        //        → 매번 p.isDead() 확인, 죽었으면 deathByHealth(p) 후 return
        //   3-5. i > 0 이면 randomIllness(p)
        //   3-6. applyAging(p) → p.isDead()면 deathByHealth(p) 후 return
        //   3-7. isPauseAge(age)면 pause(p), 아니면 InputUtil.waitEnter()
        // TODO 4. 7구간을 다 지나면 montage(p) (60세 이후 노년)
        // TODO 5. 빈 줄, DEATH_LIFESPAN 출력 → p.addRecord(StoryText.DEATH_RECORD) → lastWords(p)
    }

    /**
     * 5세 전에 떠나는지 (국가의 5세 미만 사망률 그대로 사용)
     * 예) 나이지리아 116.8 → 1,000명 중 116.8명 → 약 11.7%
     */
    private boolean checkInfantDeath(Person p) {
        // TODO 1. GameConfig.INFANT_DEATH_ENABLED가 false면 false
        // TODO 2. GameConfig.RANDOM.nextDouble() * 1000 < 국가.getUnder5Death() 이면 true
        //         (nextDouble()은 0.0 ~ 1.0 사이 소수)
        return false;
    }

    /** 건강 0으로 사망 */
    private void deathByHealth(Person p) {
        // TODO 빈 줄 → DEATH_HEALTH(나이) 출력 → p.addRecord(StoryText.DEATH_RECORD) → lastWords(p)
    }

    /** 멈춤 나이인지 (GameConfig.PAUSE_AGES에 있는지) */
    private boolean isPauseAge(int age) {
        // TODO PAUSE_AGES를 for문으로 돌면서 같은 값 있으면 true
        return false;
    }

    /**
     * 구간 사이 일상 문장 1~2줄 (+ 그 사이에 반려동물이 떠났으면 그 장면도)
     *   . . .
     *   34세 · 부모님의 머리가 하얗게 센 걸 처음 알아챘습니다.
     *   35세 · 콩이가 무지개다리를 건넜습니다. ...
     *   . . .
     * index: StoryText.DAILY 몇 번째 배열인지 / fromAge ~ toAge: 이번 구간 나이 범위
     */
    private void printDaily(Person p, int index, int fromAge, int toAge) {
        // TODO 1. StoryText.DAILY[index]를 리스트에 담고 Collections.shuffle로 섞기
        // TODO 2. 1~2개(GameConfig.randomInt(1, 2))만 앞에서 잘라 picked 리스트로
        // TODO 3. String pet = petFarewell(p, fromAge, toAge); null 아니면 picked에 추가
        // TODO 4. Collections.sort(picked) → 문장이 "나이세 · ..."로 시작하므로 나이순 정렬됨
        // TODO 5. 빈 줄, StoryText.DOTS → picked 출력 → StoryText.DOTS
    }

    /**
     * 반려동물이 fromAge 초과 ~ toAge 이하 사이에 떠나면
     *   - 연표에 그 나이로 기록하고
     *   - 화면용 문장("45세 · 보리가 무지개다리를...")을 return
     * 아니면 null
     */
    private String petFarewell(Person p, int fromAge, int toAge) {
        // TODO 1. 반려동물이 없거나(!hasPet) 이미 떠났으면(isPetGone) null
        // TODO 2. int d = p.getPetDeathAge(); d가 fromAge 초과, toAge 이하가 아니면 null
        // TODO 3. String name = p.getPetName();
        // TODO 4. p.addRecord(d, String.format(StoryText.PET_FAREWELL_RECORD, josa(name,"이","가"), p.getPetYears()))
        // TODO 5. p.markPetGone()
        // TODO 6. String.format(StoryText.AGE_LINE, d, String.format(StoryText.PET_FAREWELL, josa(name,"이","가"))) 를 return
        return null;
    }

    /**
     * 18·40·60세 잠시 멈춤 화면
     *   ================================================
     *   ⏸️  잠시 멈춤 — 18세
     *   ================================================
     *   [인생 지도] ■■■□□□□  18세
     *
     *   [지금의 조영동]
     *   (statusText)
     *
     *   > 인도에서 인터넷을 쓰는 사람은 100명 중 65명입니다.
     *
     *   지금 가장 하고 싶은 일은 무엇인가요?
     *   (엔터를 누르면 건너뜁니다)
     *   >
     */
    private void pause(Person p) {
        // TODO 1. 위 예시대로 머리말 + 인생 지도(lifeMap) + 상태 출력
        // TODO 2. 나이별로 비교 한 줄과 질문 배열 고르기
        //         18 → PAUSE_COMPARE_18(국가 이름, 인터넷 이용률), PAUSE_18
        //         40 → PAUSE_COMPARE_40, PAUSE_40
        //         60 → PAUSE_COMPARE_60(국가 이름), PAUSE_60
        // TODO 3. 질문 배열에서 랜덤 1개 출력
        // TODO 4. p.addThought(InputUtil.readLine(PAUSE_SKIP, true))
    }

    /**
     * 인생 지도 문자열: 지나온 구간까지 ■, 남은 구간은 □
     * 예) lifeMap(18) → "■■■□□□□"
     * 부르는 곳: pause(), LifeEvent.printHeader()  ← 그래서 public static
     */
    public static String lifeMap(int age) {
        // TODO STAGE_AGES를 돌면서 a <= age 면 MAP_FILL, 아니면 MAP_EMPTY를 StringBuilder에 이어 붙여 return
        return "";
    }

    /**
     * 구간마다 ILLNESS_CHANCE% 확률로 질병
     *   큰 병을 앓았지만, 겨우 이겨 냈습니다.
     *   건강 -15
     */
    private void randomIllness(Person p) {
        // TODO 1. ILLNESS_ENABLED가 false거나 chance(ILLNESS_CHANCE)가 false면 return
        // TODO 2. damage = randomInt(ILLNESS_DAMAGE_MIN, ILLNESS_DAMAGE_MAX)
        // TODO 3. StoryText.ILLNESS에서 랜덤 1개 고르기 (한 칸 = {화면 문장, 연표 문장})
        // TODO 4. 빈 줄, 화면 문장, String.format(StoryText.ILLNESS_DAMAGE, damage) 출력
        // TODO 5. p.changeStat(GameConfig.HEALTH, -damage) → p.addRecord(연표 문장)
    }

    /** 40세, 60세에 건강 자연 감소 (기질 보너스 적용 안 함) */
    private void applyAging(Person p) {
        // TODO 나이가 40이면 HEALTH에 AGING_40, 60이면 AGING_60 만큼 changeStat
    }

    /**
     * 60세 이후 ~ 수명까지 노년 장면 (선택 없이 흘러감)
     *   . . .
     *   66세 · 아침 산책이 조금씩 짧아졌습니다.
     *   73세 · 오래된 친구의 부고를 들었습니다.
     *   . . .
     */
    private void montage(Person p) {
        // TODO 1. years = 수명 - 60, 장면 개수 count = years / 7 을 1~4 사이로 자르기
        // TODO 2. StoryText.MONTAGE 순서를 섞어서 앞에서 count개 사용
        //         (배열을 직접 섞을 수 없으니 인덱스 0~7을 리스트에 넣고 shuffle)
        // TODO 3. 장면 나이 = 60 + years * (k + 1) / (count + 1)   (k = 0, 1, 2 ...) → 고르게 퍼짐
        // TODO 4. 장면마다 화면 문장은 lines 리스트에 String.format(AGE_LINE, 나이, 화면 문장)으로,
        //         연표에는 p.addRecord(나이, 연표 문장)
        // TODO 5. petFarewell(p, 60, 수명)이 null 아니면 lines에 추가
        // TODO 6. Collections.sort(lines) → 빈 줄, DOTS, lines 출력, DOTS
        // TODO 7. p.setAge(수명)
    }

    /** 마지막 한마디 입력 (빈 입력이면 저장 안 함) */
    private void lastWords(Person p) {
        // TODO 1. 빈 줄 → InputUtil.readLine(StoryText.LAST_WORDS_ASK, true)
        // TODO 2. 비어 있지 않으면 p.setLastWords(입력)
    }
}
