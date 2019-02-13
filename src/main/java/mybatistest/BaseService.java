package mybatistest;

/**
 * mybatis service
 * @author ice
 * @date 19-1-17 下午2:37
 */
public interface BaseService<T> {

    /**
     * 插入一个实体
     * @param entity 实体
     * @return 影响行
     */
    int insert(T entity);
}
