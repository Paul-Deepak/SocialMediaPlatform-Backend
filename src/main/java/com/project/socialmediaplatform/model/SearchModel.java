package com.project.socialmediaplatform.model;

public class SearchModel {

    private Long userId; 
    private String searchWord; 
    private Long friendId;
    private String UserName;
    private String email;

    
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
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
    
    public SearchModel(Long userId, String searchWord, Long friendId, String userName, String email) {
        this.userId = userId;
        this.searchWord = searchWord;
        this.friendId = friendId;
        UserName = userName;
        this.email = email;
    }
    public SearchModel() {
    } 

    
}
