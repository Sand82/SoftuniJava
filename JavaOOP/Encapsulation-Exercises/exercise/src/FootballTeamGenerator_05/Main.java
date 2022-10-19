package FootballTeamGenerator_05;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Team> teams = new LinkedHashMap<>();

        String input = scanner.nextLine();

        while (!input.equals("END")) {

            String[] tokens = input.split(";");

            String command = tokens[0];
            String teamName = tokens[1];

            Team currentTeam = null;
            String playerName = "";

            try {
                switch (command) {
                    case "Team":

                        Team team = new Team(teamName);
                        teams.putIfAbsent(teamName, team);
                        break;
                    case "Add":

                        playerName = tokens[2];
                        int endurance = Integer.parseInt(tokens[3]);
                        int sprint = Integer.parseInt(tokens[4]);
                        int dribble = Integer.parseInt(tokens[5]);
                        int passing = Integer.parseInt(tokens[6]);
                        int shooting = Integer.parseInt(tokens[7]);

                        if (!teams.containsKey(teamName)) {

                            System.out.println(String.format("Team %s does not exist.", teamName));
                        } else {

                            Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                            teams.get(teamName).addPlayer(player);
                        }
                        break;
                    case "Remove":

                        playerName = tokens[2];
                        teams.get(teamName).removePlayer(playerName);
                        break;
                    case "Rating":

                        if (!teams.containsKey(teamName)) {
                            System.out.println(String.format("Team %s does not exist.", teamName));
                        } else {
                            int result = (int) Math.round(teams.get(teamName).getRating());
                            System.out.println(String.format("%s - %d", teamName, result));
                            break;
                        }
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            input = scanner.nextLine();
        }

        System.out.println();
    }
}
