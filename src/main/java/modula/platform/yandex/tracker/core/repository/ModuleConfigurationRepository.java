package modula.platform.yandex.tracker.core.repository;

import com.modula.common.domain.moduleconfiguration.ModuleConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModuleConfigurationRepository extends JpaRepository<ModuleConfiguration, UUID> {

    Optional<ModuleConfiguration> findByName(String name);
}
