//package modula.platform.yandex.tracker.token.service;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import modula.platform.yandex.tracker.token.domain.YandexToken;
//import modula.platform.yandex.tracker.token.exception.TokenStorageException;
//import modula.platform.yandex.tracker.token.repository.YandexTokenRepository;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class YandexTokenStorageService {
//
//    private final YandexTokenRepository repository;
//
//    public void save(YandexToken token) {
//        try {
//            YandexToken saved = repository.save(token);
//            log.info("Токен сохранён в БД: {}", saved.getId());
//        } catch (Exception e) {
//            log.error("Ошибка при сохранении токена", e);
//            throw new TokenStorageException("Ошибка БД", e);
//        }
//    }
//}
//