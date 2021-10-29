import java.io.File;
import java.io.FilenameFilter;
import java.awt.image.BufferedImage;

public class LeitorImg {
    int largura, altura;
    File f, caminhoDiretorio;
    BufferedImage img = null;
    FilenameFilter filtradorJPG, filtradorPNG;

    LeitorImg(String diretorio) {
        caminhoDiretorio = new File(diretorio);
        //cria filtro para imgs .jpg
        filtradorJPG = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".jpg")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        //cria filtro para imgs .png
        filtradorPNG = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".png")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }
}