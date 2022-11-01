package builderPattern;
import java.util.*;

public class Main {

    private static final String CREATE_COMMAND = "CREATE";
    private static final String CONTACT_INFO_COMMAND = "INFO";
    private static final String DELETE_CONTACT_COMMAND = "DELETE";
    private static final String PHONEBOOK_COMMAND = "PHONEBOOK";
    private static final String END_COMMAND = "END";

    public static void main(String[] args) {

        Phonebook phonebook = createContact();

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while(!input.equals("END")) {

            String [] tokens = input.split("\\s+");

            switch (tokens[0]) {
                case "INFO":
                   Contact contactByName = phonebook.getContactByName(tokens[1]);
                    System.out.println(contactByName.toString());
                    break;
                case "DELETE":
                    phonebook.deleteContactByName(tokens[1]);
                    break;
                case "PHONEBOOK":
                    for (var contact: phonebook.getAllContacts()) {
                        System.out.println(contact.toString());
                    }
                    break;
            }

            input = scanner.nextLine();
        }
    }

    private static Phonebook createContact() {

        Phonebook contacts = new Phonebook();

        Contact contact = Contact.builder().whitName("Sand").whitNumber("0888222333").whitEmail("sand@abv.bg").whitBirthday("05.06.1982").build();
        Contact contact1 = Contact.builder().whitName("Mish").whitNumber("08888111222").whitEmail("mish@abv.bg").build();
        Contact contact2 = Contact.builder().whitName("Lub").whitNumber("08888333444").whitEmail("lub@abv.bg").whitWebsite("lub.com").build();
        Contact contact3 = Contact.builder().whitName("Mim").whitNumber("08888444555").whitEmail("mim@abv.bg").whitBirthday("06.0.1992").whitCompany("Teva").build();

        contacts.addContact(contact);
        contacts.addContact(contact1);
        contacts.addContact(contact2);
        contacts.addContact(contact3);

        return contacts;
    }

    private static List<String> readContactInfo(Scanner input) {
        List<String> contactInfo = new ArrayList<>();

        System.out.print("Name: ");
        contactInfo.add(input.nextLine());

        System.out.print("Number: ");
        contactInfo.add(input.nextLine());

        System.out.print("Company: ");
        contactInfo.add(input.nextLine());

        System.out.print("Title: ");
        contactInfo.add(input.nextLine());

        System.out.print("Email: ");
        contactInfo.add(input.nextLine());

        System.out.print("Website: ");
        contactInfo.add(input.nextLine());

        System.out.print("Birthday: ");
        contactInfo.add(input.nextLine());

        return contactInfo;
    }
}
