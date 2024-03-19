package com.project.board.Domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GEUST("GUEST","손님"),
    USER("USER","사용자");
    private final String key;
    private final String title;
}
