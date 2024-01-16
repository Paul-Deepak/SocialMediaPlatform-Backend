package com.project.socialmediaplatform.model;

public class SearchModel {

    private Long userId; 
    private String searchWord; 
    private Long friendId;

    
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getSearchWord() {
        return searchWord;
    }
    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
    public Long getFriendId() {
        return friendId;
    }
    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }
    public SearchModel(Long userId, String searchWord, Long friendId) {
        this.userId = userId;
        this.searchWord = searchWord;
        this.friendId = friendId;
    }
    public SearchModel() {
    } 

    
}
