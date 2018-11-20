package clonetest;

/**
 * @author ice
 * @date 18-11-5 上午10:15
 */
public class ExampleEntity implements Cloneable {

    private String id;

    private String name;

    private String sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
