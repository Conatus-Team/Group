package conatus.infra.config;

import conatus.domain.gallery.Gallery;
import conatus.domain.history.History;
import conatus.domain.info.Info;
import conatus.domain.like.Like;
import conatus.domain.member.Member;
import conatus.domain.posts.Posts;
import conatus.domain.recommend.Recommend;
import conatus.domain.user.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.stereotype.Component;

@Component
public class ExposeEntityIdRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Gallery.class);
        config.exposeIdsFor(History.class);
        config.exposeIdsFor(Info.class);
        config.exposeIdsFor(Like.class);
        config.exposeIdsFor(Member.class);
        config.exposeIdsFor(Posts.class);
        config.exposeIdsFor(Recommend.class);
        config.exposeIdsFor(User.class);

    }
}