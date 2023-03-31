package com.aigt.code.controller;

import com.aigt.code.common.constant.ChatGptConstant;
import com.aigt.code.utils.R;
import com.aigt.code.vo.requestVo.MyCompletionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import retrofit2.Retrofit;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.theokanning.openai.service.OpenAiService.*;

/*
 * 调用ChatGpt-4 plus接口
 */
@RestController
@RequestMapping("/chat")
public class ChatGptController {

    /**
     * 调用ChatGpt-4 plus接口
     * 接口文档 https://platform.openai.com/docs/api-reference/completions/create
     */
    @ResponseBody
    @RequestMapping(value = "/getChatGpt4Data", produces = "application/json;charset=UTF-8", method = {RequestMethod.GET, RequestMethod.POST})
    public R getChatGpt4Data(@RequestBody MyCompletionRequest completionRequest) {
        //调用chatgpt-4-puls
        String responseBody = null;
        try {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(180, TimeUnit.SECONDS)
                    .readTimeout(180, TimeUnit.SECONDS)
                    .build();

            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            String requestBody = "{\"prompt\":\""
                    + completionRequest.getPrompt() + "\",\"max_tokens\":1024,\"temperature\":0.9,\"top_p\":1," +
                    "\"frequency_penalty\":0.0,\"presence_penalty\":0.6}";

            Request request = new Request.Builder()
                    .url(ChatGptConstant.API_ENDPOINT)
                    .header("Authorization", "Bearer " + ChatGptConstant.API_KEY)
                    .header("Content-Type", "application/json")
                    .post(okhttp3.RequestBody.create(requestBody,mediaType))
                    .build();

            Call call = client.newCall(request);
            call.timeout().timeout(180, TimeUnit.SECONDS);
            Response response = call.execute();
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok(responseBody);
    }

    @ResponseBody
    @PostMapping(value = "/getOpenAiInfo")
    public R getOpenAiInfo(@RequestBody MyCompletionRequest myCompletionRequest) {
        // 构建openai api对象，由于处理时间比较长，建议设置一个合理的超时时间
        OpenAiService service = new OpenAiService("FCU6DMLNXQ96OGI1VM", Duration.ofSeconds(1000));
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(myCompletionRequest.getPrompt())
                .model("text-davinci-003")
                .temperature(0.9)
                .maxTokens(150)
                .stop(Arrays.asList("Human:", "AI:"))
                .echo(true)
                .build();
        return R.ok(service.createCompletion(completionRequest).getChoices());
    }

    @ResponseBody
    @PostMapping(value = "/getProxyOpenAiInfo")
    public R getProxyOpenAiInfo(@RequestBody MyCompletionRequest myCompletionRequest) {
        //要使用代理，请修改OkHttp客户端，如下所示：
        ObjectMapper mapper = defaultObjectMapper();
        Proxy proxy = new Proxy(Proxy.Type.HTTP,
                new InetSocketAddress("140.120.15.146", 8088));
        OkHttpClient client = defaultClient("FCU6DMLNXQ96OGI1VM", Duration.ofSeconds(1000));
        client.newBuilder().proxy(proxy).build();
        Retrofit retrofit = defaultRetrofit(client, mapper);
        OpenAiApi api = retrofit.create(OpenAiApi.class);
        OpenAiService service = new OpenAiService(api);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(myCompletionRequest.getPrompt())
                .model("text-davinci-003")
                .temperature(0.9)
                .maxTokens(150)
                .stop(Arrays.asList("Human:", "AI:"))
                .echo(true)
                .build();
        return R.ok(service.createCompletion(completionRequest).getChoices());
    }

    public static void main(String[] args) {
        String token = "key";
        // 构建openai api对象，由于处理时间比较长，建议设置一个合理的超时时间
        OpenAiService service = new OpenAiService("FCU6DMLNXQ96OGI1VM", Duration.ofSeconds(1000));
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("who are you")
                .model("text-davinci-003")
                .temperature(0.9)
                .maxTokens(150)
                .stop(Arrays.asList("Human:", "AI:"))
                .echo(true)
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
    }
}
