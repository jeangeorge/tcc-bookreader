package com.example.jean.bookreader;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;

import java.io.File;

/**
 * Created by Gabriel on 19/09/2017.
 */

public class ModuloPDF {
    //Armazena uma imagem com a pagina atual do pdf
    private Bitmap imagem;

    //Armazena o número da página atual do pdf
    private int paginaAtual = 0;

    //Caminho do pdf
    private String pathArquivo;

    //Armazena o número total de páginas
    private int totalPaginas = 0;

    public int w,h;
    public ModuloPDF(String path)
    {
        pathArquivo = path;
        Atualizar();
    }

    //Atualiza a imagem de acordo com a página atual
    private void Atualizar() {
        try {

            File file = new File(pathArquivo);

            //Abre o arquivo
            PdfRenderer renderer = new PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY));

            //Pega a pagina atual
            PdfRenderer.Page pag = renderer.openPage(paginaAtual);

            //Seta o total de páginas
            totalPaginas = renderer.getPageCount();

            //Cria um bitmap do tamanho da pagina
            Bitmap bitmap = Bitmap.createBitmap(pag.getWidth() * 2, pag.getHeight() *2, Bitmap.Config.ARGB_8888);

            //Passa a página para a imagem
            pag.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_PRINT);
            //bitmap.reconfigure(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);

            //bitmap.reconfigure(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            //Armazena o bitmap da página

            imagem = tirarTransparencia(bitmap);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Bitmap tirarTransparencia(Bitmap bitmap)
    {
        for(int i = 0;i < bitmap.getWidth(); i++)
        {
            for(int j = 0;j < bitmap.getHeight(); j++) {
                int pixel = bitmap.getPixel(i, j);
                bitmap.setPixel(i, j, Color.rgb(Color.red(pixel), Color.green(pixel), Color.blue(pixel)));
                if (Color.alpha(pixel) < 60) {
                    bitmap.setPixel(i, j, Color.WHITE);
                }
            }
        }
        return bitmap;
    }
    //Muda para a proxima pagina
    public void ProximaPagina()
    {
        if (paginaAtual < totalPaginas)
        {
            paginaAtual++;
            Atualizar();
        }
    }

    //Volta uma página
    public void VoltarPagina()
    {
        if (paginaAtual > 0) {
            paginaAtual--;
            Atualizar();
        }
    }

    //Retorna a imagem da página para ser analisada pelo leitor
    public Bitmap getImagem() {
        return imagem;
    }

    //Retorna a página atual
    public int getPaginaAtual() {
        return paginaAtual;
    }

    //Retorna o número total de páginas
    public int getTotalPaginas() {
        return totalPaginas;
    }


    public void setPaginaAtual(int paginaAtual) {
        if(paginaAtual >-1 && paginaAtual < totalPaginas)
            this.paginaAtual = paginaAtual;
    }
}
