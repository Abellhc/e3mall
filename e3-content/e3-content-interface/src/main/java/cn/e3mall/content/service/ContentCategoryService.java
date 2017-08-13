package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNode;

/**
 * 商品目录
 * @author Abel
 *
 */
public interface ContentCategoryService {

	List<TreeNode> getContentCategoryList(long parentId);
	E3Result addContentCategory(long parentId,String name);
	}
