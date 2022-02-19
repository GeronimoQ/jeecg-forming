package org.jeecg.modules.demo.geronimo.controller;

import java.sql.ResultSet;
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
import org.jeecg.modules.demo.geronimo.entity.PlatTaskTest;
import org.jeecg.modules.demo.geronimo.service.IPlatTaskTestService;

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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import utill.qr.QrUtil;

/**
 * @Description: 任务表
 * @Author: jeecg-boot
 * @Date: 2022-01-25
 * @Version: V1.0
 */
@Api(tags = "任务表")
@RestController
@RequestMapping("/geronimo/platTaskTest")
@Slf4j
public class PlatTaskTestController extends JeecgController<PlatTaskTest, IPlatTaskTestService> {
	@Autowired
	private IPlatTaskTestService platTaskTestService;

	/**
	 * 分页列表查询
	 *
	 * @param platTaskTest
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "任务表-分页列表查询")
	@ApiOperation(value = "任务表-分页列表查询", notes = "任务表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PlatTaskTest platTaskTest,
								   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
								   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PlatTaskTest> queryWrapper = QueryGenerator.initQueryWrapper(platTaskTest, req.getParameterMap());
		Page<PlatTaskTest> page = new Page<PlatTaskTest>(pageNo, pageSize);
		IPage<PlatTaskTest> pageList = platTaskTestService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 *
	 * @param platTaskTest
	 * @return
	 */
	@AutoLog(value = "任务表-添加")
	@ApiOperation(value = "任务表-添加", notes = "任务表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PlatTaskTest platTaskTest) {
		platTaskTestService.save(platTaskTest);
		return Result.OK("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param platTaskTest
	 * @return
	 */
	@AutoLog(value = "任务表-编辑")
	@ApiOperation(value = "任务表-编辑", notes = "任务表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PlatTaskTest platTaskTest) {
		platTaskTestService.updateById(platTaskTest);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务表-通过id删除")
	@ApiOperation(value = "任务表-通过id删除", notes = "任务表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
		platTaskTestService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "任务表-批量删除")
	@ApiOperation(value = "任务表-批量删除", notes = "任务表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
		this.platTaskTestService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "任务表-通过id查询")
	@ApiOperation(value = "任务表-通过id查询", notes = "任务表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
		PlatTaskTest platTaskTest = platTaskTestService.getById(id);
		if (platTaskTest == null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(platTaskTest);
	}

	/**
	 * 根据用户ID查询相的任务
	 *
	 * @param userId
	 * @return
	 */
	@AutoLog(value = "任务表-通过userId查询列表")
	@ApiOperation(value = "任务表-通过id查询", notes = "任务表-通过id查询")
	@GetMapping(value = "/queryListByUserId")
	public Result<?> queryListByUserId(@RequestParam(name = "userId", required = true) String userId) {
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.select().eq("user_id",userId);
		List list = platTaskTestService.list(queryWrapper);
		if (list==null){
			return Result.error("未找到对应数据");
		}
		return Result.OK(list);
	}

	@AutoLog(value = "任务表-获取任务QR")
	@ApiOperation(value = "任务表-获取任务QR",notes = "任务表-获取任务QR")
	@GetMapping(value = "/taskQR")
	public void getQRImage(HttpServletResponse response,@RequestParam(value = "taskId")String taskId) {
		byte[] qrCode=null;
		try {
			qrCode= QrUtil.generateImageToByte(taskId, 100, 100);
			response.setContentType(MediaType.IMAGE_PNG_VALUE);
			response.getOutputStream().write(qrCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param platTaskTest
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, PlatTaskTest platTaskTest) {
		return super.exportXls(request, platTaskTest, PlatTaskTest.class, "任务表");
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
		return super.importExcel(request, response, PlatTaskTest.class);
	}

}
