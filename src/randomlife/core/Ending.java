package randomlife.core;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import randomlife.temperament.Active;
import randomlife.temperament.Curious;
import randomlife.temperament.Empathic;
import randomlife.temperament.Temperament;
import randomlife.util.GameConfig;
import randomlife.util.StoryText;

/**
 * 인생 결산 화면 (담당: C / 대체: A)
 *
 * ================================================
 *           홍길동의 삶 (2026 ~ 2104)
 * ================================================
 *   0세  나이지리아, 어려운 가정에서 태어났다
 *   5세  울고 있는 아이 옆에 앉았다
 *  ...
 *  45세  보리가 13살의 나이로 무지개다리를 건넜다
 *  78세  눈을 감았다
 *
 * [남긴 생각]
 * 18세 · 엄마한테 집을 사주고 싶다
 *
 * [마지막 한마디] 수고했어
 *
 * [인생 평가] 사람 곁에서 살아간 인생
 * 🪦 "늘 누군가의 곁에 있었고, 사람들 곁에 머문 사람"
 * ================================================
 *
 * 조영동은 78년을 살았습니다.
 * 나이지리아의 기대수명은 54.5세. 그보다 23년을 더 살았습니다.
 * 같은 해 나이지리아에서 태어난 아이 1,000명 중 116.8명은 다섯 살을 넘기지 못했습니다.
 *
 * 보리와 함께한 13년이, 그중 가장 따뜻한 시간이었을지도 모릅니다.
 */
public class Ending {

    // ===== 화면 문구 =====
    private static final String TITLE = "          %s의 삶 (%d ~ %d)";   // 이름, 태어난 해, 떠난 해
    private static final String THOUGHTS = "[남긴 생각]";
    private static final String LAST_WORDS = "[마지막 한마디] %s";
    private static final String EVALUATION = "[인생 평가] %s";
    private static final String EPITAPH = "🪦 \"%s\"";

    // ===== 평가 기준 =====
    private static final int HIGH_STAT = 80;      // 행복·관계·지식이 이 이상이면 그 평가
    private static final int RICH_MONEY = 10000;   // 자산 1억 이상
    private static final int LOW_HEALTH = 20;

    /**
     * 부르는 곳: LifeSimulator.start()
     */
    public void printResult(Person p) {

        // TODO 1. 태어난 해 = Year.now().getValue(), 떠난 해 = 태어난 해 + 나이
        int birthYear = Year.now().getValue();
        int deathYear = birthYear + p.getAge();

        // TODO 2. 빈 줄, LINE, TITLE, LINE
        System.out.println();
        System.out.println(GameConfig.LINE);
        System.out.printf(
                TITLE + "%n",
                p.getName(),
                birthYear,
                deathYear
        );
        System.out.println(GameConfig.LINE);

        // TODO 3. printTimeline(p)
        printTimeline(p);

        // TODO 4. 남긴 생각이 있으면: 빈 줄, THOUGHTS, 생각마다 String.format(StoryText.AGE_LINE, 나이, 문장)
        if (!p.getThoughts().isEmpty()) {

            System.out.println();
            System.out.println(THOUGHTS);

            for (LifeRecord thought : p.getThoughts()) {
                System.out.println(
                        String.format(
                                StoryText.AGE_LINE,
                                thought.getAge(),
                                thought.getText()
                        )
                );
            }
        }

        // TODO 5. 마지막 한마디가 있으면: 빈 줄, LAST_WORDS
        if (p.getLastWords() != null
                && !p.getLastWords().isEmpty()) {

            System.out.println();
            System.out.printf(
                    LAST_WORDS + "%n",
                    p.getLastWords()
            );
        }

        // TODO 6. String eval = evaluate(p) → 빈 줄, EVALUATION 출력
        String eval = evaluate(p);

        System.out.println();
        System.out.printf(EVALUATION + "%n", eval);

        // TODO 7. String epitaph = epitaph(p, eval) → null이 아니면 EPITAPH 출력
        String graveText = epitaph(p, eval);

        if (graveText != null) {
            System.out.printf(EPITAPH + "%n", graveText);
        }

        // TODO 8. LINE → printAfterword(p)
        System.out.println(GameConfig.LINE);
        printAfterword(p);
    }

    /**
     * 연표를 나이순으로 정렬해서 한 줄씩 천천히 출력
     * (반려동물 죽음처럼 나중에 추가된 기록도 정렬하면 제자리로 들어감)
     */
    private void printTimeline(Person p) {
        // TODO 1. 원본을 건드리지 않게 new ArrayList<>(p.getRecords())로 복사
        // TODO 2. Collections.sort(복사본)  ← LifeRecord가 Comparable이라 나이순 정렬됨
        // TODO 3. 한 줄씩 System.out.println(r) + GameConfig.delay(GameConfig.LINE_DELAY)
        //         (println에 객체를 넣으면 toString()이 자동 호출됨)

        List<LifeRecord> records = new ArrayList<>(p.getRecords());

        Collections.sort(records);

        for (LifeRecord r : records) {
            System.out.println(r);
            GameConfig.delay((GameConfig.LINE_DELAY));
        }
    }

    /**
     * 인생 평가 (위에서부터 먼저 맞는 것 1개)
     * 5세 미만 → EVAL_INFANT
     * 행복 80↑ → EVAL_HAPPY / 관계 80↑ → EVAL_RELATION / 자산 1억↑ → EVAL_RICH
     * 지식 80↑ → EVAL_KNOW / 건강 20↓ 또는 자산 음수 → EVAL_HARD / 나머지 → EVAL_DEFAULT
     */
    private String evaluate(Person p) {
        // TODO 위 순서대로 if문 + return (능력치는 p.getStat(GameConfig.HAPPY) 처럼)

        if (p.getAge() < 5) {
            return StoryText.EVAL_INFANT;
        }

        if (p.getStat(GameConfig.HAPPY) >= HIGH_STAT) {
            return StoryText.EVAL_HAPPY;
        }

        if (p.getStat(GameConfig.RELATION) >= HIGH_STAT) {
            return StoryText.EVAL_RELATION;
        }

        if (p.getStat(GameConfig.MONEY) >= RICH_MONEY) {
            return StoryText.EVAL_RICH;
        }

        if (p.getStat(GameConfig.KNOW) >= HIGH_STAT) {
            return StoryText.EVAL_KNOW;
        }

        if (p.getStat(GameConfig.HEALTH) <= LOW_HEALTH
                || p.getStat(GameConfig.MONEY) < 0) {
            return StoryText.EVAL_HARD;
        }

        return StoryText.EVAL_DEFAULT;
    }

    /**
     * 묘비명 = 기질 앞부분 + 평가 뒷부분
     * 예) "늘 누군가의 곁에 있었고, " + "사람들 곁에 머문 사람"
     * 기질이 없으면(5세 전 사망) null
     */
    private String epitaph(Person p, String eval) {
        // TODO 1. Temperament t = p.getTemperament(); null이면 null return

        Temperament t = p.getTemperament();

        if (t == null) {
            return null;
        }

        // TODO 2. 앞부분: instanceof로 기질 종류 확인
        //         Curious → EPITAPH_CURIOUS, Active → EPITAPH_ACTIVE,
        //         Empathic → EPITAPH_EMPATHIC, 나머지 → EPITAPH_PERSISTENT

        String front;

        if (t instanceof Curious) {
            front = StoryText.EPITAPH_CURIOUS;
        } else if (t instanceof Active) {
            front = StoryText.EPITAPH_ACTIVE;
        } else if (t instanceof Empathic) {
            front = StoryText.EPITAPH_EMPATHIC;
        } else {
            front = StoryText.EPITAPH_PERSISTENT;
        }

        // TODO 3. 뒷부분: eval이 EVAL_HAPPY와 같으면(equals) EPITAPH_HAPPY ... 나머지는 EPITAPH_DEFAULT

        String back;

        if (eval.equals(StoryText.EVAL_HAPPY)) {
            back = StoryText.EPITAPH_HAPPY;
        } else if (eval.equals(StoryText.EVAL_RELATION)) {
            back = StoryText.EPITAPH_RELATION;
        } else if (eval.equals(StoryText.EVAL_RICH)) {
            back = StoryText.EPITAPH_RICH;
        } else if (eval.equals(StoryText.EVAL_KNOW)) {
            back = StoryText.EPITAPH_KNOW;
        } else if (eval.equals(StoryText.EVAL_HARD)) {
            back = StoryText.EPITAPH_HARD;
        } else {
            back = StoryText.EPITAPH_DEFAULT;
        }

        // TODO 4. 앞 + 뒤 return
        return front + back;
    }

    /**
     * 마지막 여운 문단 (결산 박스 아래)
     * 1줄: END_LIVED
     * 2줄: 5세 전에 떠났으면 END_U5 + END_INFANT
     * 아니면 기대수명 비교(END_LIFE_MORE / LESS / SAME) + END_U5
     * 3줄: 반려동물이 있었으면 떠났으면 END_PET_GONE, 끝까지 살아 있었으면 END_PET_STAY
     */
    private void printAfterword(Person p) {
        // TODO 1. Country c = p.getCountry(); 빈 줄
        // TODO 2. END_LIVED (josa(이름, "은", "는"), 나이) 출력
        Country c = p.getCountry();
        System.out.println();

        System.out.printf(
                StoryText.END_LIVED + "%n",
                StoryText.josa(p.getName(), "은", "는"),
                p.getAge()
        );

        // TODO 3. 나이 < 5 이면
        //           END_U5 (국가 이름, c.getUnder5Text()), END_INFANT 출력
        //         아니면
        //           diff = 나이 - (int) Math.round(c.getLifeExpectancy())
        //           diff > 0 → END_LIFE_MORE(국가, 기대수명, diff)
        //           diff < 0 → END_LIFE_LESS(국가, 기대수명, -diff)
        //           diff == 0 → END_LIFE_SAME(국가, 기대수명)
        //           END_U5 출력

        if (p.getAge() < 5) {

            System.out.printf(
                    StoryText.END_U5 + "%n",
                    c.getName(),
                    c.getUnder5Text()
            );

            System.out.println(StoryText.END_INFANT);

        } else {

            int diff = p.getAge()
                    - (int) Math.round(c.getLifeExpectancy());

            if (diff > 0) {
                System.out.printf(
                        StoryText.END_LIFE_MORE + "%n",
                        c.getName(),
                        c.getLifeExpectancy(),
                        diff
                );

            } else if (diff < 0) {
                System.out.printf(
                        StoryText.END_LIFE_LESS + "%n",
                        c.getName(),
                        c.getLifeExpectancy(),
                        -diff
                );

            } else {
                System.out.printf(
                        StoryText.END_LIFE_SAME + "%n",
                        c.getName(),
                        c.getLifeExpectancy()
                );
            }

            System.out.printf(
                    StoryText.END_U5 + "%n",
                    c.getName(),
                    c.getUnder5Text()
            );
        }


        // TODO 4. 반려동물이 있었으면 빈 줄 후
        //           떠났으면(isPetGone) END_PET_GONE (josa(이름, "과", "와"), getPetYears())
        //           아니면 END_PET_STAY (josa(이름, "은", "는"))

        if (p.hasPet()) {

            System.out.println();

            if (p.isPetGone()) {
                System.out.printf(
                        StoryText.END_PET_GONE + "%n",
                        StoryText.josa(p.getPetName(), "과", "와"),
                        p.getPetYears()
                );

            } else {
                System.out.printf(
                        StoryText.END_PET_STAY + "%n",
                        StoryText.josa(p.getPetName(), "은", "는")
                );
            }

        }
    }
}
