package kr.co.woobi.imyeon.fragmentexam.model;

import java.util.List;

public class ReadMovieList {
    private String message;
    private int code;
    private String resultType;
    private List<MovieInfo> result = null;

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

    public List<MovieInfo> getResult() {
        return result;
    }

    public void setResult(List<MovieInfo> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ReadMovieList{");
        sb.append("message='").append(message).append('\'');
        sb.append(", code=").append(code);
        sb.append(", resultType='").append(resultType).append('\'');
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
