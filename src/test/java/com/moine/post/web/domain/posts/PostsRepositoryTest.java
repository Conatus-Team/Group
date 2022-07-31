package com.moine.post.web.domain.posts;

import com.moine.post.domain.posts.Posts;
import com.moine.post.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }
    @Test
    public void posts_save_pull(){
        //given
        String title = "test post";
        String content = "test post content";

        postsRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("gustjs1013@naver.com")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }
    @Test
    public void BaseTimeEntity_Entry(){
        //given
        LocalDateTime now = LocalDateTime.of(2022,7,18,0,0,0);
        postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}
