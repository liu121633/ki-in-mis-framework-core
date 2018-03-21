package org.kingqueen.kiinmis.model.biz.Hourrecord;

import com.alibaba.fastjson.JSON;
import org.kingqueen.kiinmis.model.dao.hourlog.HourlogMapper;
import org.kingqueen.kiinmis.model.dao.hourrecord.HourrecordMapper;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.eaysui.pojo.ResponseDatagrid;
import org.kingqueen.kiinmis.model.pojo.*;
import org.kingqueen.kiinmis.model.vo.HourrecordVo.HourrecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("HourrecordBiz")
public class HourrecordBiz {

    @Autowired
    private HourrecordMapper hourrecordMapper;


    /**
     * 分页查询
     *
     * @param datagrid
     * @return
     */
    public ResponseDatagrid findList(RequestDatagrid datagrid, List<String> kiinids) {
        ResponseDatagrid responseDatagrid = new ResponseDatagrid();
        //取得查询条件
        HourrecordVo hourrecordVo = JSON.parseObject(datagrid.getWhereJson(), HourrecordVo.class);
        if (hourrecordVo.getChessNumber() == null) {
            hourrecordVo.setChessNumber(kiinids.get(0));
        }
        //查询行数
        int conunt = hourrecordMapper.findCount(hourrecordVo);
        responseDatagrid.setTotal(conunt);
        //查询数据
        List<HourrecordVo> hourrecordVoList = hourrecordMapper.findList(datagrid, hourrecordVo);
        responseDatagrid.setRows(hourrecordVoList);
        return responseDatagrid;
    }

    @Autowired
    private HourlogMapper hourlogMapper;

    /**
     * 签到
     * @param id
     * @return
     */
    public Map<String, Object> sign(Integer id, User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "500");
        map.put("msg", "发生未知错误");
        //得到传入编号的课时余额的持久对象
        HourrecordExample hourrecordExample = new HourrecordExample();

        hourrecordExample.or().andHourrecordidEqualTo(id);
        List<Hourrecord> hourrecords = hourrecordMapper.selectByExample(hourrecordExample);
        //判断是否有找到
        if (hourrecords.size() > 0) {
            Hourrecord hourrecord = hourrecords.get(0);
            //判断课时余额是否足够签到
            if (hourrecord.getResidue() > 0) {
                //修改课时减少1
                hourrecord.setResidue(hourrecord.getResidue() - 1);
                //如果修改后的課時小于0 修改状态
                if(hourrecord.getResidue() - 1<0){
                    hourrecord.setState(1);
                }
                hourrecordMapper.updateByPrimaryKeySelective(hourrecord);
                //生成日志 添加日志
                Hourlog hourlog = new Hourlog();
                hourlog.setHourrecordid(hourrecord.getHourrecordid());
                hourlog.setOperationtime(new Date());
                hourlog.setOperationtype("签到");
                hourlog.setQuantity(-1);
                hourlog.setUserid(user.getUserNumber());
                hourlogMapper.insertSelective(hourlog);
                //操作完畢
                map.put("code", "200");
                map.put("msg", "签到成功");
            } else {
                map.put("msg", "剩余课时不足,无法进行签到");
            }
        }else{
            map.put("msg", "错误的参数传入 请刷新后在试");
        }
        return map;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Map<String,Object> del(Integer hourrecordid){
        Map<String,Object> map = new HashMap<>();
        try {
            Hourrecord hourrecord =hourrecordMapper.selectByPrimaryKey(hourrecordid);

            if(hourrecord==null){
                map.put("code","400");
                map.put("msg","错误的编号!");
                return map;
            }

            if(hourrecord.getResidue()>0){
                map.put("code","400");
                map.put("msg","课时数量大于0 不可删除");
                return map;
            }
            //刪除課時記錄
            hourrecordMapper.deleteByPrimaryKey(hourrecordid);
            //刪除日志
            HourlogExample hourlogExample = new HourlogExample();
            hourlogExample.or().andHourrecordidEqualTo(hourrecordid);
            hourlogMapper.deleteByExample(hourlogExample);
            map.put("code","200");
            map.put("msg","删除成功!");

        }catch (Exception e){
            e.printStackTrace();
            map.put("code","500");
            map.put("msg","服务器内部错误!请稍后重试.");
        }
        return  map;


    }
}
