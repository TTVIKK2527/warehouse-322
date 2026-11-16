import java.math.BigDecimal;

public final class InventoryTest {
    private static int checks;
    public static void main(String[] args) {
        Inventory stock = new Inventory();
        Product cable = new ElectronicProduct("USB", new BigDecimal("5.25"), 2, 24);
        stock.add(cable);
        check(stock.search(" us ").size() == 1, "Case insensitive substring search");
        check(stock.totalValue().compareTo(new BigDecimal("10.50")) == 0, "Exact money calculation");
        stock.changeQuantity("USB", -2);
        check(cable.getQuantity() == 0 && stock.totalValue().signum() == 0, "Zero stock");
        expect(() -> stock.changeQuantity("USB", -1));
        check(cable.getQuantity() == 0, "Rejected change preserves stock");
        expect(() -> stock.add(new ElectronicProduct(" usb ", BigDecimal.ONE, 1, 12)));
        expect(() -> stock.changeQuantity("missing", 1));
        expect(() -> new ElectronicProduct("", BigDecimal.ONE, 1, 1));
        expect(() -> new ElectronicProduct("x", new BigDecimal("-1"), 1, 1));
        expect(() -> new ElectronicProduct("x", new BigDecimal("1.001"), 1, 1));
        expect(() -> new ElectronicProduct("x", BigDecimal.ONE, -1, 1));
        Product maximum = new ElectronicProduct("max", BigDecimal.ONE, Integer.MAX_VALUE, 0);
        expect(() -> maximum.changeQuantity(1));
        check(maximum.getQuantity() == Integer.MAX_VALUE, "Integer overflow rejected without change");
        System.out.println("PASS: " + checks + " inventory checks");
    }
    private static void check(boolean result, String label) { if (!result) throw new AssertionError(label); checks++; }
    private static void expect(Runnable action) {
        try { action.run(); throw new AssertionError("Expected invalid input rejection"); }
        catch (IllegalArgumentException expected) { checks++; }
    }
}
