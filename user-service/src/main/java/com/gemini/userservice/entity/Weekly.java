package com.gemini.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@RedisHash("reward:weekly")
public class Weekly {

    @Id
    private Long galleryNo;

    private List<String> emotionUrls;
}


