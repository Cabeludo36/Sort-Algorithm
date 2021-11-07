package algorithms;

public class HeapSort implements ISortAlgorithm {

    private long passoDelay = 20;

    private boolean isChildLargerThanRoot(int filho, int maior, int n, SortArray array) {
        return filho < n && array.getValue(filho) > array.getValue(maior);
    }

    private void toBinaryTreeArray(SortArray array,  int n, int rootIndex) {
        int largest    = rootIndex;
        int leftChild  = 2 * rootIndex + 1;
        int rightChild = 2 * rootIndex + 2;

        if (isChildLargerThanRoot(leftChild, largest, n, array)) {
            largest = leftChild;
        }
        if (isChildLargerThanRoot(rightChild, largest, n, array)) {
            largest = rightChild;
        }
        if (largest != rootIndex) {
            array.muda(rootIndex, largest, getDelay(), true);
            toBinaryTreeArray(array, n, largest);
        }
    }


    @Override
    public void runSort(SortArray array) {
        int n = array.arraySize();
        for (int i = n / 2 - 1; i >= 0; i--) {
            toBinaryTreeArray(array, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            array.muda(0, i, getDelay(), true);
            toBinaryTreeArray(array, i, 0);
        }
    }

    @Override
    public String getName() {
        return "Heap sort";
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