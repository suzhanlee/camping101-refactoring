package com.camping101.beta.web.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentListRequest {

    private Long campLogId;
    int pageNumber = 0;
    int recordSize = 5;

}
