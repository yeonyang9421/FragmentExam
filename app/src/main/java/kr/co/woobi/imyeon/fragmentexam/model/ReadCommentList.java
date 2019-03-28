package kr.co.woobi.imyeon.fragmentexam.model;

import java.util.List;

public class ReadCommentList {
    private String message;
    private int code;
    private String resultType;
    private int totalCount;
    private List<CommentList> result = null;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<CommentList> getResult() {
        return result;
    }

    public void setResult(List<CommentList> result) {
        this.result = result;
    }
}
