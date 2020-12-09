package sample.domain;

import sample.domain.NPCs.Toolset;
import sample.domain.PlasticElements.Plastic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sample.presentation.Main;

public class Player {
    private String name;
    private List<Plastic> plasticInv = new ArrayList();
    private boolean haveToolset = false;
    private Toolset toolset;
    private String image = "src/sample/presentation/pictures/keyItems/Playergame.png";

    public void setNames (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // plasticCollect will remove the plastic pieces the player stands on.
    public void plasticCollect(Plastic piece, Room room) {
        if (room.getPlasticInRoom().size() > 0) {
            plasticInv.add(piece);
            room.removePlastic(piece);
        }
    }

    public void setPlasticInv(List<Plastic> newInv) {
        plasticInv = newInv;
    }

    public List<Plastic> getPlasticInv() {
        return plasticInv;
    }

    public boolean addPlasticInv() {
        if (plasticInv.size() <= 10 - Main.game.getFarmerObject().getPlasticForPlayer().length) {
            plasticInv.addAll(Arrays.asList(Main.game.getFarmerObject().getPlasticForPlayer()));
            return true;
        }
        return false;
    }

    public void setHaveToolset(boolean haveToolsetPara) {
        haveToolset = haveToolsetPara;
    }

    public boolean getHaveToolset() {
        return haveToolset;
    }

    public void resetPlasticInv() {
        plasticInv = new ArrayList<>();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Toolset getToolset() {
        return toolset;
    }

    public void setToolset(Toolset toolset) {
        this.toolset = toolset;
    }
}

