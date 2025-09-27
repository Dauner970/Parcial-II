package main.Game;

import java.util.ArrayList;
import java.util.List;

public abstract class Pokemon {
    protected String name;
    protected int maxHp;
    protected int currentHp;
    protected List<Attack> attacks;

    public Pokemon(String name, int maxHp) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attacks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public boolean isAlive() {
        return currentHp > 0;
    }

    public void receiveDamage(int damage) {
        currentHp = Math.max(currentHp - damage, 0);
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public abstract void initializeAttacks();
}