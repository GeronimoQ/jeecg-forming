package org.jeecg.modules.demo.geronimo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="欢迎页")
@RestController
@RequestMapping("/geronimo")
@Slf4j
public class Hello {

	/**
	 * hello world
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "欢迎页数据")
	@ApiOperation(value="欢迎页数据", notes="欢迎页数据")
	@GetMapping(value = "/hello")
	public Result<String> hello() {
		Result<String> result = new Result<String>();
		result.setResult("Hello World!");
		result.setSuccess(true);
		return result;
	}
}
