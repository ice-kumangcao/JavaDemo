package mybatistest;

/**
 * @author ice
 * @date 19-1-17 下午2:34
 */
public interface OneEntityDao extends BaseMapper<OneEntity> {

    /**
     * 根据名称查询实体
     * @param oneEntity OneEntity实体
     * @return 结果
     */
    OneEntity getByName(OneEntity oneEntity);
}
