package com.example.delivery.tracking.API.service;

import java.util.List;

public interface GenericCrudService <ResponseDto,RequestDto,ID> {

    ResponseDto create(RequestDto requestDto);

    List<ResponseDto> getAll();

    ResponseDto getByID(ID id);

    ResponseDto update(RequestDto requestDto,ID id);

    void delete(ID id);
}
