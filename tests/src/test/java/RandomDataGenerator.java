
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDataGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+-=[]{}|;:,.<>?";

    public static String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }

    public static String randomAlphanumeric(int length) {
        String combined = CHARACTERS + NUMBERS;
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            sb.append(combined.charAt(random.nextInt(combined.length())));
        }

        return sb.toString();
    }



    public static int randomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String randomUsername() {
        String base = randomAlphanumeric(8);
        Random random = new Random();
        int pos = random.nextInt(base.length());
        return base.substring(0, pos) +
                NUMBERS.charAt(random.nextInt(NUMBERS.length())) +
                base.substring(pos);
    }

    public static String randomPassword() {
        String base = randomAlphanumeric(8);
        Random random = new Random();
        int pos = random.nextInt(base.length());
        return base.substring(0, pos) +
                SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())) +
                base.substring(pos);
    }
}