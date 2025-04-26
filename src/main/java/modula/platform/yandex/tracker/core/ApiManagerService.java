package modula.platform.yandex.tracker.core;

import com.modula.common.domain.moduleconfiguration.ModuleConfiguration;
import com.modula.common.dto.moduleconfiguration.ModuleConfigurationShortInfoDTO;
import com.modula.common.mapper.ModuleShortInfoMapper;
import lombok.RequiredArgsConstructor;
import modula.platform.yandex.tracker.core.repository.ModuleConfigurationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiManagerService {

    private final ModuleShortInfoMapper moduleShortInfoMapper;
    private final ModuleConfigurationRepository moduleConfigurationRepository;

    public ModuleConfiguration getModuleInfo() {
        return moduleConfigurationRepository.findByName("yandex-tracker").orElseThrow();
    }

    public ModuleConfigurationShortInfoDTO getModuleShortInfo() {
        ModuleConfiguration configuration = getModuleInfo();
        return moduleShortInfoMapper.mapToShortInfoDTO(configuration);
    }
}