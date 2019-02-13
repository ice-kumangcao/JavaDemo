/**
 * 此类生成了.class文件，不要加package包路径
 * @author ice
 * @date 18-12-25 上午10:04
 */
public class TestEntity {

    private String name;

    private String sex;

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

    public static void main(String[] args) {
        TestEntity testEntity = new TestEntity();
        System.out.println(testEntity);
    }
}
