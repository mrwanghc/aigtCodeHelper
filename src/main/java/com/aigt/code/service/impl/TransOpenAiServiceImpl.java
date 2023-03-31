package com.aigt.code.service.impl;

import com.aigt.code.service.TransOpenAiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.Duration;
import static com.theokanning.openai.service.OpenAiService.defaultObjectMapper;

@Service
@Slf4j
public class TransOpenAiServiceImpl implements TransOpenAiService {

    private static final String BASE_URL = "https://api.openai.com/";
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10L);
    private static final ObjectMapper errorMapper = defaultObjectMapper();
    private OpenAiApi api;


}
