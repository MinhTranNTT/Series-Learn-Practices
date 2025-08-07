package com.learn.domain;

import lombok.Data;

@Data
public class LibraryEvent {
    Integer libraryEventId;
    LibraryEventType libraryEventType;
    Book book;
}
