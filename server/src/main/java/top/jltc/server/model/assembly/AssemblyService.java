package top.jltc.server.model.assembly;

import java.util.List;

public interface AssemblyService {
  List<AssemblyEntity> getAll();

  AssemblyEntity getOne(Integer id);

  Integer insert(AssemblyEntity assemblyEntity);

  Integer update(AssemblyEntity assemblyEntity);

  Integer delete(Integer id);

  List<AssemblyEntity> like(AssemblyEntity assemblyEntity);
}
