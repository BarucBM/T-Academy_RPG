package Habilidades;
import Personagens.Personagem;

public class Habilidade {
    protected String nome;
    protected String tipo;
    protected double danoBase;
    protected double curaBase;

    public Habilidade(String nome, String tipo, int danoBase, int curaBase) {
        this.nome = nome;
        this.tipo = tipo;
        this.danoBase = danoBase;
        this.curaBase = curaBase;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCuraBase() {
        return curaBase;
    }

    public double getDanoBase() {
        return danoBase;
    }

    public void Cast(Personagem alvo){
        switch (this.tipo){
            case "Fogo":

        }
    }
}
