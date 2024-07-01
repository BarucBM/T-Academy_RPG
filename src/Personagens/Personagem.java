package Personagens;

import Habilidades.Habilidade;
import Loot.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Personagem {
    protected String nome;
    protected double hpMAx;
    protected double hpAtual;
    protected double Defesa;
    protected double Ataque;
    protected double forca;
    protected double Destreza;
    protected ArrayList<Habilidade> habilidades;
    protected ArrayList<Item> itens;
    protected Map<String, Integer> condicoes;
    protected int nivel;

    //Construtor de personagem________________________________________________________________________________________________________________________________________________
    public Personagem(String nome, double HPMax, double defesa, double ataque, double destreza, double forca) {
        this.nome = nome;
        this.hpMAx = HPMax;
        this.hpAtual = HPMax;
        this.Defesa = defesa;
        this.Ataque = ataque;
        this.Destreza = destreza;
        this.forca = forca;
        this.habilidades = new ArrayList<>();
        this.itens = new ArrayList<>();
        this.condicoes = new HashMap<>();
    }

    //Getters, Setters e manipuaçãos de arrays dos parâmetros de personagens_________________________________________________________________________________________________
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getHpMAx() {
        return this.hpMAx;
    }
    public void setHpMAx(double hpMAx) {
        this.hpMAx = hpMAx;
    }
    public double getHpAtual(){return this.hpAtual;}
    public void setHpAtual(double HP) {
        this.hpAtual = HP;
    }
    public double getDefesa() {
        return Defesa;
    }
    public void setDefesa(double defesa) {
        Defesa = defesa;
    }
    public double getAtaque() {
        return Ataque;
    }
    public void setAtaque(double ataque) {
        Ataque = ataque;
    }
    public double getDestreza() {
        return Destreza;
    }
    public void setDestreza(double destreza) {
        Destreza = destreza;
    }
    public ArrayList<Habilidade> getHabilidades() {
        return habilidades;
    }
    public void addHabilidades(Habilidade habilidade) {
        this.habilidades.add(habilidade);
    }
    public ArrayList<Item> getItens() {
        return itens;
    }
    public void addItens(Item item) {
        this.itens.add(item);
    }
    public double getForca() {
        return forca;
    }
    public void setForca(double forca) {
        this.forca = forca;
    }
    public void addCondicao(String condicao, Integer duracao) {
        this.condicoes.put(condicao, duracao);
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
        if(this.nivel>=20 && this.nivel<=39){
            this.aumentaNivel(1);
        } else if (this.nivel>=40 && this.nivel<=59) {
            this.aumentaNivel(2);
        } else if (this.nivel>=60) {
            this.aumentaNivel(3);
        }
    }
    public int getExp(){return 0;}

    //Teste de destreza padrão do personagem _______________________________________________________________________________________________________________________________________
    public boolean testeDestreza(int nivel){
        Random random = new Random();
        return random.nextInt(21) + this.Destreza >= nivel;
    }

    //Verificação se o personagem está vivo ________________________________________________________________________________________________________________________________________
    public boolean isAlive(){
        return this.getHpAtual()>0;
    }

    public boolean acao(){return false;}

    //Métodos de ação de um personagem_______________________________________________________________________________________________________________________________________________
    public void atacar(Personagem inimigo){

        double dano = this.Ataque-inimigo.getDefesa();
        if(dano>0){
            inimigo.setHpAtual(inimigo.getHpAtual() - dano);
            System.out.println(inimigo.getHpAtual());
            System.out.println(this.getNome() + " causou " + dano + " à " + inimigo.getNome());
        }else {
            System.out.println("Nenhum dano causado!!!");
        }

    }

    public void defender(){
        this.Defesa = (Defesa * 1.5);
        this.addCondicao("Defendendo", 1);
        System.out.println(this.nome + " está defendendo!");
    }

    public void usarHabilidade(Habilidade habilidade, Personagem alvo){
        switch (habilidade.getTipo()) {
            case "Cura":
                alvo.setHpAtual(Math.min(alvo.getHpAtual() + habilidade.getCuraBase(), alvo.getHpMAx()));
                break;

            case "Fogo":
                alvo.setHpAtual(alvo.getHpAtual() - habilidade.getDanoBase());
                alvo.addCondicao("Queimando", 1);
                System.out.println(alvo.getNome() + " está queimando por uma rodada!");
                break;

            case "Atordoamento":
                alvo.setHpAtual(alvo.getHpAtual() - habilidade.getDanoBase());
                alvo.addCondicao("Atordoado", 1);
                System.out.println(alvo.getNome() + " está atordoado por uma rodada!");
                break;

            case "Sono":
                if(this.testeDestreza(15)){
                    alvo.addCondicao("Dormindo", 2);
                    System.out.println(alvo.getNome() + " está dormindo por duas rodadas!");
                }else{
                    System.out.println("O alvo resistiu à habilidade");
                }
                break;

            case "Envenenamento":
                if(this.testeDestreza(12)){
                    alvo.addCondicao("Envenenado", 2);
                    System.out.println(alvo.getNome() + " está envenenado por duas rodadas!");
                }else{
                    System.out.println("O alvo resistiu à habilidade");
                }
                break;
        }
    }

    //Verifica se o personagem está sob algum efeito, rrealiza o efeito e decrementa todos os contadores________________________________________________________________________
    public boolean verificaCondicoes(){
        boolean aux = false;
        if(condicoes.containsKey("Dormindo") && condicoes.get("Dormindo")>0){
            System.out.println(this.getNome() + " está dormindo e não pode agir!");
            aux = true;
        }
        if(condicoes.containsKey("Atordoado") && condicoes.get("Atordoado")>0){
            System.out.println(this.getNome() + " está dormindo e não pode agir!");
            aux = true;
        }
        if(condicoes.containsKey("Queimando") && condicoes.get("Queimando")>0){
            this.setHpAtual(this.getHpAtual() - 5);
            System.out.println(this.getNome() + " sofreu 5 de dano de queimadura!");
        }
        if(condicoes.containsKey("Envenenado") && condicoes.get("Envenenado")>0){
            this.setHpAtual(this.getHpAtual() - 10);
            System.out.println(this.getNome() + " sofreu 10 de dano de envenenamento!");
        }
        if(condicoes.containsKey("Defendendo") && condicoes.get("Defendendo")>0){
            this.setDefesa(this.getDefesa() / 1.5);
        }
        condicoes.replaceAll((estado, duracao) -> duracao > 0 ? duracao - 1 : 0);

        return  aux;
    }

    public void aumentaNivel(int nivel){
        switch (nivel){
            case 1:
                this.setHpMAx(this.getHpMAx() + 5);
                this.setAtaque(this.getAtaque() + 5);
                this.setDefesa(this.getDefesa() + 5);
                this.setDestreza(this.getDestreza() + 5);
                this.setForca(this.getForca() + 5);
                break;

            case 2:
                this.setHpMAx(this.getHpMAx() + 10);
                this.setAtaque(this.getAtaque() + 10);
                this.setDefesa(this.getDefesa() + 10);
                this.setDestreza(this.getDestreza() + 10);
                this.setForca(this.getForca() + 10);
                break;

            case 3:
                this.setHpMAx(this.getHpMAx() + 15);
                this.setAtaque(this.getAtaque() + 15);
                this.setDefesa(this.getDefesa() + 15);
                this.setDestreza(this.getDestreza() + 15);
                this.setForca(this.getForca() + 15);
                break;

        }
    }

    //Print do personagem_________________________________________________________________________________________________________________________________________________________
    public void imprimir(){
        System.out.println("\n" +
                "Nome: " + this.getNome() + "\n" +
                "Vida:" + this.getHpAtual() + "\n" +
                "Defesa: " + this.getDefesa() + "\n" +
                "Ataque: " + this.getAtaque() + "\n" +
                "Destreza: " + this.getDestreza() + "\n" +
                "Força: " + this.getForca()
        );
    }

}
