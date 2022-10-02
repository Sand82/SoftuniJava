import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");

        List<Trainer> trainers = new ArrayList<>();

        while (!input[0].equals("Tournament")) {

            String trainerName = input[0].trim();
            String pokemonName = input[1].trim();
            String pokemonElement = input[2].trim();
            int pokemonHealth = Integer.parseInt(input[3].trim());

            Trainer trainer = trainers.stream()
                    .filter(x -> x.getName().equals(trainerName))
                    .findFirst().orElse(null);

            if (trainer == null) {
                trainer = new Trainer(trainerName);
                trainers.add(trainer);
            }

            Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);

            trainer.addPokemon(pokemon);

            input = scanner.nextLine().split(" ");
        }

        String command = scanner.nextLine();

        while (!command.equals("End")) {

            String pokemonSkill = command;
            setListPokemons(pokemonSkill, trainers);

            command = scanner.nextLine();
        }

        trainers = trainers.stream()
                .sorted(Comparator.comparingInt(Trainer::getBadges)
                        .reversed())
                .collect(Collectors.toList());

        trainers.forEach(t -> System.out.println(t.toString()));
    }

    private static void setListPokemons(String pokemonSkill, List<Trainer> trainers) {

        for (var trainer : trainers) {
            if (trainer.getPokemons().stream()
                    .anyMatch(x -> x.getElement().equals(pokemonSkill.trim()))) {

                int badge = trainer.getBadges() + 1;
                trainer.setBadges(badge);

            } else {

                trainer.getPokemons().stream().forEach(x -> x.setHealth(x.getHealth() - 10));
                var filteredPokemons = sheckForDeadPokemons(trainer.getPokemons());
                trainer.setPokemons(filteredPokemons);
            }
        }
    }

    private static List<Pokemon> sheckForDeadPokemons(List<Pokemon> pokemons) {
        return pokemons.stream()
                .filter(x -> x.getHealth() > 0).collect(Collectors.toList());
    }
}