package MilitaryElite_06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Private> privates = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Solder> militaryElite = new ArrayList<>();

        String input = scanner.nextLine();

        while (!input.equals("End")) {

            String[] inputInfo = input.split(" ");

            Solder solder = createSolder(inputInfo);
            militaryElite.add(solder);

            input = scanner.nextLine();
        }

        for (Solder solder : militaryElite) {

            System.out.println(solder);

        }
    }

    private static Solder createSolder(String[] inputInfo) { // Private 1 Peter Petrov 22.22

        String solderType = inputInfo[0];
        int id = Integer.parseInt(inputInfo[1]);
        String firstName = inputInfo[2];
        String lastName = inputInfo[3];

        double salary = 0.0;

        if (!solderType.equals("Spy")) {
            salary = Double.parseDouble(inputInfo[4]);
        }

        Solder currentSolder = null;

        switch (solderType) {
            case "Private":

                Private priv = new PrivateImpl(firstName, lastName, id, salary);
                privates.add(priv);

                currentSolder = priv;
                break;
            case "LieutenantGeneral":

                Solder LieutenantGeneral = new LieutenantGeneralImpl(firstName, lastName, id, salary, privates);

                currentSolder = LieutenantGeneral;
                break;
            case "Engineer":

                Corps corps = Corps.valueOf(inputInfo[5]);

                EngineerImpl engineer = new EngineerImpl(firstName, lastName, id, salary, corps);

                for (int i = 6; i < inputInfo.length; i += 2) {

                    String partName = inputInfo[i];
                    int hoursWorked = Integer.parseInt(inputInfo[i + 1]);

                    Repair repair = new Repair(partName, hoursWorked);

                    engineer.addRepair(repair);
                }

                currentSolder = engineer;
                break;
            case "Commando":

                Corps commandoCorps = Corps.valueOf(inputInfo[5]);

                CommandoImpl commando = new CommandoImpl(firstName, lastName, id, salary, commandoCorps);
                currentSolder = commando;

                for (int i = 6; i < inputInfo.length; i += 2) {
                    String missionName = inputInfo[i];
                    State state = State.valueOf(inputInfo[i + 1]);

                    Mission mission = new Mission(state, missionName);
                    commando.addMission(mission);
                }
                break;
            case "Spy":

                String codeNumber = inputInfo[4];
                SpyImpl spy = new SpyImpl(firstName, lastName, id, codeNumber);

                currentSolder = spy;
                break;
        }

        return currentSolder;
    }
}
