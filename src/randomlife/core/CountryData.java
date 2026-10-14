package randomlife.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 국가 5개 데이터 (담당: C / 대체: A) — 데이터 작성 완료
 *
 * 출처: 세계은행 World Development Indicators
 *   기대수명          SP.DYN.LE00.IN  (2023)
 *   5세 미만 사망     SH.DYN.MORT     (2023, 1,000명당)
 *   대학 총취학률     SE.TER.ENRR     (최신 자료, 연도가 오래된 나라는 괄호로 표시)
 *   흡연율(15세 이상) SH.PRV.SMOK     (2023)
 *   인터넷 이용률     IT.NET.USER.ZS  (2024)
 *
 * 참고
 *   - 한국 대학 총취학률은 실제 107%. 재수생·만학도까지 세서 100%를 넘으므로 "100% 이상"으로 표시
 *   - 게임 수명 범위는 기대수명 앞뒤로 잡은 게임 설정값 (통계 아님)
 */
public class CountryData {

    /** 부르는 곳: Person 생성자 (이 중 하나를 랜덤으로 고름) */
    public static List<Country> getAll() {
        List<Country> list = new ArrayList<>();

        //                    이름          국기   기대수명 수명범위   5세미만사망 대학취학률     흡연율 인터넷
        list.add(new Country("미국",        "🇺🇸", 78.4, 70, 88,     6.5,  "79%",          16.4, 95));
        list.add(new Country("한국",        "🇰🇷", 83.4, 75, 92,     2.9,  "100% 이상",    19.0, 98));
        list.add(new Country("인도",        "🇮🇳", 72.0, 62, 82,    28.0,  "34%",          23.5, 65));
        list.add(new Country("나이지리아",  "🇳🇬", 54.5, 44, 65,   116.8,  "10% (2011년)",  3.2, 41));
        list.add(new Country("에티오피아",  "🇪🇹", 67.3, 57, 77,    51.5,  "6% (2014년)",   5.2, 22));

        return list;
    }
}
