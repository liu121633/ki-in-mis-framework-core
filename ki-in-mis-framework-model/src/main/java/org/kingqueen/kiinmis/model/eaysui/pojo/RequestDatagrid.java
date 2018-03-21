package org.kingqueen.kiinmis.model.eaysui.pojo;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName RequestDatagrid
 * @description EaysuiDatagrid请求的数据格式
 * @author 刘洪君
 * @date 2017年11月30日上午12:40:12
 * @version V1.0
 */
public class RequestDatagrid {
	
	//第几页
	private Integer page;
	//每页显示几行
	private Integer rows;
	//排序的列
	private String sort;
	//排序的方式
	private String order;
	//自定义条件
	private String whereJson;


	public RequestDatagrid() {
		// TODO Auto-generated constructor stub
	}



	public Integer getPage() {
		return page;
	}

	public void setRows(Integer rows) {
		// 等于null 没有计算过 反之计算过 不用计算
		this.page = (this.page != null) ? (this.page - 1) * rows : this.page;
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setPage(Integer page) {
		// 在给要查询的第几页赋值的时候 计算下 sql (要的数据 当前页-1) *每页显示数量
		this.page = (this.rows != null) ? (page - 1) * this.rows : page;

	}

	public Integer getRows() {
		return rows;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getWhereJson() {
		return whereJson;
	}

	public void setWhereJson(String whereJson) {
		this.whereJson = whereJson;
	}

}
