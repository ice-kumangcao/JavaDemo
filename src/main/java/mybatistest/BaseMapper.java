package mybatistest;

/**
 * like mybatis plus BaseMapper
 * @author ice
 * @date 19-1-17 下午2:32
 */
public interface BaseMapper<T> {

    /**
     * 插入entity
     * @param entity 实体
     * @return 影响行
     */
    int insert(T entity);
}
