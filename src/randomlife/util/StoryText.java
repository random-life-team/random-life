package randomlife.util;

/**
 * 감성 문구 모음 (담당: C)
 * - 문구는 작성 완료. 말투를 바꾸고 싶으면 여기만 고치면 된다.
 * - %s, %d 자리는 String.format()으로 채운다.
 *   예) String.format(StoryText.DEATH_LIFESPAN, 78, "조영동") → "78세, 조영동의 삶이 끝나갑니다."
 * - 이름 뒤에 조사(은/는, 이/가)가 붙는 자리는 josa()로 먼저 붙여서 넣는다.
 */
public class StoryText {

    // =====================================================
    // 시작
    // =====================================================
    public static final String[] INTRO = {
        "지금 이 순간에도 1초에 네 명의 아기가 태어납니다.",
        "당신도 그중 한 명이 되어 새로운 삶을 시작합니다.",
        "",
        "어디서, 누구의 아이로 태어날지는 고를 수 없습니다."
    };

    // 연표 첫 줄 (%s=국가 이름, %s=가정환경)
    // 예) "나이지리아, 어려운 가정에서 태어났다"
    public static final String BIRTH_RECORD = "%s, %s 가정에서 태어났다";

    // =====================================================
    // 이벤트 머리말의 국가 통계 한 줄 (세계은행 실제 자료)
    // =====================================================
    // 5세 유치원 (%s=국가, %s=1,000명당 사망 수)
    public static final String STAT_U5         = "> %s에서는 아이 1,000명 중 %s명이 다섯 살을 맞이하지 못합니다.";
    // (%s=josa(이름, "은", "는"))
    public static final String STAT_U5_SURVIVE = "  %s 다섯 번째 생일을 맞이했습니다.";
    // 18세 진로 (%s=국가, %s=취학률 문구)
    public static final String STAT_COLLEGE    = "> %s 대학 총취학률 %s";
    // 40세 건강검진 (%s=국가, %s=흡연율)
    public static final String STAT_SMOKING    = "> %s 15세 이상 흡연율 %s%%";

    // =====================================================
    // 잠시 멈춤 (18·40·60세) — 질문은 3개 중 랜덤 1개
    // =====================================================
    public static final String[] PAUSE_18 = {
        "지금 가장 하고 싶은 일은 무엇인가요?",
        "열여덟의 당신이 가장 두려워했던 것은 무엇이었나요?",
        "다른 곳에서 태어났다면, 지금 무엇을 꿈꾸고 있었을까요?"
    };
    public static final String[] PAUSE_40 = {
        "지금까지 가장 잘한 선택은 무엇인가요?",
        "다시 돌아간다면, 바꾸고 싶은 하루가 있나요?",
        "요즘 가장 자주 떠올리는 사람은 누구인가요?"
    };
    public static final String[] PAUSE_60 = {
        "다음 세대에게 남기고 싶은 말이 있나요?",
        "당연하다고 여겼지만, 사실은 운이 좋았던 일이 있나요?",
        "이 삶에서 가장 오래 기억하고 싶은 장면은 무엇인가요?"
    };

    // 멈춤 화면 국가 비교 한 줄
    // 18세 (%s=국가, %d=인터넷 이용률)
    public static final String PAUSE_COMPARE_18 = "> %s에서 인터넷을 쓰는 사람은 100명 중 %d명입니다.";
    public static final String PAUSE_COMPARE_40 = "> 같은 해 태어난 사람 중 일부는 이미 세상을 떠났습니다.";
    // 60세 (%s=국가)
    public static final String PAUSE_COMPARE_60 = "> %s에서 예순을 맞이하는 건, 모두에게 주어진 일은 아니었습니다.";

    // =====================================================
    // 구간 사이 일상 문장 (화면에만 보여주고 연표에는 안 남김)
    // DAILY[0] = 5→10세 사이, DAILY[1] = 10→18세 사이 ... DAILY[5] = 40→60세 사이
    // =====================================================
    public static final String[][] DAILY = {
        { "7세 · 처음으로 자전거를 혼자 탔습니다. 동네가 조금 작아 보였습니다.",
          "8세 · 크리스마스를 가족과 함께 보냈습니다.",
          "9세 · 고장 난 장난감을 뜯어 보고는 다시 고쳐 놓았습니다." },
        { "12세 · 처음으로 누구에게도 말하지 않은 비밀이 생겼습니다.",
          "14세 · 친구와 크게 다퉜고, 다음 날 화해했습니다.",
          "16세 · 좋아하는 노래를 반복해서 들었습니다. 가사가 꼭 내 이야기 같았습니다." },
        { "20세 · 어른이 되었다고 생각했고, 처음으로 혼자 밤기차를 탔습니다.",
          "22세 · 아르바이트를 해서 받은 첫 월급으로, 부모님께 밥을 사드렸습니다.",
          "23세 · 오래된 친구의 결혼식에서 축사를 했습니다." },
        { "27세 · 언젠가는 나만의 집을 갖고 싶다는 생각을 했습니다.",
          "29세 · 한때 매일 만나던 사람과 어느새 연락이 뜸해졌습니다.",
          "30세 · 생일 케이크의 초를 세다가 그만두었습니다." },
        { "34세 · 오랜만에 마주한 부모님의 머리가 하얗게 센 걸 처음 알아챘습니다.",
          "36세 · 오래 미뤄 둔 사진첩을 정리했습니다.",
          "38세 · 계단을 오르다 숨이 차서 잠깐 쉬었습니다." },
        { "45세 · 특별한 일이 없는 하루가 얼마나 소중한지 조금씩 알게 되었습니다.",
          "50세 · 어린 시절 살던 동네에 가 보았습니다. 그 시절과 풍경이 많이 달라져 있었습니다.",
          "55세 · 노안이 와서 돋보기를 처음 샀습니다..." }
    };

    // =====================================================
    // 60세 이후 노년 장면 {화면 문장, 연표 문장}
    // =====================================================
    public static final String[][] MONTAGE = {
        { "아침 산책이 조금씩 짧아졌습니다.",                     "아침 산책이 조금씩 짧아졌다" },
        { "오래된 친구의 부고를 들었습니다.",                     "오래된 친구의 부고를 들었다" },
        { "창가에 앉아 지나가는 아이들을 오래 보았습니다.",       "창가에 앉아 지나가는 아이들을 오래 보았다" },
        { "옛날 노래를 듣다 혼자 웃었습니다.",                    "옛날 노래를 듣다 혼자 웃었다" },
        { "누군가 처음으로 '할머니', '할아버지'라고 불렀습니다.", "처음으로 할머니, 할아버지라고 불렸다" },
        { "텃밭에 심은 토마토가 처음으로 빨갛게 익었습니다.",     "텃밭의 토마토가 처음으로 익었다" },
        { "젊을 때 쓴 편지를 다시 읽었습니다.",                   "젊을 때 쓴 편지를 다시 읽었다" },
        { "계절이 바뀌는 게 예전보다 빠르게 느껴졌습니다.",       "계절이 예전보다 빠르게 지나갔다" }
    };

    // 화면/연표 공용 "나이 · 문장" 형식 (%d=나이, %s=문장)
    public static final String AGE_LINE = "%d세 · %s";
    public static final String DOTS = ". . .";

    // =====================================================
    // 반려동물
    // =====================================================
    // 이름 정하기 (%s=기본 이름)
    public static final String PET_NAME_ASK = "이름을 지어주세요. (엔터를 누르면 '%s')";
    // 데려온 날 화면 (%s=josa(이름, "이", "가"))
    public static final String PET_WELCOME = "%s 가족이 되었습니다.";
    // 데려온 날 연표 (%s=종류, %s=josa(이름, "을", "를"))  예) "강아지 보리를 데려왔다"
    public static final String PET_RECORD = "%s %s 데려왔다";
    // 떠난 날 화면 (%s=josa(이름, "이", "가"))
    public static final String PET_FAREWELL = "%s 무지개다리를 건넜습니다. 한동안 현관문을 열 때마다 이름을 불렀습니다.";
    // 떠난 날 연표 (%s=josa(이름, "이", "가"), %d=함께한 햇수)  예) "보리가 13살의 나이로 무지개다리를 건넜다"
    public static final String PET_FAREWELL_RECORD = "%s %d살의 나이로 무지개다리를 건넜다";

    // =====================================================
    // 질병 {화면 문장, 연표 문장}
    // =====================================================
    public static final String[][] ILLNESS = {
        { "한동안 원인 모를 열에 시달렸습니다.",     "원인 모를 열에 오래 시달렸다" },
        { "큰 병을 앓았지만, 겨우 이겨 냈습니다.",    "큰 병을 앓았지만, 겨우 이겨 냈다" },
        { "사고로 몇 달 동안 병원에 있었습니다.",     "사고로 몇 달 동안 병원에 있었다" }
    };
    // (%d=깎인 건강)
    public static final String ILLNESS_DAMAGE = "건강 -%d";

    // =====================================================
    // 마지막 순간
    // =====================================================
    // (%d=나이, %s=이름)
    public static final String DEATH_LIFESPAN = "%d세, %s의 삶이 끝나갑니다.";
    public static final String DEATH_HEALTH   = "%d세, 몸이 더는 버티지 못했습니다.";
    public static final String DEATH_INFANT   = "%d세, 짧은 생을 마감했습니다.";
    public static final String DEATH_RECORD   = "눈을 감았다";
    public static final String INFANT_RECORD  = "짧은 생을 마감했다";
    public static final String LAST_WORDS_ASK = "이 사람에게 마지막 한마디를 건네주세요. (엔터를 누르면 건너뜁니다)";

    // =====================================================
    // 결산 — 인생 평가 (Ending에서 조건별로 1개 선택)
    // =====================================================
    public static final String EVAL_INFANT   = "짧았지만, 이것도 하나의 인생이었습니다.";
    public static final String EVAL_HAPPY    = "행복을 끝까지 놓지 않은 인생";
    public static final String EVAL_RELATION = "사람 곁에서 살아간 인생";
    public static final String EVAL_RICH     = "넉넉함을 일군 인생";
    public static final String EVAL_KNOW     = "끝까지 배우기를 멈추지 않은 인생";
    public static final String EVAL_HARD     = "쉽지 않았지만, 끝까지 살아 낸 인생";
    public static final String EVAL_DEFAULT  = "평범하고, 그래서 소중했던 인생";

    // 묘비명 앞부분 (기질별)
    public static final String EPITAPH_CURIOUS    = "궁금한 것이 많았고, ";
    public static final String EPITAPH_ACTIVE     = "누구보다 바쁘게 달렸고, ";
    public static final String EPITAPH_EMPATHIC   = "늘 누군가의 곁에 있었고, ";
    public static final String EPITAPH_PERSISTENT = "넘어져도 다시 일어났고, ";

    // 묘비명 뒷부분 (평가별, 위 EVAL_ 순서와 짝)
    public static final String EPITAPH_HAPPY    = "끝까지 행복했던 사람";
    public static final String EPITAPH_RELATION = "사람들 곁에 머문 사람";
    public static final String EPITAPH_RICH     = "넉넉함을 일군 사람";
    public static final String EPITAPH_KNOW     = "배우기를 멈추지 않은 사람";
    public static final String EPITAPH_HARD     = "끝내 버텨 낸 사람";
    public static final String EPITAPH_DEFAULT  = "평범하게 소중했던 사람";

    // =====================================================
    // 결산 — 마지막 여운 문단
    // =====================================================
    // (%s=josa(이름, "은", "는"), %d=나이)
    public static final String END_LIVED      = "%s %d년을 살았습니다.";
    // (%s=국가, %.1f=기대수명, %d=차이)
    public static final String END_LIFE_MORE  = "%s의 기대수명은 %.1f세. 그보다 %d년을 더 살았습니다.";
    public static final String END_LIFE_LESS  = "%s의 기대수명은 %.1f세. 그보다 %d년 먼저 떠났습니다.";
    public static final String END_LIFE_SAME  = "%s의 기대수명은 %.1f세. 딱 그만큼을 살았습니다.";
    // (%s=국가, %s=1,000명당 5세 미만 사망 수)
    public static final String END_U5         = "같은 해 %s에서 태어난 아이 1,000명 중 %s명은 다섯 살을 넘기지 못했습니다.";
    public static final String END_INFANT     = "그 아이들 중 한 명에게도, 이름이 있었습니다.";
    // (%s=josa(반려동물 이름, "과", "와"), %d=함께한 햇수)
    public static final String END_PET_GONE   = "%s 함께한 %d년이, 그중 가장 따뜻한 시간이었을지도 모릅니다.";
    // (%s=josa(반려동물 이름, "은", "는"))
    public static final String END_PET_STAY   = "%s 마지막 날까지 곁을 지켰습니다.";

    /**
     * 받침에 따라 조사 붙이기 (완성본 제공 — 한글 유니코드 계산이라 TODO 대상 아님)
     * josa("영동", "은", "는") → "영동은"
     * josa("보리", "이", "가") → "보리가"
     * 부르는 곳: 이름 뒤에 조사가 붙는 모든 곳
     */
    public static String josa(String word, String withBatchim, String withoutBatchim) {
        if (word == null || word.isEmpty()) return word;
        char last = word.charAt(word.length() - 1);
        // 한글 음절(가~힣)이 아니면 받침 없는 쪽으로
        if (last < 0xAC00 || last > 0xD7A3) return word + withoutBatchim;
        // 한글 음절은 28개 단위로 받침이 바뀜. 나머지가 0이면 받침 없음
        boolean hasBatchim = (last - 0xAC00) % 28 != 0;
        return word + (hasBatchim ? withBatchim : withoutBatchim);
    }
}
