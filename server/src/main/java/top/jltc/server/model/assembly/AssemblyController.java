package top.jltc.server.model.assembly;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jltc.server.utils.ResponseResult;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/assembly")
public class AssemblyController {

  @Resource
  private AssemblyService assemblyService;

  @RequestMapping("/getAll")
  public Object getAll() {
    return new ResponseResult<>(200, "success", assemblyService.getAll());
  }

}
