package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.pojo.E3Result;
import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping("/item/save")
	@ResponseBody
	public E3Result addItem(TbItem item, String desc) {
		E3Result e3Result = itemService.addItem(item, desc);
		return e3Result;
	}

	@RequestMapping("/rest/item/query/item/desc/{id}")
	@ResponseBody
	public E3Result getItemDescById(@PathVariable long id){
		 E3Result e3Result = itemService.getItemDescById(id);
		return e3Result;
	}
	
	@RequestMapping("/rest/item/param/item/query/{id}")
	@ResponseBody
	public E3Result getTbItemById(@PathVariable long id){
		E3Result e3Result = itemService.getTbItemById(id);
		return e3Result;
	}
}
