package conatus.domain.user;

import conatus.domain.user.event.SignedUp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // kafka
    // 유저 이벤트 받기: 유저 추가
    public User postUser(SignedUp signedUp){
        User user = new User(signedUp.getUserId(), signedUp.getNickname());
        User savedUser = userRepository.save(user);
        return savedUser;
    }

}
