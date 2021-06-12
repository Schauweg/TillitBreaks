package schauweg.tillitbreaks.config;

public class TIBConfig {

    private boolean showDurabilityIfBarFull = false;
    private boolean showDurabilityNumIfFull = true;
    private boolean showDurabilityBar = true;
    private boolean showDurabilityNumber = true;
    private boolean colorDurabilityNumber = false;
    private boolean showArrowCount = true;
    private int textSize = 100;

    public boolean isShowDurabilityIfBarFull() {
        return showDurabilityIfBarFull;
    }

    public void setShowDurabilityIfBarFull(boolean showDurabilityIfBarFull) {
        this.showDurabilityIfBarFull = showDurabilityIfBarFull;
    }

    public boolean isShowDurabilityNumIfFull() {
        return showDurabilityNumIfFull;
    }

    public void setShowDurabilityNumIfFull(boolean showDurabilityNumIfFull) {
        this.showDurabilityNumIfFull = showDurabilityNumIfFull;
    }

    public boolean isShowDurabilityBar() {
        return showDurabilityBar;
    }

    public void setShowDurabilityBar(boolean showDurabilityBar) {
        this.showDurabilityBar = showDurabilityBar;
    }

    public boolean isShowDurabilityNumber() {
        return showDurabilityNumber;
    }

    public void setShowDurabilityNumber(boolean showDurabilityNumber) {
        this.showDurabilityNumber = showDurabilityNumber;
    }

    public boolean isColorDurabilityNumber() {
        return colorDurabilityNumber;
    }

    public void setColorDurabilityNumber(boolean colorDurabilityNumber) {
        this.colorDurabilityNumber = colorDurabilityNumber;
    }

    public boolean isShowArrowCount() {
        return showArrowCount;
    }

    public void setShowArrowCount(boolean showArrowCount) {
        this.showArrowCount = showArrowCount;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }
}
