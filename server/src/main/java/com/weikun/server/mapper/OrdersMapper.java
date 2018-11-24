package com.weikun.server.mapper;

import com.weikun.server.model.Orders;
import com.weikun.server.model.OrdersExample;
import com.weikun.server.model.OrdersKey;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface OrdersMapper {
    @SelectProvider(type=OrdersSqlProvider.class, method="countByExample")
    long countByExample(OrdersExample example);

    @DeleteProvider(type=OrdersSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrdersExample example);

    @Delete({
        "delete from orders",
        "where orderid = #{orderid,jdbcType=INTEGER}",
          "and userid = #{userid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(OrdersKey key);

    @Insert({
        "insert into orders (orderid, userid, ",
        "orderdate, totalprice)",
        "values (#{orderid,jdbcType=INTEGER}, #{userid,jdbcType=INTEGER}, ",
        "#{orderdate,jdbcType=DATE}, #{totalprice,jdbcType=DECIMAL})"
    })
    int insert(Orders record);

    @InsertProvider(type=OrdersSqlProvider.class, method="insertSelective")
    int insertSelective(Orders record);

    @SelectProvider(type=OrdersSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="orderid", property="orderid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="orderdate", property="orderdate", jdbcType=JdbcType.DATE),
        @Result(column="totalprice", property="totalprice", jdbcType=JdbcType.DECIMAL)
    })
    List<Orders> selectByExample(OrdersExample example);

    @Select({
        "select",
        "orderid, userid, orderdate, totalprice",
        "from orders",
        "where orderid = #{orderid,jdbcType=INTEGER}",
          "and userid = #{userid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="orderid", property="orderid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="orderdate", property="orderdate", jdbcType=JdbcType.DATE),
        @Result(column="totalprice", property="totalprice", jdbcType=JdbcType.DECIMAL)
    })
    Orders selectByPrimaryKey(OrdersKey key);

    @UpdateProvider(type=OrdersSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    @UpdateProvider(type=OrdersSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    @UpdateProvider(type=OrdersSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Orders record);

    @Update({
        "update orders",
        "set orderdate = #{orderdate,jdbcType=DATE},",
          "totalprice = #{totalprice,jdbcType=DECIMAL}",
        "where orderid = #{orderid,jdbcType=INTEGER}",
          "and userid = #{userid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Orders record);
}