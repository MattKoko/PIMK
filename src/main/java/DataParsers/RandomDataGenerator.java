package DataParsers;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class RandomDataGenerator {
    public static String getRandomIssueNumber() {
        int num = new SecureRandom().nextInt(100000);
        return String.format("%05d", num);
    }

    public static String getRandomElementFromList(List<String> listOfStrings) {
        return listOfStrings.get(new Random().nextInt(listOfStrings.size()));
    }
}
