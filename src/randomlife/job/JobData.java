package randomlife.job;

import java.util.ArrayList;
import java.util.List;

import randomlife.event.Choice;
import randomlife.util.GameConfig;
import randomlife.util.StoryText;

/**
 * 직업 30개 데이터 (담당: C) — 데이터 작성 완료
 * - 조건 없는 직업이 절반 이상 → 3개 뽑을 때 전부 잠길 확률을 낮춤
 * - 자산 단위: 만원 (25세 시작 시 한 번 받는 값)
 *
 * 직업 추가하는 법: add(list, job("이름", "결과 문장", 자산최소, 자산최대, 건강, 행복)) 한 줄
 *                   조건이 있으면 뒤에 .needFlag(...) / .needStat(...) / .needMoney(...)
 */
public class JobData {

    private static final String KNOW   = GameConfig.KNOW;
    private static final String HEALTH = GameConfig.HEALTH;
    private static final String COLLEGE = GameConfig.FLAG_COLLEGE;

    // 연표 문장 (%s=josa(직업, "이", "가"))  예) "택배 기사가 되었다"
    private static final String JOB_RECORD = "%s 되었다";

    /** 부르는 곳: JobEvent.run() */
    public static List<Job> getAll() {
        List<Job> list = new ArrayList<>();

        // ----- 조건 있음 (13개) -----
        add(list, job("의사", "흰 가운이 생각보다 무거웠습니다.", 1500, 2500, -10, 5)
                .needFlag(COLLEGE).needStat(KNOW, 80));
        add(list, job("변호사", "첫 재판 날, 손이 조금 떨렸습니다.", 1200, 2000, -5, 0)
                .needFlag(COLLEGE).needStat(KNOW, 80));
        add(list, job("연구원", "실험실의 불은 늘 마지막에 꺼졌습니다.", 600, 900, -5, 10)
                .needFlag(COLLEGE).needStat(KNOW, 70));
        add(list, job("대기업 사원", "출입증을 목에 걸고 거울을 한 번 더 봤습니다.", 800, 1200, -5, 5)
                .needFlag(COLLEGE));
        add(list, job("공무원", "정시에 퇴근하는 저녁이 좋았습니다.", 500, 700, 0, 5)
                .needStat(KNOW, 60));
        add(list, job("교사", "아이들이 이름을 부르며 달려왔습니다.", 500, 700, 0, 10)
                .needFlag(COLLEGE));
        add(list, job("웹 개발자", "첫 배포 날, 밤새 모니터를 지켜봤습니다.", 700, 1100, -5, 5)
                .needStat(KNOW, 65));
        add(list, job("간호사", "밤샘 근무가 이어졌지만, 누군가를 돕는 일에 보람을 느꼈습니다.", 600, 800, -10, 10)
                .needFlag(COLLEGE));
        add(list, job("약사", "하루에도 수백 개의 이름을 불렀습니다.", 900, 1200, 0, 5)
                .needFlag(COLLEGE).needStat(KNOW, 75));
        add(list, job("운동선수", "관중석의 함성이 귀에 오래 남았습니다.", 300, 2000, 5, 10)
                .needStat(HEALTH, 80));
        add(list, job("카페 사장", "가게 문을 처음 열던 날, 손님은 세 명이었습니다.", -500, 1000, -5, 10)
                .needMoney(1000));
        add(list, job("디자이너", "밤새 고친 시안이 결국 첫 번째 안으로 돌아갔습니다.", 400, 700, -5, 10)
                .needStat(KNOW, 50));
        add(list, job("통역사", "두 언어 사이에서 매일 다리를 놓았습니다.", 500, 900, 0, 5)
                .needFlag(COLLEGE).needStat(KNOW, 70));

        // ----- 조건 없음 (17개) -----
        add(list, job("바리스타", "아침마다 같은 손님이 같은 메뉴를 시켰습니다.", 250, 350, 0, 5));
        add(list, job("택배 기사", "하루 이백 개의 문 앞을 지나쳤습니다.", 350, 500, -10, 0));
        add(list, job("공장 노동자", "기계 소리에 익숙해지는 데 한 달이 걸렸습니다.", 350, 450, -10, 0));
        add(list, job("농부", "비가 오면 하늘을, 해가 뜨면 밭을 보았습니다.", 200, 500, 5, 5));
        add(list, job("어부", "새벽 바다는 늘 차갑고 조용했습니다.", 250, 600, -5, 5));
        add(list, job("편의점 점원", "밤 근무 손님들의 얼굴을 하나둘 외웠습니다.", 200, 300, -5, 0));
        add(list, job("요리사", "뜨거운 주방에서 매일 칼을 갈았습니다.", 300, 500, -5, 10));
        add(list, job("미용사", "손님의 이야기를 들으며 머리를 잘랐습니다.", 300, 500, 0, 10));
        add(list, job("배달 라이더", "비 오는 날이면 주문이 두 배였습니다.", 300, 500, -10, 0));
        add(list, job("영업사원", "하루에 명함을 스무 장씩 건넸습니다.", 400, 700, -5, 0));
        add(list, job("목수", "손에 굳은살이 박일수록 일이 손에 붙었습니다.", 350, 550, -5, 10));
        add(list, job("버스 기사", "같은 정류장, 같은 얼굴들. 그게 좋았습니다.", 350, 450, -5, 5));
        add(list, job("작가 지망생", "원고는 쌓여 갔고, 통장은 비어 갔습니다.", 50, 200, 0, 10));
        add(list, job("경비원", "밤마다 건물의 불이 하나씩 꺼지는 걸 보았습니다.", 250, 350, 0, 0));
        add(list, job("사회복지사", "누군가의 하루를 조금 덜 외롭게 만들었습니다.", 300, 400, 0, 10));
        add(list, job("유튜버", "구독자 숫자를 하루에 열 번씩 확인했습니다.", 0, 1500, 0, 10));
        add(list, job("프리랜서", "출근은 없었지만, 퇴근도 없었습니다.", 100, 800, -5, 5));

        return list;
    }

    /**
     * .needFlag() 같은 조건을 이어 붙이면 타입이 Choice가 되므로 Job으로 되돌려서 추가
     * (Choice의 빌더 메서드가 Choice를 return하기 때문 — 실제 객체는 Job이라 형변환 OK)
     */
    private static void add(List<Job> list, Choice c) {
        list.add((Job) c);
    }

    /**
     * 직업 공통 틀: 자산(min~max) + 건강·행복 변화 + 연표 문장
     * health, happy가 음수면 (health ~ 0), 양수면 (0 ~ health) 범위로 랜덤
     */
    private static Job job(String name, String result, int moneyMin, int moneyMax, int health, int happy) {
        Job j = new Job(name, result);
        j.effect(GameConfig.MONEY, moneyMin, moneyMax);
        if (health != 0) j.effect(GameConfig.HEALTH, Math.min(health, 0), Math.max(health, 0));
        if (happy != 0) j.effect(GameConfig.HAPPY, Math.min(happy, 0), Math.max(happy, 0));
        j.record(String.format(JOB_RECORD, StoryText.josa(name, "이", "가")));
        return j;
    }
}
