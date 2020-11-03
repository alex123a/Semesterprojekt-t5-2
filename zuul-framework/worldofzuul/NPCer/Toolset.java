package worldofzuul.NPCer;

public class Toolset {
    final private int repair = 3;

    public boolean repairMachine(int damaged) {
        boolean repaired = false;
        // Mangler at tilføje tid, således statementet under bliver kørt en gang i sekundet.
        damaged -= this.repair;
        if (damaged <= 0) {
            repaired = true;
        }
        return repaired;
    }

    public int getRepair() {
        return this.repair;
    }
}
