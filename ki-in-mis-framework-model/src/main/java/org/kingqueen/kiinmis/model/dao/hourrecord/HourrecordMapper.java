package org.kingqueen.kiinmis.model.dao.hourrecord;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.eaysui.pojo.RequestDatagrid;
import org.kingqueen.kiinmis.model.pojo.Hourrecord;
import org.kingqueen.kiinmis.model.pojo.HourrecordExample;
import org.kingqueen.kiinmis.model.vo.HourrecordVo.HourrecordVo;

public interface HourrecordMapper {
    long countByExample(HourrecordExample example);

    int deleteByExample(HourrecordExample example);

    int deleteByPrimaryKey(Integer hourrecordid);

    int insert(Hourrecord record);

    int insertSelective(Hourrecord record);

    List<Hourrecord> selectByExample(HourrecordExample example);

    Hourrecord selectByPrimaryKey(Integer hourrecordid);

    int updateByExampleSelective(@Param("record") Hourrecord record, @Param("example") HourrecordExample example);

    int updateByExample(@Param("record") Hourrecord record, @Param("example") HourrecordExample example);

    int updateByPrimaryKeySelective(Hourrecord record);

    int updateByPrimaryKey(Hourrecord record);


    /**
     * 分页查询数据
     * @param requestDatagrid
     * @param hourrecordVo
     * @return
     */
    public List<HourrecordVo> findList(
            @Param("r") RequestDatagrid requestDatagrid,
            @Param("h") HourrecordVo hourrecordVo);
    /**
     * 查询行数
     * @param hourrecordVo
     * @return
     */
    public int findCount(@Param("h") HourrecordVo hourrecordVo);
}