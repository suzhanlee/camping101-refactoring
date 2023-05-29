package com.camping101.beta.global.exception;

import org.springframework.http.HttpStatus;

public class CannotDeleteReservationException extends GeneralException{

    public CannotDeleteReservationException() {
        super(HttpStatus.BAD_REQUEST, "예약을 삭제할 수 없습니다.");
    }

}