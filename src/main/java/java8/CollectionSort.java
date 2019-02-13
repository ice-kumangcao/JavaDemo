package java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * java8的新特性
 * 对集合进行排序
 * @author ice
 * @date 19-1-9 上午9:22
 */
public class CollectionSort {
    public static void main(String[] args) {
        Apple apple1 = new Apple(10);
        Apple apple2 = new Apple(9);
        List<Apple> apples = new ArrayList<>();
        apples.add(apple1);
        apples.add(apple2);

        Collections.sort(apples, new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight() - a2.getWeight();
            }
        });

        // java 8
        apples.sort(Comparator.comparingInt(Apple::getWeight));

        apples.stream().sorted();
    }
}

class  Apple {

    private int weight;

    Apple(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
