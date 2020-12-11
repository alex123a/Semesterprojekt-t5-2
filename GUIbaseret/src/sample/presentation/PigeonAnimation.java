package sample.presentation;

public class PigeonAnimation implements Animation{
    private int count = 0;
    private int startPixelX = 144;
    private int startPixelY = 0;
    private int widthNumPictures = 3;
    private int heightNumPictures = 2;
    private final int picWidth = 48;
    private final int picHeight = 48;

    @Override
    public double[] changePic() {
        double whichPicWidth = 0;
        double whichPicHeight = 0;
        // Finding the next picture and the direction the pigeon is currently walking
        if (count % 48 < 12) {
            whichPicWidth = startPixelX + (count % widthNumPictures) * picWidth;
            whichPicHeight = 0;
        } else if (count % 48 >= 12 && count % 48 < 24) {
            whichPicWidth = startPixelX + (count % widthNumPictures) * picWidth;
            whichPicHeight = picHeight;
        } else if (count % 48 >= 24 && count % 48 < 36) {
            whichPicWidth = startPixelX + (count % widthNumPictures) * picWidth;
            whichPicHeight = picHeight * 3;
        } else {
            whichPicWidth = startPixelX + (count % widthNumPictures) * picWidth;
            whichPicHeight = picHeight * 2;
        }
        count++;
        // The reason why it gives count with is, so that count not only decides which picture but also which direction it should walk.
        double[] numbers = {whichPicWidth, whichPicHeight, picWidth, picHeight, count};
        return numbers;
    }
}
