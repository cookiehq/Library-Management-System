package com.sdcet.library.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdcet.library.utils.Constans;
import com.sdcet.library.utils.JavaMailUtil;
import com.sdcet.library.utils.RandomRange;

 
/**
 * 
 *@class_name��SendEmailServlet
 *@comments:ע��- ����������֤�빦��
 *@param:
 *@return: 
 */

@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("���䷢�͹���");
		try {
			String email = req.getParameter("email");
			JavaMailUtil.receiveMailAccount = email; // ���û���������䷢���ʼ�
 
			// 1�������������ã�������������������Ĳ�������
			Properties props = new Properties();
			// ����debug����
			props.setProperty("mail.debug", "true");
			// ���ͷ�������Ҫ�����֤
			props.setProperty("mail.smtp.auth", "true");
			// �����Ҽ���������������
			props.setProperty("mail.host", JavaMailUtil.emailSMTPHost);
			// �����ʼ�Э������
			props.setProperty("mail.transport.protocol", "smtp");
 
			// 2���������ô����Ự�������ں��ʼ�����������
			Session session = Session.getInstance(props);
			// ����debug�����Բ鿴��ϸ�ķ���log
			session.setDebug(true);
			// 3������һ���ʼ�
			String code = RandomRange.getRandom();
			System.out.println("������֤�룺" + code);
			String html = Constans.HTMLCONS + code;
			MimeMessage message = JavaMailUtil.creatMimeMessage(session, JavaMailUtil.emailAccount,
					JavaMailUtil.receiveMailAccount, html);
 
			// 4������session��ȡ�ʼ��������
			Transport transport = session.getTransport();
			// 5��ʹ�������˺ź������������������emailAccount������message�еķ���������һ�£����򱨴�
			transport.connect(JavaMailUtil.emailAccount, JavaMailUtil.emailPassword);
			// 6�������ʼ�,���������ռ��˵�ַ
			transport.sendMessage(message, message.getAllRecipients());
			// 7���ر�����
			transport.close();
			//  д��session
			req.getSession().setAttribute("mailcode", code);
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("error", "�ʼ�����ʧ��");
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("useradd.jsp");
		dispatcher.forward(req, resp);
	}
}

