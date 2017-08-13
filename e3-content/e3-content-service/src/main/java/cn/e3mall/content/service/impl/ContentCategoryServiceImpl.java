package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNode;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	@Override
	public List<TreeNode> getContentCategoryList(long parentId) {
		//根据id进行条件查询
		//创建条件
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		//把内容分类列表转化成TreeNode列表
		List<TreeNode> treeNodeList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(tbContentCategory.getId());
			treeNode.setText(tbContentCategory.getName());
			treeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			treeNodeList.add(treeNode);
		}
		//返回结果
		return treeNodeList;
	}
	@Override
	public E3Result addContentCategory(long parentId, String name) {
		// 1、创建一个TbContentCategory对象
		TbContentCategory tbContentCategory = new TbContentCategory();
		
		// 2、补全属性
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setName(name);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setIsParent(false);
		Date date = new Date();
		tbContentCategory.setCreated(date);
		tbContentCategory.setUpdated(date);
		tbContentCategory.setStatus(1);
		// 3、插入到表中。
		tbContentCategoryMapper.insert(tbContentCategory);
		System.out.println(tbContentCategory.getId());
		// 4、需要判断父节点的isparent

		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		// 5、如果父节点的isparent为false应该改为true
		if (!contentCategory.getIsParent()) {
			contentCategory.setIsParent(true);
			tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
		}
		
		// 6、返回E3Result其中包含TbContentCategory对象
		return E3Result.ok(tbContentCategory);
	}

}
