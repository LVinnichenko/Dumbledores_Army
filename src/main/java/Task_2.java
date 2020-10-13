/*
Анализатор текста:
Должен получать на вход три параметра: анализируемый текст,
максимально допустимую длину, список запрещенных слов.
Результатом работы этой функции должен быть JSON, в котором будут следующие поля:
- "length" - длина строки
- "pure_length" - длина строки без учета пробелов
- "origin_text" - текст, полученный на входе
- "pure_text" - текст, в котором все запрещенные слова из списка были заменены на три звездочки
- "pure_short_text" - текст из pure_text, обрезанный на максимальном символе. Если этот символ не последний, надо это показать, добавив многоточие в конец.
Пример:
Дано:
text: «Не забывайте о том, что все великие волшебники в истории в свое время были такими же, как мы, – школьниками. Если у них получилось, то получится и у нас», – Гарри Поттер.
maxlen: 35
forbidden_words: ["волшебники", "Гарри Поттер"]

Результат функции:

{
"length":171,
"pure_length":140
"origin_text":"«Не забывайте о том, что все великие волшебники в истории в свое время были такими же, как мы, – школьниками. Если у них получилось, то получится и у нас», – Гарри Поттер."
"pure_text":"«Не забывайте о том, что все великие *** в истории в свое время были такими же, как мы, – школьниками. Если у них получилось, то получится и у нас», – ***."
"pure_short_text":"Не забывайте о том, что все великие..."
}
 */

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Task_2 {
    public static String text;

    static {
        try {
            text = getText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int max_length;

    static {
        try {
            max_length = getMaxLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String[] words;

    static {
        try {
            words = getStopWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getText() throws IOException {
        System.out.println("Введите текст:");
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        return text.readLine();
    }
    public static Integer getMaxLength() throws IOException {
        System.out.println("Установите максимальную длину:");
        BufferedReader max_length = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(max_length.readLine());
    }
    public static String[] getStopWords() throws IOException {
        System.out.println("Введите список нежелательных слов через запятую:");
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        String words = text.readLine();
        System.out.println(words.split(", "));
        return words.split(", ");
    }

    public static String getPureText() {
            String pure_text = text;
        for (int i = 0; i < words.length; i++) {
            System.out.println("DEBUG " + words[i]);
            pure_text = pure_text.replaceAll(words[i], "***");
        }
        System.out.println(pure_text);
        return pure_text;
    }

    public static String getShotText() throws IOException {
        String pureText = getPureText();

        String shortText = "";
        if (pureText.length() <= max_length){
            try {
                shortText = pureText.substring(0, max_length);
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
       else if (pureText.length() > max_length){
            shortText = pureText.substring(0, max_length).concat("...");
        }
        return shortText;
    }

    public static void jsonGenerate() throws IOException {

        String text_without_spaces = text.replaceAll(" ", "");
        String pure_text = getPureText();

        JSONObject json = new JSONObject();
        json.put("length", text.length());
        json.put("pure_length", text_without_spaces.length());
        json.put("origin_text", text);
        json.put("pure_text", pure_text);
        json.put("pure_short_text", getShotText());
        System.out.println(json);
    }

}
