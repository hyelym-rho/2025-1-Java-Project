https://github.com/kakao-link-js/CR_Simulator  
위 링크에 있는 Java 프로그램을 활용 / 참조하여   
2025-1학기 Java프로그래밍기초 수업 中 기말 프로젝트로 진행한  
수강신청 시스템입니다.  
주요 기능으로는 
- 회원가입
- 아이디 찾기
- 로그인 / 로그아웃
- 내 정보 수정
- 수강신청
- 내 시간표 확인
- 학점 계산기
가 있습니다. 
<img width="1431" height="999" alt="image" src="https://github.com/user-attachments/assets/3cace0ec-7f58-47f0-9928-ff906603be83" />
<초기 화면>
  수업 내용 중 기능 추가가 필요하다하여
- 서버 시간 확인 기능을 추가하였습니다.
<img width="1022" height="747" alt="image" src="https://github.com/user-attachments/assets/0605c1ca-b6c5-4050-81fe-89b1212bcab2" />
<위와 같이 수강 신청 탭에서 확인이 가능합니다.(app.py 내 실행 코드 작성)>    

->서버는 명령 프롬프트(cmd)에 python –m http.server 8888 입력한 후에  
프롬프트 창을 추가해 
서버관리파일이 있는 폴더로 이동한 후,     
python app.py 입력하면 실행됩니다.  
(이후 프롬프트 계속 실행해야 프로그램이 정상적으로 작동) 

데이터베이스 구현할 시간이 부족하여 app.py 서버 안에 구현하였습니다.  
수강신청에 필요한 시간표 내용의 경우 2025 여름계절학기 개설 시간표를 참조하였습니다.  (시간의 경우 시간표 조회를 위해 임의로 제작함)

메인파일은 src/Controller/ClassRegistrationSimulator입니다.
