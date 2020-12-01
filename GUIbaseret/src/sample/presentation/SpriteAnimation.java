package sample.presentation;

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
        // int picture = count % (widthNumPictures * heightNumPictures);
        int whichPicWidth = 0;
        int whichPicHeight = 0;

        if (direction.equals("South")) {
            whichPicWidth = (count % 4) * picWidth;
            whichPicHeight = 0;
        } else if (direction.equals("West")) {
            whichPicWidth = (count % 4) * picWidth;
            whichPicHeight = picHeight;
        } else if (direction.equals("East")) {
            whichPicWidth = (count % 4) * picWidth;
            whichPicHeight = picHeight * 2;
        } else if (direction.equals("North")) {
            whichPicWidth = (count % 4) * picWidth;
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
