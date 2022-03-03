package com.sdcet.library.statics;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * ����HTML��̬ҳ�� 
 * 
 * @author xiaochangwei
 * 
 */
public class CreateStaticHTMLPage {
    /**
     * ���ɾ�̬HTMLҳ��ķ���
     * 
     * @param request
     *            �������
     * @param response
     *            ��Ӧ����
     * @param servletContext
     *            Servlet������
     * @param fileName
     *            �ļ�����
     * @param fileFullPath
     *            �ļ�����·��
     * @param jspPath
     *            ��Ҫ���ɾ�̬�ļ���JSP·��(��Լ���)
     * @throws IOException
     * @throws ServletException
     */
    public void create(HttpServletRequest request, HttpServletResponse response,
            ServletContext servletContext, String fileName, String fileFullPath, String jspPath)
                    throws ServletException, IOException {
        response.setContentType("text/html;charset=gb2312");// ����HTML���������(��HTML�ļ�����)
        RequestDispatcher rd = servletContext.getRequestDispatcher(jspPath);// �õ�JSP��Դ
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();// ���ڴ�ServletOutputStream�н�����Դ
        final ServletOutputStream servletOuputStream = new ServletOutputStream() {// ���ڴ�HttpServletResponse�н�����Դ
            public void write(byte[] b, int off, int len) {
                byteArrayOutputStream.write(b, off, len);
            }

            public void write(int b) {
                byteArrayOutputStream.write(b);
            }

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setWriteListener(WriteListener arg0) {
				// TODO Auto-generated method stub
				
			}

		
        };
        final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream));// ��ת���ֽ���ת�����ַ���
        HttpServletResponse httpServletResponse = new HttpServletResponseWrapper(response) {// ���ڴ�response��ȡ�������Դ(��д����������)
            public ServletOutputStream getOutputStream() {
                return servletOuputStream;
            }

            public PrintWriter getWriter() {
                return printWriter;
            }
        };
        rd.include(request, httpServletResponse);// ���ͽ����
        printWriter.flush();// ˢ�»��������ѻ��������������
        FileOutputStream fileOutputStream = new FileOutputStream(fileFullPath);
        byteArrayOutputStream.writeTo(fileOutputStream);// ��byteArrayOuputStream�е���Դȫ��д�뵽fileOuputStream��
        fileOutputStream.close();// �ر�����������ͷ������Դ
        response.sendRedirect(fileName);// ����ָ���ļ������ͻ���
    }
}