import styled from "styled-components";

// GeminiDetialWrapper 😶 배경 수정할수도 있음.
export const GeminiDetialWrapper = styled.div`
  aspect-ratio: 3 / 2;
  width: 50vw;
  position: relative;
  z-index: 10;
  display: flex;
  background-color: #00000099;

  // &::after {
  //   content: "";
  //   position: fixed;
  //   top: 0;
  //   left: 0;
  //   right: 0;
  //   bottom: 0;
  //   background-color: rgba(0, 0, 0, 0.5);
  //   z-index: -1;
  // }
`;

// GeminiDetialImgWrapper
export const GeminiDetailImgWrapper = styled.div<{ backgroundImage: string }>`
  width: 50%;
  height: 100%;
  background-image: url(${(props) => props.backgroundImage});
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  display: flex;
`;

// LikeNicknameWrapper
export const LikeNicknameWrapper = styled.div`
  height: 21%;
  //   margin-top: 79%;
  width: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  margin-top: auto;
  display: flex;
  flex-direction: column;
  padding-left: 5%;
  padding-right: 3%;
`;

export const LinkProfileWrapper = styled.div<ToggleWrapperProps>`
  display: flex;
  flex-direction: ${(props) => (props.hideToggle ? "row" : "row-reverse")};
  height: 100%;
`;

export const ProfileWrapper = styled.div`
  display: flex;
  flex-direction: row;
  height: 100%;
  align-items: center; // 이미지를 수직으로 중앙에 배치합니다.
  cursor: pointer;
`;

// 😶😶😶 여기서부터 해야됨.
// export const ProfileImg = styled.div<{ backgroundImage: string }>`
//   width: 10%;
//   height: 55%;
//   border-radius: 50%;
//   margin-right: 10px;
//   background-image: url(${(props) => props.backgroundImage});
//   background-position: center;
//   background-repeat: no-repeat;
//   background-size: cover;
// `;

export const ProfileImg = styled.div<{ backgroundImage: string }>`
  width: 10%; // 가로 크기를 조정합니다.
  height: 55%; // 세로 크기를 조정합니다.
  margin-bottom: 1%;
  border-radius: 50%;
  margin-right: 10px;
  background-image: url(${(props) => props.backgroundImage});
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
`;

export const Nickname = styled.div`
  font-size: 0.9rem;
  color: white;
`;

export const LikeWrapper = styled.div`
  display: flex;
  // align-items: center;
  flex-direction: row;
  padding-bottom: 3%;
  cursor: pointer;
`;

// 하트 봐야됨. 수정 필요 😶
export const HeartIcon = styled.div`
  // color: black;
  color: red;

  font-size: 1.2rem;
  // 원하는 스타일을 추가하세요
`;

export const LikeCount = styled.div`
  color: white;
  font-size: 1rem;
  margin-left: 10px;
  // 원하는 스타일을 추가하세요
`;

// 여기까지 왼쪽 아래부터 오른쪽 😀

export const GeminiDetialInfoWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 50%;
  height: 100%;
  padding-left: 5%;
  padding-right: 5%;
  //
`;

interface ToggleWrapperProps {
  hideToggle?: boolean;
}

export const ToggleWrapper = styled.div<ToggleWrapperProps>`
  visibility: ${(props) => (props.hideToggle ? "hidden" : "visible")};
  display: flex;
  flex-direction: row;
  width: 100%;
  height: ${(props) => (props.hideToggle ? "10%" : "14%")};
  // margin-top: auto;
  padding-left: 6%;
  justify-content: center;
  align-items: center;
  //
`;
export const ToggleText = styled.div`
  color: white;
  font-size: 1rem;
  // font-weight:
  //
`;

// 😀토글버튼
interface ToggleButtonProps {
  isOn: boolean;
}
export const ToggleButtonContainer = styled.div<ToggleButtonProps>`
  width: 60px;
  height: 30px;
  // width: 50%;
  // height: 50%;
  border-radius: 15px;
  background-color: ${(props) => (props.isOn ? "#785EC3" : "#d9d9d9")};
  position: relative;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-left: 10%;
  margin-right: 10%;
`;

export const ToggleButtonCircle = styled.div<ToggleButtonProps>`
  width: 28px;
  height: 28px;
  border-radius: 50%;
  background-color: white;
  position: absolute;
  top: 1px;
  left: ${(props) => (props.isOn ? "32px" : "1px")};
  transition: left 0.3s ease;
`;
// 토글버튼 end

export const NameInputWrapper = styled.div`
  display: flex;
  flex-direction: row;
`;

export const FormLabel = styled.div`
  display: block;
  color: white;
  margin-bottom: 1%;
  white-space: nowrap; // 이 부분을 추가하세요
`;

export const TextInput = styled.input`
  display: block;
  // width: 82.5%;
  width: 80%;
  margin-left: 1.3rem;
  //   padding: 8px;
  margin-bottom: 16px;
  color: white;
  border: none;
  border-bottom: 2px solid white;
  background-color: transparent;
  font-size: 0.9rem;
  font-weight: bold;
  &::placeholder {
    color: white;
    opacity: 0.5;
    font-size: 0.7rem;
  }

  &:focus {
    outline: none;
    border-bottom: 3px solid white;
  }
  //
`;

// export const TextInputDiv = styled.div`
//   // display: block;
//   // width: 82.5%;
//   width: 80%;
//   // height: 100%;
//   margin-left: 1.3rem;
//   //   padding: 8px;
//   margin-top: auto;
//   margin-bottom: 16px;
//   color: white;
//   border: none;
//   border-bottom: 2px solid white;
//   background-color: transparent;
//   font-size: 0.9rem;
//   font-weight: bold;
//   &::placeholder {
//     color: white;
//     opacity: 0.5;
//     font-size: 0.7rem;
//   }

//   &:focus {
//     outline: none;
//     border-bottom: 3px solid white;
//   }
//   //
// `;

export const TextInputDiv = styled.div`
  width: 80%;
  // height: 40px; // Add a fixed height
  height: 60%; // Add a fixed height
  display: flex; // Set display to flex
  align-items: center; // Vertically align the content
  margin-left: 1.3rem;
  margin-top: auto;
  margin-bottom: 16px;
  color: white;
  border: none;
  border-bottom: 2px solid white;
  background-color: transparent;
  font-size: 0.9rem;
  font-weight: bold;

  &::placeholder {
    color: white;
    opacity: 0.5;
    font-size: 0.7rem;
  }

  &:focus {
    outline: none;
    border-bottom: 3px solid white;
  }
`;

export const DescBlockWrapper = styled.div<ToggleWrapperProps>`
  display: flex;
  flex-direction: column;
  height: 25%;
  margin-bottom: ${(props) => (props.hideToggle ? "5%" : "3%")};
`;

export const TagBlockWrapper = styled.div<ToggleWrapperProps>`
  display: flex;
  flex-direction: column;
  height: 30%;
  margin-bottom: ${(props) => (props.hideToggle ? "5%" : "3%")};
`;

export const DescArea = styled.div`
  width: 100%;
  height: 100%;
  background-color: white;
  border-radius: 10px;
  padding: 3%;
  font-size: 0.9rem;
  line-height: 1.2;
  overflow-y: auto; // 스크롤바 추가

  // 웹킷 기반 브라우저 (Chrome, Safari 등)용 스크롤바 스타일
  &::-webkit-scrollbar {
    width: 0; // 스크롤바 너비를 0으로 설정하여 숨김
    background: transparent; // 스크롤바 배경을 투명하게 설정
  }
  //
`;

export const TagArea = styled.div`
  width: 100%;
  height: 100%;
  background-color: white;
  border-radius: 10px;
  border-bottom: 1vh solid white;
  padding: 3%;
  overflow-y: auto; // 스크롤바 추가

  // 웹킷 기반 브라우저 (Chrome, Safari 등)용 스크롤바 스타일
  &::-webkit-scrollbar {
    width: 0; // 스크롤바 너비를 0으로 설정하여 숨김
    background: transparent; // 스크롤바 배경을 투명하게 설정
  }
  //
`;

export const Tags = styled.div`
  width: auto;
  display: inline-block;
  border-radius: 5px;
  background-color: #c3cdd6;
  font-size: 0.8rem;
  padding: 2% 3% 2% 3%;
  margin-right: 2%;
  margin-bottom: 2%;
  //
`;

export const ButtonWrapper = styled.div`
  height: 20%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const GeminiInfoButton = styled.div`
  border: 1px solid white;
  font-size: 0.9rem;
  border-radius: 10px;
  width: 85%;
  padding: 3% 10% 3% 10%;
  color: white;
  display: flex;
  justify-content: center;
  cursor: pointer;
  background-color: transparent;
  transition: background-color 0.2s ease, border-color 0.2s ease;

  &:hover {
    background-color: rgba(255, 255, 255, 0.2);
    border-color: rgba(255, 255, 255, 0.8);
  }

  &:active {
    background-color: rgba(255, 255, 255, 0.3);
  }
`;
