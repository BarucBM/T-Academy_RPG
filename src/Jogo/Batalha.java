package Jogo;

import Habilidades.Habilidade;
import Personagens.Personagem;
import Loot.Item;

import java.util.*;

public class Batalha {
    protected ArrayList<Personagem> Aliados;
    protected ArrayList<Personagem> Inimigos;
    protected ArrayList<Personagem> vetor;
    protected Scanner input;
    protected ArrayList<Personagem> inimigosVivos;
    protected ArrayList<Personagem> aliadosVivos;
    protected ArrayList<Personagem> combatentesVivos;

    public Batalha() {
        this.Aliados = new ArrayList<>();
        this.Inimigos = new ArrayList<>();
        this.vetor = new ArrayList<>();
        this.inimigosVivos = new ArrayList<>();
        this.aliadosVivos = new ArrayList<>();
        this.combatentesVivos = new ArrayList<>();
        this.input = new Scanner(System.in);

    }

    public void addAliado(Personagem aux){
        Aliados.add(aux);
    }
    public void removeAliado(Personagem aux){
        Aliados.remove(aux);
    }
    public void addInimigo(Personagem aux){
        Inimigos.add(aux);
    }
    public void removeInimigo(Personagem aux){
        Inimigos.remove(aux);
    }

    //Ordena os combatentes nos arrays por ordem de Destreza (BubbleSort).
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

    //Adiciona os Inimigos e Aliados que estão vivos em uma Array separada.
    public void InimigosVivos(){
        for (Personagem inimigo: Inimigos){
            if(inimigo.getHpAtual()>0){
                inimigosVivos.add(inimigo);
            }
        }
    }
    public void AliadosVivos(){
        for (Personagem aliado: Aliados){
            if(aliado.getHpAtual()>0){
                aliadosVivos.add(aliado);
            }
        }
    }


    public boolean VerificaLista(ArrayList<Personagem> lista, Personagem personagem){
        boolean aux = false;
        for (Personagem personagem1 : lista){
            if(personagem1.equals(personagem)){
                aux = true;
                break;
            }
        }
        return aux;
    }

    public void rodada(Personagem personagem) {
        AliadosVivos();
        InimigosVivos();

        combatentesVivos.addAll(aliadosVivos);
        combatentesVivos.addAll(inimigosVivos);

        if(!personagem.verificaCondicoes() && personagem.isAlive()){
            if (VerificaLista(aliadosVivos, personagem)) {
                System.out.println("Turno do " + personagem.getNome() + ": \n   " +
                        "1 - Atacar\n   " +
                        "2 - Defender \n   " +
                        "3 - Usar Habilidade \n   " +
                        "4 - Usar item \n   " +
                        "5 - Fugir \n   ");
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
                            int aux1 = 0;
                            for (Personagem alvo : combatentesVivos) {
                                aux++;
                                System.out.println(aux + " " + alvo.getNome());
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
                   personagem.atacar(aliadosVivos.get(random.nextInt(aliadosVivos.size()-1)));
               }else{
                   personagem.defender();
               }
            }

        }

    }
}
