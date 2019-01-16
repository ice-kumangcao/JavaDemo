package designpattern;

/**
 * @author ice
 * @date 19-1-7 下午3:14
 */
public class FacadeTest {
    public static void main(String[] args) {

        AbstractDrawer abstractDrawer3 =
                new KeyDrawer("第三个抽屉", null);

        AbstractDrawer abstractDrawer2 =
                new EmbeddedKeyDrawer("第二个抽屉",
                        "2",null,abstractDrawer3);

        AbstractDrawer abstractDrawer =
                new EmbeddedKeyDrawer("第一个抽屉",
                        "1","2",abstractDrawer2);

        abstractDrawer.open("1");
    }
}

abstract class AbstractDrawer {

    String drawerName;

    String currentKey;

    public AbstractDrawer(String drawerName, String currentKey) {
        this.drawerName = drawerName;
        this.currentKey = currentKey;
    }

    public abstract void open(String key);
}
class KeyDrawer extends AbstractDrawer {

    public KeyDrawer(String drawerName, String currentKey) {
        super(drawerName, currentKey);
    }

    @Override
    public void open(String key) {
        System.out.println(this.drawerName + " open ");
    }
}
class EmbeddedKeyDrawer extends AbstractDrawer {

    private String nextKey;

    private AbstractDrawer nextDrawer;

    public EmbeddedKeyDrawer(String drawerName, String currentKey,
                     String nextKey, AbstractDrawer nextDrawer) {
        super(drawerName, currentKey);
        this.nextKey = nextKey;
        this.nextDrawer = nextDrawer;
    }

    public void open(String key) {
        if (key.equals(currentKey)) {
            this.open();
            if (nextDrawer != null) {
                nextDrawer.open(nextKey);
            }
        }
    }

    protected void open() {
        System.out.println(drawerName + " open ");
    }
}