package Personagens;

public class Arqueiro extends Personagem{
    protected int mira;

    public Arqueiro(String nome, double HPMax, double defesa, double ataque, double destreza, double forca, int mira) {
        super(nome, HPMax, defesa, ataque, destreza, forca);
        this.mira = mira;
    }

    public int getMira() {
        return mira;
    }
    public void setMira(int mira) {
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

    @Override
    public void imprimir(){
        System.out.println(
                "Nome: " + this.getNome() + "\n" +
                        "Vida:" + this.getHpAtual() + "\n" +
                        "Defesa: " + this.getDefesa() + "\n" +
                        "Ataque: " + this.getAtaque() + "\n" +
                        "Destreza: " + this.getDestreza() + "\n" +
                        "Força: " + this.getForca() + "\n" +
                        "Mira: " + this.getMira()
        );
    }

    @Override
    public void aumentaNivel(int nivel){
        switch (nivel) {
            case 1:
                this.setHpMAx(this.getHpMAx() + 5);
                this.setAtaque(this.getAtaque() + 5);
                this.setDefesa(this.getDefesa() + 5);
                this.setDestreza(this.getDestreza() + 5);
                this.setForca(this.getForca() + 5);
                this.setMira(this.getMira() + 5);
                break;

            case 2:
                this.setHpMAx(this.getHpMAx() + 10);
                this.setAtaque(this.getAtaque() + 10);
                this.setDefesa(this.getDefesa() + 10);
                this.setDestreza(this.getDestreza() + 10);
                this.setForca(this.getForca() + 10);
                this.setMira(this.getMira() + 10);
                break;

            case 3:
                this.setHpMAx(this.getHpMAx() + 15);
                this.setAtaque(this.getAtaque() + 15);
                this.setDefesa(this.getDefesa() + 15);
                this.setDestreza(this.getDestreza() + 15);
                this.setForca(this.getForca() + 15);
                this.setMira(this.getMira() + 15);
                break;


        }
    }
}

