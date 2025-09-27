package main.Game;


public class Charmander extends Pokemon {
    public Charmander() {
        super("Charmander", 100);
        initializeAttacks();
    }

    @Override
    public void initializeAttacks() {
        attacks.add(new Attack("Arañazo", 10, 0.9, dmg -> dmg));
        attacks.add(new Attack("Ascuas", 15, 0.8, dmg -> dmg + 5));
        attacks.add(new Attack("Lanzallamas", 25, 0.7, dmg -> dmg + 10));
    }
}
