package conatus.domain.posts;


import conatus.domain.history.History;
import conatus.domain.history.HistoryService;
import conatus.domain.posts.dto.PostsResponseDto;
import conatus.domain.posts.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    private final HistoryService historyService;

    @Transactional
    public Long save(Long userId, PostsSaveRequestDto requestDto){
        Posts post = requestDto.toEntity();
        post.setUserId(userId);
        System.out.println("========================================");
        System.out.println("========================================");
        System.out.println("post.getUserId() = " + post.getUserId());
        System.out.println("post.getGroupId() = " + post.getGroupId());
        System.out.println("========================================");
        System.out.println("========================================");
//        return postsRepository.save(requestDto.toEntity()).getId();
        return postsRepository.save(post).getId();
    }

    @Transactional
    public Long update(Long userId, Long id, PostsSaveRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long userId, Long postId){
        Posts entity = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + postId));
        // history에 저장
        Optional<History> foundHistory = historyService.findAccessPostId(userId, postId);
        if (foundHistory.isPresent()) {
            foundHistory.get().updateCount();
            historyService.saveHistory(foundHistory.get());
        }else {
            History history = new History(userId, entity.getGroupId(), postId);
            historyService.saveHistory(history);
        }

        return new PostsResponseDto(entity);
    }

}

