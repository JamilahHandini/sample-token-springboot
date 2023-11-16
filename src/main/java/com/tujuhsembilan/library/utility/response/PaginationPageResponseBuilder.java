package com.tujuhsembilan.library.utility.response;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

/**
 * This class should extends to ListResponseBuilder instead, but because it has
 * builder already, it can't.
 * 
 * <br>
 * <br>
 * 
 * So now you're stuck with it extending to BaseResponseBuilder
 */

@Getter
public class PaginationPageResponseBuilder<D extends Object> extends BaseResponseBuilder {
    private D[] data;
    private int count;
    private int pageNumber;

    @Builder
    public PaginationPageResponseBuilder(HttpStatus status, String message, D[] data, int pageNumber) {
        super(status, message);

        this.data = data;
        this.count = data.length;
        this.pageNumber = pageNumber;
    }
}
