package cn.e3mall.service;

import cn.e3mall.pojo.E3Result;
import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;

public interface ItemService {

	TbItem getItemById(long id);
	E3Result getTbItemById(long id);
	EasyUIDataGridResult getItemList(int page,int rows);
	E3Result addItem(TbItem item, String desc);
	E3Result getItemDescById(long id);
	
}
