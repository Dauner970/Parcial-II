package main.Game;

public class Squirtle extends Pokemon {
    public Squirtle() {
        super("Squirtle", 110);
        initializeAttacks();
    }

    @Override
    public void initializeAttacks() {
        attacks.add(new Attack("Placaje", 10, 0.9, dmg -> dmg));
        attacks.add(new Attack("Pistola Agua", 15, 0.85, dmg -> dmg + 5));
        attacks.add(new Attack("Hidrobomba", 30, 0.6, dmg -> dmg + 15));
    }
}
