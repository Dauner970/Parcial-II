package main.Game;

@FunctionalInterface
public interface DamageRule {
    int calculate(int baseDamage);
}
