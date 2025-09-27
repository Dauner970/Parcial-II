package main.Game;

import java.util.*;
import java.util.stream.Collectors;

public class BattleLogger {
    private final List<String> events = new ArrayList<>();
    private final List<Integer> damages = new ArrayList<>();
    private final Map<String, Integer> misses = new HashMap<>();

    public void logEvent(String event) {
        events.add(event);
    }

    public void logDamage(int damage) {
        damages.add(damage);
    }

    public void logMiss(String actor) {
        misses.put(actor, misses.getOrDefault(actor, 0) + 1);
    }

    public void printSummary() {
        System.out.println("\n=== Resumen de la batalla ===");
        events.forEach(System.out::println);

        System.out.println("\n📊 Estadísticas:");
        System.out.println("- Total de fallos: " + misses.values().stream().mapToInt(Integer::intValue).sum());
        System.out.println("- Fallos por actor: " + misses);
        System.out.println("- Promedio de daño: " + damages.stream().mapToInt(Integer::intValue).average().orElse(0));

        System.out.println("- Top 3 golpes más fuertes: " +
                damages.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList()));
    }
}