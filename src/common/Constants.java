package common;

/**
 * Constants Class
 * 상수를 저장해 둔 Class 입니다.
 */
public class Constants {
    public static final String SCORES[] ={"NONE","A+","A","B+","B","C+","C","D+","D","F"};
    public static final String MENU[] = {"수강신청", "내 시간표", "학점 계산기","내 정보 수정", "로그아웃"};

    public static final String[] COLUMN_HEADER = {"", "월", "화", "수", "목", "금"};
    public static final String[][] ROW_HEADER = {
            {"9"}, {""}, {"10"}, {""},
            {"11"}, {""}, {"12"}, {""},
            {"1"}, {""}, {"2"}, {""},
            {"3"}, {""}, {"4"}, {""},
            {"5"}, {""}, {"6"}, {""},
            {"7"}, {""}, {"8"}
    };
    public static final String TABLE_HEADER[] = {"신청/취소","개설학과전공","학수번호","분반","교과목명","이수구분","학년","학점","요일 및 강의시간","강의실","교수","신청인원"};
    public static final String MYTABLE_HEADER[] = {"신청/취소","개설학과전공","학수번호","분반","교과목명","이수구분","학년","학점","요일 및 강의시간","강의실","교수"};
    // 콤보 박스 설정을 위한 배열
    public static final String[] GRADE_HEADER = {"NONE", "1", "2", "3", "4"};
    public static final String[] MAJOR_HEADER = {"","경영학과","생명환경공학과","디지털미디어학과","언론영상학부","원예생명조경학과","교양영역(2014학번이후)"};
    public static final String[] COMPLETION_HEADER = {"","전공필수","전공선택","교양선택"};

    //한글 라벨 부분
    public static final String INSERT_TXT = "수강신청";
    public static final String MYLECTURE_TXT = "내 시간표";
    public static final String CALCUL_TXT = "학점 계산기";
    public static final String MODIFY_TXT = "내 정보 수정";
    public static final String END_TXT = "종료";
    public static final String LOGOUT_TXT = "로그아웃";
    public static final String SEARCH_TXT = "검색";
    public static final String EXIT_TXT = "나가기";
    public static final String BACK_TXT = "<";

    public static final String MAJORKOR_TXT = "개설학과전공";
    public static final String COURSENUMKOR_TXT = "학수번호";
    public static final String CLASSNUMKOR_TXT = "분반";
    public static final String CLASSNAMEKOR_TXT = "교과목명";
    public static final String COMPLETIONKOR_TXT = "이수구분";
    public static final String GRADEKOR_TXT = "학년";
    public static final String SCOREKOR_TXT = "학점";
    public static final String PROFESSORKOR_TXT = "교수명";
    public static final String MYSCOREKOR_TXT = "성적";
    public static final String APPLY_TXT = "신청";
    public static final String CANCEL_TXT = "취소";
    public static final String REFRESH_TXT = "새로고침";
    public static final String SERVERTIME_TXT = "서버시간";

    public static final String MAJOR_TXT = "major";
    public static final String COURSENUM_TXT = "lectureId";
    public static final String CLASSNUM_TXT = "classNum";
    public static final String CLASSNAME_TXT = "lectureName";
    public static final String COMPLETION_TXT = "completion";
    public static final String GRADE_TXT = "grade";
    public static final String SCORE_TXT = "score";
    public static final String TIME_TXT = "time";
    public static final String CLASSROOM_TXT = "room";
    public static final String PROFESSOR_TXT = "professor";
    public static final String NUMBER_TXT = "nCount";

    public static final String LOGIN_TXT = "로그인";
    public static final String SIGNUP_TXT = "회원가입";
    public static final String IDSEARCH_TXT = "ID찾기";

    public static final String DUPLICATE_TXT = "중복";

    public static final String IDKOR_TXT = "ID(학번)";
    public static final String PWKOR_TXT = "비밀번호";
    public static final String NAMEKOR_TXT = "이름";
    public static final String PHONEKOR_TXT = "휴대전화";
    public static final String MODIFYKOR_TXT = "수정";


    //통신 부분
    public static final String ID_TXT = "id";
    public static final String PASSWORD_TXT = "password";
    public static final String NAME_TXT = "name";
    public static final String PHONE_TXT = "phone";
    public static final String SUCCESS_TXT = "success";
    public static final String TRUE_TXT = "true";
    public static final String DELETE_TXT = "DELETE";
    public static final String PUT_TXT = "PUT";
    public static final String POST_TXT = "POST";

    //정규식 부분
    public static final String KOREAN_REGEX = "^[가-힣]*$";
    public static final String PHONE_REGEX = "^01(?:0|1|[6-9])[.-]?([0-9]{3}|[0-9]{4})[.-]?([0-9]{4})$";
    public static final String NUMBER_REGEX = "^[0-9]+$";

    //서버 부분
    public static final String BASE_URL = "http://127.0.0.1:8888/";

    public static final String UNIV_NAME = "SEOUL WOMAN'S UNIVERSITY";
}
