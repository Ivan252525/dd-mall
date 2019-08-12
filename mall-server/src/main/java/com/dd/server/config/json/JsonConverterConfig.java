package com.dd.server.config.json;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON消息转换器配置
 *
 */
@Configuration
public class JsonConverterConfig {

    /**
     * FastJson消息转换对象
     *
     * @return
     */
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,  // 输出为null的字段
                SerializerFeature.PrettyFormat,    //是否需要格式化
                SerializerFeature.DisableCircularReferenceDetect    // 一个json中多个同一对象处理避免出现$ref
        );
        List<MediaType> fastMedisTypes=new ArrayList<>();
        fastMedisTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMedisTypes);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return fastConverter;
    }

}
