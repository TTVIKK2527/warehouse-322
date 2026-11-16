import java.math.BigDecimal;

public abstract class Product {
    private final String name;
    private final BigDecimal price;
    private int quantity;

    protected Product(String name, BigDecimal price, int quantity) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Nimi puudub.");
        if (price == null || price.signum() < 0 || price.stripTrailingZeros().scale() > 2) throw new IllegalArgumentException("Hind peab olema mittenegatiivne ja kuni kahe komakohaga.");
        if (quantity < 0) throw new IllegalArgumentException("Kogus ei tohi olla negatiivne.");
        this.name = name.strip(); this.price = price; this.quantity = quantity;
    }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public BigDecimal value() { return price.multiply(BigDecimal.valueOf(quantity)); }
    public void changeQuantity(int delta) {
        int next;
        try { next = Math.addExact(quantity, delta); }
        catch (ArithmeticException error) { throw new IllegalArgumentException("Kogus on liiga suur.", error); }
        if (next < 0) throw new IllegalArgumentException("Laos ei ole piisavalt kaupa.");
        quantity = next;
    }
    public abstract String details();
    @Override public String toString() { return name + " | " + price.toPlainString() + " EUR | " + quantity + " tk | " + details(); }
}
