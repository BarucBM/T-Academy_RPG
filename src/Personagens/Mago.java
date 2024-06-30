package Personagens;

import java.util.Scanner;

public class Mago extends Personagem{
    protected int pontosMagiaMax;
    protected int pontosMagiaAtual;

    public Mago(String nome, double HPMax, double defesa, double ataque, double destreza, double forca, int pontosMagiaMax) {
        super(nome, HPMax, defesa, ataque, destreza, forca);
        this.pontosMagiaMax = pontosMagiaMax;
        this.pontosMagiaAtual = pontosMagiaMax;
    }

    public int getPontosMagiaMax() {
        return pontosMagiaMax;
    }

    @Override
    public void atacar(Personagem inimigo){
        Scanner input = new Scanner(System.in);
        System.out.println("Usar pontos de magia para aumentar o dano? \nAté 5.");

        double dano = this.Ataque + (Math.min(input.nextInt(), 5)) - inimigo.getDefesa();
        if(dano>0){
            inimigo.setHpAtual(inimigo.getHpAtual() - dano);
            System.out.println(this.getNome() + " causou " + dano + " à " + inimigo.getNome());
        }else {
            System.out.println("Nenhum dano causado!!!");
        }
    }

    @Override
    public void imprimir(){
        System.out.println(
                "Nome: " + this.getNome() + "\n" +
                "Vida:" + this.getHpAtual() + "\n" +
                "Defesa: " + this.getDefesa() + "\n" +
                "Ataque: " + this.getAtaque() + "\n" +
                "Destreza: " + this.getDestreza() + "\n" +
                "Força: " + this.getForca() + "\n" +
                "Pontos de magia: " + this.getPontosMagiaMax()
        );
    }
}
