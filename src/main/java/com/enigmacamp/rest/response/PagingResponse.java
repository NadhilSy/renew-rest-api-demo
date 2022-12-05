package com.enigmacamp.rest.response;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
@Data
public class PagingResponse<T> extends CommonResponse {
    private List<T> listData;
    private int page;
    private int totalPage;
    private long count;
    private int size;

    public PagingResponse(String message, Page<T> page){
        super.setCode("00");
        super.setMessage(message);
        super.setStatus(HttpStatus.OK.name());

        this.listData = page.toList();
        this.page = page.getNumber() + 1;
        this.totalPage = page.getTotalPages();
        this.count = page.getTotalElements();
        this.size = page.getSize();
    }

}
