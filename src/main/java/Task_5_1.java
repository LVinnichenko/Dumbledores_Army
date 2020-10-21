import java.io.IOException;

public class Task_5_1 {
    public static String text;
    static
    {
        System.out.println("Введите текст:");
        try
        {
            text = Helpers.getText();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String getCiphertext(String text) {

        char[] text_array = text.toCharArray();
        char[] crypted = new char[text_array.length];

        for (int i = 0; i < text_array.length; i++) {
            char sim = text_array[i];

            if ((int)sim >= 97 && (int)sim < 122){
                crypted[i] = (char)((int)sim-31);
            }
            else if((int)sim >= 65 && (int)sim < 90){
                crypted[i] = (char)((int)sim+33);
            }
            else if((int)sim == 122){
                crypted[i] = (char)((int)sim-57);
            }
            else if((int)sim == 90){
                crypted[i] = (char)((int)sim + 7);
            }
            else{
                crypted[i] = sim;
            }
        }

        return String.valueOf(crypted);
    }

    public static String getDecryptedText(String text) {

        char[] text_array = text.toCharArray();
        char[] decrypted = new char[text_array.length];

        for (int i = 0; i < text_array.length; i++) {
            char sim = text_array[i];

            if ((int)sim > 97 && (int)sim <= 122){
                decrypted[i] = (char)((int)sim-33);
            }
            else if((int)sim > 65 && (int)sim <= 90){
                decrypted[i] = (char)((int)sim+31);
            }
            else if((int)sim == 97){
                decrypted[i] = (char)((int)sim-7);
            }
            else if((int)sim == 65){
                decrypted[i] = (char)(122);
            }
            else{
                decrypted[i] = sim;
            }
        }
        return String.valueOf(decrypted);
    }

    public static void printCiphertext(){
        System.out.println(getCiphertext(text));
        printDecryptedText();

    }
    public static void printDecryptedText(){
        System.out.println(getDecryptedText(getCiphertext(text)));
    }
}
