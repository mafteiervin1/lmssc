package Lab1.crawl;

import java.util.regex.Pattern;

public class Constants {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_URL_REGEX =
        Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", Pattern.CASE_INSENSITIVE);

    public static final Pattern ITS_NAME_REGEX = Pattern.compile("^[A-Z].+");

    public static final Pattern ACADEMIC_TITLE_REGEX =
        Pattern.compile("((dl.)|(d-l)|(d-na)|(dna.)|(acad.)|(asist.)|(doc.)|(dr.)|(conf.)|(lect.)|(prof.))", Pattern.CASE_INSENSITIVE);

    public static final Pattern PHONE_NUMBER_REGEX = Pattern.compile("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$");

    private static String zeroTo255 = "(\\d{1,2}|(0|1)\\d{2}|2[0-4]\\d|25[0-5])";

    public static final String VALID_IP_REGEX = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;

    public static final String WIKI_LINK = "https://zonait.ro/interviu-ghost-recon-frontline/";

    public static final String FILE_PATH = "src/main/resources/corpus.txt";

}
