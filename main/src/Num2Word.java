import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Num2Word {

    // TODO:
    //  minus
    //  decimal
    //  multi number string
    //  caching

    public static final String NUMBER_INVALID = "number invalid";

    private static final Pattern NUMBER_PATTERN = Pattern.compile("(.?\\d+.?\\d+.?|.?\\d.?)");

    private static final Map<Long, String> SMALL_NUMBER_TO_TEXT_MAP = new HashMap<>();

    static {
        SMALL_NUMBER_TO_TEXT_MAP.put(0L, "zero");
        SMALL_NUMBER_TO_TEXT_MAP.put(1L, "one");
        SMALL_NUMBER_TO_TEXT_MAP.put(2L, "two");
        SMALL_NUMBER_TO_TEXT_MAP.put(3L, "three");
        SMALL_NUMBER_TO_TEXT_MAP.put(4L, "four");
        SMALL_NUMBER_TO_TEXT_MAP.put(5L, "five");
        SMALL_NUMBER_TO_TEXT_MAP.put(6L, "six");
        SMALL_NUMBER_TO_TEXT_MAP.put(7L, "seven");
        SMALL_NUMBER_TO_TEXT_MAP.put(8L, "eight");
        SMALL_NUMBER_TO_TEXT_MAP.put(9L, "nine");
        SMALL_NUMBER_TO_TEXT_MAP.put(10L, "ten");
        SMALL_NUMBER_TO_TEXT_MAP.put(11L, "eleven");
        SMALL_NUMBER_TO_TEXT_MAP.put(12L, "twelve");
        SMALL_NUMBER_TO_TEXT_MAP.put(13L, "thirteen");
        SMALL_NUMBER_TO_TEXT_MAP.put(14L, "fourteen");
        SMALL_NUMBER_TO_TEXT_MAP.put(15L, "fifteen");
        SMALL_NUMBER_TO_TEXT_MAP.put(16L, "sixteen");
        SMALL_NUMBER_TO_TEXT_MAP.put(17L, "seventeen");
        SMALL_NUMBER_TO_TEXT_MAP.put(18L, "eighteen");
        SMALL_NUMBER_TO_TEXT_MAP.put(19L, "nineteen");
    }

    public static String extractNumberToWord(String str) {
        Long val = extractNumber(str);
        if (val == null) {
            return NUMBER_INVALID;
        }
        return numberToWord(val);
    }

    public static Long extractNumber(String str) {
        Matcher m = NUMBER_PATTERN.matcher(str);
        if (m.find()) {
            String numberSubString = str.substring(m.start(), m.end());
            try {
                return Long.valueOf(numberSubString.trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public static String numberToWord(Long number) {
        return numberTextJoiner(extractWordParts(number));
    }

    private static List<String> extractWordParts(Long number) {
        if (number < 100) {
            return Collections.singletonList(smallNumberToWord(number));
        }
        // loop through units to extract largest unit, and how many of that unit
        // recursive call with remainder to build the rest
        // e.g. 12,345,678
        //  -> 12 units of millions, then repeat with 345,678 to find 345 units of thousands. etc.
        //  until base condition (remainder < 100) then call smallNumberToWord().
        List<String> parts = new ArrayList<>();
        for (Unit t : Unit.UNITS_DESC) {
            long floorDiv = Math.floorDiv(number, t.getNumber());
            if (floorDiv > 0) {
                parts.add(numberToWord(floorDiv) + " " + t.getText());
                long rem = number - floorDiv * t.getNumber();
                if (rem != 0) {
                    parts.addAll(extractWordParts(rem));
                }
                break;
            }
        }
        // returns collection of number components by unit
        // e.g. [twelve million, three hundred and forty-five thousand, six hundred, seventy-eight]
        return parts;
    }

    private static String smallNumberToWord(Long number) {
        if (number > 99) {
            return null;
        }
        List<String> parts = new ArrayList<>();
        // if number if less than 20 - lookup from small numbers map
        if (SMALL_NUMBER_TO_TEXT_MAP.containsKey(number)) {
            return SMALL_NUMBER_TO_TEXT_MAP.get(number);
        }
        // else divide through 'tens' descending to find the biggest 'tens' bucket, and append remainder
        // e.g. 75 -> 70 + 5
        for (Unit t : Unit.TENS) {
            long floorDiv = Math.floorDiv(number, t.getNumber());
            if (floorDiv == 1) {
                parts.add(t.getText());
                // append remainder text if there is one
                long rem = number - floorDiv * t.getNumber();
                if (rem != 0) {
                    parts.add(smallNumberToWord(rem));
                }
                break;
            }
        }

        return String.join("-", parts);
    }

    private static String numberTextJoiner(List<String> parts) {
        if (parts.size() == 1) {
            return parts.get(0);
        }
        if (parts.size() == 2) {
            return String.join(" and ", parts);
        }
        return parts.remove(0) + ", " + numberTextJoiner(parts);
    }

}