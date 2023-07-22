package top.jltc.server.model.assembly;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AssemblyMapper {

  @Select("SELECT * FROM mes_assembly")
  List<AssemblyEntity> getAll();

  @Select("SELECT * FROM mes_assembly WHERE id = #{id}")
  AssemblyEntity getOne(Integer id);

  @Select("INSERT INTO mes_assembly (name, pmc, pps, product, model, specifications, unit, start_time, end_time) VALUES (#{name}, #{pmc}, #{pps}, #{product}, #{model}, #{specifications}, #{unit}, #{start_time}, #{end_time})")
  Integer insert(AssemblyEntity assemblyEntity);

  @Select("UPDATE mes_assembly SET name = #{name}, pmc = #{pmc}, pps = #{pps}, product = #{product}, model = #{model}, specifications = #{specifications}, unit = #{unit}, start_time = #{start_time}, end_time = #{end_time} WHERE id = #{id}")
  Integer update(AssemblyEntity assemblyEntity);

  @Select("DELETE FROM mes_assembly WHERE id = #{id}")
  Integer delete(Integer id);
  @Select("SELECT * FROM mes_assembly WHERE " +
    "(name LIKE #{name} OR #{name} IS NULL) AND " +
    "(pmc LIKE #{pmc} OR #{pmc} IS NULL) AND " +
    "(pps LIKE #{pps} OR #{pps} IS NULL) AND " +
    "(product LIKE #{product} OR #{product} IS NULL) AND " +
    "(model LIKE #{model} OR #{model} IS NULL) AND " +
    "(specifications LIKE #{specifications} OR #{specifications} IS NULL) AND " +
    "(unit LIKE #{unit} OR #{unit} IS NULL) AND " +
    "(#{end_time} IS NOT NULL AND start_time >= #{start_time} AND end_time <= #{end_time}) OR " +
    "(#{end_time} IS NULL AND start_time >= #{start_time}) " +
    "ORDER BY end_time DESC")
  List<AssemblyEntity> like(AssemblyEntity assemblyEntity);
}
