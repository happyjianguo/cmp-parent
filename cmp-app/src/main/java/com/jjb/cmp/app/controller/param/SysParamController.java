package com.jjb.cmp.app.controller.param;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jjb.unicorn.web.controller.BaseController;

/**
 * @author hn
 * @description cmp系统参数配置
 * @date 2018年8月29日15:15:03
 */

@Controller
@RequestMapping("/sysParam")
public class SysParamController extends BaseController {

    //	@Autowired
//	private SysParamService paramService;
//    @Autowired
//    private CacheContext cacheContext;

    /*
     * 进入参数页面
     */
    @RequestMapping("/page")
    public String page() {
        return "param/sysParam.ftl";
    }
}
