//package modula.platform.yandex.tracker.token.controller;
//
//import lombok.RequiredArgsConstructor;
//import modula.platform.yandex.tracker.token.dto.AuthCodeDTO;
//import modula.platform.yandex.tracker.token.dto.TokenResponse;
//import modula.platform.yandex.tracker.token.service.TokenService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/yandex-tracker/api/token/")
//@RequiredArgsConstructor
//public class TokenController {
//
//    private final TokenService tokenService;
//
//    @PostMapping("/login")
//    public Mono<ResponseEntity<TokenResponse>> login(@RequestBody AuthCodeDTO authCodeDTO) {
//        return tokenService.exchangeAndStoreToken(authCodeDTO.getCode())
//                .thenReturn(ResponseEntity.ok(new TokenResponse("Token successfully received and stored")));
//    }
//}
