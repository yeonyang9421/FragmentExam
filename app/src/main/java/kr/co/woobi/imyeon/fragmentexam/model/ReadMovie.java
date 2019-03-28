package kr.co.woobi.imyeon.fragmentexam.model;

import java.util.List;

public class ReadMovie {
    private String message;
    private int code;
    private String resultType;
    private List<MovieDetail> result = null;

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

    public List<MovieDetail> getResult() {
        return result;
    }

    public void setResult(List<MovieDetail> result) {
        this.result = result;
    }
}
