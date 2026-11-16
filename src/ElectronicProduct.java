import java.math.BigDecimal;

public final class ElectronicProduct extends Product {
    private final int warrantyMonths;
    public ElectronicProduct(String name, BigDecimal price, int quantity, int warrantyMonths) {
        super(name, price, quantity);
        if (warrantyMonths < 0) throw new IllegalArgumentException("Garantii ei tohi olla negatiivne.");
        this.warrantyMonths = warrantyMonths;
    }
    @Override public String details() { return "Garantii " + warrantyMonths + " kuud"; }
}
