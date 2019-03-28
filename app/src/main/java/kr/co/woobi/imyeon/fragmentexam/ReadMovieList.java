package kr.co.woobi.imyeon.fragmentexam;

import java.util.List;

public class ReadMovieList {
    public String message;
    public int code;
    public String resultType;
    public List<Result> result = null;

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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
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
