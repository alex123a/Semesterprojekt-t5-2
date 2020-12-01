package sample.presentation;

public class FireAnimation {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getWidthNumPictures() {
        return widthNumPictures;
    }

    public void setWidthNumPictures(int widthNumPictures) {
        this.widthNumPictures = widthNumPictures;
    }

    public double getPicWidth() {
        return picWidth;
    }

    public double getPicHeight() {
        return picHeight;
    }
}

