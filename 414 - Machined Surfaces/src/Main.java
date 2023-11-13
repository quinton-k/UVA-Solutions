import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main {

    public static void main(String[] args) {
        //Start code
        Scanner sc = new Scanner(System.in);
        Main main = new Main();

        main.calculateData(sc);
    }

    void calculateData(Scanner sc) {
        int imageMaxSize = 25;//the max size a single line can be
        int setSize = sc.nextInt();//the number of images in the set
        List<Integer> differences = new ArrayList<>(); //stores the differences of the X and the blanks
        sc.nextLine(); //consumes the empty line
        if (setSize != 0) { //verifies the set has images in it
            for (int set = 0; set < setSize; set++) { //loops through all images in the set
                String image = sc.nextLine();//the contents of the image
                String[] surfaces = image.trim().split(" "); //splits the line into the left and right sides if there are spaces between
                long xCount = Arrays.stream(surfaces).mapToInt(String::length).sum(); //counts the total number of X's in the image
                int difference = (int) (imageMaxSize - xCount);//the difference between the max size and the number of x's
                differences.add(difference); //stores the difference in our list
            }
            int sumOfDifferences = differences.stream().mapToInt(Integer::intValue).sum(); //the sum of all the differences in our list
            int minDistance = differences.stream().min(Comparator.comparingInt(o -> o)).orElse(0); //the smallest difference in our list (the one with the least space between)
            int reduction = setSize * minDistance; //the amount to reduce all other differences by
            int voidSum = sumOfDifferences - reduction; //the sum of all the void in an image
            System.out.println(voidSum);
            if (sc.hasNextLine()) { //recursively calls this to begin the next set
                calculateData(sc);
            }
        }
    }
}
