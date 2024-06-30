import Jogo.Batalha;
import Personagens.Barbaro;
import Personagens.Inimigo;

public class Main {
    public static void main(String[] args) {
        Barbaro B = new Barbaro("B",100, 50,50,15, 11);
        Barbaro C = new Barbaro("C",100, 50,50,10, 11);
        Barbaro A = new Barbaro("A",100, 50,50,50, 11);
        Inimigo D = new Inimigo("D", 50, 50,50, 20, 100, "Boss");
        Inimigo E = new Inimigo("E", 50, 10,10, 20,100, "Capanga");
        Batalha Battle = new Batalha();
        Battle.addAliado(B);
        Battle.addAliado(A);
        Battle.addAliado(C);
        Battle.addInimigo(E);



        Battle.rodada(A);
        }
}