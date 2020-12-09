package sample.presentation;

public class SpriteAnimation implements Animation {
    /**
     * @param direction The direction the player is walking
     */

    // Counter to find the next picture
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

    public double[] changePic() {
        // Count should only be reset if the player tries a new direction else it should just continue the next animation
        if (!direction.equals(lastDirection)) {
            count = 0;
            lastDirection = direction;
        }

        int whichPicWidth = 0;
        int whichPicHeight = 0;

        // Giving the next picture by the direction the player walks
        // The % is for keep repeating the same 4 pictures in the direction the player is walking.
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
        double[] numbers = {whichPicWidth, whichPicHeight, picWidth, picHeight};

        return numbers;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
