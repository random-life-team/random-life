# 🌍 Random Life — 80억 분의 1

Java 콘솔 인생 시뮬레이션 게임 (3인 / 4일)
어디서 태어날지 고를 수 없는 한 번의 인생을, 실제 국가 통계(세계은행)와 함께 살아 보는 게임.

## 실행

IntelliJ: `src` 폴더를 Sources Root로 지정 → `Application.java` 실행
(한글이 `???`로 나오면 Run Configuration → VM options에 `-Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8`)

터미널:
```
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp out randomlife.Application
```
## 담당

| 담당 | 파일 |
| --- | --- |
| A | `Application`, `core/Person`, `core/LifeSimulator`, `util/GameConfig` |
| B | `event/` 전체, `temperament/` 전체 |
| C (대체: A) | `job/` 전체, `util/StoryText`, `util/InputUtil`, `core/Country`, `core/CountryData`, `core/FamilyType`, `core/LifeRecord`, `core/Ending` |

> C가 기한까지 못 하면 A가 이어받음
> - **1일차 점심까지**: `Country`, `FamilyType`, `LifeRecord`, `InputUtil` (A·B가 기다리는 파일)
> - **2일차 끝까지**: `Ending`

## 이미 채워져 있는 것 / 우리가 채울 것

| 이미 작성됨 (수정은 자유) | 우리가 채움 (`// TODO`) |
| --- | --- |
| 모든 출력 문구 (상수로 선언) | 그 문구를 **언제, 어떤 조건에서** 출력할지 |
| 데이터 파일 4개: `CountryData`, `EventData`, `JobData`, `StoryText` | 메서드 안의 동작 (로직) |
| 필드 선언, 메서드 이름·파라미터·리턴 타입 | getter / setter, 값 범위 제한 같은 캡슐화 |

- TODO가 남아 있어도 **항상 컴파일된다.** (임시 `return` 값이 들어 있음 → 구현할 때 지우기)
- 각 메서드 위 주석의 `부르는 곳:`을 보면 누가 이 메서드를 쓰는지 알 수 있다.

## 코드 규칙

1. 능력치·플래그 이름은 문자열을 직접 쓰지 말고 상수로: `GameConfig.HEALTH`, `GameConfig.FLAG_COLLEGE`
2. 화면 문구를 새로 만들면 문자열을 코드 중간에 쓰지 말고 클래스 위쪽 상수나 `StoryText`에 추가
3. 랜덤은 `GameConfig.RANDOM` / `GameConfig.randomInt()` / `GameConfig.chance()`만 사용
4. 입력은 `InputUtil`로만 받기 (`Scanner`를 새로 만들지 않기)
5. 문자열 비교는 `==` 말고 `.equals()`

## 협업 규칙 (Git)

1. **자기 담당 파일만 수정**
2. 공용 파일(`GameConfig`, `Choice`, `Person`)을 고쳐야 하면 **단톡에 먼저 말하고** 고치기
3. 브랜치는 **기능 단위**로: `feature/기능명` → 끝나면 PR → Merge → 브랜치 삭제
   예) `feature/person`, `feature/choice`, `feature/job-event`
4. `main`에는 PR로만 합치기 (main에 직접 push 금지)
5. push 전에 `main`을 받아서 내 브랜치에 먼저 merge (충돌은 내 컴퓨터에서 해결)
6. 커밋 메시지

| 앞머리 | 언제 | 예시 |
| --- | --- | --- |
| `feat:` | 기능 구현 | `feat: Person 능력치 변경 구현` |
| `fix:` | 버그 수정 | `fix: 반려동물 이름 조사 오류 수정` |
| `docs:` | 문서·주석 | `docs: README 실행 방법 추가` |
| `refactor:` | 동작은 그대로, 코드만 정리 | `refactor: 중복 출력 메서드로 분리` |

> 자세한 Git 사용법과 주의사항은 [CONTRIBUTING.md](CONTRIBUTING.md) 참고

## 추천 구현 순서 (서로 기다리지 않게)

| 순서 | A | B | C |
| --- | --- | --- | --- |
| 1일차 | `GameConfig`, `Person` | `LifeEvent`, `Choice` 빌더·조건 검사, `temperament/` 5개 | **점심까지** `Country`, `FamilyType`, `LifeRecord`, `InputUtil` → 오후 `Job` |
| 2일차 | `LifeSimulator` 기본 흐름 (출생 → 이벤트 → 사망) | `Choice.apply`, `ChoiceEvent`, `TemperamentEvent`, `AutoEvent` | `JobEvent`, `StoryText` 문구 다듬기 |
| 3일차 | `LifeSimulator` 멈춤·노년·반려동물 | 연결 테스트, 버그 수정 | `Ending` (결산 화면) |
| 4일차 | 전체 플레이 테스트 · 발표 준비 | ← | ← |

## 데이터 추가하는 법

| 하고 싶은 것 | 고칠 곳 |
| --- | --- |
| 선택지 추가 | `EventData`에서 해당 이벤트에 `.add(new Choice(...))` 한 줄 |
| 이벤트 교체 | `EventData` (나이별 2개 유지, 조건 없는 선택지 최소 1개) |
| 직업 추가 | `JobData`에 `add(list, job(...))` 한 줄 |
| 감성 문구 추가 | `StoryText` 배열에 한 줄 |
| 밸런스 조정 | `GameConfig` 숫자만 |

## 통계 출처

세계은행 World Development Indicators: 기대수명(SP.DYN.LE00.IN), 5세 미만 사망률(SH.DYN.MORT), 대학 총취학률(SE.TER.ENRR), 흡연율(SH.PRV.SMOK), 인터넷 이용률(IT.NET.USER.ZS)