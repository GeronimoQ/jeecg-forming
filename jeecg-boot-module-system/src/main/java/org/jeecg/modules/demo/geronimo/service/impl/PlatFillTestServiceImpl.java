package org.jeecg.modules.demo.geronimo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.demo.geronimo.entity.PlatFillTest;
import org.jeecg.modules.demo.geronimo.entity.vo.VFill;
import org.jeecg.modules.demo.geronimo.mapper.PlatFillTestMapper;
import org.jeecg.modules.demo.geronimo.service.IPlatFillTestService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;

/**
 * @Description: 用户填报表
 * @Author: jeecg-boot
 * @Date: 2022-02-13
 * @Version: V1.0
 */
@Service
public class PlatFillTestServiceImpl extends ServiceImpl<PlatFillTestMapper, PlatFillTest> implements IPlatFillTestService {


	/**
	 * 检查任务填报唯一性后，保存填报数据
	 * @param platFillTest
	 * @return
	 */
	@Override
	public boolean saveWithCheckUni(PlatFillTest platFillTest) {
//		检查唯一性
		QueryWrapper<PlatFillTest> queryWrapper = new QueryWrapper();
		HashMap<String, Object> eq = new HashMap<>();
		eq.put("user_id", platFillTest.getUserId());
		eq.put("task_id", platFillTest.getTaskId());
		queryWrapper.select().allEq(eq);
		PlatFillTest one = this.getOne(queryWrapper);
		if (one == null) {
//		新增
			return this.save(platFillTest);
		} else {
//			更新
			platFillTest.setId(one.getId());
			return this.updateById(platFillTest);
		}
	}


	@Override
	public IPage<VFill> pageFilledByTaskId(Page<VFill> page, String taskId) {
		QueryWrapper<PlatFillTest> queryWrapper=new QueryWrapper<PlatFillTest>();
		queryWrapper.select("user_id","fill_date").eq("task_id",taskId);
//		IPage<PlatFillTest> page1 = this.page(page, queryWrapper);
		return null;
	}


}
