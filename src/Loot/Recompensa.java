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
                this.exp = random.nextInt(50-5)+5;
                break;

            case "Boss":
                this.exp = random.nextInt(100-50)+50;
                break;
        }

    }
    public Item getItem() {
        return item;
    }

    public int getExp() {
        return exp;
    }


}
