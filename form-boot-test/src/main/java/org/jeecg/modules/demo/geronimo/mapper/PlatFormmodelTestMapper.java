package org.jeecg.modules.demo.geronimo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.demo.geronimo.entity.PlatFormmodelTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 测试
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
public interface PlatFormmodelTestMapper extends BaseMapper<PlatFormmodelTest> {


	@Select("select id as Id,user_id as userId from plat_formmodel_dev")
	public List<PlatFormmodelTest>  queryListByUId(String userId);

}
