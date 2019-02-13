package mybatistest;

/**
 * Service
 * @author ice
 * @date 19-1-17 下午2:40
 */
public interface OneEntityService extends BaseService<OneEntity> {

    /**
     * 插入oneEnitty实体
     * @param oneEntity 实体
     * @return 影响行
     */
    int insertOneEntity(OneEntity oneEntity);
}
