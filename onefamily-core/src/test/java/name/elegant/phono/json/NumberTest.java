package name.elegant.phono.json;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Author: Garry King
 * Date: 13-7-29 ÏÂÎç10:55
 * E-mail:flyhzq@sina.com
 */
public class NumberTest {

    private static Random random = new Random();

    private List<PersonNumber> testList = generateThousandPerson();

    private List<String> testList_de = generateThousandPersonString();

    private static Gson gson = new Gson();

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final int TIMES = 10000 * 10;


    static {
        objectMapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
//        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
    }


    @Test
    public void testEnviroment() {
        System.out.println("===================");
        System.out.println("  CODE=200 , isOk  ");
        System.out.println("===================");
    }

    @Test
    public void testCode() throws IOException {
        int type = 2;
        testGson(type);
        testJackson(type);
        testFastJson(type);
    }

    private void testGson(int type) {
        long before = new Date().getTime();
        for (int i = 0; i < TIMES; i++) {
            for (int j = 0; j < testList.size() - 1; j++) {
                if (type == 1)
                    gson.toJson(testList.get(j));
                else
                    gson.fromJson(testList_de.get(j), PersonNumber.class);
//                System.out.println(gson.toJson(testList.get(j)));
            }
        }
        long after = new Date().getTime();
        showTimes("gson", before, after);
    }

    private void testJackson(int type) throws IOException {
        long before = new Date().getTime();
        for (int i = 0; i < TIMES; i++) {
            for (int j = 0; j < testList.size() - 1; j++) {
                if (type == 1)
                    objectMapper.writeValueAsString(testList.get(j));
                else
                    objectMapper.readValue(testList_de.get(j), PersonNumber.class);
//                System.out.println(objectMapper.writeValueAsString(testList.get(j)));
            }
        }
        long after = new Date().getTime();
        showTimes("jackson", before, after);
    }

    private void testFastJson(int type) {
        long before = new Date().getTime();
        for (int i = 0; i < TIMES; i++) {
            for (int j = 0; j < testList.size() - 1; j++) {
                if (type == 1)
                    JSON.toJSONString(testList.get(j));
                else
                    JSON.parseObject(testList_de.get(j), PersonNumber.class);
//                System.out.println(JSON.toJSONString(testList.get(j)));
            }
        }
        long after = new Date().getTime();
        showTimes("fastjson", before, after);
    }

    private void showTimes(String type, long before, long after) {
        double diff = after - before;
        diff = diff / 1000;
        System.out.println(type + " used:" + diff + " seconds");
    }

    private List<PersonNumber> generateThousandPerson() {
        List<PersonNumber> list = new ArrayList<PersonNumber>();
        for (int i = 0; i < 1000; i++) {
            list.add(generatePerson());
        }
        return list;
    }

    private List<String> generateThousandPersonString() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1000; i++) {
            list.add(generatePersonString());
        }
        return list;
    }

    private String generatePersonString() {
        return "{\"age\":" + random.nextInt(100) + ",\"height\":" + random.nextDouble() + 1 +
                ",\"birth\":" + System.currentTimeMillis() + ",\"cardId\":" + random.nextLong() + "}";
    }

    private PersonNumber generatePerson() {
        PersonNumber person = new PersonNumber();
        person.setAge(random.nextInt(100));
        person.setHeight(random.nextDouble() + 1);
        person.setBirth(System.currentTimeMillis());
        person.setCardId(random.nextLong());
        return person;
    }

}

class PersonNumber {
    private int age;
    private double height;
    private long birth;
    private long cardId;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getBirth() {
        return birth;
    }

    public void setBirth(long birth) {
        this.birth = birth;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}

