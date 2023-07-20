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
}
