package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.tools.javac.jvm.CRTable;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	@Override
	public EasyUIDataGridResult getContentList(long categoryId, int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		//插入查询条件
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = tbContentMapper.selectByExample(example);
		//取查询结果
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		//取出分页结果total
		long total = pageInfo.getTotal();
		//封装datagridresult对象
		EasyUIDataGridResult result = new EasyUIDataGridResult(total, list);
		//返回easyUidatagridresult
		return result;
	}
	@Override
	public E3Result addContent(TbContent tbContent) {
		//补全信息
		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		//保存
		tbContentMapper.insert(tbContent);
		
		return E3Result.ok();
	}
	@Override
	public List<TbContent> getContentList(long cid) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = tbContentMapper.selectByExample(example);
		return list;
	}

}
