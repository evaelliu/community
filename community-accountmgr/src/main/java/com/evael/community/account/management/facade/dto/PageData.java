package com.evael.community.account.management.facade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData<T> {

    private int pageSize;
    private int currentPage;
    private int totalPages;
    private long totalNumber;
    @JsonProperty("pageData")
    private T t;

    public PageData(Page page, T t) {
        this(page.getSize(), page.getNumber(), page.getTotalPages(), page.getTotalElements(), t);
    }

}
