package com.wangzhu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangzhu.servlet.test.GrinderImpl1;
import com.wangzhu.servlet.test.GrinderImpl2;
import com.wangzhu.servlet.test.IGrinder;

/**
 * Servlet implementation class GrindServlet
 */
public class GrindServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory
			.getLogger(GrindServlet.class);

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GrindServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		IGrinder grinder = GrinderImpl1.getGrinder();
		if ("1".equals(type)) {
			grinder = GrinderImpl1.getGrinder();
		} else if ("2".equals(type)) {
			grinder = GrinderImpl2.getGrinder();
		}

		StopWatch clock = new StopWatch();
		clock.start();
		String result = grinder.grindCPU(5);
		clock.stop();
		StringBuilder accum = new StringBuilder();
		accum.append("Grind result : [");
		accum.append(result);
		accum.append("] Time =");
		accum.append(clock.getTime());

		String info = accum.toString();
		GrindServlet.logger.info("info:{}", info);

		PrintWriter pw = response.getWriter();
		pw.print("<html>\n<body>\n");
		pw.print(info);
		pw.print("</body>\n</html>\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
