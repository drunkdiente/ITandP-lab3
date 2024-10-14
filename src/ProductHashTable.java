import java.util.LinkedList;

public class ProductHashTable {
    private static class HashEntry {
        String key;
        Product value;

        HashEntry(String key, Product value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<HashEntry>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public ProductHashTable(int capacity) {
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int hash(String key) {
        return key.hashCode() % table.length;
    }

    public void insert(String key, Product value) {
        int index = hash(key);
        for (HashEntry entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value; // Обновляем товар, если артикул уже существует
                return;
            }
        }
        table[index].add(new HashEntry(key, value));
        size++;
    }

    public Product search(String key) {
        int index = hash(key);
        for (HashEntry entry : table[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null; // Товар не найден
    }

    public void delete(String key) {
        int index = hash(key);
        HashEntry toRemove = null;
        for (HashEntry entry : table[index]) {
            if (entry.key.equals(key)) {
                toRemove = entry;
                break;
            }
        }
        if (toRemove != null) {
            table[index].remove(toRemove);
            size--;
        }
    }

    public int size() {
        return size;
    }
}
