package tech.sollabs.kepcoapi.type;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * KEPCO 조회를 위한 시/도 코드
 * @see <a href="https://bigdata.kepco.co.kr/cmsmain.do?scode=S01&pcode=000493&pstate=code" />
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum KepcoProvinceCode {

  SEOUL("11", "서울특별시"),
  BUSAN("21", "부산광역시"),
  DAEGU("22", "대구광역시"),
  INCHEON("23", "인천광역시"),
  GWANGJU("24", "광주광역시"),
  DAEJEON("25", "대전광역시"),
  ULSAN("26", "울산광역시"),
  GYEONGGI("31", "경기도"),
  GANGWON("32", "강원도"),
  CHUNGCHEONGBUK("33", "충청북도"),
  CHUNGCHEONGNAM("34", "충청남도"),
  JEOLLABUK("35", "전라북도"),
  JEOLLANAM("36", "전라남도"),
  GYEONGSANGBUK("37", "경상북도"),
  GYEONGSANGNAM("38", "경상남도"),
  JEJU("39", "제주특별자치도"),
  SEJONG("41", "세종특별자치시");

  private final String code;
  private final String name;
}
