package modula.platform.yandex.tracker.core;

import com.modula.common.mapper.ModuleShortInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestHandlerService {

    private final ModuleShortInfoMapper moduleShortInfoMapper;

}
