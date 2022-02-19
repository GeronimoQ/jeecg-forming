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
 * @Description: 用户填报表
 * @Author: jeecg-boot
 * @Date:   2022-02-13
 * @Version: V1.0
 */
@Data
@TableName("plat_fill_test")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="plat_fill_test对象", description="用户填报表")
public class PlatFillTest implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**任务Id*/
	@Excel(name = "任务Id", width = 15)
    @ApiModelProperty(value = "任务Id")
    private java.lang.String taskId;
	/**填报用户ID*/
	@Excel(name = "填报用户ID", width = 15)
    @ApiModelProperty(value = "填报用户ID")
    private java.lang.String userId;
	/**填报时间*/
	@Excel(name = "填报时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "填报时间")
    private java.util.Date fillDate;
	/**表单数据*/
	@Excel(name = "表单数据", width = 15)
    @ApiModelProperty(value = "表单数据")
    private java.lang.String formData;
}
