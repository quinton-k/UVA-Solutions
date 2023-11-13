import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("10300");
        logger.setLevel(Level.INFO);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.addHandler(consoleHandler);

        Scanner sc = new Scanner(System.in);

        int cases = sc.nextInt(); // the number of cases in the data only occurs once and is always first
        sc.nextLine(); //consumes the new line that is not consumed when calling nextInt
        logger.fine("Processing " + cases + " total cases");
        for (int caseNumber = 0; caseNumber < cases; caseNumber++) { //loop through each case
            logger.fine("Beginning case number " + caseNumber);
            int farmers = sc.nextInt(); //the number of farmers in a case
            logger.fine("Number of farmers in case " + caseNumber + " is " + farmers);
            sc.nextLine(); //consumes the new line that is not consumed when calling nextInt
            List<Long> casePremiums = new ArrayList<>(); //stores the total premium each farmer receives per case
            for (int farmer = 0; farmer < farmers; farmer++) { //loop through all farmers in a case
                logger.fine("Beginning to process farmer " + farmer);
                String farm = sc.nextLine(); //the 3 positive integers representing the size of the farm, the number of animals on the farm, and the eco score
                logger.fine("Calculating totals for farm " + farmer + " " + farm);
                String[] farmParameters = farm.split(" "); //split the numbers in the farm string into 3 separate values

                int sizeOfTheFarm = Integer.parseInt(farmParameters[0]); //The size of the farm in square meters will always be the first index
                int animalsOnFarm = Integer.parseInt(farmParameters[1]); //the number of animals on the farm will always be the 2nd index
                int environmentalScore = Integer.parseInt(farmParameters[2]); //the farm's environmental score will always be the last index

                logger.fine("Farm " + farmer + " is " + sizeOfTheFarm + " Sq Meters. There are " + animalsOnFarm + " animals on the farm. The eco score is: " + environmentalScore);

                double averageMetersPerAnimal = (double) sizeOfTheFarm / animalsOnFarm; //the average space a single animal takes on the farm in sq meters
                double premiumPerAnimal = averageMetersPerAnimal * environmentalScore; //the premium a farmer receives per animal
                long totalPremium = Math.round(premiumPerAnimal * animalsOnFarm); //the total premium a farmer receives

                logger.fine("Farm " + farmer
                        + " has an average of " + averageMetersPerAnimal
                        + " Sq meters per animal for a total of " + premiumPerAnimal
                        + " premium per animal. For a total premium of " + totalPremium);

                casePremiums.add(totalPremium);
            }
            long totalCasePremium = casePremiums.stream().mapToLong(Long::longValue).sum();//the sum of all the total premiums in a single case
            logger.fine("The total premium due for case[" + caseNumber + "] is " + totalCasePremium);
            System.out.println(totalCasePremium);
        }
    }
}
