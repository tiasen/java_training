package com.thoughtworks.user.model;

import com.thoughtworks.user.dto.PageMeta;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageResult<T> {
    private PageMeta meta;
    private List<T> data;
}
