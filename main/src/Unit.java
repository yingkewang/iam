import java.util.Arrays;
import java.util.Collection;

public enum Unit {

    TWENTY(20L, "twenty"),
    THIRTY(30L, "thirty"),
    FORTY(40L, "forty"),
    FIFTY(50L, "fifty"),
    SIXTY(60L, "sixty"),
    SEVENTY(70L, "seventy"),
    EIGHTY(80L, "eighty"),
    NINETY(90L, "ninety"),

    HUNDRED(100L, "hundred"),
    THOUSAND(1000L, "thousand"),
    MILLION(1000000L, "million"),
    BILLION(1000000000L, "billion"),
    TRILLION(1000000000000L, "trillion");

    private Long number;
    private String text;

    Unit(Long number, String text) {
        this.number = number;
        this.text = text;
    }

    public Long getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public static final Collection<Unit> TENS = Arrays.asList(NINETY, EIGHTY, SEVENTY, SIXTY, FIFTY, FORTY, THIRTY, TWENTY);
    public static final Collection<Unit> UNITS_DESC = Arrays.asList(TRILLION, BILLION, MILLION, THOUSAND, HUNDRED);

}
