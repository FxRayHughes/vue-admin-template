package top.jltc.server.model.assembly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 1. 编号 id
 * 2. 作业名称 name
 * 3. 生产计划 pmc
 * 4. 计划排产 pps
 * 5. 产品 product
 * 6. 型号 model
 * 7. 规格 specifications
 * 8. 单位 unit
 * 9. 开始时间 start_time
 * 10. 结束时间 end_time
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssemblyEntity {

  private Integer id;

  private String name;

  private String pmc;

  private String pps;

  private String product;

  private String model;

  private String specifications;

  private String unit;

  private Date start_time;

  private Date end_time;

  private static final long serialVersionUID = 1L;

}
