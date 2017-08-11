package cn.e3mall.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

@Controller
public class IndexController {

	@Value("${index.slider.cid}")
	private Long indexSliderCid;
	@Autowired
	private ContentService contentService;
	@RequestMapping("/index")
	public String showIndex(Model model){
		//展示首页之前先查询打广告图
		List<TbContent> list = contentService.getContentList(indexSliderCid);
		//把结果返回给jsp页面
		model.addAttribute("ad1List", list);
		//返回视图
		return "index";
	}
}
