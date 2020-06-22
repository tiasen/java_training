package com.thoughtworks.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageMeta {
    private long page;
    private long size;
    private long total;
}
