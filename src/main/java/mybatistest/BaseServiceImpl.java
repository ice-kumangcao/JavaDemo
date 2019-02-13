package mybatistest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * BaseService imple
 * @author ice
 * @date 19-1-17 下午2:38
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> implements BaseService<T> {

    @Autowired
    protected M baseDao;

    /**
     * 插入一个实体
     *
     * @param entity 实体
     * @return 影响行
     */
    @Override
    public int insert(T entity) {
        return baseDao.insert(entity);
    }
}
