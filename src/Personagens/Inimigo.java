package Personagens;

import Loot.*;

import java.util.Random;

public class Inimigo extends Personagem{
    protected String tipo;
    protected Recompensa recompensa;

    public Inimigo(String nome, double HPMax, double defesa, double ataque, double destreza, double forca, String tipo) {
        super(nome, HPMax, defesa, ataque, destreza, forca);
        this.tipo = tipo;
        this.recompensa.setDrop(tipo);
    }

    public String getTipo() {
        return tipo;
    }
    public String getDrop() {return recompensa.getItem().getNome();}
    public int getExp() {return recompensa.getExp();}

    @Override
    public boolean acao(){
        Random random = new Random();
        boolean aux = false;
        if (random.nextInt(4) == 0){
           return aux;
        }else {
            aux = true;
            return aux;
        }
    }


}
