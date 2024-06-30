package Personagens;

public class Barbaro extends Personagem {
    public Barbaro(String nome, double HPMax, double defesa, double ataque, double destreza, double forca) {
        super(nome, HPMax, defesa, ataque, destreza, forca);
    }

    @Override
    public void atacar(Personagem inimigo){
        double dano = this.Ataque + (this.forca/2) -inimigo.getDefesa();
        if(dano>0){
            inimigo.setHpAtual(inimigo.getHpAtual() - dano);
            System.out.println(this.getNome() + " causou " + dano + " à " + inimigo.getNome());
        }else {
            System.out.println("Nenhum dano causado!!!");
        }
    }
}
