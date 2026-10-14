package randomlife.util;

import java.util.Random;

/**
 * 게임 전체 설정값 모음 (담당: A)
 * - 숫자를 바꾸고 싶으면 여기만 고치면 된다.
 * - 다른 클래스에서는 GameConfig.상수명 으로 사용한다.
 * - 능력치·플래그 이름은 문자열을 직접 쓰지 말고 반드시 여기 상수를 쓴다. (오타 방지)
 */
public class GameConfig {

    // ===== 공용 랜덤 (프로그램 전체에서 이것 하나만 사용) =====
    public static final Random RANDOM = new Random();

    // ===== 화면 =====
    public static final String LINE      = "================================================";
    public static final String THIN_LINE = "------------------------------------------------";
    public static final String MAP_FILL  = "■";
    public static final String MAP_EMPTY = "□";
    public static final String LOCK_ICON = "🔒";

    // 감성 연출용 대기 시간 (ms) — 시연 때 답답하면 0으로
    public static final int LINE_DELAY  = 300;   // 마지막 연표를 한 줄씩 띄울 때
    public static final int PAUSE_DELAY = 800;   // 출생 "..." 연출

    // ===== 인생 단계 =====
    // 인생 지도 칸 수 = STAGE_AGES.length (7칸)
    public static final int[] STAGE_AGES = {5, 10, 18, 25, 32, 40, 60};
    public static final int EVENTS_PER_STAGE = 2;

    // 잠깐 멈추는 질문(남긴 생각)이 나오는 나이
    public static final int[] PAUSE_AGES = {18, 40, 60};

    // 60세부터는 선택지 없이 자동 결과
    public static final int AUTO_EVENT_AGE = 60;

    // ===== 능력치 이름 =====
    public static final String HEALTH   = "HEALTH";
    public static final String HAPPY    = "HAPPY";
    public static final String KNOW     = "KNOW";
    public static final String RELATION = "RELATION";
    public static final String MONEY    = "MONEY";     // 단위: 만원

    public static final int STAT_MIN = 0;
    public static final int STAT_MAX = 100;

    // 시작 능력치 범위
    public static final int START_STAT_MIN = 40;
    public static final int START_STAT_MAX = 70;

    // 건강이 이 값 이하가 되면 사망
    public static final int DEATH_HEALTH = 0;

    // 나이 들며 자연스럽게 깎이는 건강
    public static final int AGING_40 = -10;
    public static final int AGING_60 = -15;

    // ===== 사망·질병 =====
    public static final boolean INFANT_DEATH_ENABLED = true;   // 시연 때 false로 끌 수 있음
    public static final boolean ILLNESS_ENABLED = true;
    public static final int ILLNESS_CHANCE     = 10;           // 구간마다 % 확률
    public static final int ILLNESS_DAMAGE_MIN = 10;
    public static final int ILLNESS_DAMAGE_MAX = 20;

    // ===== 직업 =====
    public static final int JOB_SHOW_COUNT = 3;   // 30개 중 3개만 보여줌

    // ===== 반려동물 (같은 칸끼리 짝) =====
    // 32세에 들이므로 강아지면 42~48세, 고양이면 44~52세, 햄스터면 34~35세쯤 떠남
    public static final String[] PET_TYPES    = {"강아지", "고양이", "햄스터"};
    public static final int[]    PET_LIFE_MIN = {10, 12, 2};
    public static final int[]    PET_LIFE_MAX = {16, 20, 3};

    // ===== 플래그 이름 =====
    public static final String FLAG_COLLEGE   = "COLLEGE";     // 대학/유학
    public static final String FLAG_EARLY_JOB = "EARLY_JOB";   // 고졸 취업
    public static final String FLAG_MARRIED   = "MARRIED";     // 결혼
    public static final String FLAG_PET       = "PET";         // 반려동물

    /**
     * min ~ max 사이 랜덤 정수 (양 끝 포함)
     * 예) randomInt(10, 16) → 10, 11, ..., 16 중 하나
     * 부르는 곳: 거의 모든 클래스
     */
    public static int randomInt(int min, int max) {
        // TODO RANDOM.nextInt(개수)는 0 ~ 개수-1 을 준다.
        //      개수 = max - min + 1 → 거기에 min을 더해서 return
        return min;
    }

    /**
     * percent% 확률로 true
     * 예) chance(70) → 10번 중 7번 정도 true
     * 부르는 곳: Choice(성공 확률), LifeSimulator(질병)
     */
    public static boolean chance(int percent) {
        // TODO randomInt(1, 100)이 percent 이하이면 true
        return false;
    }

    /**
     * ms 만큼 잠깐 멈춤 (감성 연출용)
     * 부르는 곳: LifeSimulator(출생 연출), Ending(연표 한 줄씩)
     */
    public static void delay(int ms) {
        // TODO 1. ms가 0 이하면 바로 return
        // TODO 2. Thread.sleep(ms) 호출
        //         → InterruptedException을 던지므로 try-catch로 감싸야 컴파일됨 (catch 안은 비워도 OK)
            // 무시
    }
}
