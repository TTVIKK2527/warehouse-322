import java.math.BigDecimal;
import java.time.LocalDate;

public final class FoodProduct extends Product {
    private final LocalDate bestBefore;
    public FoodProduct(String name, BigDecimal price, int quantity, LocalDate bestBefore) {
        super(name, price, quantity);
        if (bestBefore == null) throw new IllegalArgumentException("Kuupäev puudub.");
        this.bestBefore = bestBefore;
    }
    @Override public String details() { return "Parim enne " + bestBefore; }
}
