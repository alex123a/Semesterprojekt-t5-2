package sample.presentation;

public class OldLadyAnimation implements Animation {
    private int count = 0;
    private final double picWidth = 128;
    private final double picHeight = 192;

    @Override
    public double[] changePic() {
        double whichPicWidth = 0;
        double whichPicHeight = 0;
        whichPicWidth = (count % 4) * picWidth;
        whichPicHeight = (count % 4) * picHeight;
        count++;
        double[] numbers = {whichPicWidth, whichPicHeight, picWidth, picHeight};
        return numbers;
    }
}
