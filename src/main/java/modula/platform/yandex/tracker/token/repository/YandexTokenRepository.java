package modula.platform.yandex.tracker.token.repository;

import modula.platform.yandex.tracker.token.domain.YandexToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YandexTokenRepository extends JpaRepository<YandexToken, String> {
}
