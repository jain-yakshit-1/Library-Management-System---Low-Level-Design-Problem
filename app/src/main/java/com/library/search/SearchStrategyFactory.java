package com.library.search;

public class SearchStrategyFactory {
    public static SearchStrategy getSearchStrategy(SearchType type) {
        switch (type) {
            case TITLE:
                return new SearchByTitleStrategy();
            case AUTHOR:
                return new SearchByAuthorStrategy();
            case ISBN:
                return new SearchByIsbnStrategy();
            default:
                throw new IllegalArgumentException("Unknown search type: " + type);
        }
    }
}

