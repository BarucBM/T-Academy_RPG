package Loot;
import java.util.*;

public class Recompensa {
    protected Item item;
    protected int exp;

    public Recompensa() {}

    public void setDrop(String tipo){
        Random random = new Random();
        switch (tipo){
            case "Monstro":
                int aux = random.nextInt(50-5)+5;

            case "Boss":
                aux = random.nextInt(100-50)+50;
        }
    }
    public Item getItem() {
        return item;
    }

    public int getExp() {
        return exp;
    }


}
