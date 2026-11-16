import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public final class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.add(new FoodProduct("Õun", new BigDecimal("1.20"), 10, LocalDate.of(2027, 1, 1)));
        inventory.add(new ElectronicProduct("USB kaabel", new BigDecimal("5.00"), 3, 24));
        inventory.add(new FoodProduct("Tee", new BigDecimal("3.50"), 2, LocalDate.of(2027, 2, 1)));
        try (Scanner input = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("\n1 Kõik | 2 Lisa toit | 3 Lisa elektroonika | 4 Muuda kogust | 5 Otsi | 6 Koguväärtus | 0 Välju");
                try {
                    String choice = read(input, "Valik: ");
                    switch (choice) {
                        case "1" -> inventory.search("").forEach(System.out::println);
                        case "2", "3" -> {
                            String name = read(input, "Nimi: ");
                            BigDecimal price = new BigDecimal(read(input, "Hind (nt 1.25): "));
                            int quantity = Integer.parseInt(read(input, "Kogus: "));
                            Product product = choice.equals("2")
                                ? new FoodProduct(name, price, quantity, LocalDate.parse(read(input, "Parim enne (AAAA-KK-PP): ")))
                                : new ElectronicProduct(name, price, quantity, Integer.parseInt(read(input, "Garantii kuudes: ")));
                            inventory.add(product);
                            System.out.println("Toode lisatud.");
                        }
                        case "4" -> { inventory.changeQuantity(read(input, "Nimi: "), Integer.parseInt(read(input, "Koguse muutus (+/-): "))); System.out.println("Kogus muudetud."); }
                        case "5" -> inventory.search(read(input, "Otsing: ")).forEach(System.out::println);
                        case "6" -> System.out.println("Koguväärtus: " + inventory.totalValue().toPlainString() + " EUR");
                        case "0" -> running = false;
                        default -> System.out.println("Tundmatu valik.");
                    }
                } catch (EndOfInput end) { running = false; }
                catch (IllegalArgumentException | DateTimeParseException error) { System.out.println("Viga: " + error.getMessage()); }
            }
        }
        System.out.println("Head aega! Selle harjutuse andmed on ainult mälus.");
    }
    private static String read(Scanner input, String prompt) {
        System.out.print(prompt);
        if (!input.hasNextLine()) throw new EndOfInput();
        return input.nextLine();
    }
    private static final class EndOfInput extends RuntimeException { private static final long serialVersionUID = 1L; }
}
