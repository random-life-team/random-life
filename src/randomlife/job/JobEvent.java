package randomlife.job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import randomlife.core.Person;
import randomlife.event.ChoiceEvent;
import randomlife.util.GameConfig;

/**
 * 25세 첫 직업 이벤트 (담당: C)
 * - 직업 30개 중 3개를 랜덤으로 보여준다.
 * - 3개가 전부 잠기면 진행이 막히므로, 고를 수 있는 직업 1개를 꼭 끼워 넣는다.
 */
public class JobEvent extends ChoiceEvent {

    public JobEvent(int age, String title, String description, String statKey) {
        super(age, title, description, statKey);
    }

    /** 부르는 곳: LifeSimulator (LifeEvent 타입으로) */
    @Override
    public void run(Person p) {
        // TODO 1. printHeader(p)
        // TODO 2. List<Job> all = JobData.getAll();  → Collections.shuffle(all)로 섞기
        // TODO 3. 앞에서 GameConfig.JOB_SHOW_COUNT개를 picked 리스트에 담기
        //         new ArrayList<>(all.subList(0, 개수))

        printHeader(p);

        List<Job> all = JobData.getAll();
        Collections.shuffle(all);

        List<Job> picked = new ArrayList<>(
                all.subList(0, GameConfig.JOB_SHOW_COUNT)
        );

        boolean hasAvailable = false;

        for (Job job : picked) {
            if (job.isAvailable(p)) {
                hasAvailable = true;
                break;
            }
        }

        // TODO 4. picked 중 isAvailable(p)가 true인 게 하나도 없으면
        //         all에서 고를 수 있고 picked에 없는 직업을 찾아 picked의 마지막 칸에 set
        // TODO 5. int index = askChoice(p, picked);   (부모 ChoiceEvent 메서드 재사용)
        // TODO 6. picked.get(index).apply(p);         (Job의 apply가 실행됨)

        if (!hasAvailable) {
            for (Job job : all) {
                if (job.isAvailable(p) && !picked.contains(job)) {
                    picked.set(picked.size() -1, job);
                    break;
                }
            }
        }

        int index = askChoice(p, picked);

        picked.get(index).apply(p);

    }
}
