from flask import Flask, jsonify, request
from datetime import datetime

app = Flask(__name__)

# 아이디 중복 확인
@app.route("/signup/<userid>", methods=["GET"])
def check_duplicate(userid):
    if userid == "usedid":
        return jsonify(success="true")  # 중복
    else:
        return jsonify(success="false")  # 사용 가능

#  회원가입 요청 처리
@app.route("/signup", methods=["POST"])
def signup():
    try:
        print("🔥 요청 도착!")
        data = request.get_json(force=True)
        print("받은 데이터:", data)

        # 임시 응답
        return jsonify(success="true", message="가입 성공!")
    except Exception as e:
        print("❌ 예외 발생:", e)
        return jsonify(success="false", message="가입 실패"), 500

# 로그인 처리 라우트
@app.route("/login", methods=["POST"])
def login():
    try:
        data = request.get_json(force=True)
        user_id = data.get("id")
        password = data.get("password")

        print("🔥 로그인 시도:", user_id, password)

        # 테스트용 로그인 검증 로직 (현재 DB구현까지는 X)
        if user_id == "2022120170" and password == "1234":
            return jsonify(success="true", message="로그인 성공!")
        else:
            return jsonify(success="false", message="아이디 또는 비밀번호가 틀렸습니다.")

    except Exception as e:
        print("❌ 로그인 중 예외:", str(e))
        return jsonify(success="false", message="서버 에러 발생"), 500

#유저 정보
@app.route("/user/<user_id>", methods=["GET"])
def get_user(user_id):
    print(f"👤 유저 정보 요청: {user_id}")

    # 테스트용 더미 유저 데이터
    dummy_user = {
        "id": "2022100000",
        "password": "1234",
        "name": "홍길동",
        "phone": "010-1234-5678"
    }


    return jsonify({"user": dummy_user})

#아이디 핸드폰번호를 통해 찾기
@app.route("/user/find/phone", methods=["POST"])
def find_user_by_phone():
    try:
        data = request.get_json()
        name = data.get("name")
        phone = data.get("phone")

        fake_users = [
            {"name": "노혜림", "phone": "010-4557-0536", "id": "2022120170"},
        ]

        user = next((u for u in fake_users if u["name"] == name and u["phone"] == phone), None)
        if user:
            return jsonify({"success": "true", "user": {"id": user["id"]}})
        else:
            return jsonify({"success": "false", "message": "사용자를 찾을 수 없습니다."}), 404
    except Exception as e:
        print("🔥 서버 오류:", e)
        return jsonify({"success": "false", "error": str(e)}), 500

#시간표 정보
@app.route("/timetable", methods=["GET"])
def get_timetable():
    print("📚 전체 강의 목록 요청 도착!")

    dummy_timetable = {
        "reg": {
            "timetable": [
                {
                    "major": "언론영상학부",
                    "lectureId": "DM01054",
                    "classNum": "01",
                    "lectureName": "문화트렌드분석과기획",
                    "completion": "전공선택",
                    "professor": "주창윤",
                    "grade": 2,
                    "score": 3,
                    "time": "월 15:00~17:45",
                    "room": "인문사회관 109호",
                    "numberOfPeople": 35
                },
                {
                    "major": "생명환경공학과",
                    "lectureId": "BI04020",
                    "classNum": "01",
                    "lectureName": "미생물분류실무",
                    "completion": "전공선택",
                    "professor": "맹수현",
                    "grade": 1,
                    "score": 3,
                    "time": "수 10:00~14:45",
                    "room": "제1과학관 301호",
                    "numberOfPeople": 16
                },
                {
                    "major": "생명환경공학과",
                    "lectureId": "BI04059",
                    "classNum": "01",
                    "lectureName": "NGS시퀀싱실무",
                    "completion": "전공선택",
                    "professor": "맹수현",
                    "grade": 3,
                    "score": 3,
                    "time": "수 15:00~18:45",
                    "room": "제1과학관 301호",
                    "numberOfPeople": 16
                },
                {
                    "major": "원예생명조경학과",
                    "lectureId": "HT03044",
                    "classNum": "01",
                    "lectureName": "토양학",
                    "completion": "전공선택",
                    "professor": "장매희,이지선",
                    "grade": 2,
                    "score": 3,
                    "time": "화 9:00~11:45",
                    "room": "제1과학관 204호",
                    "numberOfPeople": 30
                },
                {
                    "major": "원예생명조경학과",
                    "lectureId": "HT03058",
                    "classNum": "01",
                    "lectureName": "식물생리학및실험",
                    "completion": "전공선택",
                    "professor": "장매희,이지선",
                    "grade": 4,
                    "score": 3,
                    "time": "화 15:00~17:45",
                    "room": "제1과학관 204호",
                    "numberOfPeople": 30
                },
                {
                    "major": "원예생명조경학과",
                    "lectureId": "HT03086",
                    "classNum": "01",
                    "lectureName": "원예치료학",
                    "completion": "전공선택",
                    "professor": "장매희,이지선",
                    "grade": 3,
                    "score": 3,
                    "time": "목 12:00~14:45",
                    "room": "제1과학관 303호",
                    "numberOfPeople": 30
                },
                {
                    "major": "원예생명조경학과",
                    "lectureId": "HT03093",
                    "classNum": "01",
                    "lectureName": "식물생명공학개론",
                    "completion": "전공필수",
                    "professor": "류기현",
                    "grade": 1,
                    "score": 3,
                    "time": "수 12:00~14:45",
                    "room": "제1과학관 204호",
                    "numberOfPeople": 30
                },
                {
                    "major": "경영학과",
                    "lectureId": "BA02019",
                    "classNum": "01",
                    "lectureName": "경영전략(캡스톤디자인)",
                    "completion": "전공선택",
                    "professor": "조원애",
                    "grade": 4,
                    "score": 3,
                    "time": "수 9:00~11:45",
                    "room": "인문사회관 404호",
                    "numberOfPeople": 25
                },
                {
                    "major": "경영학과",
                    "lectureId": "BA02041",
                    "classNum": "01",
                    "lectureName": "경영학원론",
                    "completion": "전공선택",
                    "professor": "조원애",
                    "grade": 2,
                    "score": 3,
                    "time": "화 12:00~14:45",
                    "room": "인문사회관 404호",
                    "numberOfPeople": 25
                },
                {
                    "major": "경영학과",
                    "lectureId": "BA02020",
                    "classNum": "01",
                    "lectureName": "재무회계",
                    "completion": "전공필수",
                    "professor": "이강영",
                    "grade": 1,
                    "score": 3,
                    "time": "금 9:00~11:45",
                    "room": "인문사회관 109호",
                    "numberOfPeople": 40
                },
                {
                    "major": "디지털미디어학과",
                    "lectureId": "DA01004",
                    "classNum": "01",
                    "lectureName": "C++프로그래밍기초",
                    "completion": "전공필수",
                    "professor": "신현덕",
                    "grade": 1,
                    "score": 3,
                    "time": "목 15:00~17:45",
                    "room": "제2과학관 301호",
                    "numberOfPeople": 15
                },
                {
                    "major": "디지털미디어학과",
                    "lectureId": "DA01006",
                    "classNum": "01",
                    "lectureName": "고급C++프로그래밍",
                    "completion": "전공선택",
                    "professor": "신현덕",
                    "grade": 4,
                    "score": 3,
                    "time": "목 12:00~14:45",
                    "room": "제2과학관 301호",
                    "numberOfPeople": 15
                },
                {
                    "major": "교양영역(2014학번이후)",
                    "lectureId": "UBB0002",
                    "classNum": "01",
                    "lectureName": "한국의역사",
                    "completion": "교양선택",
                    "professor": "나종현",
                    "grade": 0,
                    "score": 3,
                    "time": "월 9:00~11:45",
                    "room": "인문사회관 401호",
                    "numberOfPeople": 100
                },
                {
                    "major": "교양영역(2014학번이후)",
                    "lectureId": "UCC0014",
                    "classNum": "01",
                    "lectureName": "서양음악의이해",
                    "completion": "교양선택",
                    "professor": "한상명",
                    "grade": 0,
                    "score": 3,
                    "time": "금 12:00~14:45",
                    "room": "50주년기념관 405호",
                    "numberOfPeople": 40
                },
                 {
                     "major": "교양영역(2014학번이후)",
                    "lectureId": "UHH0001",
                    "lectureName": "죽음과종교",
                     "completion": "교양선택",
                    "classNum": "01",
                    "professor": "강진구",
                    "grade": 0,
                    "score": 3,
                    "time": "목 9:00~11:45",
                    "room": "50주년기념관 421호",
                     "numberOfPeople": 50
                },
                 {
                    "major": "교양영역(2014학번이후)",
                    "lectureId": "USS0002",
                    "lectureName": "성심리와성건강",
                    "completion": "교양선택",
                    "classNum": "01",
                    "professor": "정형선",
                    "grade": 0,
                    "score": 3,
                    "time": "수 12:00~14:45",
                    "room": "50주년기념관 426호",
                    "numberOfPeople": 50
                },
                 {
                    "major": "교양영역(2014학번이후)",
                    "lectureId": "USS0014",
                    "lectureName": "인간과사회복지",
                    "completion": "교양선택",
                    "classNum": "01",
                    "professor": "정재훈",
                    "grade": 0,
                    "score": 3,
                    "time": "월 12:00~14:45",
                    "room": "인문사회관 102호",
                    "numberOfPeople": 100
                },
            ]
        }
    }

    return jsonify(dummy_timetable)


#수강 신청 
user_lectures = {}

@app.route("/reg", methods=["PUT"])
def register_lecture():
    try:
        data = request.get_json(force=True)
        user_id = data.get("id")
        lectures = data.get("lectures")

        print("📝 수강신청 요청 :", data)

        if user_id not in user_lectures:
            user_lectures[user_id] = []

        for lecture_info in lectures:
            user_lectures[user_id].append(lecture_info)

        print(f"✅ 저장 완료! 현재 내역: {user_lectures[user_id]}")
        return jsonify(success="true", message="수강신청 완료!")

    except Exception as e:
        print("❌ 수강신청 중 예외:", str(e))
        return jsonify(success="false", message="서버 에러 발생"), 500

#수강 취소
@app.route("/reg", methods=["DELETE"])
def delete_registered_lecture():
    try:
        data = request.get_json(force=True)
        print("🗑️ 수강취소 요청:", data)

        user_id = data.get("id")
        course_num = data.get("courseNum")
        class_num = data.get("classNum")
        major = data.get("major")

        if user_id not in user_lectures:
            return jsonify(success="false", message="사용자 수강 정보 없음"), 404

        # 조건에 맞는 강의만 삭제
        before = len(user_lectures[user_id])
        user_lectures[user_id] = [
            lec for lec in user_lectures[user_id]
            if not (
                lec.get("lectureId") == course_num and
                lec.get("classNum") == class_num and
                lec.get("major") == major
            )
        ]
        after = len(user_lectures[user_id])

        print(f"🧹 삭제 완료! 전:{before} → 후:{after}")

        return jsonify(success="true" if before != after else "false")
    except Exception as e:
        print("❌ 수강 취소 중 예외:", str(e))
        return jsonify(success="false", message="서버 에러 발생"), 500
    
#내가 신청한 강의
@app.route("/reg/<user_id>", methods=["GET"])
def get_registered_lectures(user_id):
    print(f"📦 수강 신청 조회 요청 for {user_id}")
    user_data = user_lectures.get(user_id, [])
    
    return jsonify({"reg": {"timetable": user_data}})

#서버 시간 확인
@app.route("/time", methods=["GET"])
def get_server_time():
    now = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    return jsonify({"time": now})

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8888)

