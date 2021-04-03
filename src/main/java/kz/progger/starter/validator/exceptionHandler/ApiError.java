package kz.progger.starter.validator.exceptionHandler;

import java.util.List;

/**
 * @author assem.balamanova
 * @created 04/09/2020 - 9:33 AM
 */
public class ApiError {
    private List<String> errorList;
    private String message;

    public ApiError(List<String> errorList) {
        this.message = "Validation Error";
        this.errorList = errorList;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public String getMessage() {
        return message;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
