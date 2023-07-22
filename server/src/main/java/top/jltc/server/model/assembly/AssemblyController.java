package top.jltc.server.model.assembly;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.*;
import top.jltc.server.utils.ResponseResult;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/assembly")
public class AssemblyController {

  @Resource
  private AssemblyService assemblyService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public Object getAll() {
    return ResponseResult.success(assemblyService.getAll(), "成功");
  }

  @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
  public Object getById(@PathVariable String id) {
    return ResponseResult.success(assemblyService.getOne(Integer.parseInt(id)), "获取成功");
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public Object update(@RequestBody JSONObject jsonObject) {
    AssemblyEntity assembly = jsonObject.toJavaObject(AssemblyEntity.class);
    return ResponseResult.success(assemblyService.update(assembly), "更改成功");
  }

  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public Object insert(@RequestBody JSONObject jsonObject) {
    AssemblyEntity assembly = jsonObject.toJavaObject(AssemblyEntity.class);
    return ResponseResult.success(assemblyService.insert(assembly), "添加成功");
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public Object delete(@PathVariable String id) {
    return ResponseResult.success(assemblyService.delete(Integer.parseInt(id)), "删除成功");
  }

}
