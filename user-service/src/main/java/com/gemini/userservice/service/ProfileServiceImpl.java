package com.gemini.userservice.service;

import com.gemini.userservice.dto.ProfileResponseDto;
import com.gemini.userservice.dto.GeminiDto;
import com.gemini.userservice.entity.UserInfo;
import com.gemini.userservice.entity.Gemini;
import com.gemini.userservice.repository.UserInfoRepository;
import com.gemini.userservice.repository.GeminiRepository;
import com.gemini.userservice.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private GeminiRepository geminiRepository;

    @Autowired
    private FollowRepository followRepository;

    @Override
    public ProfileResponseDto getProfileByUsername(String username) {
        UserInfo userInfo = userInfoRepository.findByUsername(username);

        long follower = followRepository.countByFollowing(userInfo.getUserPk());
        long following = followRepository.countByFollower(userInfo.getUserPk());

        List<Gemini> geminis = geminiRepository.findByUserInfo(userInfo);
        List<GeminiDto> geminiDtoList = geminis.stream()
                .map(gemini -> GeminiDto.builder()
                        .geminiPk(gemini.getId())
                        .image(gemini.getImage())
                        .userPk(gemini.getUserInfo().getUserPk())
                        .build())
                .collect(Collectors.toList());

        return ProfileResponseDto.builder()
                .nickname(userInfo.getNickname())
                .description(userInfo.getDescription())
                .follower(follower)
                .following(following)
                .star(userInfo.getStar())
                .geminis(geminiDtoList)
                .build();
    }
}