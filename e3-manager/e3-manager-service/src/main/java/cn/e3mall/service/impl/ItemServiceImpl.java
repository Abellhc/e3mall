package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Override
	public TbItem getItemById(long id) {

		TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
		return tbItem;
	}
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		//取查询结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		//取商品列表
		//把记过封装到easyuidata
		EasyUIDataGridResult result = new EasyUIDataGridResult(total, list);
		//返回结果
		return result;
	}
	@Override
	public E3Result addItem(TbItem item, String desc) {
		//生成产品id
		long itemId = IDUtils.genItemId();
		//补全item的信息
		item.setId(itemId);
		//商品状态 ， 1 正常  2 下架  3 删除
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		//向商品插入数据
		tbItemMapper.insert(item);
		//创建一个TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
		//补全属性
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDesc.setItemId(itemId);
		//向商品插入数据
		tbItemDescMapper.insert(itemDesc);
		return E3Result.ok();
	}
	@Override
	public E3Result getItemDescById(long id) {
		TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(id);
		
		return E3Result.ok(itemDesc);
	}
	@Override
	public E3Result getTbItemById(long id) {

		TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
		return E3Result.ok(tbItem);
	}
}
