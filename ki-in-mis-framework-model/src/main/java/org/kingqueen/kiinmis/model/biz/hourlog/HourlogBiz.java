package org.kingqueen.kiinmis.model.biz.hourlog;

import com.alibaba.fastjson.JSON;
import org.kingqueen.kiinmis.model.dao.hourlog.HourlogMapper;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.pojo.Hourlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("HourlogBiz")
public class HourlogBiz {


    @Autowired
    private HourlogMapper hourlogMapper;

    /**
     * 分页查询
     * @param requestDatagrid
     * @return
     */
    public ResponseDatagrid findlist(RequestDatagrid requestDatagrid) {
        Map<String, Object> map = new HashMap<>();

        Hourlog hourlog = JSON.parseObject(requestDatagrid.getWhereJson(), Hourlog.class);

        ResponseDatagrid responseDatagrid = new ResponseDatagrid();
        //设置数据
        responseDatagrid.setRows(hourlogMapper.findList(hourlog, requestDatagrid.getPage(), requestDatagrid.getRows()));
        //设置行数
        responseDatagrid.setTotal(hourlogMapper.findCount(hourlog));
        return responseDatagrid;
    }


}
