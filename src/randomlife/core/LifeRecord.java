package randomlife.core;

/**
 * 연표 한 줄 = (나이, 문장) (담당: C / 대체: A)
 * - 마지막 결산에서 나이순으로 정렬해서 쭉 보여준다.
 * - 반려동물 죽음처럼 "나중에 계산된 나이"도 끼워 넣을 수 있게 나이를 숫자로 따로 저장한다.
 * - Comparable을 구현하면 Collections.sort(리스트)만 해도 나이순 정렬이 된다.
 *
 * 예) new LifeRecord(45, "보리가 13살의 나이로 무지개다리를 건넜다")
 *     → 출력: " 45세  보리가 13살의 나이로 무지개다리를 건넜다"
 */
public class LifeRecord implements Comparable<LifeRecord> {

    // 연표 출력 형식 (%3d=나이를 3칸 오른쪽 정렬, %s=문장)
    private static final String FORMAT = "%3d세  %s";

    private int age;
    private String text;

    /** 부르는 곳: Person.addRecord(), Person.addThought() */
    public LifeRecord(int age, String text) {
        // TODO 필드 2개(age, text)에 매개변수 값 저장
        this.age = age;
        this.text = text;
    }

    /**
     * 정렬 기준: 나이가 작은 게 앞으로
     * 반환값: 음수면 this가 앞, 양수면 other가 앞, 0이면 같음
     * 부르는 곳: Collections.sort()가 자동으로 호출
     */
    @Override
    public int compareTo(LifeRecord other) {
        // TODO Integer.compare(this.age, other.age) 를 return
        //      (this.age - other.age 도 되지만 Integer.compare가 더 안전함)
        return Integer.compare(this.age, other.age);
    }

    /**
     * 연표 한 줄 문자열
     * 부르는 곳: Ending (System.out.println(record) 하면 자동으로 호출됨)
     */
    @Override
    public String toString() {
        // TODO String.format(FORMAT, age, text) 를 return
        return String.format(FORMAT, age, text);
    }

    public int getAge() {
        // TODO age 반환
        return age;
    }

    public String getText() {
        // TODO text 반환
        return text;
    }
}
