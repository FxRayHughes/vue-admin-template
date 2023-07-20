package top.jltc.server.model.assembly;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssemblyServiceImpl implements AssemblyService {

  @Resource
  private AssemblyMapper assemblyMapper;

  @Override
  public Integer delete(Integer id) {
    return assemblyMapper.delete(id);
  }

  @Override
  public List<AssemblyEntity> getAll() {
    return assemblyMapper.getAll();
  }

  @Override
  public AssemblyEntity getOne(Integer id) {
    return assemblyMapper.getOne(id);
  }

  @Override
  public Integer insert(AssemblyEntity assemblyEntity) {
    return assemblyMapper.insert(assemblyEntity);
  }

  @Override
  public Integer update(AssemblyEntity assemblyEntity) {
    return assemblyMapper.update(assemblyEntity);
  }
}
