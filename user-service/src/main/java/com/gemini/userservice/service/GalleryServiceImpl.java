package com.gemini.userservice.service;

import com.gemini.userservice.dto.*;
import com.gemini.userservice.dto.response.*;
import com.gemini.userservice.entity.*;
import com.gemini.userservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService{

    private final GalleryRepository galleryRepository;

    private final UserInfoRepository userInfoRepository;

    private final LikeRepository likeRepository;

    private final GeminiRepository geminiRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    private final MongoTemplate mongoTemplate;
    private final TagRepository tagRepository;

    public Long getTotal() {
        Long total = galleryRepository.count();
        return total;
    }

    // 전체갤러리. 페이징 조회 기준은 갤러리.
    // 반환해주는게 갤러리 pk인가? 제미니 pk인가? -> 제미니 pk여야함. 😀 확인필요. 제미니 pk를 보내고 있는지 갤러리 pk를 보내고 있는지
    public ResponseGalleryPageDto getGalleryPage(Integer page, Integer size) {

        List<Gallery> galleries = galleryRepository.findAll();
        if (galleries.size() > 0) {
            if (galleries.size() < size) {
                size = galleries.size();
            }
            Pageable pageable = PageRequest.of(page, size);
            int start = (int)pageable.getOffset();
            if (start + 1 > galleries.size()) {
                ResponseGalleryPageDto responseGalleryPageDto = new ResponseGalleryPageDto();
                return responseGalleryPageDto;
            }
            List<GalleryDto> galleryDtos = new ArrayList<>();
            for (int i = start; i < start + size; i++) {
                if (galleries.size() < i + 1) {
                    break;
                }
                Gallery gallery = galleries.get(i);

                GalleryDto galleryDto = new GalleryDto(gallery, gallery.getGemini());
                galleryDtos.add(galleryDto);
            }
            Page<GalleryDto> galleryPage = new PageImpl<>(galleryDtos, pageable, galleries.size());
            ResponseGalleryPageDto responseGalleryPageDto = new ResponseGalleryPageDto(galleryPage);
            return responseGalleryPageDto;
        }
        ResponseGalleryPageDto responseGalleryPageDto = new ResponseGalleryPageDto();
        return responseGalleryPageDto;
    }


    // 내 갤러리. 페이징 조회 기준은 갤러리.
    // 반환해주는게 갤러리 pk인가? 제미니 pk인가? -> 제미니 pk여야함. 😀 확인필요. 제미니 pk를 보내고 있는지 갤러리 pk를 보내고 있는지
    public ResponseGeminiPageDto getMyGalleryPage(String username, Integer page, Integer size) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findByUsername(username);
        if (!optionalUserInfo.isPresent()) {
            // 사용자가 존재하지 않는 경우 처리
            // 예: 예외를 던지거나 빈 ResponseGalleryPageDto를 반환
        }
        UserInfo userInfo = optionalUserInfo.get();

        List<Gemini> myGeminis = geminiRepository.findByUserInfo(userInfo); //mypage니까 다 가져옴.

//        List<Gallery> galleries = galleryRepository.findByGemini_UserInfo(userInfo);

        // 위에서 사용했던 로직을 재사용
        if (myGeminis.size() > 0) {
            if (myGeminis.size() < size) {
                size = myGeminis.size();
            }
            Pageable pageable = PageRequest.of(page, size);
            int start = (int)pageable.getOffset();
            if (start + 1 > myGeminis.size()) {
                ResponseGeminiPageDto responseGeminiPageDto = new ResponseGeminiPageDto();
                return responseGeminiPageDto;
            }
            List<GeminiDto> geminiDtos = new ArrayList<>();
            for (int i = start; i < start + size; i++) {
                if (myGeminis.size() < i + 1) {
                    break;
                }
                Gemini myGemini = myGeminis.get(i);

                GeminiDto geminiDto = new GeminiDto(myGemini);
                geminiDtos.add(geminiDto);
            }
            Page<GeminiDto> geminiPage = new PageImpl<>(geminiDtos, pageable, myGeminis.size());
            ResponseGeminiPageDto responseGeminiPageDto = new ResponseGeminiPageDto(geminiPage);
            return responseGeminiPageDto;
        }
        ResponseGeminiPageDto responseGeminiPageDto = new ResponseGeminiPageDto();
        return responseGeminiPageDto;
    }


    // 유저갤러리. 페이징 조회 기준은 갤러리. -> 제미니 기준으로 바꾸는게 좋음. (갤러리는 ispublic으로 한번 필터링 된놈들이라서..)
    // 반환해주는게 갤러리 pk인가? 제미니 pk인가? -> 제미니 pk여야함. 😀 수정필요.
    public ResponseGalleryPageDto getUserGalleryPage(String nickname, Integer page, Integer size) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findByNickname(nickname);
        if (!optionalUserInfo.isPresent()) {
            // 사용자가 존재하지 않는 경우 처리
            // 예: 예외를 던지거나 빈 ResponseGalleryPageDto를 반환
        }
        UserInfo userInfo = optionalUserInfo.get();
//        List<Gallery> galleries = galleryRepository.findByGemini_UserInfoAndGemini_IsPublic(userInfo, true);
        List<Gallery> galleries = galleryRepository.findPublicGalleriesByUserInfo(userInfo);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@ 다른유저 갤러리 페이징 합니다.");


        // 위에서 사용했던 로직을 재사용
        if (galleries.size() > 0) {
            if (galleries.size() < size) {
                size = galleries.size();
            }
            Pageable pageable = PageRequest.of(page, size);
            int start = (int)pageable.getOffset();
            if (start + 1 > galleries.size()) {
                ResponseGalleryPageDto responseGalleryPageDto = new ResponseGalleryPageDto();
                return responseGalleryPageDto;
            }
            List<GalleryDto> galleryDtos = new ArrayList<>();
            for (int i = start; i < start + size; i++) {
                if (galleries.size() < i + 1) {
                    break;
                }
                Gallery gallery = galleries.get(i);

                GalleryDto galleryDto = new GalleryDto(gallery, gallery.getGemini());
                galleryDtos.add(galleryDto);
            }
            Page<GalleryDto> galleryPage = new PageImpl<>(galleryDtos, pageable, galleries.size());
            ResponseGalleryPageDto responseGalleryPageDto = new ResponseGalleryPageDto(galleryPage);
            return responseGalleryPageDto;
        }
        ResponseGalleryPageDto responseGalleryPageDto = new ResponseGalleryPageDto();
        return responseGalleryPageDto;
    }


    public ResponseGalleryRankingDto getDailyGallery() {

        ResponseGalleryRankingDto responseGalleryRankingDto = new ResponseGalleryRankingDto();
        return responseGalleryRankingDto;
    }

    public ResponseGalleryRankingDto getWeeklyGallery() {

        String key = "weekly";
        long start = 0;
        long end = -1;

        Set<Object> galleries = redisTemplate.execute((RedisCallback<Set<Object>>) connection -> {
            Set<Object> weeklySet = new LinkedHashSet<>();
            Set<byte[]> bytesSet = connection.zRange(key.getBytes(), start, end);
            for (byte[] bytes : bytesSet) {
                weeklySet.add(redisTemplate.getValueSerializer().deserialize(bytes));
            }
            return weeklySet;
        });

        for (Object gallery : galleries) {
            System.out.println("gallery: " + gallery);
        }
        ResponseGalleryRankingDto responseGalleryRankingDto = new ResponseGalleryRankingDto();
        return responseGalleryRankingDto;
    }

    public ResponseGalleryDetailDto getGalleryDetail(String username, Long galleryNo) {

        Optional<UserInfo> me = userInfoRepository.findByUsername(username);
        Gallery gallery = galleryRepository.findByGalleryNo(galleryNo);
        Gemini gemini = gallery.getGemini();
        Optional<Like> isLiked = likeRepository.findByUserInfoAndGemini(me.get(), gemini);
        Boolean liked = isLiked.isPresent();
        UserInfo producer = gemini.getUserInfo();

        ResponseGalleryDetailDto responseGalleryDetailDto = new ResponseGalleryDetailDto(producer, gemini, liked);
        return responseGalleryDetailDto;
    }

    public ResponseGeminiDetailDto getGeminiDetail(String username, Long geminiNo) {
        Optional<UserInfo> owner = userInfoRepository.findByUsername(username);
        Gemini gemini = geminiRepository.findByGeminiNo(geminiNo);
//        List<Tag> tags = gemini.get

        return ResponseGeminiDetailDto.builder()
                .geminiName(gemini.getName())
                .geminiDescription(gemini.getDescription())
                .geminiImage(gemini.getImageUrl())
                .isPublic(gemini.getIsPublic())
                .totalLike(gemini.getTotalLike())
//                .tags(tags) // 😀수정 필요
                .build();


    }


    public String likeGallery(String username, Long galleryNo) {

        Optional<UserInfo> userInfo = userInfoRepository.findByUsername(username);
        Gallery gallery = galleryRepository.findByGalleryNo(galleryNo);
        Gemini gemini = gallery.getGemini();
        Optional<Like> isLiked = likeRepository.findByUserInfoAndGemini(userInfo.get(), gemini);
        if (isLiked.isPresent()) {
            return "fail";
        }
        Like like = Like.builder()
                    .gemini(gallery.getGemini())
                    .userInfo(userInfo.get())
                    .build();
        likeRepository.save(like);
        Integer totalLikes = gemini.getTotalLike();
        Integer dailyLikes = gallery.getDailyLike();
        Integer weeklyLikes = gallery.getWeeklyLike();
        gemini.updateLikes(totalLikes + 1);
        gallery.updateLikes(dailyLikes + 1, weeklyLikes + 1);
        geminiRepository.save(gemini);
        galleryRepository.save(gallery);

        return String.valueOf(totalLikes + 1); // 좋아요 성공하면, 전체 좋아요 개수를 다시 반환해줌.
    }

    public String cancelGalleryLike(String username, Long galleryNo) {

        Optional<UserInfo> userInfo = userInfoRepository.findByUsername(username);
        Gallery gallery = galleryRepository.findByGalleryNo(galleryNo);
        Gemini gemini = gallery.getGemini();
        Optional<Like> isLiked = likeRepository.findByUserInfoAndGemini(userInfo.get(), gemini);
        if (isLiked.isPresent()) {
            likeRepository.delete(isLiked.get());
            Integer totalLikes = gemini.getTotalLike();
            Integer dailyLikes = gallery.getDailyLike();
            Integer weeklyLikes = gallery.getWeeklyLike();
            gemini.updateLikes(totalLikes - 1);
            gallery.updateLikes(dailyLikes - 1, weeklyLikes - 1);
            geminiRepository.save(gemini);
            galleryRepository.save(gallery);
            return String.valueOf(totalLikes - 1);
        }
        return "fail";
    }

    public GeminiTagDto getGeminiTags(Long geminiNo) {

        GeminiTag geminiTag = mongoTemplate.findOne(
                Query.query(Criteria.where("gemini_no").is(geminiNo)),
                GeminiTag.class
        );
        List<TagDto> tagDtos = new ArrayList<>();
        for(Long tagId: geminiTag.getTagIds()) {
            Tag tag = tagRepository.findByTagNo(tagId);
            TagDto tagDto = new TagDto(tag);
            tagDtos.add(tagDto);
        }
        GeminiTagDto geminiTagDto = GeminiTagDto.builder()
                .tagDtos(tagDtos)
                .build();
        return geminiTagDto;
    }



    public Gallery createGallery(Long geminiNo) {
        Gemini gemini = geminiRepository.findById(geminiNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid geminiNo: " + geminiNo));

        Gallery gallery = Gallery.builder()
                .dailyLike(0)
                .weeklyLike(0)
                .gemini(gemini)
                .build();

        return galleryRepository.save(gallery);
    }




    public void deleteGallery(Long geminiNo) {
        Gemini gemini = geminiRepository.findById(geminiNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid geminiNo: " + geminiNo));

        Gallery gallery = gemini.getGallery();

        if (gallery != null) {
            galleryRepository.delete(gallery);
        } else {
            throw new IllegalArgumentException("No Gallery associated with geminiNo: " + geminiNo);
        }
    }

}
