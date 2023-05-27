package ru.shavshin.weathersensor.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.shavshin.weathersensor.exception.SensorNotFoundException;

import java.util.List;

//public class ErrorsUtil {
//    public static void returnErrorsToClient(BindingResult bindingResult) {
//        StringBuilder errors = new StringBuilder();
//
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//
//        for (FieldError e : fieldErrors) {
//            errors.append(e.getField())
//                    .append(" - ").append(e.getDefaultMessage() == null ? e.getCode() : e.getDefaultMessage())
//                    .append(";");
//        }
//        throw new SensorNotFoundException(errors.toString());
//    }
//}
