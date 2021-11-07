package algorithms;

public class HeapSort implements ISortAlgoritimo {

    private long passoDelay = 20;

    private boolean isChildLargerThanRoot(int filho, int maior, int n, SortArray array) {
        return filho < n && array.getValue(filho) > array.getValue(maior);
    }

    private void toBinaryTreeArray(SortArray array,  int n, int rootIndex) {
        int maior      = rootIndex;
        int esqChild   = 2 * rootIndex + 1;
        int direChild  = 2 * rootIndex + 2;

        if (isChildLargerThanRoot(esqChild, maior, n, array)) {
            maior = esqChild;
        }
        if (isChildLargerThanRoot(direChild, maior, n, array)) {
            maior = direChild;
        }
        if (maior != rootIndex) {
            array.muda(rootIndex, maior, getDelay(), true);
            toBinaryTreeArray(array, n, maior);
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