
public class Main {
    public static void main(String args[]) {
        LeitorImg li;

        li = new LeitorImg("img");

        String imageFilesList[] = li.caminhoDiretorio.list(li.filtradorJPG);
        if (imageFilesList.length == 0) {
            System.out.println("Formato não encontrado no diretorio.");  
        } else {
            System.out.println("List of the jpeg files in the specified directory:");  
            for(String fileName : imageFilesList) {
                System.out.println(fileName);
            }
        }
    }
}
