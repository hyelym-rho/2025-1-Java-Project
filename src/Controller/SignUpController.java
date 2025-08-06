package Controller;

import common.ClassManager;
import Model.UserDTO;
import View.*;
import common.Constants;
import common.ExceptionHandling;

import javax.mail.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.AddressException;

import java.io.StringWriter;
import java.io.PrintWriter;

public class SignUpController implements ActionListener {

    SignUpView signUpView;
    boolean flag = false;
    // emailFlag = false;
    boolean validEmailFlag = true;

    public SignUpController(SignUpView signUpView) {
        this.signUpView = signUpView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String operator = ((JButton) e.getSource()).getText();

        switch (operator) {
            case Constants.DUPLICATE_TXT:
                checkDuplication();
                break;
            case Constants.SIGNUP_TXT:
                signUp();
                break;
            /*case Constants.EMAIL_TXT:
                checkEmail();
                break;*/
            case Constants.EXIT_TXT:
                resetData();
                ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLoginView());
                break;
        }
    }

    private void resetData() {
        flag = false;
        //emailFlag = false;
        validEmailFlag = true;
        signUpView.resetView();
    }


    private void checkDuplication() {
        String id = signUpView.getId();
        if (!ExceptionHandling.isOnlyNumber(id)) {
            showMessege("id를 숫자로만 입력 해주세요.");
            return;
        }
        //중복이 된 경우
        if (ClassManager.getInstance().getDAO().isDuplicateID(id)) {
            showMessege("중복된 아이디 입니다.");
            return;
        }
        //사용이 가능한 경우
        flag = true;
        showMessege("사용이 가능한 아이디 입니다.");
    }

    private void signUp() {
        if (flag) {
            UserDTO temp = signUpView.getInsertData();
            if (temp == null) {
                showMessege("입력 되지 않은 값이 있습니다.");
                return;
            }
            if (!checkException(temp))
                return;
            /*if (!emailFlag) {
                showMessege("이메일 인증을 완료해주세요.");
                return;
            }*/
            if (ClassManager.getInstance().getDAO().signUp(temp)) {
                showMessege("회원가입 성공");
                resetData();
                ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLoginView());
            } else {
                showMessege("회원가입 실패");
            }
        } else {
            showMessege("중복 확인을 해주세요.");
        }
    }

    private boolean checkException(UserDTO user) {
        if (user.getPassword() == null) {
            showMessege("비밀번호가 빈값입니다.");
            return false;
        }
        if (!ExceptionHandling.isOnlyKorean(user.getName())) {
            showMessege("이름이 한글이 아닙니다.");
            return false;
        }
        if (!ExceptionHandling.isPhoneNumber(user.getPhone())) {
            showMessege("전화번호 형식이 맞지 않습니다.(010-XXXX-XXXX)");
            return false;
        }
        return true;
    }

    private void showMessege(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    /*class MyAuthentication extends Authenticator {
        PasswordAuthentication pa;

        public MyAuthentication(String mailId, String mailPass) {
            pa = new PasswordAuthentication(mailId, mailPass);
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return pa;
        }
    }

    //이메일 인증 메소드
    private void checkEmail() {
        String mailId = "noohyerim36@gmail.com"; // 구글계정
        String mailPassword = "irmsjlmlgqppokia"; // 앱 비밀번호 (공백 없음)
        String fromEmail = mailId;
        String fromName = "관리자";
        String toEmail = signUpView.getEmail();
        System.out.println("DEBUG: 원본 이메일 = [" + toEmail + "]");
        if (toEmail == null || toEmail.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "❌ 이메일이 비어있습니다.");
            return;
        }
        toEmail = toEmail.trim();

        try {
            // 이메일 유효성 검사
            InternetAddress emailAddr = new InternetAddress(toEmail);
            emailAddr.validate();
            System.out.println("✅ 이메일 형식 유효: " + toEmail);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "❌ 유효하지 않은 이메일 형식입니다.");
            ex.printStackTrace();  // 콘솔 출력
            return;
        }

        String toName = "회원";
        String mailTitle = "회원가입 인증 코드 발급 안내입니다.";
        int ran = new Random().nextInt(900000) + 100000;
        String authCode = String.valueOf(ran);
        String mailContents = "귀하의 인증 코드는 <b>" + authCode + "</b> 입니다.";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        System.out.println("받는 이메일 주소: " + toEmail);
        try {
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailId, mailPassword);
                }
            });

            System.setProperty("mail.debug", "true");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, fromName, "UTF-8"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, toName));
            message.setSubject(mailTitle);
            message.setContent(mailContents, "text/html; charset=UTF-8");

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();  // 콘솔에 출력

            // 콘솔 출력 안 보일 경우를 대비해 팝업에도 띄우기
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String errorDetails = sw.toString();

            // 이건 단순히 에러 내용을 보기 위한 디버깅용 팝업입니다.
            JTextArea textArea = new JTextArea(errorDetails);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(600, 300));
            JOptionPane.showMessageDialog(null, scrollPane, "메일 전송 오류 발생", JOptionPane.ERROR_MESSAGE);

            // 이건 사용자용 메시지
            showMessege("메일 전송 중 오류가 발생했습니다.");
            validEmailFlag = false;
            return;
        }

        // 인증번호 입력 창
        while (!emailFlag && validEmailFlag) {
            String code = JOptionPane.showInputDialog("인증번호를 입력하세요.");
            if (code == null) break;

            if (code.equals(authCode)) {
                JOptionPane.showMessageDialog(null, "인증 완료");
                emailFlag = true;
                signUpView.setEmailChk();
                break;
            } else {
                JOptionPane.showMessageDialog(null, "인증 실패");
            }
        }
        validEmailFlag = true;
    }*/
}
