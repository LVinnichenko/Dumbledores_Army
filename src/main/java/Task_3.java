import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            text = getText();
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
            wordsCount = Integer.parseInt(getText());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String getText() throws IOException
    {
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        return text.readLine();
    }

    public static String getRepeatedWords(String text)
    {
        return text.replaceAll("[^\\\\da-zA-Zа-яёА-ЯЁ ]", "").toLowerCase();
    }

    public static String[] getWords(String text)
    {
        return text.split(" ");
    }

    public static Map<String, Integer> getWordsCounter(String[] text)
    {
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String temp : text)
        {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        return map;
    }

    public static void jsonGenerate() throws IOException
    {
        Map<String, Integer> map = getWordsCounter(getWords(getRepeatedWords(text)));
        JSONObject json = new JSONObject();
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext())
        {
            Object key   = iterator.next();
            Object value = map.get(key);

            if (map.get(key) >= wordsCount)
            {
                json.put((String) key, map.get(key));
            }
        }
        System.out.println(json);
    }
}
