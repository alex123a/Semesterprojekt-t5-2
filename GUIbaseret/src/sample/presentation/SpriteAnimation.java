package sample.presentation;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class SpriteAnimation {
    private int count = 0;
    private int widthNumPictures = 4;
    private int heightNumPictures = 4;
    private final int picWidth = 32;
    private final int picHeight = 48;
    private String direction;
    private String lastDirection = "South";

    public SpriteAnimation() {
        this.direction = "South";
    }

    public SpriteAnimation(String direction) {
        this.direction = direction;
    }

    public int[] changePic() {
        if (!direction.equals(lastDirection)) {
            count = 0;
            lastDirection = direction;
        }
        // Skal måske minus med en under i gange stykket
        int picture = count % (widthNumPictures * heightNumPictures);
        int whichPicWidth = 0;
        int whichPicHeight = 0;

        if (picture <= 3) {
            whichPicWidth = picture * picWidth;
        } else if (picture > 3 && picture <= 7) {
            whichPicWidth = (picture % 4) * picWidth;
            whichPicHeight = picHeight;
        } else if (picture > 7 && picture <= 11) {
            whichPicWidth = (picture % 4) * picWidth;
            whichPicHeight = picHeight * 2;
        } else if (picture > 11 && picture <= 15) {
            whichPicWidth = (picture % 4) * picWidth;
            whichPicHeight = picHeight * 3;
        }
        count++;
        int[] numbers = {whichPicWidth, whichPicHeight, picWidth, picHeight};
        return numbers;
    }




    // Gør så billede bliver scalet op til en rimlig størrelse


    public void setDirection(String direction) {
        this.direction = direction;
    }
}