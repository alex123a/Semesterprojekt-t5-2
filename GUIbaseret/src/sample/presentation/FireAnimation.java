package sample.presentation;

public class FireAnimation {
    private int count = 0;
    private int widthNumPictures = 3;
    private final double picWidth = 266.66;
    private final double picHeight = 200.0;

    public double[] changePic() {
        double whichPicWidth = 0;
        whichPicWidth = (count % 4) * picWidth;
        count++;
        double[] numbers = {whichPicWidth, 0, picWidth, picHeight};
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

