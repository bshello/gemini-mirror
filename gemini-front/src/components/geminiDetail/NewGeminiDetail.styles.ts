import styled from "styled-components";

export const TextInput = styled.input`
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
    font-size: 0.6rem;
  }

  &:focus {
    outline: none;
    border-bottom: 3px solid white;
  }
`;

// export const DescInput = styled.textarea`
//   width: 100%;
//   height: 100%;
//   background-color: white;
//   border-radius: 10px;
//   padding: 3%;
//   font-size: 0.9rem;
//   line-height: 1.2;
//   overflow-y: auto; // 스크롤바 추가

//   // 웹킷 기반 브라우저 (Chrome, Safari 등)용 스크롤바 스타일
//   &::-webkit-scrollbar {
//     width: 0; // 스크롤바 너비를 0으로 설정하여 숨김
//     background: transparent; // 스크롤바 배경을 투명하게 설정
//   }
//   //

//   // 기본 테두리 제거
//   border: none;
//   outline: none;
//   resize: none;

//   // Focus 상태에서 테두리 변경
//   &:focus {
//     border: none;
//     outline: none;
//     box-shadow: none;
//   }

//   font-family: "NanumSquareNeoTTF-Regular", "sans-serif";
//   font-size: 0.8rem;
// `;

export const DescInput = styled.div.attrs({ contentEditable: true })`
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

  // 기본 테두리 제거
  border: none;
  outline: none;
  resize: none;

  // Focus 상태에서 테두리 변경
  &:focus {
    border: none;
    outline: none;
    box-shadow: none;
  }

  font-family: "NanumSquareNeoTTF-Regular", "sans-serif";
  font-size: 0.8rem;
`;

export const EnrollButton = styled.div`
  border: 1px solid white;
  font-size: 0.9rem;
  border-radius: 10px;
  width: 45%;
  padding: 3% 5% 3% 5%;
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

export const TagArea = styled.div`
  //
`;

export const Tags = styled.div`
  //
`;

export const GeminiInfoButton = styled.div`
  //
`;
