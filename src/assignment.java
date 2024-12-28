import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// InventoryOperation interface (Abstraction and Polymorphism)
interface InventoryOperation {
    void add(Scanner input);
    void delete(Scanner input);
    void update(Scanner input);
    void display();
}

// Item class (Encapsulation and Inheritance)
abstract class Item {
    private String id;
    private String name;
    private int quantity;
    private double price;

    public Item(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract void displayItemDetails(); // Abstract method to be implemented in subclasses
}

class Inventory implements InventoryOperation {
    private List<Item> items = new ArrayList<>();

    public void menu() {
        System.out.println("\nInventory Management System Menu");
        System.out.println("----------------------------------");
        System.out.println("1. Add Item.");
        System.out.println("2. Delete Item");
        System.out.println("3. Update Item.");
        System.out.println("4. Display Items.");
        System.out.println("5. Exit");
        System.out.print("Enter Your Choice from Menu Items: \n=> ");
    }

    @Override
    public void add(Scanner input) {
        System.out.print("Enter item ID: \n=> ");
        String id = input.nextLine();
        System.out.print("Enter item name: \n=> ");
        String name = input.nextLine();
        System.out.print("Enter item quantity: \n=> ");
        int quantity = input.nextInt();
        System.out.print("Enter item price: \n=>");
        double price = input.nextDouble();
        input.nextLine();

        Item newItem = new Item(id, name, quantity, price) {
            @Override
            public void displayItemDetails() {
                System.out.printf("ID: %-10s Name: %-20s Quantity: %-10d Price: %-10.2f\n", getId(), getName(), getQuantity(), getPrice());
            }
        };

        items.add(newItem);
        System.out.println("The Item added successfully.");
    }

    @Override
    public void delete(Scanner input) {
        System.out.print("\nEnter item ID to delete: ");
        String delId = input.nextLine();
        items.removeIf(item -> item.getId().equals(delId));
        System.out.println("\nItem deleted.");
    }

    @Override
    public void update(Scanner input) {
        System.out.print("\nEnter item ID to update: ");
        String itemId = input.nextLine();
        for (Item item : items) {
            if (item.getId().equals(itemId)) {
                System.out.print("\nEnter item name: \n=> ");
                item.setName(input.nextLine());
                System.out.print("Enter new quantity: \n=> ");
                item.setQuantity(input.nextInt());
                System.out.print("Enter new price: \n=> ");
                item.setPrice(input.nextDouble());
                input.nextLine();
                System.out.println("Item updated successfully.");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    @Override
    public void display() {
        if (items.isEmpty()) {
            System.out.println("\nInventory is empty.");
            return;
        }
        System.out.println("\nInventory Items:");
        for (Item item : items) {
            item.displayItemDetails();
        }
    }
}

public class assignment {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Inventory stock = new Inventory();
        while (true) {
            stock.menu();
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    stock.add(input);
                    break;
                case 2:
                    stock.delete(input);
                    break;
                case 3:
                    stock.update(input);
                    break;
                case 4:
                    stock.display();
                    break;
                case 5:
                    System.out.println("Exiting system");
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
