# inventory_product_balance
![image](https://github.com/luckyKite/inventory_product_balance/assets/122114197/cf07961f-113b-456b-b6f1-3fe3ce9e27b9)
## ● 팀명 : 삑 그리고 다음
## ● 프로젝트 주제 : 기업형 슈퍼마켓의 효율적인 재고, 유통기한 관리를 위한 영업 정보 시스템
## ● 프로젝트 소개
유통업에서 재고와 유통기한은 매출과 직결되기 때문에 관리의 필요성이 높고 중요하다. 본 프로젝트는 유통업 점주가 상품의 재고, 유통기한 등 전반적인 상품을 관리할 수 있는 시스템 개발을 목적으로 한다.
## ● 개발기간 : 2023.04.20 - 2023.06.21
## ● 배포 주소 : www.ipbalance.net:3000

### KDT 과정을 배운 후 진행한 프로젝트입니다. 
### 이 프로젝트는 웹으로 구현하였습니다.
### 시중에 나와있는 여러 발주시스템과 재고관리 프로그램을 참고했습니다.
##
## ● 웹 페이지에 적용된 기능
 ### 1. 메인화면
    - 메인화면 모달창
    - 상단의 날씨와 시간, 날씨아이콘 배너
    - 알림기능
  ### 2. 로그인
    - 이메일, 비밀번호 유효성 체크
  ### 3. 상품관리
   #### 3-1. 재고관리
    - 검색단어 없이 조회하면 전체 상품 목록 보여짐
    - 카테고리별, 보관방법, 상품이름, SKUCODE로 상품 검색 가능   
    - 단어 검색시 관련 문구를 포함한 상품 목록 보여짐
    - 상품 이름을 클릭하여 상세페이지 이동 가능
    - 상세페이지 내 자동발주 버튼으로 최소수량, 기준수량을 입력하고 자동발주 신청가능
    - 자동발주 신청시 최소수량보다 적어지면 자동발주되고 해당 점포 관리자에게 SMS 알림 제공
   #### 3-2. 유통기한관리
    - 검색단어 없이 조회하면 전체 상품의 유통기한이 보여짐
    - 단어 검색시 관련 문구를 포함한 상품 목록 보여짐
    - 유통기한에 따라 빨강, 노랑, 초록, 파랑의 컬러인덱스로 표기함
    - 유통기한이 경과된 상품은 폐기 버튼 생성
    - 유통기한 관리의 ? 아이콘을 누르면 해당 페이지 상세설명 확인 가능

  ### 4. 발주  
   #### 4-1. 발주하기
    - 카테고리별, 보관방법, 상품이름, SKUCODE로 상품 검색 가능
    - 발주리스트에서 현재 발주 가능한 상품 정보 확인 가능  
    - 카트모양의 아이콘을 클릭하여 발주 리스트에 상품담기 가능
    - 상품 수량 증가, 감소 가능 (최소 수량: 1개)
    - 상품 삭제 가능
    - 오른쪽의 발주예정리스트에서 파란색 화물차아이콘을 클릭하면 발주가 진행
   #### 4-2. 발주내역조회
    - 왼쪽의 내역에서 해당 일자별 발주리스트와 배송상태 확인 가능
    - 조회하는 일자의 리스트를 클릭하면 발주내역을 오른쪽에서 리스트 형태로 확인 가능    
    - 전체, 일반, 자동, 이벤트로 구분해서 확인 가능
    - 배송준비중인 경우 발주수량 변경 가능
    - 배송준비중인 경우 발주했던 내역 삭제 가능

  ### 5. 자동발주
   #### 5-1. 사용자 설정 자동발주
    - 해당 점포에서 등록한 자동발주 내역 전체 조회 가능
    - 최소수량과 최대수량을 변경 가능
    - 신청했던 자동발주 내역을 삭제 가능
   #### 5-2. 이벤트 자동발주
    - 본사에서 진행되는 이벤트 자동발주로 이벤트 진행 5일 전, 점포별 작년 매출을 기준으로 판매촉진비율인 10%를 가산한 수량을 자동발주로 담아줌. 
      만일 작년 매출이 존재하지 않는 점포라면 임의적으로 50개의 수량을 자동발주
    - 점포 관리자는 2일의 기간동안 발주된 수량을 수정 가능
    - 이벤트 자동발주가 진행되면 점포 관리자에게 SMS 알림 제공

  ### 6. 이벤트
    - 이벤트 이름과 일자 조회가 가능하고, 오름차순 및 내림차순으로 정렬    
    - 이벤트 배너를 클릭하면, 상세 정보를 확인 가능

  ### 7. 게시판
    - 관리자만 작성할 수 있으며, 제목으로 조회 가능
    - 제목을 클릭하면 해당 게시글을 자세하게 확인 가능

##  

## ● 개발환경
 <img src="https://img.shields.io/badge/Java-483D8B?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Jar-16A5F3?style=for-the-badge&logo=iconjar&logoColor=white"> <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 
##
## ● 사용한 dependencies 및 기술스택
 <img src="https://img.shields.io/badge/AmazonAws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/AmazonRds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white"> <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/Github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/GoogleAPI-4285F4?style=for-the-badge&logo=google&logoColor=white"> <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"> <img src="https://img.shields.io/badge/Intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white"> <img src="https://img.shields.io/badge/Json-000000?style=for-the-badge&logo=json&logoColor=white"> <img src="https://img.shields.io/badge/Lombok-800000?style=for-the-badge&logo=lombok&logoColor=white"> <img src="https://img.shields.io/badge/MyBatis-B22222?style=for-the-badge&logo=mybatis&logoColor=white"> <img src="https://img.shields.io/badge/NaverCloudAPI-03C75A?style=for-the-badge&logo=naver&logoColor=white">  <img src="https://img.shields.io/badge/SpringFreamwork-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white">
 
##

