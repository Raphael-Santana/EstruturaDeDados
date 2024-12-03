import java.util.Random;

public class HashTableMap<K, V> {
    protected int n = 0;
    protected int prime, capacity;
    protected Node<K, V>[] bucket;

    protected long scale, shift;

    public HashTableMap(int capacity, int prime) {
        this.capacity = capacity;
        this.prime = prime;
        this.bucket = (Node<K, V>[]) new Node[capacity];

        Random rand = new Random();
        this.scale = rand.nextInt(prime - 1) + 1;
        this.shift = rand.nextInt(prime);
    }

    /* Função Hash [(ai + b) mod p] mod N */
    protected int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    /* Validação da chave */
    protected void checkKey(K key) {
        if (key == null) throw new IllegalArgumentException("Chave não pode ser null.");
    }

    /* Inserir par (k, v) */
    public void put(K key, V value) {
        checkKey(key);

        if (n >= 0.75 * capacity) {
            rehash();
        }

        int index = hashValue(key);
        Node<K, V> newNode = new Node<>(key, value);

        if (bucket[index] == null) {
            bucket[index] = newNode;
        } else {
            Node<K, V> current = bucket[index];

            // Percorre a lista encadeada no bucket até encontrar a posição
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                if (current.next == null) {
                    break;
                }
                current = current.next;
            }
            assert current != null;
            current.next = newNode;
        }
        n++;
    }

    /* Busca pela chave */
    public V get(K key) {
        checkKey(key);
        int index = hashValue(key);
        Node<K, V> current = bucket[index];

        /* Percorre a lista encadeada até achar onde está com a mesma chave*/
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /* Remoção */
    public V remove(K key) {
        checkKey(key);
        int index = hashValue(key);
        Node<K, V> current = bucket[index];
        Node<K, V> prev = null;

        /* Percorre a lista encadeada do índice até chegar no último */
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    bucket[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                n--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    /* Função de rehash */
    protected void rehash() {
        int oldCapacity = capacity;
        Node<K, V>[] oldBucket = bucket;

        capacity = 2 * oldCapacity;
        bucket = (Node<K, V>[]) new Node[capacity];
        n = 0;

        // Reinsere todos os pares na nova tabela
        for (int i = 0; i < oldCapacity; i++) {
            Node<K, V> current = oldBucket[i];
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    /* Tamanho */
    public int size() {
        return n;
    }

    /* Verificar se está vazio */
    public boolean isEmpty() {
        return n == 0;
    }
}
