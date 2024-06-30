package Jogo;

import Habilidades.Habilidade;
import Personagens.Barbaro;
import Personagens.Inimigo;
import Personagens.Mago;
import Personagens.Personagem;
import Loot.Item;

import java.util.*;

public class Batalha {
    protected ArrayList<Personagem> Aliados;
    protected ArrayList<Personagem> Inimigos;
    protected ArrayList<Personagem> vetor;
    protected ArrayList<Personagem> inimigosVivos;
    protected ArrayList<Personagem> aliadosVivos;
    protected ArrayList<Personagem> combatentesVivos;
    protected Scanner input;

    //Construtor ________________________________________________________________________
    public Batalha() {
        this.Aliados = new ArrayList<>();
        this.Inimigos = new ArrayList<>();
        this.vetor = new ArrayList<>();
        this.inimigosVivos = new ArrayList<>();
        this.aliadosVivos = new ArrayList<>();
        this.combatentesVivos = new ArrayList<>();
        this.input = new Scanner(System.in);;
    }

    //Criação dos Aliados, inimigos e habilidades________________________________________________________________________
    public void criarAliado(int aux){

        System.out.println("Nome do personagem:");
        String nome = input.nextLine();
        if (Objects.equals(nome, "")){
            nome = input.nextLine();
        }
        System.out.println("Vida máxima:");
        int HpMAx1 = input.nextInt();
        System.out.println("Defesa:");
        int Defesa1 = input.nextInt();
        System.out.println("Ataque:");
        int Ataque1 = input.nextInt();
        System.out.println("Destreza:");
        int Destreza1 = input.nextInt();
        System.out.println("Força:");
        int Forca1 = input.nextInt();

        switch (aux){
            case 1:
                this.addAliado(new Barbaro(nome, HpMAx1, Defesa1, Ataque1, Destreza1, Forca1));

                break;

            case 2:
                System.out.println("Pontos de magia:");
                int pontosMa = input.nextInt();
                this.addAliado(new Mago(nome, HpMAx1, Defesa1, Ataque1, Destreza1, Forca1, pontosMa));

                break;

            case 3:
                System.out.println("Mira:");
                int mira = input.nextInt();
                this.addAliado(new Mago(nome, HpMAx1, Defesa1, Ataque1, Destreza1, Forca1, mira));

                break;
        }
    }
    public void criarInimigo(){

        System.out.println("Nome do personagem:");
        String nome = input.nextLine();
        if (Objects.equals(nome, "")){
            nome = input.nextLine();
        }
        System.out.println("Vida máxima:");
        int HpMAx1 = input.nextInt();
        System.out.println("Defesa:");
        int Defesa1 = input.nextInt();
        System.out.println("Ataque:");
        int Ataque1 = input.nextInt();
        System.out.println("Destreza:");
        int Destreza1 = input.nextInt();
        System.out.println("Força:");
        int Forca1 = input.nextInt();
        System.out.println("Tipo:");
        String Tipo1 = input.nextLine();
        String Tipo2 = input.nextLine();

        this.addInimigo(new Inimigo(nome, HpMAx1, Defesa1, Ataque1, Destreza1, Forca1, Tipo2));

    }
    public void criarHabilidade(Personagem personagem){
        System.out.println("Nome:");
        String nome = input.nextLine();
        if (Objects.equals(nome, "")){
            nome = input.nextLine();
        }
        System.out.println("Tipo:");
        String tipo = input.nextLine();
        int cura = 0, dano = 0;
        if(tipo.equalsIgnoreCase("Cura")){
            System.out.println("Cura:");
            cura = input.nextInt();
        }else{
            System.out.println("Dano:");
            dano = input.nextInt();
        }
        personagem.addHabilidades(new Habilidade(nome, tipo, dano, cura));
    }

    //Manipulação de dados de aliados e Inimigos________________________________________________________________________
    public ArrayList<Personagem> getAliados() {
        return Aliados;
    }
    public ArrayList<Personagem> getInimigos() {
        return Inimigos;
    }
    public void addAliado(Personagem aux){
        Aliados.add(aux);
        aux.imprimir();
    }
    public void removeAliado(Personagem aux){
        Aliados.remove(aux);
    }
    public void addInimigo(Personagem aux){
        Inimigos.add(aux);
        aux.imprimir();
    }
    public void removeInimigo(Personagem aux){
        Inimigos.remove(aux);
    }

    //Ordena os combatentes nos arrays por ordem de Destreza (BubbleSort).________________________________________________________________________
    public ArrayList<Personagem> OrdenaCombatentes(ArrayList<Personagem> aliados, ArrayList<Personagem> inimigos){
        vetor.addAll(aliados);
        vetor.addAll(inimigos);

        boolean troca = true;
        Personagem aux;
        while (troca) {
            troca = false;
            for (int i = 0; i < vetor.size() - 1; i++) {
                if (vetor.get(i).getDestreza() < vetor.get(i+1).getDestreza()) {
                    aux = vetor.get(i);
                    vetor.set(i, vetor.get(i+1));
                    vetor.set(i+1, aux);
                    troca = true;

                }
            }
        }
        return vetor;

    }

    //Adiciona os Inimigos e Aliados que estão vivos em uma Array distinta.________________________________________________________________________
    public void InimigosVivos(){
        inimigosVivos.clear();
        for (Personagem inimigo: Inimigos){
            if(inimigo.getHpAtual()>0){
                inimigosVivos.add(inimigo);
            }
        }
    }
    public void AliadosVivos(){
        aliadosVivos.clear();
        for (Personagem aliado: Aliados){
            if(aliado.getHpAtual()>0){
                aliadosVivos.add(aliado);
            }
        }
    }

    //Verifica existência de um Objeto na array________________________________________________________________________________________________________________________________________________
    public boolean VerificaLista(ArrayList<Personagem> lista, Object personagem){
        boolean aux = false;
        for (Personagem personagem1 : lista){
            if(personagem1.equals(personagem)){
                aux = true;
                break;
            }
        }
        return aux;
    }

    //Método de cada rodada________________________________________________________________________________________________________________________________________________
    public boolean rodada(Personagem personagem) {
        //Inicia variáveis e faz verificação de condições dos personagens
        boolean test = false;
        AliadosVivos();
        InimigosVivos();
        if(aliadosVivos.isEmpty()){
            test = true;
            System.out.println("Todos os aliados morreram! Você perdeu!");
            return test;
        } else if (inimigosVivos.isEmpty()) {
            test = true;
            System.out.println("Todos os inimigos morreram! Você venceu!");
            return test;
        }
        combatentesVivos.clear();
        combatentesVivos.addAll(aliadosVivos);
        combatentesVivos.addAll(inimigosVivos);

        //Inicia os menus de ações dos personagens
        //Caso ele seja um aliado leva à um menu, caso seja um inimigo realiza uma ação aleatória
        if(!personagem.verificaCondicoes() && personagem.isAlive()){
            if (VerificaLista(aliadosVivos, personagem)) {
                System.out.println("Turno do " + personagem.getNome() + ": \n   " +
                        "1 - Atacar\n   " +
                        "2 - Defender \n   " +
                        "3 - Usar Habilidade \n   " +
                        "4 - Usar item \n   " +
                        "5 - Fugir \n   ");
                //Switch para cada ação do Aliado
                switch (input.nextInt()) {
                    case 1:
                        try {
                            if (inimigosVivos.size() > 1) {
                                System.out.println("Escolha um alvo:");
                                int aux = 0;
                                for (Personagem alvo : inimigosVivos) {
                                    aux++;
                                    System.out.println(aux + " " + alvo.getNome());
                                }
                                personagem.atacar(inimigosVivos.get(input.nextInt() - 1));
                            } else {
                                personagem.atacar(inimigosVivos.get(0));
                            }

                            break;
                        } catch (Exception e) {
                            System.out.println("Não existem inimigos vivos!");
                        }


                    case 2:
                        personagem.defender();
                        break;

                    case 3:
                        try {
                            int aux = 0;
                            for (Habilidade habilidade : personagem.getHabilidades()) {
                                aux++;
                                System.out.println(aux + " " + habilidade.getNome());
                            }
                            int habilidade = input.nextInt();

                            System.out.println("Escolha um alvo:");
                            int counter = 0;
                            for (Personagem alvo : combatentesVivos) {
                                counter++;
                                System.out.println(counter + " " + alvo.getNome());
                            }
                            personagem.usarHabilidade(personagem.getHabilidades().get(habilidade - 1), combatentesVivos.get(input.nextInt() - 1));
                        } catch (Exception e) {
                            System.out.println(personagem.getNome() + " não possui habilidades!");
                        }
                        break;

                    case 4:
                        try {
                            int aux = 0;
                            for (Item item : personagem.getItens()) {
                                aux++;
                                System.out.println(aux + " " + item.getNome());
                            }
                        } catch (Exception e) {
                            System.out.println(personagem.getNome() + " não possui itens!");
                        }
                        break;

                    case 5:
                        boolean aux = personagem.testeDestreza(11);
                        if (aux) {
                            System.out.println(personagem.getNome() + " fugiu!");

                        } else {
                            System.out.println(personagem.getNome() + " não conseguiu fugir!");
                        }
                        break;
                }
            }else{
               if(personagem.acao()){
                   Random random = new Random();
                   personagem.atacar(aliadosVivos.get(random.nextInt(aliadosVivos.size())));
               }else{
                   personagem.defender();
               }
            }

        }
        return test;

    }
}
