
import java.io.*;
import java.util.ArrayList;

public class ContactFileHandler {

    private static final String FILE_NAME = "contacts.txt";

    public static void saveContacts(ArrayList<Contact> contacts) {

        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Contact contact : contacts) {
                writer.write(contact.getName().replace("|", "/") + "|"
                        + contact.getPhone().replace("|", "/") + "|"
                        + contact.getEmail().replace("|", "/"));
                writer.newLine();
            }

            System.out.println("Contacts saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    public static ArrayList<Contact> loadContacts() {

        ArrayList<Contact> contacts = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return contacts;
        }

        try (BufferedReader reader =
                new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length == 3) {
                    contacts.add(new Contact(
                            data[0], data[1], data[2]));
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }

        return contacts;
    }
}
