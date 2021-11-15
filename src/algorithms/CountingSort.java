package algorithms;

public class CountingSort implements ISortAlgoritimo {
	
	private long passoDelay = 2;
	
	@Override
	public String getName() 
	{
		return "Counting Sort";
	}

	@Override
	public long getDelay()
	{
		return passoDelay;
	}

	@Override
	public void setDelay(long delay) 
	{		
		passoDelay = delay;
	}
	
	/**
	 * Esta é uma implementação de uma versão estável do Counting Sort.
	 * Conta cada instância de cada número e os adiciona de volta na ordem.
	 *
	 * @param array Array a ser ordenado
	 */	
	@Override
	public void runSort(SortArray array) {
		int[] result = new int[array.arraySize()];
		int[] cont = new int[array.getValorMax()+1];		
		for(int i = 0; i < result.length; ++i) {	
			array.updateSingle(i, result[i] = array.getValue(i), getDelay(), false);		
			++cont[array.getValue(i)];
		}
		for(int i = 1; i < cont.length; ++i)
			cont[i] += cont[i-1];
		for(int i = result.length-1; i > -1; --i)
			array.updateSingle(--cont[result[i]], result[i], getDelay(), true);		
	}

}
