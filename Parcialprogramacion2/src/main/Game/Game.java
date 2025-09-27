package main.Game;

import exceptions.AttackMissedException;
import exceptions.InvalidChoiceException;

import java.util.*;

public class Game {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("¡Bienvenido al mundo Pokémon GO!");
        System.out.print("Hola, que emoción. Iniciaremos a jugar. ");

        System.out.print(" Ingresa tu nombre o alias: ");
        String playerName = scanner.nextLine();

        List<Pokemon> pokemons = List.of(new Charmander(), new Squirtle(), new Pikachu());

        // Selección del jugador
        Pokemon playerPokemon = null;
        while (playerPokemon == null) {
            try {
                System.out.println("\n! Es hora de elegir tu Pokémon de preferencia!");
                for (int i = 0; i < pokemons.size(); i++) {
                    System.out.println((i + 1) + ". " + pokemons.get(i).getName());
                }
                int choice = scanner.nextInt();
                if (choice < 1 || choice > pokemons.size()) {
                    throw new InvalidChoiceException("Opción inválida.");
                }
                playerPokemon = pokemons.get(choice - 1);
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next();
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            }
        }

        // CPU elige
        Random random = new Random();
        Pokemon cpuPokemon;
        do {
            cpuPokemon = pokemons.get(random.nextInt(pokemons.size()));
        } while (cpuPokemon.getName().equals(playerPokemon.getName()));

        System.out.println("\n" + playerName + " eligió a " + playerPokemon.getName() + "!");
        System.out.println("La CPU eligió a " + cpuPokemon.getName() + "!");

        // Batalla
        BattleLogger logger = new BattleLogger();

        while (playerPokemon.isAlive() && cpuPokemon.isAlive()) {
            System.out.println("\n--- Turno de " + playerName + " ---");
            for (int i = 0; i < playerPokemon.getAttacks().size(); i++) {
                System.out.println((i + 1) + ". " + playerPokemon.getAttacks().get(i).getName());
            }

            int attackChoice = -1;
            try {
                attackChoice = scanner.nextInt();
                if (attackChoice < 1 || attackChoice > playerPokemon.getAttacks().size()) {
                    throw new InvalidChoiceException("Opción inválida.");
                }
                Attack attack = playerPokemon.getAttacks().get(attackChoice - 1);
                try {
                    int damage = attack.execute();
                    cpuPokemon.receiveDamage(damage);
                    logger.logEvent(playerPokemon.getName() + " usó " + attack.getName() + " e hizo " + damage + " de daño.");
                    logger.logDamage(damage);
                } catch (AttackMissedException e) {
                    System.out.println(e.getMessage());
                    logger.logEvent(playerPokemon.getName() + " falló el ataque.");
                    logger.logMiss(playerName);
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next();
                continue;
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (!cpuPokemon.isAlive()) break;

            System.out.println("\n--- Turno de la CPU ---");
            Attack cpuAttack = cpuPokemon.getAttacks().get(random.nextInt(cpuPokemon.getAttacks().size()));
            try {
                int damage = cpuAttack.execute();
                playerPokemon.receiveDamage(damage);
                logger.logEvent(cpuPokemon.getName() + " usó " + cpuAttack.getName() + " e hizo " + damage + " de daño.");
                logger.logDamage(damage);
            } catch (AttackMissedException e) {
                System.out.println(e.getMessage());
                logger.logEvent(cpuPokemon.getName() + " falló el ataque.");
                logger.logMiss("CPU");
            }

            System.out.println("\nHP de " + playerPokemon.getName() + ": " + playerPokemon.getCurrentHp());
            System.out.println("HP de " + cpuPokemon.getName() + ": " + cpuPokemon.getCurrentHp());
        }

        // Resultado
        if (playerPokemon.isAlive()) {
            System.out.println("\n🎉 ¡" + playerName + " ganó la batalla!");
        } else if (cpuPokemon.isAlive()) {
            System.out.println("\n💀 La CPU ganó la batalla.");
        } else {
            System.out.println("\n🤝 ¡Empate!");
        }

        logger.printSummary();
    }
}
