public class Main {
    public static void main(String[] args) {
        ProductHashTable productTable = new ProductHashTable(10);

        Product product1 = new Product("Laptop", "Gaming laptop", 1200.99, 5);
        Product product2 = new Product("Smartphone", "Latest model smartphone", 799.99, 10);

        productTable.insert("ART001", product1);
        productTable.insert("ART002", product2);

        // Поиск товара
        Product searchResult = productTable.search("ART001");
        System.out.println(searchResult);

        // Удаление товара
        productTable.delete("ART001");
        System.out.println(productTable.search("ART001")); // Должен вернуть null
    }
}
