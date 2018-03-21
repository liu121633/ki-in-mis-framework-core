package org.kingqueen.kiinmis.model.eaysui.pojo;

import java.util.List;

/**
 * @ClassName ResponseDatagrid
 * @description EaysuiDatagrid 返回的数据格式
 * @author 刘洪君
 * @date 2017年11月30日上午1:49:14
 * @version V1.0
 */
public class ResponseDatagrid {

	private int total;
	private List rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
