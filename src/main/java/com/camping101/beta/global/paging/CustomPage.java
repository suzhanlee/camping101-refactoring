package com.camping101.beta.global.paging;

import java.util.Locale;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomPage {

    int pageNumber;
    int recordSize;
    String orderBy;
    String orderDir;

    public static CustomPage fromPageable(Pageable pageable) {

        Optional<Sort.Order> sort = pageable.getSort().stream().findFirst();

        return CustomPage.builder()
            .pageNumber(pageable.getPageNumber())
            .recordSize(pageable.getPageSize())
            .orderBy(sort.isEmpty() ? "" : sort.get().getProperty())
            .orderDir(sort.isEmpty() ? "" : sort.get().getDirection().name())
            .build();
    }

    public Pageable toPageable() {
        return PageRequest.of(this.pageNumber, this.recordSize,
            Sort.Direction.valueOf(this.orderDir.toUpperCase(Locale.ROOT)), this.orderBy);
    }

}
