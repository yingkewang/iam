import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Num2WordTest {

    @Test
    public void testEndToEndExamples() {
        assertEquals("five hundred and thirty-six", Num2Word.extractNumberToWord("The pump is 536 deep underground."));
        assertEquals("nine thousand, one hundred and twenty-one", Num2Word.extractNumberToWord("We processed 9121 records."));
        assertEquals("ten thousand and twenty-two", Num2Word.extractNumberToWord("Interactive and printable 10022 ZIP code."));
        assertEquals("sixty-six billion, seven hundred and twenty-three million, one hundred and seven thousand and eight", Num2Word.extractNumberToWord("The database has 66723107008 records."));
        assertEquals(Num2Word.NUMBER_INVALID, Num2Word.extractNumberToWord("Variables reported as having a missing type #65678."));
        assertEquals(Num2Word.NUMBER_INVALID, Num2Word.extractNumberToWord("I received 23 456,9 KGs."));
    }

    @Test
    public void testExtractNumber() {
        assertEquals(536L, Num2Word.extractNumber("The pump is 536 deep underground.").longValue());
        assertEquals(9121L, Num2Word.extractNumber("We processed 9121 records.").longValue());
        assertEquals(10022L, Num2Word.extractNumber("Interactive and printable 10022 ZIP code.").longValue());
        assertEquals(66723107008L, Num2Word.extractNumber("The database has 66723107008 records.").longValue());

        assertEquals(536L, Num2Word.extractNumber("The pump is 536").longValue());
        assertEquals(536L, Num2Word.extractNumber("536 deep underground.").longValue());
        assertEquals(536L, Num2Word.extractNumber(" 536 ").longValue());
        assertEquals(536L, Num2Word.extractNumber("536").longValue());
        assertEquals(1L, Num2Word.extractNumber("1").longValue());
    }

    @Test
    public void testExtractNumberErrors() {
        assertNull(Num2Word.extractNumber("Variables reported as having a missing type #65678."));
        assertNull(Num2Word.extractNumber("I received 23 456,9 KGs."));

        assertNull(Num2Word.extractNumber("AAA1"));
        assertNull(Num2Word.extractNumber("1AAA"));
        assertNull(Num2Word.extractNumber("1 1"));
    }

    @Test
    public void testNumberToWordSub100() {
        assertEquals("zero", Num2Word.numberToWord(0L));
        assertEquals("one", Num2Word.numberToWord(1L));
        assertEquals("twenty", Num2Word.numberToWord(20L));
        assertEquals("twenty-one", Num2Word.numberToWord(21L));
        assertEquals("thirty-six", Num2Word.numberToWord(36L));
        assertNull(Num2Word.numberToWord(100L));
    }

    @Test
    public void testNumberToWordOver100() {
        assertEquals("one hundred", Num2Word.numberToWord(100L));
        assertEquals("one hundred and one", Num2Word.numberToWord(101L));
        assertEquals("two hundred and twenty-two", Num2Word.numberToWord(222L));
        assertEquals("one thousand", Num2Word.numberToWord(1000L));
        assertEquals("one thousand and one", Num2Word.numberToWord(1001L));
        assertEquals("twenty-three thousand, four hundred and fifty", Num2Word.numberToWord(23450L));
        assertEquals("twelve million, three hundred and forty-five thousand, six hundred and seventy-eight", Num2Word.numberToWord(12345678L));
        assertEquals("two trillion, two hundred and sixty-six billion, seven hundred and twenty-three million, one hundred and seven thousand and eight", Num2Word.numberToWord(2266723107008L));

        assertEquals("five hundred and thirty-six", Num2Word.numberToWord(536L));
        assertEquals("nine thousand, one hundred and twenty-one", Num2Word.numberToWord(9121L));
        assertEquals("ten thousand and twenty-two", Num2Word.numberToWord(10022L));
        assertEquals("sixty-six billion, seven hundred and twenty-three million, one hundred and seven thousand and eight", Num2Word.numberToWord(66723107008L));
    }

    @Ignore
    @Test
    public void testMillion() {
        long start = System.currentTimeMillis();
        for (long i = 0; i < 1000000; i++) {
            Num2Word.numberToWord(i);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

}