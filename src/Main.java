import java.util.*;

public class Main {
    public static class Pair<F, S> {
        public F first;
        public S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    public static Pair<List<Integer>, Long> fillNoResizeArray(int size) {
        List<Integer> list = new ArrayList<>();
        Long startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        Long endTime = System.nanoTime();

        return new Pair<List<Integer>, Long>(list, endTime - startTime);
    }

    public static Pair<List<Integer>, Long> fillResizeArray(int size) {
        List<Integer> list = new ArrayList<>(size);
        Long startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        Long endTime = System.nanoTime();

        return new Pair<List<Integer>, Long>(list, endTime - startTime);
    }

    public static Pair<List<Integer>, Long> fillList(int size) {
        List<Integer> list = new LinkedList<>();
        Long startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        Long endTime = System.nanoTime();

        return new Pair<List<Integer>, Long>(list, endTime - startTime);
    }

    public static long deleteConcurrentForwardList(int size, int amountToDelete) {
        if (amountToDelete > size)
            throw new RuntimeException("can't delete more elements than the size of a collection");
        List<Integer> lst = fillList(size).first;
        Long startTime = System.nanoTime();
        for (int i = 0; i < amountToDelete; i++) {
            lst.removeFirst();
        }
        Long endTime = System.nanoTime();
      return endTime - startTime;
    }

    public static long deleteConcurrentReverseList(int size, int amountToDelete) {
        if (amountToDelete > size)
            throw new RuntimeException("can't delete more elements than the size of a collection");
        List<Integer> lst = fillList(size).first;
        Long startTime = System.nanoTime();
        for (int i = 0; i < amountToDelete; i++) {
            lst.removeLast();
        }
        Long endTime = System.nanoTime();
      return endTime - startTime;
    }

    public static long deleteConcurrentForwardArray(int size, int amountToDelete) {
        if (amountToDelete > size)
            throw new RuntimeException("can't delete more elements than the size of a collection");
        List<Integer> lst = fillResizeArray(size).first;
        Long startTime = System.nanoTime();
        for (int i = 0; i < amountToDelete; i++) {
            lst.removeFirst();
        }
        Long endTime = System.nanoTime();
      return endTime - startTime;
    }

    public static long deleteConcurrentReverseArray(int size, int amountToDelete) {
        if (amountToDelete > size)
            throw new RuntimeException("can't delete more elements than the size of a collection");
        List<Integer> lst = fillResizeArray(size).first;
        Long startTime = System.nanoTime();
        for (int i = 0; i < amountToDelete; i++) {
            lst.removeLast();
        }
        Long endTime = System.nanoTime();
      return endTime - startTime;
    }

    public static long deleteRandomList(int size, int amountToDelete) {
        if (amountToDelete > size)
            throw new RuntimeException("can't delete more elements than the size of a collection");
        Random random = new Random();
        List<Integer> lst = fillList(size).first;

        Long startTime = System.nanoTime();
        for (int i = 0; i < amountToDelete; i++) {
            lst.remove(random.nextInt(size-i));
        }

        Long endTime = System.nanoTime();
      return endTime - startTime;
    }

    public static long deleteRandomArray(int size, int amountToDelete) {
        if (amountToDelete > size)
            throw new RuntimeException("can't delete more elements than the size of a collection");
        Random random = new Random();
        List<Integer> lst = fillResizeArray(size).first;

        Long startTime = System.nanoTime();
        for (int i = 0; i < amountToDelete; i++) {
            lst.remove(random.nextInt(size-i));
        }

        Long endTime = System.nanoTime();
      return endTime - startTime;
    }

    public static long getArray(int size, int amountToGet) {
        List<Integer> lst = fillResizeArray(size).first;
        Random random = new Random();
        Long startTime = System.nanoTime();
        for (int i = 0; i < amountToGet; i++) {
            int value = lst.get(random.nextInt(size));
        }
        Long endTime = System.nanoTime();
      return endTime - startTime;

    }

    public static long getRandomList(int size, int amountToGet) {
        List<Integer> lst = fillList(size).first;
        Random random = new Random();
        Long startTime = System.nanoTime();
        for (int i = 0; i < amountToGet; i++) {
            int value = lst.get(random.nextInt(size));
        }
        Long endTime = System.nanoTime();
      return endTime - startTime;

    }

    public static long getCloseToStart(int size, int amountToGet) {
        List<Integer> lst = fillList(size).first;
        Random random = new Random();
        Long startTime = System.nanoTime();
        for (int i = 0; i < amountToGet; i++) {
            int value = lst.get(random.nextInt(size / 10));
        }
        Long endTime = System.nanoTime();
      return endTime - startTime;
    }

    public static long getCloseToEnd(int size, int amountToGet) {
        List<Integer> lst = fillList(size).first;
        Random random = new Random();
        Long startTime = System.nanoTime();
        for (int i = 0; i < amountToGet; i++) {
            int value = lst.get(random.nextInt(size / 10, size / 2));
        }
        Long endTime = System.nanoTime();
      return endTime - startTime;
    }

    public static void conductTests(int size) {
        System.out.println("###############################################################################");
        System.out.println("fill arr no initial capacity with SIZE = " + size + " " + fillNoResizeArray(size).second);
        System.out.println("fill arr with initial capacity with SIZE = " + size + " " + fillResizeArray(size).second);

        System.out.println("\n\n");

        System.out.println("remove half of the elements from array from the start with SIZE = " + size + " "
                + deleteConcurrentForwardArray(size, size / 2));
        System.out.println("remove half of the elements from list from the start with SIZE = " + size + " "
                + deleteConcurrentForwardList(size, size / 2));

        System.out.println("\n\n");

        System.out.println("remove half of the elements from array from the end with SIZE = " + size + " "
                + deleteConcurrentReverseArray(size, size / 2));
        System.out.println("remove half of the elements from list from the end with SIZE = " + size + " "
                + deleteConcurrentReverseList(size, size / 2));

        System.out.println("\n\n");

        System.out.println("remove half of the elements from array randomly with SIZE = " + size + " "
                + deleteRandomArray(size, size / 2));
        System.out.println("remove half of the elements from list randomly with SIZE = " + size + " "
                + deleteRandomList(size, size / 2));

        System.out.println("\n\n");

        System.out.println("get half of the elements from array with SIZE = " + size + " "
                + getArray(size, size / 2));

        System.out.println("\n\n");

        System.out.println("get half of the elements from list close to start with SIZE = " + size + " "
                + getCloseToStart(size, size / 2));
        System.out.println("get half of the elements from list close to End with SIZE = " + size + " "
                + getCloseToEnd(size, size / 2));
        System.out.println("get half of the elements from list randomly with SIZE = " + size + " "
                + getRandomList(size, size / 2));

    }


    public static final int thousand = 1000;

    static List<Integer> testSizes = new ArrayList<>(Arrays.asList(thousand, thousand * 10, thousand * 100, thousand * 1000, thousand * 10000));

    public static void main(String[] args) {
        for(var size : testSizes){
            conductTests(size);
        }
    }

}
