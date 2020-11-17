package sample.user.NPCer;

import sample.user.RoadBuilder;

public class Toolset {
    final private int repair = 25;

    public void repairMachine(int damaged) throws InterruptedException {
        // Mangler at tilføje tid, således statementet under bliver kørt en gang i sekundet.

        while(RoadBuilder.getDamaged() != 0 || RoadBuilder.getDamaged() > 0) {
            long millis = System.currentTimeMillis();
            RoadBuilder.setDamaged(RoadBuilder.getDamaged() - repair);
            System.out.println(100-RoadBuilder.getDamaged() + "% repaired");
            try {
                Thread.sleep(1000 - millis % 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public int getRepair() {
        return this.repair;
    }
}
