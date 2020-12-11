package sample.presentation;

public class FireAnimation implements Animation {
    private int count = 0;
    private int widthNumPictures = 8;
    private final double picWidth = 72.125;
    private final double picHeight = 52.0;

    public double[] changePic() {
        double whichPicWidth = 0;
        double whichPicHeight = 0;
        whichPicWidth = (count % 8) * picWidth;
        count++;
        double[] numbers = {whichPicWidth, whichPicHeight, picWidth, picHeight};
        return numbers;
    }
}

