package com.tujuhsembilan.library.utility.response;

import com.google.common.collect.Iterables;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ListResponseBuilder<T extends Object> extends BaseResponseBuilder {
    private Iterable<T> data;
    private int count;

    @Builder
    public ListResponseBuilder(HttpStatus status, String message, Iterable<T> data) {
        super(status, message);

        this.data = data;
        this.count = Iterables.size(data);
    }
}
