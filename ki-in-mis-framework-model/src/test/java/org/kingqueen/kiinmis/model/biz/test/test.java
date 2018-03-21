package org.kingqueen.kiinmis.model.biz.test;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.runner.RunWith;
import org.kingqueen.kiinmis.model.dao.index.IIndexDao;
import org.kingqueen.kiinmis.model.eaysui.pojo.Tree;
import org.kingqueen.kiinmis.model.pojo.Kiin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName test
 * @description 功能描述
 * @author 刘洪君
 * @date 2017年12月19日下午1:18:59
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-ctx.xml")
public class test {

	@Autowired
	private IIndexDao indexDao;

	@Test
	public void test() {										
		
		List<Tree> list = indexDao.findJuniorKiin("0");
		
		System.out.println(JSON.toJSONString(list));
		
	}
}
