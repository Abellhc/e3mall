package cn.e3mall.helperTest;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;

public class testPageHelper {

	@SuppressWarnings("resource")
	@Test
	public void testPageHelper1(){
		//获得初始化的spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		//获得mapper的代理对象
		 TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		 //设置分页信息
		 PageHelper.startPage(1, 30);
		 //执行分页
		 TbItemExample example = new TbItemExample();
		// Criteria criteria = example.createCriteria();
		 List<TbItem> list = itemMapper.selectByExample(example);
		 PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		 System.out.println(pageInfo.getTotal());
		 System.out.println(pageInfo.getPages());
		 System.out.println(pageInfo.getPageNum());
		 System.out.println(pageInfo.getPageSize());
		
		
	}
}
