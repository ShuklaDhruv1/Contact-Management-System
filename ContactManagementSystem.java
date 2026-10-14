
import java.util.ArrayList;
import java.util.Scanner;

public class ContactManagementSystem {

    static ArrayList<Contact> contacts =
            ContactFileHandler.loadContacts();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Contacts loaded: " + contacts.size());

        while (true) {

            System.out.println("\n================================");
            System.out.println("    CONTACT MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addContact();
                    break;

                case 2:
                    viewContacts();
                    break;

                case 3:
                    searchContact();
                    break;

                case 4:
                    updateContact();
                    break;

                case 5:
                    deleteContact();
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void addContact() {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        contacts.add(new Contact(name, phone, email));

        ContactFileHandler.saveContacts(contacts);
    }

    public static void viewContacts() {

        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        for (Contact contact : contacts) {
            contact.displayContact();
        }
    }

    public static void searchContact() {

        System.out.print("Enter name to search: ");
        String name = sc.nextLine();

        for (Contact contact : contacts) {

            if (contact.getName().equalsIgnoreCase(name)) {
                contact.displayContact();
                return;
            }
        }

        System.out.println("Contact not found!");
    }

    public static void updateContact() {

        System.out.print("Enter name to update: ");
        String name = sc.nextLine();

        for (Contact contact : contacts) {

            if (contact.getName().equalsIgnoreCase(name)) {

                System.out.print("Enter new phone: ");
                contact.setPhone(sc.nextLine());

                System.out.print("Enter new email: ");
                contact.setEmail(sc.nextLine());

                ContactFileHandler.saveContacts(contacts);
                return;
            }
        }

        System.out.println("Contact not found!");
    }

    public static void deleteContact() {

        System.out.print("Enter name to delete: ");
        String name = sc.nextLine();

        for (int i = 0; i < contacts.size(); i++) {

            if (contacts.get(i).getName().equalsIgnoreCase(name)) {

                contacts.remove(i);

                ContactFileHandler.saveContacts(contacts);
                return;
            }
        }

        System.out.println("Contact not found!");
    }
}
