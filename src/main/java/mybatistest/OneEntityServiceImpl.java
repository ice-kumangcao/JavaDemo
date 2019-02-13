package mybatistest;

/**
 * Service Imple
 * @author ice
 * @date 19-1-17 下午2:44
 */
public class OneEntityServiceImpl
        extends BaseServiceImpl<OneEntityDao, OneEntity>
        implements OneEntityService {
    /**
     * 插入oneEnitty实体
     *
     * @param oneEntity 实体
     * @return 影响行
     */
    @Override
    public int insertOneEntity(OneEntity oneEntity) {
        return baseDao.insert(oneEntity);
    }
}
