package pl.gda.pg.eti.kernelhive.engine.web.servlets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataDownloadServlet
 */
@WebServlet("/download")
public class DataDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = -8434975843408149616L;
	private static final String OUTPUT_DATA_DIR = "output-data/";
	private static final int BUFSIZE = 4096;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataDownloadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String filename = request.getParameter("filename");

		ServletContext ctx = getServletContext();
		String filePath = ctx.getRealPath("/" + OUTPUT_DATA_DIR + filename);
		File file = new File(filePath);

		if (!file.exists()) {
			response.sendError(404, "File not found");
		} else {
			String mimetype = ctx.getMimeType(filename);
			response.setContentType((mimetype != null) ? mimetype
					: "application/octet-stream");
			response.setContentLength((int) file.length());
			response.setHeader("Content-Disposition",
					"attachement: filename=\"" + filename + "\"");

			byte[] bbuf = new byte[BUFSIZE];
			int length = 0;
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			ServletOutputStream os = response.getOutputStream();
			while ((dis != null) && ((length = dis.read(bbuf)) != -1)) {
				os.write(bbuf, 0, length);
			}
			dis.close();
			os.flush();
			os.close();
		}
	}
}
