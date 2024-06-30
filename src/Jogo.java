import Jogo.Batalha;
import Personagens.Barbaro;
import Personagens.Inimigo;
import Personagens.Personagem;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Batalha batalha = new Batalha();
        System.out.println("Nova batalha iniciada!");

        //MENU 1 - Criar Aliados________________________________________________________________________
        boolean aux1 = true;
        while(aux1) {

            System.out.println(
                    "Criar aliados: \n" +
                            "1 - Barbaro.\n" +
                            "2 - Mago.\n" +
                            "3 - Arqueiro.\n" +
                            "4 - Próxima etapa.\n");
            int menu1 = scanner.nextInt();
            if (menu1 < 4 && menu1 > 0) {
                batalha.criarAliado(menu1);
            } else if (menu1 == 4) {
                aux1 = false;
            }else{
                System.out.println("Valor inválido, digite outro número do menu!");
            }

        }

        //MENU 2 - Criari Habilidades________________________________________________________________________
        boolean aux4 = true;
        while(aux4){
            System.out.println(
                    "Criar habilidades: \n" +
                            "1 - Nova habilidade.\n" +
                            "2 - Próxima etapa.\n");
            int scan = scanner.nextInt();
            if(scan == 1){
                int count = 0;
                System.out.println("Escolha o aliado:");
                for (Personagem alvo : batalha.getAliados()) {
                    count++;
                    System.out.println(count + " " + alvo.getNome());
                }
                batalha.criarHabilidade(batalha.getAliados().get(scanner.nextInt()-1));
            } else if (scan == 2) {
                aux4 = false;
            }
        }

        //MENU 3 - Criar Inimigos________________________________________________________________________
        boolean aux2 = true;
        while(aux2) {

            System.out.println(
                    "Criar Inimigos: \n" +
                            "1 - Novo Inimigo.\n" +
                            "2 - Próxima Etapa.\n");
            int menu1 = scanner.nextInt();
            if (menu1 == 1) {
                batalha.criarInimigo();
            } else if (menu1 == 2) {
                aux2 = false;
            }else{
                System.out.println("Valor inválido, digite outro número do menu!");
            }
        }

        //Inicio da batalha ___________________________________________________________________________
        boolean aux3 = true;
        ArrayList<Personagem> combatentes = batalha.OrdenaCombatentes(batalha.getAliados(), batalha.getInimigos());
        while (aux3){
            for(Personagem personagem : combatentes){
               if(batalha.rodada(personagem)){
                   aux3 = false;
                   break;
               }
            }

        }
    }
}
