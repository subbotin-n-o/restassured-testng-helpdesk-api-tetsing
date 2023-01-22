package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    OPEN(1),
    CLOSED(4),
    ;

    private final int code;
}