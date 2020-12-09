package sample.presentation;

public class OldLadyAnimation implements Animation {
    private int count = 0;
    private final double picWidth = 32;
    private final double picHeight = 48;


    @Override
    public double[] changePic() {
        double whichPicWidth = (count % 4) * picWidth;
        double whichPicHeight = 0;
        count++;
        double[] numbers = {whichPicWidth, whichPicHeight, picWidth, picHeight};
        return numbers;
    }
}
