package randomlife.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import randomlife.temperament.Temperament;
import randomlife.util.GameConfig;
import randomlife.util.StoryText;

/**
 * 한 사람의 인생 정보 (담당: A)
 * - 능력치, 자산, 나이, 연표, 플래그, 반려동물을 전부 여기서 관리한다.
 * - 모든 필드는 private. 다른 클래스는 메서드로만 값을 바꿀 수 있다. (캡슐화)
 *   → 건강이 120이 되거나 -30이 되는 일을 여기서 한 번에 막을 수 있음
 */
public class Person {

    // ===== 상태 화면 문구 =====
    private static final String STATUS_LINE_1 = "건강 %s %3d   행복 %s %3d";
    private static final String STATUS_LINE_2 = "지식 %s %3d   관계 %s %3d";
    private static final String STATUS_LINE_3 = "자산 %,d만원";
    private static final int BAR_LENGTH = 10;   // 막대 칸 수

    // ===== 기본 정보 =====
    private String name;
    private Country country;
    private FamilyType familyType;
    private Temperament temperament;   // 5세 전에는 null

    private int age;
    private int lifespan;              // 랜덤 수명 (화면에 안 보여줌)
    private String job;                // 25세 전에는 null
    private String lastWords;

    // ===== 능력치 (0~100) =====
    private int health;
    private int happiness;
    private int knowledge;
    private int relation;
    private int money;                 // 만원, 0~100 제한 없음, 음수 가능

    // ===== 반려동물 =====
    private String petType;            // "강아지" 등, 없으면 null
    private String petName;
    private int petAdoptAge;           // 데려온 나이
    private int petDeathAge;           // 떠나는 나이 (데려올 때 미리 계산)
    private boolean petGone;           // 이미 떠났는지

    // ===== 기록 =====
    private List<LifeRecord> records = new ArrayList<>();    // 연표
    private List<LifeRecord> thoughts = new ArrayList<>();   // 멈춤 화면에서 남긴 생각
    private Set<String> flags = new HashSet<>();             // 지나온 선택 표시 (COLLEGE 등)

    /**
     * 새 인생 시작 — 국가, 가정환경, 능력치, 수명을 전부 랜덤으로 정한다.
     * 부르는 곳: LifeSimulator.start()
     */
    public Person(String name) {
        // TODO 1. name 저장
        // TODO 2. CountryData.getAll()에서 랜덤 국가 1개 → country
        //         (인덱스는 GameConfig.RANDOM.nextInt(리스트.size()))
        // TODO 3. FamilyType.values()에서 랜덤 1개 → familyType
        // TODO 4. money = familyType.getStartMoney()
        // TODO 5. health, happiness, knowledge, relation = randomStartStat()
        // TODO 6. lifespan = 국가의 수명 범위(min ~ max) 안에서 랜덤 (GameConfig.randomInt 사용)
        // TODO 7. 0세 연표 추가
        //         addRecord(0, String.format(StoryText.BIRTH_RECORD, 국가 이름, 가정환경 label))
    }

    /** 시작 능력치 랜덤 (START_STAT_MIN ~ START_STAT_MAX) */
    private int randomStartStat() {
        // TODO GameConfig.randomInt(GameConfig.START_STAT_MIN, GameConfig.START_STAT_MAX) 를 return
        return 0;
    }

    /** 값을 STAT_MIN ~ STAT_MAX(0~100) 사이로 자르기. 예) 120 → 100, -5 → 0 */
    private int clamp(int value) {
        // TODO Math.max와 Math.min을 써서 범위 밖이면 끝값으로 자른 값을 return
        return value;
    }

    /**
     * 능력치 변경 (모든 능력치 변화는 이 메서드 하나로만!)
     * stat  : GameConfig.HEALTH / HAPPY / KNOW / RELATION / MONEY
     * amount: 변화량 (+10, -5 ...)
     * 부르는 곳: Choice.apply(), LifeSimulator(질병·노화)
     */
    public void changeStat(String stat, int amount) {
        // TODO switch (stat)으로 나눠서
        //      HEALTH  → health = clamp(health + amount)
        //      HAPPY   → happiness = clamp(...)
        //      KNOW    → knowledge = clamp(...)
        //      RELATION→ relation = clamp(...)
        //      MONEY   → money += amount   (돈은 clamp 안 함)
        // 주의: case에는 상수가 와야 하므로 case "HEALTH": 처럼 문자열을 쓰거나 if-else로 GameConfig 상수와 비교
    }

    /**
     * 능력치 조회
     * 부르는 곳: Choice(잠금 조건), Ending(평가)
     */
    public int getStat(String stat) {
        // TODO changeStat과 같은 방식으로 stat에 맞는 필드 값을 return
        //      해당 없으면 0 return
        return 0;
    }

    /** 건강이 DEATH_HEALTH 이하면 사망. 부르는 곳: LifeSimulator */
    public boolean isDead() {
        // TODO health <= GameConfig.DEATH_HEALTH 를 return
        return false;
    }

    // =====================================================
    // 연표·생각·플래그
    // =====================================================

    /** 지금 나이로 연표 추가. 부르는 곳: Choice, LifeSimulator */
    public void addRecord(String text) {
        // TODO addRecord(age, text) 호출 (아래 메서드 재사용)
    }

    /** 나이를 직접 정해서 연표 추가 (반려동물 죽음처럼 지금 나이와 다를 때). 부르는 곳: LifeSimulator */
    public void addRecord(int atAge, String text) {
        // TODO records에 new LifeRecord(atAge, text) 추가
    }

    /** 멈춤 화면에서 남긴 생각 저장 (빈 문자열이면 저장 안 함). 부르는 곳: LifeSimulator.pause() */
    public void addThought(String text) {
        // TODO text가 null이 아니고 비어 있지 않으면 thoughts에 new LifeRecord(age, text) 추가
    }

    /** 부르는 곳: Choice.apply() */
    public void addFlag(String flag) {
        // TODO flags에 추가
    }

    /** 부르는 곳: Choice.getLockReason() */
    public boolean hasFlag(String flag) {
        // TODO flags에 들어 있는지 return (contains)
        return false;
    }

    // =====================================================
    // 반려동물
    // =====================================================

    /**
     * 반려동물 입양 — 떠나는 나이를 지금 미리 정해 둔다.
     * 부르는 곳: Choice.apply() (반려동물 선택지)
     */
    public void adoptPet(String type, String name) {
        // TODO 1. petType, petName 저장, petAdoptAge = 지금 age
        // TODO 2. GameConfig.PET_TYPES에서 type과 같은 칸 번호(i)를 찾기 (for문 + equals)
        // TODO 3. 수명 = GameConfig.randomInt(PET_LIFE_MIN[i], PET_LIFE_MAX[i])
        // TODO 4. petDeathAge = age + 수명
        // TODO 5. petGone = false
        // TODO 6. addFlag(GameConfig.FLAG_PET)
    }

    /** 반려동물을 키운 적이 있는지. 부르는 곳: LifeSimulator, Ending */
    public boolean hasPet() {
        // TODO petName이 null이 아니면 true
        return false;
    }

    /** 함께한 햇수 (= 떠날 때 나이). 부르는 곳: LifeSimulator, Ending */
    public int getPetYears() {
        // TODO petDeathAge - petAdoptAge 를 return
        return 0;
    }

    /** 반려동물이 떠났다고 표시. 부르는 곳: LifeSimulator.petFarewell() */
    public void markPetGone() {
        // TODO petGone = true
    }

    // =====================================================
    // 화면용
    // =====================================================

    /**
     * 상태 3줄 (출생·멈춤 화면 공용)
     * 예)
     *   건강 ■■■■■□□□□□  50   행복 ■■■■■■□□□□  60
     *   지식 ■■■■□□□□□□  40   관계 ■■■■■■■□□□  70
     *   자산 1,000만원
     * 부르는 곳: LifeSimulator
     */
    public String statusText() {
        // TODO STATUS_LINE_1, 2, 3을 String.format으로 채우고 "\n"으로 이어서 return
        //      막대는 bar(값)으로 만든다
        return "";
    }

    /** 0~100 값을 막대로. 예) 50 → "■■■■■□□□□□" */
    private String bar(int value) {
        // TODO 1. 채울 칸 수 = value / 10
        // TODO 2. GameConfig.MAP_FILL.repeat(채울 칸) + GameConfig.MAP_EMPTY.repeat(BAR_LENGTH - 채울 칸)
        return "";
    }

    // =====================================================
    // getter / setter
    // =====================================================
    public String getName() {
        // TODO name 반환
        return null;
    }

    public Country getCountry() {
        // TODO country 반환
        return null;
    }

    public FamilyType getFamilyType() {
        // TODO familyType 반환
        return null;
    }

    public Temperament getTemperament() {
        // TODO temperament 반환
        return null;
    }

    public void setTemperament(Temperament temperament) {
        // TODO temperament 저장
    }

    public int getAge() {
        // TODO age 반환
        return 0;
    }

    public void setAge(int age) {
        // TODO age 저장
    }

    public int getLifespan() {
        // TODO lifespan 반환
        return 0;
    }

    public String getJob() {
        // TODO job 반환
        return null;
    }

    public void setJob(String job) {
        // TODO job 저장
    }

    public String getLastWords() {
        // TODO lastWords 반환
        return null;
    }

    public void setLastWords(String lastWords) {
        // TODO lastWords 저장
    }

    public String getPetType() {
        // TODO petType 반환
        return null;
    }

    public String getPetName() {
        // TODO petName 반환
        return null;
    }

    public int getPetDeathAge() {
        // TODO petDeathAge 반환
        return 0;
    }

    public boolean isPetGone() {
        // TODO petGone 반환
        return false;
    }

    public List<LifeRecord> getRecords() {
        // TODO records 반환
        return null;
    }

    public List<LifeRecord> getThoughts() {
        // TODO thoughts 반환
        return null;
    }
}
