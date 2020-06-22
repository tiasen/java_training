package com.thoughtworks.user.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PageMapper {
    PageMapper INSTANCE = Mappers.getMapper(PageMapper.class);


}
