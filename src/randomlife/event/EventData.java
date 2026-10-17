package randomlife.event;

import java.util.ArrayList;
import java.util.List;

import randomlife.job.JobEvent;
import randomlife.util.GameConfig;

/**
 * 이벤트 14개 데이터 (담당: B) — 데이터 작성 완료
 * - 나이별 정확히 2개씩: 5 · 10 · 18 · 25 · 32 · 40 · 60
 * - 자산 단위: 만원
 * - 국가 통계 줄은 꼭 필요한 이벤트에만 (5세 유치원, 18세 진로, 40세 건강검진)
 * - 규칙: 모든 이벤트에는 조건 없는 선택지가 최소 1개 있어야 한다. (전부 잠기면 진행 불가)
 *
 * 선택지 추가하는 법: 해당 이벤트에 .add(new Choice("문장", "결과").effect(...).record("연표")) 한 줄 추가
 */
public class EventData {

    // 짧게 쓰려고 별명 붙임
    private static final String HEALTH   = GameConfig.HEALTH;
    private static final String HAPPY    = GameConfig.HAPPY;
    private static final String KNOW     = GameConfig.KNOW;
    private static final String RELATION = GameConfig.RELATION;
    private static final String MONEY    = GameConfig.MONEY;

    /** 부르는 곳: LifeSimulator.playLife() */
    public static List<LifeEvent> getAll() {
        List<LifeEvent> list = new ArrayList<>();

        // =====================================================
        // 5세
        // =====================================================
        // 기질 이벤트: 선택지 순서 = 탐구형 / 활동형 / 공감형 / 끈기형 (바꾸면 TemperamentEvent도 수정)
        list.add(new TemperamentEvent(5, "유치원 첫날",
                "처음 보는 아이들, 처음 보는 장난감들.\n엄마 손을 놓고 나서, 무엇부터 할까요?",
                LifeEvent.STAT_U5)
            .add(new Choice("구석의 블록을 한참 만지작거린다",
                    "블록으로 탑을 쌓고, 무너뜨리고, 또 쌓았습니다.\n어른들은 궁금한 게 많은 아이라고 불렀습니다.")
                    .effect(KNOW, 3, 5)
                    .record("블록으로 탑을 쌓고, 무너뜨리고, 또 쌓았다"))
            .add(new Choice("마당으로 뛰쳐나간다",
                    "땀에 젖은 머리로 돌아온 아이는 하루 종일 웃고 있었습니다.\n어른들은 지칠 줄 모르는 아이라고 불렀습니다.")
                    .effect(HEALTH, 3, 5)
                    .record("유치원 첫날, 마당을 하루 종일 뛰어다녔다"))
            .add(new Choice("울고 있는 아이 옆에 앉는다",
                    "한참 뒤, 두 아이는 손을 잡고 함께 걷고 있었습니다.\n어른들은 마음이 따뜻한 아이라고 불렀습니다.")
                    .effect(RELATION, 3, 5)
                    .record("울고 있는 아이 옆에 앉았다"))
            .add(new Choice("넘어져도 울지 않고 다시 일어난다",
                    "무릎에 반창고를 붙인 채로, 다시 미끄럼틀에 올랐습니다.\n어른들은 단단한 아이라고 불렀습니다.")
                    .effect(HAPPY, 3, 5)
                    .record("넘어져도 울지 않고 다시 일어났다")));

        list.add(new ChoiceEvent(5, "생일",
                "다섯 번째 생일입니다. 부모님이 무엇을 갖고 싶은지 물어봅니다.",
                null)
            .add(new Choice("그림책", "밤마다 같은 책을 읽어 달라고 졸랐습니다.")
                    .effect(KNOW, 3, 8)
                    .record("생일 선물로 받은 그림책을 닳도록 읽었다"))
            .add(new Choice("공", "공은 금세 흙투성이가 되었습니다.")
                    .effect(HEALTH, 3, 8)
                    .record("생일 선물로 받은 공이 금세 흙투성이가 되었다"))
            .add(new Choice("갖고 싶은 게 없다고 한다", "부모님은 조금 놀란 얼굴로 아이를 꼭 안아 주었습니다.")
                    .effect(RELATION, 3, 8)
                    .record("갖고 싶은 게 없다고 말해 부모님 품에 안겼다")));

        // =====================================================
        // 10세
        // =====================================================
        list.add(new ChoiceEvent(10, "방과 후",
                "수업이 끝나고 교문을 나섰습니다. 오늘은 어디로 갈까요?",
                null)
            .add(new Choice("학원에 간다", "학원 가방을 메고 버스에 올랐습니다.\n창밖으로 공을 차는 친구들이 지나갔습니다.")
                    .needMoney(100)
                    .effect(KNOW, 5, 15).effect(MONEY, -100, -100)
                    .record("방과 후마다 학원 버스에 올랐다"))
            .add(new Choice("친구들과 뛰어논다", "해가 질 때까지 골목을 뛰어다녔습니다.")
                    .effect(HEALTH, 5, 10).effect(RELATION, 5, 10)
                    .record("해가 질 때까지 친구들과 골목을 뛰어다녔다"))
            .add(new Choice("집에서 혼자 책을 읽는다", "조용한 방에서 책장이 넘어가는 소리만 들렸습니다.")
                    .effect(KNOW, 5, 10).effect(RELATION, -5, -2)
                    .record("조용한 방에서 혼자 책을 읽으며 자랐다")));

        list.add(new ChoiceEvent(10, "전학 온 아이",
                "새 학기, 낯선 아이가 반에 들어왔습니다.\n쉬는 시간마다 혼자 창밖만 보고 있습니다.",
                null)
            .add(new Choice("먼저 말을 건다", "그 아이는 그날 처음으로 웃었습니다.")
                    .effect(RELATION, 8, 12).effect(HAPPY, 3, 6)
                    .record("전학 온 아이에게 먼저 말을 걸었고, 단짝이 되었다"))
            .add(new Choice("신경 쓰지 않는다", "며칠 뒤, 그 아이 옆자리는 다른 누군가가 채워 있었습니다.")
                    .record("전학 온 아이를 그냥 지나쳤다"))
            .add(new Choice("다른 친구들과 같이 놀린다", "모두가 웃었지만, 집에 오는 길은 이상하게 조용했습니다.")
                    .effect(RELATION, 3, 5).effect(HAPPY, -10, -5)
                    .record("전학 온 아이를 놀렸다. 그 얼굴이 오래 남았다")));

        // =====================================================
        // 18세
        // =====================================================
        list.add(new ChoiceEvent(18, "진로 선택",
                "졸업식이 끝났습니다. 교복을 벗고 나면, 무엇을 할까요?",
                LifeEvent.STAT_COLLEGE)
            .add(new Choice("대학에 진학한다", "입학식 날, 강의실은 생각보다 넓었습니다.")
                    .needMoney(200)
                    .effect(KNOW, 10, 15).effect(MONEY, -200, -200)
                    .addFlag(GameConfig.FLAG_COLLEGE)
                    .record("대학에 입학했다"))
            .add(new Choice("해외로 유학을 떠난다", "공항에서 가족들이 오래 손을 흔들었습니다.")
                    .needMoney(2000)
                    .effect(KNOW, 15, 20).effect(HAPPY, 5, 10).effect(MONEY, -2000, -2000)
                    .addFlag(GameConfig.FLAG_COLLEGE)
                    .record("가족들의 배웅을 받으며 유학을 떠났다"))
            .add(new Choice("바로 취업한다", "첫 월급날, 부모님께 내복을 사 드렸습니다.")
                    .effect(MONEY, 200, 400).effect(HAPPY, -5, 0)
                    .addFlag(GameConfig.FLAG_EARLY_JOB)
                    .record("졸업하자마자 일을 시작했다"))
            .add(new Choice("1년 동안 여행을 떠난다", "낯선 도시의 골목에서, 처음으로 길을 잃어도 괜찮다는 걸 알았습니다.")
                    .needMoney(100)
                    .effect(HAPPY, 10, 15).effect(MONEY, -100, -100)
                    .record("1년 동안 낯선 도시들을 떠돌았다")));

        list.add(new ChoiceEvent(18, "첫 연애",
                "자꾸만 눈이 가는 사람이 생겼습니다.",
                null)
            .add(new Choice("고백한다", "떨리는 목소리였지만, 대답은 '나도'였습니다.")
                    .effect(HAPPY, 10, 15)
                    .record("첫사랑에게 고백했고, '나도'라는 대답을 들었다")
                    .success(50, "대답 대신 미안하다는 말이 돌아왔습니다.", "첫사랑에게 고백했지만, 닿지 않았다")
                    .failEffect(HAPPY, -10, -5))
            .add(new Choice("친구로 남는다", "그 사람과는 오래도록 좋은 친구로 지냈습니다.")
                    .effect(RELATION, 3, 6)
                    .record("좋아하는 마음을 접고 친구로 남았다"))
            .add(new Choice("공부에 집중한다", "책상 앞에서 가끔 그 사람 생각이 났습니다.")
                    .effect(KNOW, 5, 10).effect(HAPPY, -5, -2)
                    .record("마음을 접고 책상 앞에 앉았다")));

        // =====================================================
        // 25세
        // =====================================================
        // 직업 30개 중 3개 랜덤 (job 패키지). 연표 문장은 JobData에서 자동 생성
        list.add(new JobEvent(25, "첫 직업",
                "사회에 나설 때입니다. 눈앞에 몇 개의 길이 보입니다.",
                null));

        list.add(new ChoiceEvent(25, "부모님의 부탁",
                "오랜만에 전화한 부모님 목소리가 조금 지쳐 있습니다.",
                null)
            .add(new Choice("매달 용돈을 보내 드린다", "부모님은 괜찮다면서도 몇 번이나 고맙다고 했습니다.")
                    .needMoney(200)
                    .effect(MONEY, -200, -200).effect(RELATION, 10, 15)
                    .record("매달 부모님께 용돈을 보내 드렸다"))
            .add(new Choice("주말마다 찾아뵌다", "식탁에는 늘 좋아하던 반찬이 올라와 있었습니다.")
                    .effect(RELATION, 5, 10).effect(HEALTH, -3, 0)
                    .record("주말마다 부모님 댁 식탁에 앉았다"))
            .add(new Choice("바쁘다고 미룬다", "전화는 점점 짧아졌습니다.")
                    .effect(RELATION, -10, -5).effect(MONEY, 50, 100)
                    .record("바쁘다는 말로 부모님 전화를 미뤘다")));

        // =====================================================
        // 32세
        // =====================================================
        // 반려동물: 이름 입력 → 수명 자동 계산 → 떠나는 나이에 연표에 들어감
        list.add(new ChoiceEvent(32, "반려동물을 들일까?",
                "보호소에서 새 가족을 기다리는 동물들을 보았습니다.",
                null)
            .add(new Choice("강아지를 들인다", "")
                    .needMoney(50)
                    .effect(HAPPY, 8, 12).effect(MONEY, -50, -50)
                    .pet("강아지", "보리"))
            .add(new Choice("고양이를 들인다", "")
                    .needMoney(30)
                    .effect(HAPPY, 8, 12).effect(MONEY, -30, -30)
                    .pet("고양이", "나비"))
            .add(new Choice("햄스터를 들인다", "")
                    .needMoney(5)
                    .effect(HAPPY, 5, 8).effect(MONEY, -5, -5)
                    .pet("햄스터", "콩이"))
            .add(new Choice("지금은 아니다", "보호소를 나서며 한 번 뒤를 돌아보았습니다.")
                    .record("보호소를 나서며 한 번 뒤를 돌아보았다")));

        list.add(new ChoiceEvent(32, "결혼",
                "오래 만난 사람이 조심스럽게 미래 이야기를 꺼냈습니다.",
                null)
            .add(new Choice("결혼한다", "작은 식장에서, 생각보다 많이 울었습니다.")
                    .needMoney(500)
                    .effect(HAPPY, 10, 15).effect(RELATION, 5, 10).effect(MONEY, -500, -500)
                    .addFlag(GameConfig.FLAG_MARRIED)
                    .record("작은 식장에서 결혼했다. 생각보다 많이 울었다"))
            .add(new Choice("혼자의 삶을 택한다", "혼자 먹는 저녁도 나쁘지 않았습니다.")
                    .effect(MONEY, 200, 300).effect(HAPPY, -5, 5)
                    .record("혼자의 삶을 택했다"))
            .add(new Choice("조금 더 미룬다", "대답 대신 손을 잡았습니다.")
                    .record("결혼 대신 손을 잡았다")));

        // =====================================================
        // 40세
        // =====================================================
        list.add(new ChoiceEvent(40, "승진 기회",
                "팀장 자리가 비었습니다. 당신의 이름이 오르내립니다.",
                null)
            .add(new Choice("밤낮없이 매달린다", "명함에 새 직함이 찍혔습니다.")
                    .effect(MONEY, 1000, 1500).effect(HEALTH, -10, -5)
                    .record("밤낮없이 매달린 끝에 팀장이 되었다")
                    .success(50, "자리는 다른 사람에게 돌아갔습니다.", "밤낮없이 매달렸지만, 승진에서 떨어졌다")
                    .failEffect(HAPPY, -10, -5).failEffect(HEALTH, -10, -5))
            .add(new Choice("적당히 일하며 기다린다", "늘 그렇듯, 평범한 하루가 이어졌습니다.")
                    .effect(HAPPY, 0, 5)
                    .record("승진 대신 평범한 하루를 택했다"))
            .add(new Choice("새로운 곳으로 이직한다", "낯선 책상, 낯선 사람들. 조금 설렜습니다.")
                    .needStat(KNOW, 60)
                    .effect(MONEY, -500, 1500).effect(HAPPY, 5, 10)
                    .record("마흔에 새로운 회사로 옮겼다")));

        list.add(new ChoiceEvent(40, "건강검진",
                "회사에서 건강검진 안내 문자가 왔습니다.",
                LifeEvent.STAT_SMOKING)
            .add(new Choice("정밀 검사를 받는다", "큰 문제는 없었습니다. 의사는 운동을 권했습니다.")
                    .needMoney(100)
                    .effect(MONEY, -100, -100).effect(HEALTH, 5, 10)
                    .record("정밀 검사를 받았다. 큰 문제는 없었다"))
            .add(new Choice("기본 검사만 받는다", "별일 없다는 결과지를 받았습니다.")
                    .record("건강검진에서 별일 없다는 결과를 받았다")
                    .success(70, "작은 혹이 발견되었습니다. 치료가 길어졌습니다.", "건강검진에서 작은 혹이 발견되었다")
                    .failEffect(HEALTH, -15, -10).failEffect(MONEY, -300, -200))
            .add(new Choice("바빠서 미룬다", "올해도 그냥 넘어갔습니다.")
                    .record("올해도 건강검진을 미뤘다")
                    .success(50, "어느 날 아침, 가슴이 답답해 병원에 실려 갔습니다.", "가슴이 답답해 병원에 실려 갔다")
                    .failEffect(HEALTH, -25, -15)));
        // 결혼한 사람한테만 나오는 40세 이벤트 (requireFlag)
        list.add(new ChoiceEvent(40, "멀어진 사이",
                "언젠가부터 식탁에서 대화가 줄었습니다.\n어느 밤, 배우자가 조용히 이혼 이야기를 꺼냈습니다.",
                null)
                .add(new Choice("다시 대화해 본다", "밤새 이야기를 나눴습니다. 오랜만에 서로를 다시 보았습니다.")
                        .effect(RELATION, 5, 10).effect(HAPPY, 5, 8)
                        .record("멀어졌던 배우자와 밤새 이야기를 나눴다")
                        .success(60, "대화는 늘 같은 곳에서 끊겼습니다. 둘 사이에 긴 침묵이 남았습니다.",
                                "배우자와 대화를 시도했지만, 사이는 쉽게 좁혀지지 않았다")
                        .failEffect(HAPPY, -10, -5).failEffect(RELATION, -5, -3))
                .add(new Choice("부부 상담을 받아 본다", "낯선 상담실에서, 처음으로 서로의 속마음을 들었습니다.")
                        .needMoney(300)
                        .effect(MONEY, -300, -300).effect(RELATION, 8, 12).effect(HAPPY, 3, 6)
                        .record("부부 상담을 받았다. 처음으로 서로의 속마음을 들었다"))
                .add(new Choice("각자의 길을 가기로 한다", "서류에 도장을 찍던 날, 서로의 앞날을 빌어 주었습니다.")
                        .effect(HAPPY, -15, -8).effect(MONEY, -1500, -500)
                        .removeFlag(GameConfig.FLAG_MARRIED)
                        .record("이혼했다. 서로의 앞날을 빌어 주었다"))
                .requireFlag(GameConfig.FLAG_MARRIED));

        // =====================================================
        // 60세 (자동 — 위에서부터 조건 맞는 첫 번째 결말 1개)
        // =====================================================
        list.add(new AutoEvent(60, "은퇴",
                "마지막 출근 날, 책상 위의 짐은 작은 상자 하나에 다 들어갔습니다.",
                null)
            .add(new Choice("", "넉넉한 노후가 기다리고 있었습니다.")
                    .needMoney(5000)
                    .effect(HAPPY, 10, 10)
                    .record("넉넉한 노후와 함께 은퇴했다"))
            .add(new Choice("", "아껴 쓰면 지낼 만했습니다.")
                    .needMoney(0)
                    .effect(HAPPY, 0, 3)
                    .record("은퇴했다. 아껴 쓰면 지낼 만했다"))
            .add(new Choice("", "은퇴했지만, 일을 놓을 수 없었습니다.")
                    .effect(HEALTH, -10, -10)
                    .record("은퇴했지만, 일을 놓을 수 없었다")));

        list.add(new AutoEvent(60, "곁에 남은 사람들",
                "환갑날, 상에 둘러앉은 얼굴들을 세어 봅니다.",
                null)
            .add(new Choice("", "북적이는 웃음소리가 오래 이어졌습니다.")
                    .needFlag(GameConfig.FLAG_MARRIED).needStat(RELATION, 70)
                    .effect(HAPPY, 15, 15)
                    .record("환갑날, 북적이는 웃음소리 속에 있었다"))
            .add(new Choice("", "몇몇 반가운 얼굴이 찾아왔습니다.")
                    .needStat(RELATION, 40)
                    .effect(HAPPY, 5, 5)
                    .record("환갑날, 몇몇 반가운 얼굴이 찾아왔다"))
            .add(new Choice("", "조용한 저녁이었습니다.")
                    .effect(HAPPY, -10, -10)
                    .record("환갑날, 조용한 저녁을 보냈다")));

        return list;
    }
}
