import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * ToyShop
 */
public class ToyShop {

    public static void main(String[] args) {
        String[] toyArr1 = put("1 2 конструктор");
        String[] toyArr2 = put("2 2 робот");
        String[] toyArr3 = put("3 6 кукла");
        PriorityQueue<Toy> queue = new PriorityQueue<>(Comparator.comparingInt(Toy::getFrequency));
        queue.add(parseToy(toyArr1));
        queue.add(parseToy(toyArr2));
        queue.add(parseToy(toyArr3));
        List<Toy> toyList = new ArrayList<>();
        while (!queue.isEmpty()) {
            Toy current = queue.peek();
            if (current.getFrequency() > 0) {
                toyList.add(current);
                current.setFrequency(current.getFrequency() - 1);
            } else
                queue.poll();
        }
        Random rnd = new Random();
        while (!toyList.isEmpty()) {
            int index = rnd.nextInt(toyList.size());
            System.out.println(toyList.get(index).getId());
            File file = new File("toys.txt");
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(toyList.get(index).getId() + "\n");
            fileWriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            toyList.remove(index);
        }

    }

    static String[] put(String string) {
        String[] parts = string.split(" ");
        return parts;
    }

    static Toy parseToy(String[] parts) {
        int id = Integer.parseInt(parts[0]);
        String name = parts[2];
        int frq = Integer.parseInt(parts[1]);
        return new Toy(id, name, frq);
    }
}