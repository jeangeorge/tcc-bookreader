package com.example.jean.bookreader;

import java.util.List;

/**
 * Created by Gabriel on 20/06/2017.
 */
class Bloco
{
    int x1, y1, x2, y2;
    String texto;
    public Bloco()
    {

    }
}

public class Pagina {
    List<Bloco> blocos;
    int numero;

    public void AddBloco()
    {
        blocos.add(new Bloco());
    }

    private void Ordenar()
    {

    }

    public void Salvar()
    {

    }

}
