package org.jeecg.modules.demo.geronimo.com.entity;

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
 * @Description: 表单模板(dev)
 * @Author: jeecg-boot
 * @Date:   2022-01-08
 * @Version: V1.0
 */
@Data
@TableName("plat_formmodel_dev")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="plat_formmodel_dev对象", description="表单模板(dev)")
public class PlatFormmodelDev implements Serializable {
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
	/**表达介绍*/
	@Excel(name = "表达介绍", width = 15)
    @ApiModelProperty(value = "表达介绍")
    private java.lang.String formInstr;
	/**表单元素*/
	@Excel(name = "表单元素", width = 15)
    @ApiModelProperty(value = "表单元素")
    private java.lang.String itemList;
	/**表单配置*/
	@Excel(name = "表单配置", width = 15)
    @ApiModelProperty(value = "表单配置")
    private java.lang.String formConfig;
}
