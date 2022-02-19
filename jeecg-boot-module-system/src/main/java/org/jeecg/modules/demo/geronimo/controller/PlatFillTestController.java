package org.jeecg.modules.demo.geronimo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.geronimo.entity.PlatFillTest;
import org.jeecg.modules.demo.geronimo.service.IPlatFillTestService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 用户填报表
 * @Author: jeecg-boot
 * @Date:   2022-02-13
 * @Version: V1.0
 */
@Api(tags="用户填报表")
@RestController
@RequestMapping("/geronimo/platFillTest")
@Slf4j
public class PlatFillTestController extends JeecgController<PlatFillTest, IPlatFillTestService> {
	@Autowired
	private IPlatFillTestService platFillTestService;
	
	/**
	 * 分页列表查询
	 *
	 * @param platFillTest
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "用户填报表-分页列表查询")
	@ApiOperation(value="用户填报表-分页列表查询", notes="用户填报表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PlatFillTest platFillTest,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PlatFillTest> queryWrapper = QueryGenerator.initQueryWrapper(platFillTest, req.getParameterMap());
		Page<PlatFillTest> page = new Page<PlatFillTest>(pageNo, pageSize);
		IPage<PlatFillTest> pageList = platFillTestService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param platFillTest
	 * @return
	 */
	@AutoLog(value = "用户填报表-添加")
	@ApiOperation(value="用户填报表-添加", notes="用户填报表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PlatFillTest platFillTest) {
		platFillTestService.saveWithCheckUni(platFillTest);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param platFillTest
	 * @return
	 */
	@AutoLog(value = "用户填报表-编辑")
	@ApiOperation(value="用户填报表-编辑", notes="用户填报表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PlatFillTest platFillTest) {
		platFillTestService.updateById(platFillTest);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户填报表-通过id删除")
	@ApiOperation(value="用户填报表-通过id删除", notes="用户填报表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		platFillTestService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "用户填报表-批量删除")
	@ApiOperation(value="用户填报表-批量删除", notes="用户填报表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.platFillTestService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户填报表-通过id查询")
	@ApiOperation(value="用户填报表-通过id查询", notes="用户填报表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PlatFillTest platFillTest = platFillTestService.getById(id);
		if(platFillTest==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(platFillTest);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param platFillTest
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PlatFillTest platFillTest) {
        return super.exportXls(request, platFillTest, PlatFillTest.class, "用户填报表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, PlatFillTest.class);
    }

}
