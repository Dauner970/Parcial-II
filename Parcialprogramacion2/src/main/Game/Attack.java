package main.Game;

import exceptions.AttackMissedException;
import java.util.Random;

public class Attack {
    private final String name;
    private final int baseDamage;
    private final double accuracy;
    private final DamageRule damageRule;

    public Attack(String name, int baseDamage, double accuracy, DamageRule damageRule) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.accuracy = accuracy;
        this.damageRule = damageRule;
    }

    public String getName() {
        return name;
    }

    public int execute() throws AttackMissedException {
        if (new Random().nextDouble() > accuracy) {
            throw new AttackMissedException("¡El ataque " + name + " falló!");
        }
        return damageRule.calculate(baseDamage);
    }
}