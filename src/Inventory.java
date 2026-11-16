import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public final class Inventory {
    private final LinkedHashMap<String, Product> products = new LinkedHashMap<>();
    private static String key(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Nimi puudub.");
        return name.strip().toLowerCase(Locale.ROOT);
    }
    public void add(Product product) {
        if (product == null) throw new IllegalArgumentException("Toode puudub.");
        if (products.putIfAbsent(key(product.getName()), product) != null) throw new IllegalArgumentException("Sama toode on juba olemas.");
    }
    public List<Product> search(String text) {
        if (text == null) throw new IllegalArgumentException("Otsing puudub.");
        String query = text.strip().toLowerCase(Locale.ROOT);
        return products.values().stream().filter(p -> p.getName().toLowerCase(Locale.ROOT).contains(query)).toList();
    }
    public void changeQuantity(String name, int delta) {
        Product product = products.get(key(name));
        if (product == null) throw new IllegalArgumentException("Toodet ei leitud.");
        product.changeQuantity(delta);
    }
    public BigDecimal totalValue() { return products.values().stream().map(Product::value).reduce(BigDecimal.ZERO, BigDecimal::add); }
}
