import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Task_3
{
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

    public static int wordsCount;
    static
    {
        System.out.println("Введите N:");
        try
        {
            wordsCount = Integer.parseInt(Helpers.getText());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String[] getWordsArray(String text){
        String clean_text = text.replaceAll("[^\\\\da-zA-Zа-яёА-ЯЁ ]", "").toLowerCase();
        String[] words = clean_text.split(" ");
        return words;
}

    public static Map<String, Integer> getRepeatedWords(String[] text)
    {
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String temp : text)
        {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }

        Map<String, Integer> repeatedWords = new HashMap<String, Integer>();
        Iterator iterator = map.keySet().iterator();

        while(iterator.hasNext())
        {
            Object key   = iterator.next();
            Object value = map.get(key);

            if (map.get(key) >= wordsCount)
            {
                repeatedWords.put((String) key, map.get(key));
            }
        }
        return repeatedWords;
    }

    public static void jsonGenerate() throws IOException
    {
        Map<String, Integer> map = getRepeatedWords(getWordsArray(text));
        JSONObject json = new JSONObject();

        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext())
        {
            Object key   = iterator.next();
            Object value = map.get(key);
            json.put((String) key, map.get(key));
        }
        System.out.println(json);
    }
}
