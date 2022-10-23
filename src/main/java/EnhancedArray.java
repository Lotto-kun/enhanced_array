import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class EnhancedArray<T extends Comparable<? super T>> {
    private Object[] array;
    private int length;
    private int size;
    private static final int DEFAULT_SIZE = 16;
    private static final Logger LOGGER = LoggerFactory.getLogger(EnhancedArray.class);
    private static final Marker EXCEPTIONS_MARKER = MarkerFactory.getMarker("EXCEPTIONS");

    public EnhancedArray() {
        this(DEFAULT_SIZE);
    }

    public EnhancedArray(int size) {
        if (size < 0) {
            LOGGER.error(EXCEPTIONS_MARKER, "Некорректный размер в контструкторе ", new IllegalArgumentException(String.valueOf(size)));
            throw new IllegalArgumentException(String.valueOf(size));
        }
        this.size = size;
        length = 0;
        array = new Object[size];
    }

    public void add(T element) {
        add(length, element);
    }

    public void add(int index, T element) {
        try {
            if (element == null) {
                LOGGER.error(EXCEPTIONS_MARKER, "Попытка добавить Null в коллекцию");
                throw new IllegalArgumentException("null");
            }
            if (!checkSize(length + 1)) {
                LOGGER.error(EXCEPTIONS_MARKER, "Размер внутреннего массива вышел за пределы int");
                throw new OutOfMemoryError("размер коллекции превысел максимум");
            }
            checkIndex(index);
            if (index < length) {
                shiftRight(index);
            }
            length++;
            array[index] = element;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shiftRight(int index) {
        for (int i = length - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
    }

    public void remove(int index) {
        checkIndex(index);
        shiftLeft(index);
        length--;
    }

    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    public int length() {
        return length;
    }

    private void shiftLeft(int index) {
        for (int i = index; i < length - 1; i++) {
            array[i] = array[i + 1];
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private boolean checkSize(int size) {
        if (size >= array.length) {
            return grow();
        }
        return true;
    }

    private boolean grow() {
        size = size + size / 2;
        if (size < 0) {
            return false;
        }
        copyArray();
        return true;
    }

    private void copyArray() {
        Object[] temp = new Object[size];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public void sort() {
        mergeSort(array, length);
    }

    private void mergeSort(Object[] arr, int n) {
        if (n < 2) {
            return;
        }
        int mid = n >>> 1;
        Object[] left = new Object[mid];
        Object[] right = new Object[n - mid];
        arrCopy(arr, left, 0, mid);
        arrCopy(arr, right, mid, n);
        mergeSort(left, mid);
        mergeSort(right, n - mid);
        merge(arr, left, right, mid, n - mid);
    }

    private void merge(Object[] arr, Object[] leftArr, Object[] rightArr, int left, int right) {
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < left && j < right) {
            if (((T) leftArr[i]).compareTo((T) rightArr[j]) <= 0) {
                arr[index] = leftArr[i++];
            } else {
                arr[index] = rightArr[j++];
            }
            index++;
        }
        while (i < left) {
            arr[index++] = leftArr[i++];
        }
        while (j < right) {
            arr[index++] = rightArr[j++];
        }

    }

    private void arrCopy(Object[] orig, Object[] dest, int indexFrom, int indexTo) {
        int j = 0;
        for (int i = indexFrom; i < indexTo; i++) {
            dest[j] = orig[i];
            j++;
        }
    }

    public void clear(){
        length = 0;
    }

    public int getSize(){
        return size;
    }

}
