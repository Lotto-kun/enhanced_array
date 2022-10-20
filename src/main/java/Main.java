import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        EnhancedArray<Integer> array = new EnhancedArray<>();
        array.add(null);
        array.add(7);
        array.add(2);
        array.add(2,8);
        array.add(4);
        array.add(null);
        array.add(1);
        array.remove(0);
        for(int i = 0; i < array.length(); i++){
            System.out.println(array.get(i));
        }
        System.out.println("--------------------");
  //      array.sort();
   //     array.forEach(n -> System.out.println(n));
    }
}
