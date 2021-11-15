package algorithms;

public class StoogeSort implements ISortAlgoritimo {

    private long passoDelay = 5;
    
    public void stoogeSort(SortArray array, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }
        if (array.getValue(lowIndex) > array.getValue(highIndex)) {
            array.muda(lowIndex, highIndex, getDelay(), true);
        }

        if (highIndex - lowIndex + 1 > 2) {
            int t = (highIndex - lowIndex + 1) / 3;
            stoogeSort(array, lowIndex, highIndex - t);
            stoogeSort(array, lowIndex + t, highIndex);
            stoogeSort(array, lowIndex, highIndex - t);
        }
    }

    @Override
    public void runSort(SortArray array) {
        stoogeSort(array, 0, array.arraySize() - 1);
    }

    @Override
    public String getName() {
        return "Stooge sort";
    }

    @Override
    public long getDelay() {
        return passoDelay;
    }

    @Override
    public void setDelay(long delay) {
        this.passoDelay = delay;
    }
}