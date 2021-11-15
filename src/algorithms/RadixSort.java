package algorithms;
import java.util.Arrays;

public class RadixSort implements ISortAlgoritimo {

	private long passoDelay = 5;
	private int radix;	
	private int[] contArr;

	/**
	 * @param radix Sistema numerico que você quer trabalhar. Deve ser maior que zero
	 */
	public RadixSort(int radix)
	{
		this.radix = radix;
		contArr = new int[radix];
	}	
	
	/**
	 * Seta Radix para 10 como padrão.
	 */
	public RadixSort()
	{
		this(10);
	}

	/**
	 * Este é o método que chama a primeira instância de Radix Sort.
	 * Radix Sort é um algoritmo não baseado em comparação que usa contagem de
	 * classificação como uma sub-rotina.
	 * 
	 * Ele funciona classificando pelo dígito menos significativo do menor ao maior.
	 * Em seguida, ele classifica o próximo dígito menos significativo e assim por diante. 
	 * No entanto, não estamos limitados ao sistema numérico decimal. Podemos classificar 
	 * em hexadecimal, binário, etc; daí o nome <a href="https://en.wikipedia.org/wiki/Radix_sort">Radix Sort</a>.
	 *
	 * @param array the array to be sorted
	 * @see SortArray
	 */	
	@Override
	public void runSort(SortArray array) {
		int maior = array.getValorMax();
		int[] result = new int[array.arraySize()];

		// na vida real se Radix fosse 2, então mudaríamos de bit.
		for(int exp = 1; maior/exp > 0; exp *= radix) {
			contArr = contSort(array, exp);
			
			for(int i = 0; i < result.length; ++i)
				array.updateSingle(i, result[i] = array.getValue(i), getDelay(), false);				
			
			for(int i = 1; i < radix; ++i)
				contArr[i] += contArr[i-1];
			
			for(int i = array.arraySize() - 1; i > -1 ; --i)
				array.updateSingle(--contArr[(result[i]/exp)%radix], result[i], getDelay(), true);	
		}		
	}

	/**
	 * Executa uma sub-rotina de Counting Sort
	 * 
	 * @param arr Array sendo ordenado
	 * @param exp Exponente atual
	 * @return Um array de contagem que fornece novos índices para todos os valores
	 */
	private int[] contSort(SortArray arr, int exp) {
		Arrays.fill(contArr, 0);
		for(int i = 0; i < arr.arraySize(); ++i)
			contArr[(arr.getValue(i)/exp)%radix]++;
		return contArr;
	}

	@Override
	public String getName() {
		return "Radix Sort (Base " + radix + ")";
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
