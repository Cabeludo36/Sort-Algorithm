package algorithms;

public class IterativeMergeSort implements ISortAlgoritimo {
	
	private long passoDelay = 5;
	
	/**
	 * Esta é uma implementação de Merge Sort usando iteração em vez de Recursion.
	 * Funciona dividindo o array em pequenas subsequências. Essas subseqüências são
	 * então mescladas em matrizes classificadas maiores. No final, haverá um grande array ordenado.
	 * 
	 * @param array a ser ordenado
	 * @see SortArray
	 */
	@Override
	public void runSort(SortArray array) 
	{
		for(int exp = 1; exp < array.arraySize(); exp <<= 1)
			for(int k = 0, j = exp+exp, s = array.arraySize()-exp; k<s; k+=j)
				merge(array, k, exp);
	}
	
	/**
	 * Retorna uma subsequência do array obtido da entrada. O array original
	 * é cortado começando do início e terminando no final.
	 * @param array Array a ser cortado
	 * @param inicio Posição inicial da subsequência (inclusiva)
	 * @param fim Posição final da subsequência (não inclusiva)
	 * @return Subsequencia do array
	 */
	private int[] getSubArray(SortArray array, int inicio, int fim)
	{
		int size = fim - inicio;
		int arr[] = new int[size];
		for (int i = 0; i < size; i++) 
			arr[i] = array.getValue(inicio + i);		
		return arr;
	}
	
	/**
	 * Mescla duas subsequncias ordenadas do array em uma grande sequência ordenada.
	 * @param arr Array a ser ordenado
	 * @param inicio Começo da subsequência
	 * @param exp Expoente atual, e tamanho da subsequência
	 */
	private void merge(SortArray arr, int inicio, int exp)
	{
		int s = inicio;
		int m = inicio + exp;
		int end = (arr.arraySize() < m+exp) ? arr.arraySize() : m + exp;				
		int[] leftArr = getSubArray(arr, s, m);
		int[] rightArr = getSubArray(arr, m, end);
		int i = 0, j = 0;
		while(i < leftArr.length && j < rightArr.length)
			if(leftArr[i] <= rightArr[j])
				arr.updateSingle(inicio++, leftArr[i++], getDelay(), true);
			else
				arr.updateSingle(inicio++, rightArr[j++], getDelay(), true);
		while (i < leftArr.length)
			arr.updateSingle(inicio++, leftArr[i++], getDelay(), true);
		while (j < leftArr.length)
			arr.updateSingle(inicio++, rightArr[j++], getDelay(), true);		
	}	

	@Override
	public String getName() {
		return "Iterative Merge Sort";
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
