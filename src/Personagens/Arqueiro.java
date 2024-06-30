package Personagens;

public class Arqueiro extends Personagem{
    protected int mira;

    public Arqueiro(String nome, double HPMax, double defesa, double ataque, double destreza, double forca, int mira) {
        super(nome, HPMax, defesa, ataque, destreza, forca);
        this.mira = mira;
    }

    @Override
    public void atacar(Personagem inimigo){
        double dano = this.Ataque + this.mira -inimigo.getDefesa();
        if(dano>0){
            inimigo.setHpAtual(inimigo.getHpAtual() - dano);
            System.out.println(this.getNome() + " causou " + dano + " à " + inimigo.getNome());
        }else {
            System.out.println("Nenhum dano causado!!!");
        }
    }
}
