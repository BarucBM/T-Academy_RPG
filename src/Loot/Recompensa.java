package Loot;
import java.util.*;

public class Recompensa {
    protected Item item;
    protected int exp;

    public Recompensa() {}

    public int setDrop(String tipo){
        Random random = new Random();
        int aux = 0;
        switch (tipo){
            case "Monstro":
                aux = random.nextInt(50-5)+5;

            case "Boss":
                aux = random.nextInt(100-50)+50;
        }
        return aux;
    }
    public Item getItem() {
        return item;
    }

    public int getExp() {
        return exp;
    }


}
