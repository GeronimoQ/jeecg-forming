package org.jeecg.modules.demo.geronimo.service;

import org.jeecg.modules.demo.geronimo.entity.PlatTaskTest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 任务表
 * @Author: jeecg-boot
 * @Date:   2022-01-25
 * @Version: V1.0
 */
public interface IPlatTaskTestService extends IService<PlatTaskTest> {

	public byte[] getTaskQR(String taskId);
}
