package com.example.jean.bookreader;

import android.graphics.Bitmap;
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

    private String pathArquivo;

    private int totalPaginas = 0;
    public ModuloPDF(String path)
    {
        pathArquivo = path;
    }
    //Atualiza a imagem de acordo com a página atual
    private void Atualizar() {
        try {

            File file = new File(pathArquivo);
            //Abre o arquivo
            PdfRenderer renderer = new PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY));

            //Pega a pagina atual
            PdfRenderer.Page pag = renderer.openPage(paginaAtual);

            //Cria um bitmap do tamanho da pagina
            Bitmap bitmap = Bitmap.createBitmap(pag.getWidth(), pag.getHeight(), Bitmap.Config.ARGB_4444);

            //Passa a página para a imagem
            pag.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        } catch (Exception e) {
            e.printStackTrace();
        }
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


}
