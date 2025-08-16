import java.util.*;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0);     // US Dollar
        rates.put("INR", 87.65);    // Indian Rupee
        rates.put("EUR", 0.86);    // Euro
        rates.put("GBP", 0.74);    // British Pound
        rates.put("JPY", 146.77);  // Japanese Yen
        rates.put("KWD", 0.74); // Kuwaiti Dinar

        System.out.println("Available currencies: " + rates.keySet());

        System.out.print("Enter base currency (e.g., USD, INR): ");
        String base = sc.next().toUpperCase();

        System.out.print("Enter target currency (e.g., USD, INR): ");
        String target = sc.next().toUpperCase();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        if (rates.containsKey(base) && rates.containsKey(target)) {
            // Converting to USD first, then to target currency
            double amountInUSD = amount / rates.get(base);
            double converted = amountInUSD * rates.get(target);

            System.out.printf("%.2f %s = %.2f %s%n", amount, base, converted, target);
        } else {
            System.out.println("Invalid currency code!");
        }

        sc.close();
    }
}

