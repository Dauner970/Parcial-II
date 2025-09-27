package main.Game;

public class Pikachu extends Pokemon {
    public Pikachu() {
        super("Pikachu", 95);
        initializeAttacks();
    }

    @Override
    public void initializeAttacks() {
        attacks.add(new Attack("Placaje", 10, 0.9, dmg -> dmg));
        attacks.add(new Attack("Impactrueno", 18, 0.8, dmg -> dmg + 6));
        attacks.add(new Attack("Trueno", 30, 0.6, dmg -> dmg + 15));
    }
}
