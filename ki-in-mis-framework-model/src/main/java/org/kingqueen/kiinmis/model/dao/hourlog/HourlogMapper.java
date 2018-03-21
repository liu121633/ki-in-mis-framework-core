package org.kingqueen.kiinmis.model.dao.hourlog;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kingqueen.kiinmis.model.pojo.Hourlog;
import org.kingqueen.kiinmis.model.pojo.HourlogExample;
import org.kingqueen.kiinmis.model.vo.hourlog.HourlogVo;

public interface HourlogMapper {
    long countByExample(HourlogExample example);

    int deleteByExample(HourlogExample example);

    int deleteByPrimaryKey(Integer hourlogid);

    int insert(Hourlog record);

    int insertSelective(Hourlog record);

    List<Hourlog> selectByExample(HourlogExample example);

    Hourlog selectByPrimaryKey(Integer hourlogid);

    int updateByExampleSelective(@Param("record") Hourlog record, @Param("example") HourlogExample example);

    int updateByExample(@Param("record") Hourlog record, @Param("example") HourlogExample example);

    int updateByPrimaryKeySelective(Hourlog record);

    int updateByPrimaryKey(Hourlog record);

    List<HourlogVo> findList(@Param("h") Hourlog hourlog, @Param("page") Integer page, @Param("size") Integer size);

    int findCount(@Param("h") Hourlog hourlog);
}