package org.jeecg.modules.demo.geronimo.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 任务表
 * @Author: jeecg-boot
 * @Date:   2022-01-25
 * @Version: V1.0
 */
@Data
@TableName("plat_task_test")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="plat_task_test对象", description="任务表")
public class PlatTaskTest implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private java.lang.String userId;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**任务标题*/
	@Excel(name = "任务标题", width = 15)
    @ApiModelProperty(value = "任务标题")
    private java.lang.String title;
	/**人物介绍*/
	@Excel(name = "人物介绍", width = 15)
    @ApiModelProperty(value = "人物介绍")
    private java.lang.String instr;
	/**模板ID*/
	@Excel(name = "模板ID", width = 15)
    @ApiModelProperty(value = "模板ID")
    private java.lang.String modelId;
	/**是否在用*/
	@Excel(name = "是否在用", width = 15)
    @ApiModelProperty(value = "是否在用")
    private boolean disabled;
}
