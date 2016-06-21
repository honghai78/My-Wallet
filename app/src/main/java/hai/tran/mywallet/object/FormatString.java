package hai.tran.mywallet.object;

/**
 * Created by hongh on 6/19/2016.
 */
public class FormatString {
    public static String format(String string) {
        string = string.trim();
        char chars[] = string.toCharArray();
        int count = 0;
        String resul = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            resul = resul + chars[i];
            count++;
            if (count == 3) {
                resul = resul + ",";
                count = 0;
            }
        }
        String reverse = new StringBuffer(resul).
                reverse().toString();
        chars = reverse.toCharArray();

        if (chars[0] == ',')
            reverse = reverse.subSequence(1, reverse.length()).toString();
        if (chars[0] == '-' && chars[1] == ',')
            reverse = chars[0] + reverse.subSequence(2, reverse.length()).toString();
        return reverse;
    }

    public static String notFormat(String string) {
        String st[] = string.trim().split(",");
        String resyl = "";
        for (int i = 0; i < st.length; i++)
            resyl = resyl + st[i];
        return resyl;
    }
}
