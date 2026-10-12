package org.example.service;

import org.example.config.AppProperties;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class LocalizedIOService {

    private final IOService ioService;
    private final MessageSource messageSource;
    private final AppProperties appProperties;

    public LocalizedIOService(IOService ioService,
                              MessageSource messageSource,
                              AppProperties appProperties) {
        this.ioService = ioService;
        this.messageSource = messageSource;
        this.appProperties = appProperties;
    }

    public void printLine(String line) {
        ioService.printLine(line);
    }

    public void printLocalized(String code, Object... args) {
        ioService.printLine(getMessage(code, args));
    }

    public void printFormattedLine(String format, Object... args) {
        ioService.printFormattedLine(format, args);
    }

    public String readStringLocalized(String code, Object... args) {
        return ioService.readStringWithPrompt(getMessage(code, args));
    }

    public int readIntLocalized(int min, int max, String promptCode, String errorCode) {
        return ioService.readIntForRangeWithPrompt(
                min,
                max,
                getMessage(promptCode),
                getMessage(errorCode)
        );
    }

    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, appProperties.getLocale());
    }
}
