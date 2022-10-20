public class EnhancedArray<T> {
    private Object[] array;
    private int length;
    private int size;
    private static final int DEFAULT_SIZE = 16;

    public EnhancedArray() {
        this(DEFAULT_SIZE);
    }

    public EnhancedArray(int size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        this.size = size;
        length = 0;
        array = new Object[size];
    }

    public void add(T element) {
        add(length, element);
    }

    public void add(int index, T element) {
        if (!checkSize(length + 1)) {
            throw new OutOfMemoryError();
        }
        checkIndex(index);
        if (index < length) {
            shiftRight(index);
        }
        length++;
        array[index] = element;
    }

    private void shiftRight(int index) {
        for (int i = length - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
    }

    public void remove(int index){
        checkIndex(index);
        shiftLeft(index);
        length--;
    }

    public T get(int index){
        checkIndex(index);
        return (T)array[index];
    }
    public int length(){
        return length;
    }

    private void shiftLeft(int index){
        for (int i = index; i < length - 1; i++){
            array[i] = array[i+1];
        }
    }

    private void checkIndex(int index){
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

    public void sort(){
        mergeSort(array, array.length);
    }

    private void mergeSort(Object[] arr, int n){
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

    private void merge(Object[] arr, Object[] leftArr, Object[] rightArr, int left, int right){

    }

    private void arrCopy(Object[] orig, Object[] dest, int indexFrom, int indexTo){
        int j = 0;
        for (int i = indexFrom; i < indexTo; i++){
            dest[j] = orig[i];
            j++;
        }
    }

}
