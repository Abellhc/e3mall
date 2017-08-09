package cn.e3mall.service;

import cn.e3mall.pojo.EasyUIDataGridResult;
import cn.e3mall.pojo.TbItem;

public interface ItemService {

	public TbItem getItemById(long id);
	EasyUIDataGridResult getItemList(int page,int rows);
}
