package randomlife.core;

/**
 * 국가 정보 (담당: C / 대체: A)
 * - 실제 통계(세계은행)를 담는 그릇. 값은 CountryData에서 채운다.
 * - 값은 처음에 한 번 정하면 바뀌지 않으므로 setter는 만들지 않는다.
 */
public class Country {

    private String name;              // "나이지리아"
    private String flag;              // "🇳🇬"
    private double lifeExpectancy;    // 기대수명 (예: 54.5)
    private int minLifespan;          // 게임 수명 범위 최소
    private int maxLifespan;          // 게임 수명 범위 최대
    private double under5Death;       // 5세 미만 사망 (1,000명당, 예: 116.8)
    private String collegeRate;       // 대학 총취학률 표시용 문구 (예: "34%", "100% 이상")
    private double smokingRate;       // 15세 이상 흡연율 % (예: 19.0)
    private int internetRate;         // 인터넷 이용률 % (예: 98)

    /** 부르는 곳: CountryData */
    public Country(String name, String flag, double lifeExpectancy, int minLifespan, int maxLifespan,
                   double under5Death, String collegeRate, double smokingRate, int internetRate) {
        // TODO 매개변수 9개를 같은 이름의 필드에 전부 저장 (this.name = name; ...)
        this.name = name;
        this.flag = flag;
        this.lifeExpectancy = lifeExpectancy;
        this.minLifespan = minLifespan;
        this.maxLifespan = maxLifespan;
        this.under5Death = under5Death;
        this.collegeRate = collegeRate;
        this.smokingRate = smokingRate;
        this.internetRate = internetRate;
    }

    /**
     * 국기 + 이름 (출생 신고 화면용)
     * 예) "🇳🇬 나이지리아"
     * 부르는 곳: LifeSimulator.printBirth()
     */
    public String getDisplayName() {
        // TODO flag + " " + name 을 return
        return flag + " " + name;
    }

    /**
     * 5세 미만 사망 수를 화면용 문자열로 (소수 첫째 자리까지)
     * 예) 116.8 → "116.8", 28.0 → "28.0"
     * 부르는 곳: LifeEvent(5세 통계 줄), Ending(여운 문단)
     */
    public String getUnder5Text() {
        // TODO String.format("%.1f", under5Death) 를 return
        return String.format("%.1f", under5Death);
    }

    public String getName() {
        // TODO name 반환
        return name;
    }

    public String getFlag() {
        // TODO flag 반환
        return flag;
    }

    public double getLifeExpectancy() {
        // TODO lifeExpectancy 반환
        return lifeExpectancy;
    }

    public int getMinLifespan() {
        // TODO minLifespan 반환
        return minLifespan;
    }

    public int getMaxLifespan() {
        // TODO maxLifespan 반환
        return maxLifespan;
    }

    public double getUnder5Death() {
        // TODO under5Death 반환
        return under5Death;
    }

    public String getCollegeRate() {
        // TODO collegeRate 반환
        return collegeRate;
    }

    public double getSmokingRate() {
        // TODO smokingRate 반환
        return smokingRate;
    }

    public int getInternetRate() {
        // TODO internetRate 반환
        return internetRate;
    }
}
