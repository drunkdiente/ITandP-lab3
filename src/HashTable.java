import java.util.LinkedList;

public class HashTable<K, V> {
    // Внутренний класс для хранения пар ключ-значение
    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] table;
    private int size;


    public HashTable(int capacity) {
        table = new LinkedList[capacity];
        size = 0;
    }

    // Хеш-функция для вычисления индекса в таблице
    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    // Метод добавления пары ключ-значение
    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return; // Если ключ уже существует, обновляем значение
            }
        }
        table[index].add(new Entry<>(key, value)); // Добавляем новую пару
        size++;
    }

    // Метод получения значения по ключу
    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null; // Ключ не найден
    }

    // Метод удаления пары по ключу
    public void remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    table[index].remove(entry);
                    size--;
                    return;
                }
            }
        }
    }

    // Возвращает количество элементов в хэш-таблице
    public int size() {
        return size;
    }

    // Проверяет, пуста ли хэш-таблица
    public boolean isEmpty() {
        return size == 0;
    }

    // Тестирование работы хэш-таблицы
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>(10);
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);

        System.out.println("Size: " + hashTable.size()); // Size: 3
        System.out.println("Get 'two': " + hashTable.get("two")); // Get 'two': 2

        hashTable.remove("two");
        System.out.println("Get 'two' after removal: " + hashTable.get("two")); // Get 'two' after removal: null
        System.out.println("Is empty: " + hashTable.isEmpty()); // Is empty: false
    }
}
