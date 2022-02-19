package org.jeecg.modules.demo.geronimo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.demo.geronimo.entity.PlatFillTest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.geronimo.entity.vo.VFill;

import java.util.List;

/**
 * @Description: 用户填报表
 * @Author: jeecg-boot
 * @Date: 2022-02-13
 * @Version: V1.0
 */
public interface IPlatFillTestService extends IService<PlatFillTest> {
	public boolean saveWithCheckUni(PlatFillTest platFillTest);


	/**
	 * 分页查询任务填报数据
	 *
	 * @param page
	 * @param taskId
	 * @return
	 */
	@Select("select * from `jeecg-boot`.plat_fill_test where task_id=#{taskId} ")
	@Results(id = "pageByTaskId", value = {
			@Result(column = "user_id", property = "userId"),
			@Result(column = "id", property = "id", id = true),
			@Result(column = "fill_date", property = "fillDate")
	})
	public IPage<VFill> pageFilledByTaskId(Page<VFill> page, @Param(value = "taskId") String taskId);
}
