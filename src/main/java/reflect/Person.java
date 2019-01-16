package reflect;

/**
 * @author ice
 * @date 18-12-27 上午11:18
 */
public class Person {

    public String name;

    private int age;

    public Person() {
        //System.out.println("Person 构造函数");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person[name='" + name + "', age='" + age + "']";
    }

    private void privateMethod() {
        System.out.println("私有的方法");
    }

    public void setName(String name, int age) {
        System.out.println("getName is: " + getName());
        System.out.println("getAge is: " + getAge());
    }
}
