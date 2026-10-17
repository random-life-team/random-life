package randomlife.util;

import java.util.Scanner;

/**
 * 입력 처리 전담 클래스 (담당: C / 대체: A)
 * - 모든 입력은 이 클래스를 통해서만 받는다.
 * - Scanner는 프로그램 전체에서 1개만 사용한다. (여러 개 만들면 입력이 꼬임)
 */
public class InputUtil {

    private static final Scanner sc = new Scanner(System.in);

    // ===== 출력 문구 =====
    private static final String PROMPT        = "> ";
    private static final String ENTER_MSG     = "(엔터를 누르면 계속)";
    private static final String NOT_NUMBER    = "숫자로 입력해주세요.";
    private static final String OUT_OF_RANGE  = "%d ~ %d 사이의 숫자를 입력해주세요.";
    private static final String EMPTY_INPUT   = "한 글자 이상 입력해주세요.";
    private static final String LOCKED_CHOICE = "지금은 고를 수 없는 선택지입니다. 다른 번호를 골라주세요.";

    /**
     * 숫자를 입력받는다. (min ~ max)
     * 예) readInt(1, 3) → 1, 2, 3 중 하나가 들어올 때까지 반복
     * 부르는 곳: readChoice(), LifeSimulator(다시 하기 질문)
     */
    public static int readInt(int min, int max) {
        // TODO 1. while (true)로 무한 반복
        // TODO 2. System.out.print(PROMPT);
        // TODO 3. sc.nextLine()으로 한 줄 읽고 trim()
        // TODO 4. Integer.parseInt()로 숫자 변환
        //         → 실패하면(NumberFormatException) NOT_NUMBER 출력 후 continue
        // TODO 5. min ~ max 범위 밖이면 System.out.printf(OUT_OF_RANGE + "%n", min, max); 후 continue
        // TODO 6. 정상이면 return

        while (true) {
            System.out.println(PROMPT);
            String input = sc.nextLine().trim();

            int number;

            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(NOT_NUMBER);
                continue;
            }
            if (number < min || number > max) {
                System.out.printf(OUT_OF_RANGE + "%n", min, max);
                continue;
            }
            return number;
        }

    }

    /**
     * 잠긴 선택지는 고를 수 없게 하는 번호 입력
     * locked[i] == true 이면 (i+1)번 선택지는 잠김
     * 반환값: 고른 번호 (1부터 시작)
     * 부르는 곳: ChoiceEvent.askChoice()
     */
    public static int readChoice(boolean[] locked) {
        // TODO 1. while (true)로 무한 반복
        // TODO 2. readInt(1, locked.length)로 번호 입력
        // TODO 3. locked[번호 - 1]이 true면 LOCKED_CHOICE 출력 후 다시 입력
        // TODO 4. 잠기지 않은 번호면 return


        while (true) {
            int choice = readInt(1, locked.length);

            if (locked[choice - 1]) {
                System.out.println(LOCKED_CHOICE);
                continue;
            }

            return choice;
        }
    }

    /**
     * 문자열 한 줄 입력 (이름, 남긴 생각, 마지막 한마디 등)
     * question  : 입력 전에 보여줄 질문 (null이면 안 보여줌)
     * allowEmpty: false면 빈 입력을 막는다. true면 엔터만 쳐도 "" 반환
     * 부르는 곳: LifeSimulator, Choice(반려동물 이름)
     */
    public static String readLine(String question, boolean allowEmpty) {
        // TODO 1. question이 null이 아니면 출력
        // TODO 2. while (true)로 무한 반복
        // TODO 3. System.out.print(PROMPT); → sc.nextLine().trim()
        // TODO 4. allowEmpty가 false인데 비어 있으면(isEmpty) EMPTY_INPUT 출력 후 다시 반복
        // TODO 5. return 입력값
        if (question != null) {
            System.out.println(PROMPT);
        }

        while (true) {
            System.out.println(PROMPT);
            String input = sc.nextLine().trim();

            if (!allowEmpty && input.isEmpty()) {
                System.out.println(EMPTY_INPUT);
                continue;
            }

            return input;
        }
    }

    /**
     * 엔터 입력 대기 (화면 넘김용)
     * 부르는 곳: LifeSimulator, AutoEvent
     */
    public static void waitEnter() {
        // TODO 1. 빈 줄 하나 출력
        // TODO 2. ENTER_MSG 출력
        // TODO 3. sc.nextLine(); (입력값은 버림)

        System.out.println();
        System.out.println(ENTER_MSG);
        sc.nextInt();
    }
}
