package Support;

public class Pair<U,V> {
    public final U key;       // the first field of a pair
    public final V value;      // the second field of a pair

    // Constructs a new pair with specified values
    public Pair(U key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public U getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    // Checks specified object is "equal to" the current object or not
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) o;

        // call `equals()` method of the underlying objects
        if (!key.equals(pair.key)) {
            return false;
        }
        return value.equals(pair.value);
    }

    @Override
    // Computes hash code for an object to support hash tables
    public int hashCode()
    {
        // use hash codes of the underlying objects
        return 31 * key.hashCode() + value.hashCode();
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    // Factory method for creating a typed Pair immutable instance
    public static <U, V> Pair <U, V> of(U a, V b)
    {
        // calls private constructor
        return new Pair<>(a, b);
    }
}
