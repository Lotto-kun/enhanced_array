import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertArrayEquals;

public class EnhancedArrayTest extends TestCase {
    EnhancedArray<Integer> list;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        list = new EnhancedArray<>(8);
        list.add(1);
        list.add(5);
        list.add(0);
    }

    public void testInitSize() {
        int expected = 8;
        assertEquals(expected, list.getSize());
    }

    public void testAddToList() {
        int size = list.length();
        list.add(7);
        list.add(-5);
        list.add(3);
        list.add(5);
        int expected = size + 4;
        assertEquals(expected, list.length());
    }

    public void testAddNull() {
        int expected = list.length();
        list.add(null);
        assertEquals(expected, list.length());
    }

    public void testGrowSize() {
        while (list.length() < 9) {
            list.add(17);
        }
        int expected = 12;
        assertEquals(expected, list.getSize());
    }

    public void testGetElem() {
        int expected = 1;
        int actual = list.get(0);
        assertEquals(expected, actual);
    }

    public void testRemoveElem() {
        int expected = list.get(1);
        list.remove(0);
        int actual = list.get(0);
        assertEquals(expected, actual);
    }

    public void testAddElemByIndex() {
        int expected = 9;
        list.add(3, 9);
        int actual = list.get(3);
        assertEquals(expected, actual);
    }

    public void testSortArray() {
        Integer[] array = {6, 1, -5, 9, 4, 0, 6, 2};
        list.clear();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        list.sort();
        EnhancedArray<Integer> expected = new EnhancedArray<>();
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            expected.add(array[i]);
        }
        assertEquals(expected.length(), list.length());
        for (int i = 0; i < expected.length(); i++)
            assertEquals("расхождение на индексе " + i, expected.get(i), list.get(i));
    }
}
