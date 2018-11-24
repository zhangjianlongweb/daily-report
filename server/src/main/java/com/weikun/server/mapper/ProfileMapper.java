package com.weikun.server.mapper;

import com.weikun.server.model.Profile;
import com.weikun.server.model.ProfileExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ProfileMapper {
    @SelectProvider(type=ProfileSqlProvider.class, method="countByExample")
    long countByExample(ProfileExample example);

    @DeleteProvider(type=ProfileSqlProvider.class, method="deleteByExample")
    int deleteByExample(ProfileExample example);

    @Delete({
        "delete from profile",
        "where userid = #{userid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userid);

    @Insert({
        "insert into profile (userid, lang, ",
        "catid)",
        "values (#{userid,jdbcType=INTEGER}, #{lang,jdbcType=VARCHAR}, ",
        "#{catid,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "userid", keyColumn = "userid")/*�������ע������¼��᷵����������id*/
    int insert(Profile record);

    @InsertProvider(type=ProfileSqlProvider.class, method="insertSelective")
    int insertSelective(Profile record);

    @SelectProvider(type=ProfileSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="lang", property="lang", jdbcType=JdbcType.VARCHAR),
        @Result(column="catid", property="catid", jdbcType=JdbcType.INTEGER)
    })
    List<Profile> selectByExample(ProfileExample example);

    @Select({
        "select",
        "userid, lang, catid",
        "from profile",
        "where userid = #{userid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="lang", property="lang", jdbcType=JdbcType.VARCHAR),
        @Result(column="catid", property="catid", jdbcType=JdbcType.INTEGER)
    })
    Profile selectByPrimaryKey(Integer userid);

    @UpdateProvider(type=ProfileSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Profile record, @Param("example") ProfileExample example);

    @UpdateProvider(type=ProfileSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Profile record, @Param("example") ProfileExample example);

    @UpdateProvider(type=ProfileSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Profile record);

    @Update({
        "update profile",
        "set lang = #{lang,jdbcType=VARCHAR},",
          "catid = #{catid,jdbcType=INTEGER}",
        "where userid = #{userid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Profile record);
}