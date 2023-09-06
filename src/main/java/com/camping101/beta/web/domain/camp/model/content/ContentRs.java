package com.camping101.beta.web.domain.camp.model.content;

import com.camping101.beta.web.domain.camp.model.rs.FindCampListRs;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContentRs {

    private List<FindCampListRs> content = new ArrayList<>();

    public static ContentRs of(List<FindCampListRs> rs) {
        ContentRs contentRs = new ContentRs();
        contentRs.content = rs;
        return contentRs;
    }
}
