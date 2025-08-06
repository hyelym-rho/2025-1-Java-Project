package Model;

import common.Constants;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * LectureDTO Class
 * 강의의 정보를 갖고 전달하기 위해 만들어진 Class
 */

public class LectureDTO {

    private String major;		//개설학과전공
    private String lectureId;	//학수번호
    private String classNum;		//분반
    private String lectureName;	//교과목명
    private String completion;	//이수구분
    private float grade;			//학년
    private float score; 		//학점
    private String time; 		// 요일 및 강의시간
    private String room; 	//강의실
    private String professor; 	//교수
    private int numberOfPeople; //신청인원

    //유틸 함수 추가
    private String safeGet(JSONObject obj, String key) {
        Object value = obj.get(key);
        return value != null ? value.toString() : "";
    }

    private float parseFloatSafe(Object obj) {
        try {
            return obj != null ? Float.parseFloat(obj.toString()) : 0f;
        } catch (Exception e) {
            return 0f;
        }
    }

    // 1.constructor

    public LectureDTO(Object[] obj) {
        try {
            this.major      = obj[1].toString();
            this.lectureId  = obj[2].toString();
            this.classNum   = obj[3].toString();
            this.lectureName = obj[4].toString();
            this.completion = obj[5].toString();
            this.grade      = Float.parseFloat(obj[6].toString());
            this.score      = Float.parseFloat(obj[7].toString());
            this.time       = obj[8] != null ? obj[8].toString() : "";
            this.room       = obj[9] != null ? obj[9].toString() : "";
            this.professor  = obj[10] != null ? obj[10].toString() : "";
            //this.numberOfPeople = obj[11] != null ? Integer.parseInt(obj[11].toString()) : 0;
        } catch (Exception e) {
            System.err.println("⚠️ LectureDTO 생성자 오류: " + e);
        }
    }

    // 문장 배열로 반환하는 메소드
    public String[] makeStringArray() {
        String output[] = new String[12];
        output[0] = "";
        output[1] = major;
        output[2] = lectureId;
        output[3] = classNum;
        output[4] = lectureName;
        output[5] = completion;
        output[6] = Float.toString(grade);
        output[7] = Float.toString(score);
        output[8] = time;
        output[9] = room;
        output[10] = professor;
        output[11] = String.valueOf(numberOfPeople);
        return output;
    } // public String[] makeStringArray()

    public String getMajor(){ return major; } //개설전공
    public String getCourseNum(){return lectureId;} //학수번호
    public String getClassNum(){return classNum;} //분반
    public String getClassName(){return lectureName;}	//교과목명
    public String getCompletion(){return completion;} //이수구분
    public float getGrade(){return grade;} //학년
    public float getScore(){return score;} //학점
    public String getTime(){return time;} // 요일 및 강의시간
    public String getClassRoom(){return room;}	//강의실
    public String getProfessor(){return professor;}	//교수
    public int getNumberOfPeople(){return numberOfPeople;} //신청인원

}