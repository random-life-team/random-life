# ⚠️ Random Life 팀원 주의사항

> 시작 전에 한 번 꼭 읽어 주세요. 이것만 지키면 4일 동안 크게 꼬일 일은 없습니다.

---

## 1. 처음 시작할 때 (한 번만)

1. **zip은 A 한 명만 GitHub에 올리기.** 나머지는 그걸 `clone` 받기.
   세 명이 각자 올리면 시작부터 충돌이 납니다.
2. IntelliJ에서 `RandomLife` 폴더를 열고 → `src`에 우클릭 → **Mark Directory as → Sources Root**
3. 한글이 깨지면 두 군데를 확인
   - Settings → Editor → File Encodings → 전부 **UTF-8**
   - Run → Edit Configurations → VM options에 `-Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8`
4. `Application.java`를 실행해서 **컴파일되는지만 확인.**
   실행하면 바로 에러(NullPointerException)가 나는데, 아직 TODO를 안 채워서 그런 거라 정상입니다.

## 0. 내 파일은 어디에?

```
RandomLife/
├── README.md
├── CONTRIBUTING.md
└── src/randomlife/
    ├── Application.java           🅰️
    │
    ├── core/                      인생 진행
    │   ├── Person.java            🅰️ ⚠️ 공용
    │   ├── LifeSimulator.java     🅰️
    │   ├── LifeRecord.java        🅲
    │   ├── Country.java           🅲
    │   ├── CountryData.java       🅲 📦 데이터 완성
    │   ├── FamilyType.java        🅲
    │   └── Ending.java            🅲
    │
    ├── temperament/               🅱️ 기질
    │   ├── Temperament.java       🅱️
    │   ├── Curious.java           🅱️
    │   ├── Active.java            🅱️
    │   ├── Empathic.java          🅱️
    │   └── Persistent.java        🅱️
    │
    ├── event/                     🅱️ 이벤트
    │   ├── LifeEvent.java         🅱️
    │   ├── Choice.java            🅱️ ⚠️ 공용
    │   ├── ChoiceEvent.java       🅱️
    │   ├── TemperamentEvent.java  🅱️
    │   ├── AutoEvent.java         🅱️
    │   └── EventData.java         🅱️ 📦 데이터 완성
    │
    ├── job/                       🅲 직업
    │   ├── Job.java               🅲
    │   ├── JobEvent.java          🅲
    │   └── JobData.java           🅲 📦 데이터 완성
    │
    └── util/
        ├── GameConfig.java        🅰️ ⚠️ 공용
        ├── InputUtil.java         🅲 ⚠️ 공용
        └── StoryText.java         🅲 📦 데이터 완성
```

| 표시 | 뜻 |
| --- | --- |
| 🅰️ 🅱️ 🅲 | 담당자 (이 사람만 수정) |
| ⚠️ 공용 | 모두가 가져다 쓰는 파일 → 고칠 땐 **단톡에 먼저 말하기** |
| 📦 데이터 완성 | 내용이 이미 다 들어 있음 → 문구·수치 수정만 |

### 누가 누구 코드를 쓰나

```
LifeSimulator (A) ──▶ LifeEvent (B) ──▶ Choice (B) ──▶ Person (A)
       │                    │                │
       │                    └─ JobEvent (C) ─┘
       ▼
    Ending (A) ──▶ LifeRecord (A)
```

> 화살표 오른쪽 파일이 먼저 완성돼야 왼쪽이 돌아감
> → **A는 `Person`, B는 `Choice`를 1일차에 먼저** 끝내야 다른 사람이 안 기다림
---

## 2. 코드 짤 때

| 하지 말 것 ❌ | 대신 이렇게 ✅ |
| --- | --- |
| 메서드 이름·파라미터·리턴 타입 바꾸기 | 그대로 두고 몸통만 채우기 (다른 사람 코드가 그 메서드를 부르고 있음) |
| 남의 담당 파일 고치기 | 필요하면 담당자한테 말하기 |
| 공용 파일(`GameConfig`, `Choice`, `Person`) 몰래 고치기 | **단톡에 먼저 말하고** 고치기 |
| `"HEALTH"`처럼 문자열 직접 쓰기 | `GameConfig.HEALTH` 상수 쓰기 (오타 방지) |
| 문구를 코드 중간에 직접 쓰기 | 클래스 위쪽 상수나 `StoryText`에 추가 |
| `new Scanner(System.in)` 새로 만들기 | `InputUtil`만 쓰기 (여러 개 만들면 입력이 꼬임) |
| 문자열을 `==`로 비교 | `.equals()`로 비교 |
| `new Random()` 새로 만들기 | `GameConfig.randomInt()`, `GameConfig.chance()` 쓰기 |

- TODO 밑에 있는 임시 `return 0;`, `return null;`은 구현하면서 **지우고 진짜 값으로 바꾸기**
- 메서드 위 주석의 `부르는 곳:`을 보면 누가 이 메서드를 쓰는지 알 수 있음
- 막히면 `RandomLife_example`(정답)을 보되, **복붙하지 말고 이해하고 직접 치기** (발표 때 설명해야 함)

---

## 3. 올릴 때

1. **push 전에 꼭 실행해 보기.** main에는 항상 컴파일되는 코드만 올라가야 함.
   한 명이 깨진 코드를 올리면 세 명이 다 멈춤.
2. **작게, 자주 올리기.** 메서드 몇 개 끝날 때마다 커밋.
   하루치를 한 번에 올리면 충돌 났을 때 고치기 지옥.
3. `git status`에 `.idea/`, `out/`, `.class`가 보이면 올리지 말기. (`.gitignore`에 들어 있어서 원래 안 보여야 정상)
4. **main에 직접 push 금지.** 무조건 브랜치 → PR → Merge

---

## 4. Git 작업 흐름

> **비유:** `main` = 팀 공용 **원본**, `feature` 브랜치 = **내 복사본**
> 복사본에서 마음껏 고치고, 다 되면 원본에 합쳐 달라고 요청(PR)하는 것

### 🔁 한 줄 요약

**브랜치 만들기 → 작업·커밋 → main 받아서 합쳐 보기 → push → PR → Merge → main 최신화 → 다음 브랜치**

### 1단계. 내 작업 공간 만들기

```bash
git clone <저장소 주소>
git switch -c feature/기능명      # 내 복사본(브랜치)을 만들면서 그리로 이동
```

> `-c` = create. "만들면서 이동"이라는 뜻

### 2단계. 작업하고 저장하기

```bash
git status                        # ⭐ 지금 어느 브랜치인지 꼭 확인! main이면 멈추기
git add .
git commit -m "feat: Person 능력치 변경 구현"
```

> 커밋 = **게임 세이브 포인트.** 자주 찍을수록 되돌리기 쉬움

### 3단계. 올리기 전에 최신 원본을 받아서 합쳐 보기 (충돌 예방)

```bash
git checkout main
git pull origin main              # 그사이 팀원이 올린 최신 원본 받기
git checkout feature/기능명
git merge main                    # 최신 원본을 내 복사본에 미리 합쳐 보기
```

> **왜?** 원본에 넣기 전에 내 컴퓨터에서 미리 합쳐 보는 것.
> 충돌이 나도 **내 컴퓨터에서** 나니까 원본은 안전함

### 4단계. 내 복사본 올리기

```bash
git push -u origin feature/기능명  # 처음 한 번만
git push                           # 두 번째부터는 이것만
```

### 5단계. 원본에 합쳐 달라고 요청하기

1. GitHub → **Pull Request** 만들기 (제목은 커밋 메시지처럼 `feat: ...`)
2. 팀원 1명이 확인 → 문제없으면 **Merge**
3. 단톡에 "merge했어요" 한마디 → 다른 팀원들이 3단계로 최신화

> PR = "내 복사본을 원본에 합쳐도 될까요?" 하고 물어보는 것

### 6단계. 정리하고 다음 작업

```bash
git checkout main
git pull origin main               # 합쳐진 최신 원본 받기
git branch -d feature/기능명       # 다 쓴 복사본 삭제
git switch -c feature/다음기능명   # 새 복사본 만들어서 다시 1단계부터
```

---

## 5. 충돌(conflict)이 났을 때

파일 안에 이런 표시가 생깁니다.

```
<<<<<<< HEAD
내가 쓴 코드
=======
팀원이 쓴 코드
>>>>>>> main
```

1. 둘 중 맞는 걸 남기거나 둘을 합치기
2. 표시 줄 3개(`<<<<<<<`, `=======`, `>>>>>>>`)는 **반드시 지우기**
3. 실행해서 컴파일되는지 확인
4. 아래처럼 커밋

```bash
git add .
git commit -m "fix: main 병합 충돌 해결"
```

> 모르겠으면 혼자 고치지 말고 **그 코드를 쓴 사람이랑 같이** 보기.
> IntelliJ에서는 충돌 파일에서 **Merge...** 버튼을 누르면 양쪽을 나란히 보면서 고를 수 있음

---

## 6. 이름 규칙

### 브랜치

`feature/기능명` (소문자, 단어 사이는 `-`)

| 담당 | 예시 |
| --- | --- |
| A | `feature/person`, `feature/temperament`, `feature/life-simulator`, `feature/ending` |
| B | `feature/life-event`, `feature/choice`, `feature/auto-event` |
| C | `feature/job`, `feature/job-event` |

버그를 고칠 때는 `fix/내용` (예: `fix/pet-josa`)

### 커밋 메시지

| 앞머리 | 언제 | 예시 |
| --- | --- | --- |
| `feat:` | 기능 구현 | `feat: Choice 잠금 조건 구현` |
| `fix:` | 버그 수정 | `fix: 반려동물 이름 조사 오류 수정` |
| `docs:` | 문서·주석 | `docs: README 실행 방법 추가` |
| `refactor:` | 동작은 그대로, 코드만 정리 | `refactor: 중복 출력 메서드로 분리` |

---

## 7. 자주 하는 실수 모음

| 상황 | 해결 |
| --- | --- |
| main에서 작업해 버림 (아직 커밋 전) | `git switch -c feature/기능명` → 작업 내용 그대로 새 브랜치로 옮겨짐 |
| 커밋 메시지 오타 (아직 push 전) | `git commit --amend -m "새 메시지"` |
| `git pull`이 안 됨 (작업 중인 변경 있음) | 먼저 `git add .` → `git commit` 하고 다시 pull |
| push가 거절됨 (rejected) | 3단계(main 받아서 merge)부터 다시 |
| 한글이 `???`로 나옴 | 1번 항목 3번(UTF-8 설정) 확인 |
| 다른 사람 코드가 빨갛게 터짐 | 메서드 이름·파라미터를 바꾼 사람이 있는지 확인 |

---

## 8. 하루 루틴

| 언제 | 할 일 |
| --- | --- |
| 아침 | `git checkout main` → `git pull origin main` → 새 브랜치 |
| 작업 중 | 메서드 몇 개마다 커밋 |
| 점심 · 저녁 | PR 올리고 서로 Merge |
| 하루 끝 | main에서 전체 실행 → 깨진 곳 없는지 확인 |
