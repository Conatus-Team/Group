package conatus.infra;

import conatus.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class GroupHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Group>> {

    @Override
    public EntityModel<Group> process(EntityModel<Group> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/joingroup")
                .withRel("joingroup")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/quitgroup")
                .withRel("quitgroup")
        );

        return model;
    }
}
